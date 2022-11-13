package MyComponent.myGraph;

import MyComponent.myLine.MyPoint;

import java.awt.*;

public class JLine extends JGraph {
    private MyPoint pointA;
    private MyPoint pointB;


    public JLine(){
        pointA.px = getX();
        pointA.py = getY();
        pointB.px = getX()+getWidth();
        pointB.py = getY()+getHeight();
    }
    public JLine(Color color,BasicStroke stroke){
        super(color,stroke);
        pointA = new MyPoint(getX(),getY());
        pointB = new MyPoint(getX()+getWidth(),getY()+getHeight());
    }

    @Override
    public void resize(MyPoint A, MyPoint B) {
        super.resize(A, B);
        pointA.px = A.px-getX();
        pointA.py = A.py-getY();
        pointB.px = B.px-getX();
        pointB.py = B.py-getY();
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

        g2d.drawLine(pointA.px,pointA.py,pointB.px,pointB.py);
        g2d.dispose();
    }

    @Override
    public String save() {
        String sup = super.save();
        StringBuilder log =  new StringBuilder();
        if (pointA.px<pointB.px == pointA.py<pointB.py)
            log.append("JLine-WN").append(System.getProperty("line.separator"));
        else
            log.append("JLine-EN").append(System.getProperty("line.separator"));
        log.append(sup);
        return log.toString();
    }
}
