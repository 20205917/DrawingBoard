package MyComponent.myGraph;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;
import window.area.ToolBox;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel implements MyComponent {
    //
    private final ToolBox toolbox;
    protected Color color = Color.black;
    protected final BasicStroke stroke;
    protected final MyGraphType type;


    public JGraph(Color color, BasicStroke stroke, MyGraphType type, ToolBox toolbox){
        this.stroke = stroke;
        this.color = color;
        this.type = type;
        this.toolbox = toolbox;
        setOpaque(false);
        //移动变形所需监听器
        addListener();
    }
    public JGraph(MyGraphType type, ToolBox toolbox){
        this.stroke = toolbox.getDrawLineStroke();
        this.color = toolbox.getDrawLineColor();
        this.type = type;
        this.toolbox = toolbox;
        setOpaque(false);
        //移动变形所需监听器
        addListener();
    }
    //重设大小方位参数A，B：对角两点。
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));
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

        switch (type){
            case Line -> {}                                                                                                          //线段
            case Oval -> g2d.drawOval(drawGap,drawGap,getWidth()-drawGap,getHeight()-drawGap);                          //椭圆形
            case Rect -> g2d.drawRect(drawGap,drawGap,getWidth()-drawGap,getHeight()-drawGap);                          //长方形
            case Square -> g2d.drawRect(drawGap,drawGap,Math.min(getWidth(),getHeight())-2,Math.min(getWidth(),getHeight()));  //正方形
            case Triangle -> {                                                                                                       //等腰三角形
                g2d.drawLine(getX(),getY()+getHeight()+drawGap,getX()+getWidth(),getY()+getHeight()+drawGap);
                g2d.drawLine(getX()+getWidth()/2,getY(),getX(),getY()+getHeight());
                g2d.drawLine(getX()+getWidth()/2,getY(),getX()+getWidth(),getY()+getHeight());
            }
            case IsoscelesLadder ->{                                                                                                 //等腰梯型
                g2d.drawLine(getX(),getY()+getHeight()+drawGap,getX()+getWidth(),getY()+getHeight()+drawGap);
                g2d.drawLine(getX(),getY()+getHeight()+drawGap,getX()+getWidth(),getY()+getHeight()+drawGap);
                g2d.drawLine(getX()+getWidth()/4,getY(),getX(),getY()+getHeight());
                g2d.drawLine(getX()+getWidth()*3/4,getY(),getX()+getWidth(),getY()+getHeight());
            }
        }

        g2d.dispose();
    }


    @Override
    public String save() {
        StringBuilder log =  new StringBuilder();

        switch(type){
            case Line -> log.append("Line");
            case Rect -> log.append("Rect");
            case Oval -> log.append("Oval");
            case Triangle -> log.append("Triangle");
            case Square -> log.append("Square");
            case IsoscelesLadder -> log.append("IsoscelesLadder");
            default -> log.append("Error");
        }
        log.append(System.getProperty("line.separator"));
        log.append("color:").append(color.getRGB()).append(System.getProperty("line.separator"))
                .append("stroke:").append(stroke.getLineWidth()).append(System.getProperty("line.separator"));

        log.append("location:").append(getX()).append(" ").append(getY()).append(System.getProperty("line.separator"));
        log.append("size:").append(getWidth()).append(" ").append(getHeight()).append(System.getProperty("line.separator"));
        return log.toString();
    }

}