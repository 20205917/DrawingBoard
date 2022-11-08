package window.area.part;

import JPanels.JGraph.JGraph;
import JPanels.JGraph.JRect;
import JPanels.Jline.JDrawLine;
import JPanels.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane{
    //图形集合
    HashMap<String,JComponent> GraphSet = new HashMap<>();
    //画图笔轨迹集合
    public ArrayList<JDrawLine> jDrawLines = new ArrayList<>();
    //大小
    private int Bwidth = 300;
    private int Bheight = 200;

    //控制图形创建
    private int selection = 2;      //之后换枚举
    private boolean isMake = false;
    private MyPoint mousePressedPoint;
    //图形颜色
    private Color drawLineColor = Color.black;
    private BasicStroke drawLineStroke = new BasicStroke(3);

    //当前图形
    public JGraph chooseGraph;



    public Board(){
        setLayout(null);
        //白色画板
        JPanel background = new JPanel();
        background.setSize(new Dimension(getWidth(),getHeight()));
        background.setBackground(Color.red);
        background.setSize(Bwidth,Bheight);
        add(background,DEFAULT_LAYER,-1);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(isMake){
                    switch (selection){
                        case 1->{
                            jDrawLines.get(jDrawLines.size()-1).addPoint(e.getX(),e.getY());
                            jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics()); }
                        case 2,3,4->{
                            chooseGraph.resize(mousePressedPoint,new MyPoint(e.getX(),e.getY()));
                        }
                        default -> {System.out.println( "创建失败"+selection);}
                    }
                }
            }
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePressedPoint = new MyPoint(e.getX(),e.getY());
                switch (selection){
                    case 1->{
                        isMake = true;
                        jDrawLines.add(new JDrawLine(drawLineColor,drawLineStroke));
                        jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics());
                    }
                    case 2,3,4->{
                        isMake = true;
                        //创建图形
                        chooseGraph = switch (selection){
                            case 2 -> new JRect(drawLineColor,drawLineStroke);
                            case 3 -> new JGraph();
                            default -> new JGraph();
                        };
                        chooseGraph.setLocation(e.getX(),e.getY());
                        chooseGraph.setSize(10,10);
                        add(chooseGraph,DEFAULT_LAYER+1,0);
                        for (int i=0;i<100;i++){
                            if(!GraphSet.containsKey("图形"+i)){
                                GraphSet.put("图形"+i,chooseGraph);
                                break;
                            }
                        }
                    }
                }
            }

            //鼠标松开
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                //创建结束
                if(isMake){
                    isMake = false;
                    switch (selection){
                        case 1->{jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics());}
                        case 2,3,4->{}
                    }
                }

            }
        });


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(Bwidth,Bheight);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (JDrawLine jDrawLine : jDrawLines)
            jDrawLine.drawLine(g);
    }


    public String save(){
        StringBuilder logs = new StringBuilder();
        for (String s : GraphSet.keySet())
        {
            JComponent c = GraphSet.get(s);
            logs.append(s).append(getLayer(c)).append(getPosition(c));
        }
        return logs.toString();
    }



    public int getBheight() {
        return Bheight;
    }

    public void setBheight(int bheight) {
        Bheight = bheight;
    }

    public int getBwidth() {
        return Bwidth;
    }

    public void setBwidth(int bwidth) {
        Bwidth = bwidth;
    }
}
