package MyComponent.myGraph;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;
import window.area.ToolBox;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel implements MyComponent {
    protected Color color = Color.black;
    protected final BasicStroke stroke;
    protected final MyGraphType type;
    protected boolean isHollow = false;


    protected JGraph(MyGraphType type, ToolBox toolbox boolean isHollow){
        this.stroke = toolbox.getDrawLineStroke();
        this.color = toolbox.getDrawLineColor();
        this.type = type;
        this.isHollow = isHollow;
        this.isHollow = toolbox.getHollow();
        setOpaque(false);
        //移动变形所需监听器
        addListener();
    }

    //重设大小方位参数A，B：对角两点。
    public void resize(MyPoint A, MyPoint B) {
        setBounds(Math.min(A.px, B.px), Math.min(A.py, B.py), Math.abs(A.px - B.px), Math.abs(A.py - B.py));
    }

    //
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        //设置画笔
        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(stroke);
        if (!isHollow) {
            switch (type) {
                case Line -> {
                }                                                                                                          //线段
                case Oval ->
                        g2d.drawOval(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap);                          //椭圆形
                case RoundRect ->
                        g2d.drawRoundRect(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap, gap, gap);
                case Rect ->
                        g2d.drawRect(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap);                          //长方形
                case Circle ->
                        g2d.drawOval(drawGap, drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap);  // 圆形
                case Square ->
                        g2d.drawRect(drawGap, drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap);  //正方形
                case Triangle -> {                                                                                                       //等腰三角形
//                g2d.drawLine(getX(),getY()+getHeight()+drawGap,getX()+getWidth(),getY()+getHeight()+drawGap);
//                g2d.drawLine(getX()+getWidth()/2,getY(),getX(),getY()+getHeight());
//                g2d.drawLine(getX()+getWidth()/2,getY(),getX()+getWidth(),getY()+getHeight());
                    // System.out.println("Tri");
                    g2d.drawPolygon(new int[]{g2d.getClipBounds().x,
                                    g2d.getClipBounds().x + getWidth(),
                                    g2d.getClipBounds().x + getWidth() / 2},
                            new int[]{g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y},
                            3);
//                g2d.drawLine(g2d.getClipBounds().x, g2d.getClipBounds().y + getHeight() - drawGap, g2d.getClipBounds().x + getWidth(), g2d.getClipBounds().y + getHeight() - drawGap);
//                g2d.drawLine(g2d.getClipBounds().x + getWidth() / 2, g2d.getClipBounds().y, g2d.getClipBounds().x, g2d.getClipBounds().y + getHeight());
//                g2d.drawLine(g2d.getClipBounds().x + getWidth() / 2, g2d.getClipBounds().y, g2d.getClipBounds().x + getWidth(), g2d.getClipBounds().y + getHeight());
                }
                case IsoscelesLadder -> {                                                                                                 //等腰梯型
                    g2d.drawPolygon(new int[]{g2d.getClipBounds().x + getWidth() / 4,
                                    g2d.getClipBounds().x + getWidth() * 3 / 4,
                                    g2d.getClipBounds().x + getWidth(),
                                    g2d.getClipBounds().x},
                            new int[]{g2d.getClipBounds().y + drawGap,
                                    g2d.getClipBounds().y + drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap},
                            4);

//                g2d.drawLine(g2d.getClipBounds().x + getWidth() / 4, g2d.getClipBounds().y + drawGap, g2d.getClipBounds().x + getWidth() * 3 / 4, g2d.getClipBounds().y + drawGap);
//                g2d.drawLine(g2d.getClipBounds().x, g2d.getClipBounds().y + getHeight() - drawGap, g2d.getClipBounds().x + getWidth(), g2d.getClipBounds().y + getHeight() - drawGap);
//                g2d.drawLine(g2d.getClipBounds().x + getWidth() / 4, g2d.getClipBounds().y + drawGap, g2d.getClipBounds().x, g2d.getClipBounds().y + getHeight() + drawGap);
//                g2d.drawLine(g2d.getClipBounds().x + getWidth() * 3 / 4, g2d.getClipBounds().y + drawGap, g2d.getClipBounds().x + getWidth(), g2d.getClipBounds().y + getHeight() - drawGap);
                }
            }
        } else {
            switch (type) {
                case Line -> {
                }                                                                                                          //线段
                case Oval ->
                        g2d.fillOval(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap);                          //椭圆形
                case RoundRect ->
                        g2d.fillRoundRect(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap, gap, gap);
                case Rect ->
                        g2d.fillRect(drawGap, drawGap, getWidth() - 2 * drawGap, getHeight() - 2 * drawGap);                          //长方形
                case Circle ->
                        g2d.fillOval(drawGap, drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap);  // 圆形
                case Square ->
                        g2d.fillRect(drawGap, drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap, Math.min(getWidth(), getHeight()) - 2 * drawGap);  //正方形
                case Triangle -> {                                                                                                       //等腰三角形
                    g2d.fillPolygon(new int[]{g2d.getClipBounds().x,
                                    g2d.getClipBounds().x + getWidth(),
                                    g2d.getClipBounds().x + getWidth() / 2},
                            new int[]{g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y},
                            3);
                }
                case IsoscelesLadder -> {                                                                                                 //等腰梯型
                    g2d.fillPolygon(new int[]{g2d.getClipBounds().x + getWidth() / 4,
                                    g2d.getClipBounds().x + getWidth() * 3 / 4,
                                    g2d.getClipBounds().x + getWidth(),
                                    g2d.getClipBounds().x},
                            new int[]{g2d.getClipBounds().y + drawGap,
                                    g2d.getClipBounds().y + drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap,
                                    g2d.getClipBounds().y + getHeight() - drawGap},
                            4);

                }
            }

        }

        g2d.dispose();
    }


    @Override
    public String save() {
        StringBuilder log = new StringBuilder();
        log.append(type.toString());
//        switch (type) {
//            case Line -> log.append("Line");
//            case Rect -> log.append("Rect");
//            case Oval -> log.append("Oval");
//            case Triangle -> log.append("Triangle");
//            case Square -> log.append("Square");
//            case IsoscelesLadder -> log.append("IsoscelesLadder");
//            case Circle -> log.append("Circle");
//            default -> log.append("Error");
//        }
        log.append(System.getProperty("line.separator"));
        log.append("color:").append(color.getRGB()).append(System.getProperty("line.separator"))
                .append("stroke:").append(stroke.getLineWidth()).append(System.getProperty("line.separator"));

        log.append("location:").append(getX()).append(" ").append(getY()).append(System.getProperty("line.separator"));
        log.append("size:").append(getWidth()).append(" ").append(getHeight()).append(System.getProperty("line.separator"));
        log.append("isHollow:").append(isHollow).append(System.getProperty("line.separator"));
        return log.toString();
    }

    @Override
    public MyComponent clone() {
        JGraph clone = new JGraph(this.color, this.stroke, this.type, this.isHollow);
        clone.setBounds(getX(), getY(), getWidth(), getHeight());
        return clone;
    }

}