package MyComponent.myGraph;

import MyComponent.myLine.MyPoint;

import java.awt.*;

public class JOval extends JGraph{

    public JOval(){}
    public JOval(Color color,BasicStroke stroke){
        super(color,stroke);
    }
    @Override
    public void resize(MyPoint A, MyPoint B) {
        super.resize(A, B);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        //设置画笔
        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(stroke);

        g2d.drawOval(0,0,getWidth(),getHeight());
        g2d.dispose();
    }

    @Override
    public String save() {
        String sup = super.save();
        StringBuilder log =  new StringBuilder();
        log.append("JOval").append(System.getProperty("line.separator"));
        log.append(sup);
        return log.toString();
    }
}
