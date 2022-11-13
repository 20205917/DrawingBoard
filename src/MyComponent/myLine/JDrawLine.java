package MyComponent.myLine;

import java.awt.*;
import java.util.Vector;

public class JDrawLine {
    BasicStroke stroke;
    Color color;
    Vector<MyPoint> myPoints =  new Vector<>();

    public JDrawLine(Color color, BasicStroke stroke){
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

        for(int i = 0; i< myPoints.size()-1; i++){
            g2d.drawLine(myPoints.get(i).px, myPoints.get(i).py, myPoints.get(i+1).px, myPoints.get(i+1).py);
        }
        g2d.dispose();
    }
    public void addPoint(int x,int y){
        myPoints.add(new MyPoint(x,y));
    }

    public String save(){
        StringBuilder log = new StringBuilder();
        log.append("JDrawLine").append(System.getProperty("line.separator"));
        log.append("color:").append(color.getRGB()).append("stroke:").append(stroke.getLineWidth())
                .append(System.getProperty("line")).append(System.getProperty("line.separator"));
        int i=0;
        for (MyPoint point : myPoints){
            log.append(point.px).append(" ").append(point.py).append(" ");
            if(++i==10){
                i=0; log.append(System.getProperty("line.separator"));
            }
        }

        return log.toString();
    }
}
