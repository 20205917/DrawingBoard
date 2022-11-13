package window.area.part;

import MyComponent.MyComponent;
import MyComponent.myLine.JDrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane {
    // 画布大小
    protected static final int INITIAL_WIDTH = 500;
    protected static final int INITIAL_HEIGHT = 450;
    //背景
    JPanel background;
    //图形集合
    HashMap<String, MyComponent> GraphSet = new HashMap<>();
    //画图笔轨迹集合
    public ArrayList<JDrawLine> jDrawLines = new ArrayList<>();

    // 字体设置
    protected Font textFont;
    // 字体字典
    protected HashMap<TextAttribute, Object> textAttribute = new HashMap<>();
    // 文本框字体
    protected String textStyle = "Times New Roman";
    // 默认字体大小
    protected int textSize = 25;

    protected Boolean isBold = false;
    protected Boolean isUnderline = false;
    protected Boolean isItalic = false;

    //控制图形创建
    protected selects selection = selects.Rect;
    //图形颜色
    protected Color drawLineColor = Color.blue;
    // 线条宽度
    protected BasicStroke drawLineStroke = new BasicStroke(2);

                 
    //当前图形
    protected MyComponent chooseGraph;
   

    public Board() {
        setLayout(null);
        //白色画板
        background = new JPanel();
        background.setBackground(Color.white);
        add(background, DEFAULT_LAYER-1, 0);


        // 处理生成图形时，截获鼠标事件
        BoardGlassPane boardGlassPane = new BoardGlassPane(this);
        // 初始与底层，一般情况不截取
        add(boardGlassPane,FRAME_CONTENT_LAYER,0);

        //大小改变的自适配
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(getWidth(), getHeight());
                boardGlassPane.setSize(getWidth(), getHeight());
            }
        });
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (JDrawLine jDrawLine : jDrawLines)
            jDrawLine.drawLine(g);
    }

    //文件保存
    public String save(){
        StringBuffer log = new StringBuffer();
        //保存尺寸和背景
        log.append("board-width:").append(getWidth()).append(System.getProperty("line.separator"));
        log.append("board-width:").append(getWidth()).append(System.getProperty("line.separator"));
        log.append("background-color: ").append(background.getBackground()).append(System.getProperty("line.separator"));

        //保存组件
        for (MyComponent myComponent : GraphSet.values()){
            log.append("Layer: ").append(getLayer((Component) myComponent)).append(System.getProperty("line.separator"));
            log.append(myComponent.save()).append(System.getProperty("line.separator"));
        }

        return log.toString();
    }


    //属性的设置与读取
    public void setDrawLineColor(Color drawLineColor) {
        this.drawLineColor = drawLineColor;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
        this.textAttribute.put(TextAttribute.FAMILY, textStyle);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        this.textAttribute.put(TextAttribute.SIZE, textSize);
    }

    public void setIsBold() {
        this.isBold = !this.isBold;
        if (isBold)
            this.textAttribute.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        else
            this.textAttribute.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
    }

    public void setIsItalic() {
        this.isItalic = !this.isItalic;
        if (isItalic)
            this.textAttribute.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        else
            this.textAttribute.put(TextAttribute.POSTURE, TextAttribute.POSTURE_REGULAR);
    }

    public void setIsUnderline() {
        this.isUnderline = !this.isUnderline;
        if (isUnderline)
            this.textAttribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        else
            this.textAttribute.remove(TextAttribute.UNDERLINE);
    }

    public void setTextFont() {
        this.textFont = new Font(textAttribute);
    }

    public void setSelection(selects selection) {
        this.selection = selection;
    }

    public void setDrawLineStroke(int thickness) {
        this.drawLineStroke = new BasicStroke(thickness);
    }
}

/*提醒
selection为鼠标时应当设置GlassPane为顶部
 */