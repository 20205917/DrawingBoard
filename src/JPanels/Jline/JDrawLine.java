package JPanels.Jline;

import java.awt.*;
import java.util.Vector;

public class JDrawLine {
    Stroke stroke;
    Color color = Color.black;
    Vector<Point> points =  new Vector<>();

    public JDrawLine(Color color, Stroke stroke){
        this.stroke = stroke;
        this.color = color;
    }
    public void drawLine(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        //设置画笔
        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(stroke);

        for(int i=0;i<points.size()-1;i++){
            g2d.drawLine(points.get(i).px,points.get(i).py,points.get(i+1).px,points.get(i+1).py);
        }
        g2d.dispose();
    }
    public void addPoint(int x,int y){
        points.add(new Point(x,y));
    }
}
