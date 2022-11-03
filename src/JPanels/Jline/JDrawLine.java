package JPanels.Jline;

import java.awt.*;
import java.util.Vector;

public class JDrawLine {
    Stroke stroke;
    Color color = Color.black;
    Vector<Point> points;

    public JDrawLine(Color color, Stroke stroke){
        this.stroke = stroke;
        this.color = color;
    }
    public Graphics2D getGraphics2D(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(stroke);


        for(int i=0;i<points.size();i++){
            g2d.draw()
        }
        return g2d;
    }
    public void addPoint(int x,int y){
        points.add(new Point(x,y));
    }
}
