package window.area.part;

import MyComponent.MyComponent;
import MyComponent.myGraph.JGraph;
import MyComponent.myGraph.JGraphFactory;
import MyComponent.myGraph.MyGraphType;
import MyComponent.myLine.JDrawLine;
import MyComponent.myLine.MyPoint;
import MyComponent.textarea.JMyTextArea;
import window.area.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane {
    // 画布大小
    protected  int board_width = 450;
    protected  int board_height = 320;
    //背景
    final JPanel background;
    //画图笔轨迹集合
    public final ArrayList<JDrawLine> jDrawLines = new ArrayList<>();
    //最大层
    public int maxLayer = 0;
    //选择层
    Integer frontLayer = 0;
    // 处理生成图形时，截获鼠标事件
    final BoardGlassPane boardGlassPane;
    //工具盒
    public ToolBox toolBox;
    //当前图形
    private MyComponent chooseGraph;

    private MyComponent copyGraph;


    public Board(ToolBox toolBox) {
        //加载工具栏
        this.toolBox = toolBox;
        setLayout(null);
        //白色画板
        background = new JPanel();
        background.setBackground(Color.white);

        add(background, DEFAULT_LAYER - 1, 0);

        // 处理生成图形时，截获鼠标事件
        boardGlassPane = new BoardGlassPane(this);
        // 初始与底层，一般情况不截取
        add(boardGlassPane, FRAME_CONTENT_LAYER, 0);

        background.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) boardGlassPane.requestFocus();
            }
        });

        //大小改变的自适配
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(getWidth(), getHeight());
                boardGlassPane.setSize(getWidth(), getHeight());
            }
        });
        setSize(board_width, board_height);

    }

    public Board(String data, ToolBox toolBox) {
        // 只有画板大小与画布颜色需要读取
        String[] settings = data.split("\n");
        int width = Integer.parseInt(settings[0].substring(settings[0].indexOf(':') + 1));
        int height = Integer.parseInt(settings[1].substring(settings[1].indexOf(':') + 1));
        int rgb = Integer.parseInt(settings[2].substring(settings[2].indexOf(':') + 1));
        int layer = Integer.parseInt(settings[3].substring(settings[3].indexOf(':') + 1));
        //加载工具栏
        this.toolBox = toolBox;
        setLayout(null);
        //白色画板
        background = new JPanel();
        background.setBackground(new Color(rgb));
        background.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) boardGlassPane.requestFocus();
            }
        });
        add(background, DEFAULT_LAYER - 1, 0);


        // 处理生成图形时，截获鼠标事件
        boardGlassPane = new BoardGlassPane(this);
        // 初始与底层，一般情况不截取
        add(boardGlassPane, FRAME_CONTENT_LAYER, 0);

        //大小改变的自适配
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(getWidth(), getHeight());
                boardGlassPane.setSize(getWidth(), getHeight());
            }
        });
        setSize(width, height);
        maxLayer = layer;
    }

    public Board(Board specificBoard) {
        background = new JPanel();
        background.setBackground(Color.white);
        add(background, DEFAULT_LAYER - 1, 0);
        boardGlassPane = new BoardGlassPane(this);
        for(Component c:specificBoard.getComponents()){
            if(c instanceof JGraph){
                add(c);
            }
        }
        setSize(board_width, board_height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (JDrawLine jDrawLine : jDrawLines)
            jDrawLine.drawLine(g);
    }

    //更换选中图形
    public void changeChooseGraph(MyComponent next) {
        //前图形回到原来位置
        if (chooseGraph != null)
            setLayer((Component) chooseGraph, frontLayer);
        if (next == null)
            return;
        //如果有新图形，将其放于拖动层
        frontLayer = getLayer((Component) next);
        setLayer((Component) next, DRAG_LAYER);
        chooseGraph = next;
    }

    //添加图形
    public void add(MyComponent myComponent, int Layer) {
        add((Component) myComponent, Layer, 0);
        myComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() >= 2) {
                    changeChooseGraph(myComponent);
                }
            }
        });
    }

    //重设鼠标拦截面位置
    public void resetBoardGlassPane() {
        if(toolBox.getSelection() == selects.Mouse)
            setLayer(boardGlassPane, FRAME_CONTENT_LAYER, 0);
        else
            setLayer(boardGlassPane, MODAL_LAYER, 0);
    }

    public void copy() {
        copyGraph = chooseGraph.clone();
    }

    public void paste() {
        if(copyGraph != null) {
            add(copyGraph, this.maxLayer++);
            copyGraph.setBounds(copyGraph.getX() + 20,
                    copyGraph.getY() + 20,
                    copyGraph.getWidth(),
                    copyGraph.getHeight());
            copyGraph = copyGraph.clone();
        }
    }

    public void delete() {
        if (chooseGraph != null) {
            System.out.println(chooseGraph);
            this.remove((Component) chooseGraph);
        }
        repaint();
        chooseGraph = null;
    }

    //文件保存
    public String save() {
        StringBuffer log = new StringBuffer();
        //保存尺寸和背景
        log.append("board-width:").append(getWidth()).append(System.getProperty("line.separator"));
        log.append("board-height:").append(getHeight()).append(System.getProperty("line.separator"));
        log.append("background-color:").append(background.getBackground().getRGB()).append(System.getProperty("line.separator"));
        log.append("max-layer:").append(maxLayer).append(System.getProperty("line.separator"));
        //保存组件

        changeChooseGraph(null);
        for (int i = 0; i < maxLayer; i++) {
            if (getComponentCountInLayer(i) == 0) continue;
            for (Component component : getComponentsInLayer(i)) {
                if (component instanceof MyComponent) {
                    log.append("#####").append(System.getProperty("line.separator"));
                    log.append("Layer:").append(i).append(System.getProperty("line.separator"));
                    log.append(((MyComponent) component).save());
                }
            }
        }
        log.append("\n");

        return log.toString();
    }

    public void setBoardSize(int width,int height){
        board_width = width;
        board_height = height;
    }

    public void addGraphic(String graphicData) {
        // System.out.println(graphicData);
        String[] info = graphicData.split("\n");

        int layer = Integer.parseInt(info[0].substring(info[0].indexOf(':') + 1));
        String type = info[1];
        int rgb = Integer.parseInt(info[2].substring(info[2].indexOf(':') + 1));

        // graph
        MyComponent myComponent = null;
        switch (type) {
            case "Rect", "Oval", "Circle", "RoundRect","Line", "Triangle", "Square", "IsoscelesLadder" -> {
                float stroke = Float.parseFloat(info[3].substring(info[3].indexOf(':') + 1));
                String temp = info[4].substring(info[4].indexOf(':') + 1);
                int x = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                int y = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
                temp = info[5].substring(info[5].indexOf(':') + 1);
                int width = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                int height = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
                boolean isHollow = Boolean.parseBoolean(info[6].substring(info[6].indexOf(':') + 1));

                toolBox.setDrawLineStroke((int) stroke);
                toolBox.setDrawLineColor(new Color(rgb));
                toolBox.setHollow(isHollow);
                myComponent = JGraphFactory.creatJGraph(toolBox, MyGraphType.valueOf(type));

                myComponent.setBounds(x, y, width, height);
                if ("Line".equals(type)) {
                    String direction = info[6];
                    if (direction.equals("JLine-WN"))
                        myComponent.resize(new MyPoint(x, y), new MyPoint(x + width, y + height));
                    else
                        myComponent.resize(new MyPoint(x, y + height), new MyPoint(x + width, y));
                }
            }
            case "TextArea" -> {
                String content = info[8].substring(info[8].indexOf(':') + 1);
                String temp = info[9].substring(info[9].indexOf(':') + 1);
                int x = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                int y = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
                temp = info[10].substring(info[10].indexOf(':') + 1);
                int width = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                int height = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));

                HashMap<TextAttribute, Object> newTextAttribute = new HashMap<>();
                String textStyle = info[3].substring(info[3].indexOf(':') + 1);
                int textSize = Integer.parseInt(info[4].substring(info[4].indexOf(':') + 1));
                boolean isBold = Boolean.parseBoolean(info[5].substring(info[5].indexOf(':') + 1));
                boolean isItalic = Boolean.parseBoolean(info[6].substring(info[6].indexOf(':') + 1));
                boolean isUnderline = Boolean.parseBoolean(info[7].substring(info[7].indexOf(':') + 1));
                newTextAttribute.put(TextAttribute.FAMILY, textStyle);
                newTextAttribute.put(TextAttribute.SIZE, textSize);
                if (isBold)
                    newTextAttribute.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                if (isItalic)
                    newTextAttribute.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                if (isUnderline)
                    newTextAttribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

                Font newFont = new Font(newTextAttribute);
                JMyTextArea textArea = new JMyTextArea(newFont, new Color(rgb));
                textArea.setText(content);
                textArea.setBounds(x, y, width, height);
                myComponent = textArea;

            }
            default -> System.out.println("error");
        }
        add(myComponent, layer);
        changeChooseGraph(myComponent);
    }

    public MyComponent getChooseGraph() {
        return chooseGraph;
    }

}

