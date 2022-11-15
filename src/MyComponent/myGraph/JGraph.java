package MyComponent.myGraph;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel implements MyComponent {
    protected Color color = Color.black;
    protected BasicStroke stroke;
    protected MyGraphType type;


    public JGraph(){}
    public JGraph(Color color, BasicStroke stroke,MyGraphType type){
        this.stroke = stroke;
        this.color = color;
        this.type = type;

        setOpaque(false);
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
            case Line -> {}
            case Oval -> g2d.drawOval(2,2,getWidth()-2,getHeight()-2);
            case Rect -> g2d.drawRect(2,2,getWidth()-2,getHeight()-2);
            case Triangle -> {
                g2d.drawLine(getX(),getY()+getHeight(),getX()+getWidth(),getY()+getHeight());
                g2d.drawLine(getX()+getWidth()/2,getY(),getX(),getY()+getHeight());
                g2d.drawLine(getX()+getWidth()/2,getY(),getX()+getWidth(),getY()+getHeight());
            }
        }

        g2d.dispose();
    }


    @Override
    public String save() {
        StringBuilder log =  new StringBuilder();
        log.append("color:").append(color.getRGB()).append(System.getProperty("line.separator"))
                .append("stroke:").append(stroke.getLineWidth()).append(System.getProperty("line.separator"));

        log.append("location:").append(getX()).append(" ").append(getY()).append(System.getProperty("line.separator"));
        log.append("size:").append(getWidth()).append(" ").append(getHeight()).append(System.getProperty("line.separator"));
        return log.toString();
    }

}