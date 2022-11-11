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
    //图形集合
    HashMap<String, MyComponent> GraphSet = new HashMap<>();
    //画图笔轨迹集合
    public ArrayList<JDrawLine> jDrawLines = new ArrayList<>();

    // 字体设置
    protected Font textFont;
    protected HashMap<TextAttribute, Object> textAttribute = new HashMap<TextAttribute, Object>();
    protected String textStyle = "Times New Roman";
    protected int textSize = 25;
    protected Boolean isBold = false;
    protected Boolean isUnderline = false;
    protected Boolean isItalic = false;

    //控制图形创建
    protected selects selection = selects.Rect;      //之后换枚举
    //图形颜色
    protected Color drawLineColor = Color.blue;
    // 线条宽度
    protected BasicStroke drawLineStroke = new BasicStroke(1);

    //当前图形
    public MyComponent chooseGraph;


    public Board() {
        setLayout(null);
        //白色画板
        JPanel background = new JPanel();
        background.setBackground(Color.red);
        add(background,DEFAULT_LAYER,0);


        //处理生成图形时，截获鼠标事件
        BoardGlassPane boardGlassPane = new BoardGlassPane(this);
        //一般情况不截取
        add(boardGlassPane,FRAME_CONTENT_LAYER,0);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(getWidth(), getHeight());
                boardGlassPane.setSize(getWidth(), getHeight());
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

        return logs.toString();
    }

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