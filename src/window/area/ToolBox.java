package window.area;

import MyComponent.myGraph.MyGraphType;
import window.area.part.selects;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class ToolBox {
    //控制图形创建
    protected selects selection = selects.CreatJGraph;
    // 图形选项
    private MyGraphType graphType = MyGraphType.Rect;
    //图形颜色
    protected Color drawLineColor = Color.blue;
    // 是否空心
    protected boolean isHollow = false;
    // 线条宽度
    protected BasicStroke drawLineStroke = new BasicStroke(2);
    // 字体设置
    protected Font textFont = new Font("宋体",Font.PLAIN,10);
    // 字体字典
    protected HashMap<TextAttribute, Object> textAttribute = new HashMap<>();
    // 文本框字体
    protected String textStyle = "Times New Roman";
    // 默认字体大小
    protected int textSize = 25;
    //更新监听器
    public ToolBoxUpdateListenerInterface listener;

    protected Boolean isBold = false;
    protected Boolean isUnderline = false;
    protected Boolean isItalic = false;


    public ToolBox(){
        // 初始化字体
        setTextFont();
    }

    //添加工具栏监听器
    public void addToolBoxUpdateListener(ToolBoxUpdateListenerInterface e){
        listener = e;
    }
    //删除工具栏监听器
    public void deleteToolBoxUpdateListener(){
        listener = null;
    }
    public void fireToolBoxUpdateListener(ToolBoxUpdateEvent e){
        if(listener!=null) listener.handleEvent(e);
    }

    //属性的设置与读取

    public void setDrawLineColor(Color drawLineColor) {
        this.drawLineColor = drawLineColor;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
        this.textAttribute.put(TextAttribute.FAMILY, textStyle);
        setTextFont();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        this.textAttribute.put(TextAttribute.SIZE, textSize);
        setTextFont();
    }

    public void setIsBold() {
        this.isBold = !this.isBold;
        if (isBold)
            this.textAttribute.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        else
            this.textAttribute.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        setTextFont();
    }

    public void setIsItalic() {
        this.isItalic = !this.isItalic;
        if (isItalic)
            this.textAttribute.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        else
            this.textAttribute.put(TextAttribute.POSTURE, TextAttribute.POSTURE_REGULAR);
        setTextFont();
    }

    public void setIsUnderline() {
        this.isUnderline = !this.isUnderline;
        if (isUnderline)
            this.textAttribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        else
            this.textAttribute.remove(TextAttribute.UNDERLINE);
        setTextFont();
    }

    public void setTextFont() {
        this.textFont = new Font(textAttribute);
    }



    public selects getSelection() {
        return selection;
    }

    public void setSelection(selects selection) {
        this.selection = selection;
        fireToolBoxUpdateListener(new ToolBoxUpdateEvent(this));
    }

    public void setDrawLineStroke(int thickness) {
        this.drawLineStroke = new BasicStroke(thickness);
    }

    public MyGraphType getGraphType() {
        return graphType;
    }

    public void setGraphType(MyGraphType graphType) {
        if(graphType != null)
            this.graphType = graphType;
    }

    public Color getDrawLineColor() {
        return drawLineColor;
    }

    public BasicStroke getDrawLineStroke() {
        return drawLineStroke;
    }

    public Font getTextFont() {
        return textFont;
    }

    public void switchHollow(){
        isHollow = !isHollow;
    }

    public void setHollow(boolean isHollow) {
        this.isHollow = isHollow;
    }

    public boolean getHollow() {
        return isHollow;
    }
}
