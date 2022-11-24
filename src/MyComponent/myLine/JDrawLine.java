package MyComponent.myLine;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class JDrawLine {
    final BasicStroke stroke;
    final Color color;
    HashMap<Integer, HashSet<MyPoint>> myLines = new HashMap<>();
    MyPoint frontPoint = null;
    public JDrawLine(Color color, BasicStroke stroke){
        this.stroke = stroke;
        this.color = color;
    }
    public void drawLine(Graphics g){
        if(myLines==null) return;
        Graphics2D g2d = (Graphics2D) g.create();
        //设置画笔
        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(stroke);
        for(int i : myLines.keySet()){
            MyPoint A = new MyPoint(i);
            for (MyPoint B : myLines.get(i))
                g2d.drawLine(A.px, A.py, B.px, B.py);
        }
        g2d.dispose();
    }
    //添加经过点
    public void addPoint(MyPoint newPoint){
        if(frontPoint == null){
            frontPoint = newPoint;
            myLines.put(newPoint.toInt(),new HashSet<>());
            return;
        }
        myLines.get(frontPoint.toInt()).add(newPoint);
        if(!myLines.containsKey(newPoint.toInt())){
            myLines.put(newPoint.toInt(),new HashSet<>());
        }
        myLines.get(newPoint.toInt()).add(frontPoint);
        frontPoint = newPoint;
    }
    //返回 0 代表无线条
    public int deletePoint(MyPoint point){
        if(myLines == null)
            return 0;
        System.out.println(point.px+" "+point.py);
        if(!myLines.containsKey(point.toInt()))
            return 1;
        //删除到达point的线，如果该线起点无其他路径删除点
        for(MyPoint myPoint : myLines.get(point.toInt())){
            myLines.get(myPoint.toInt()).remove(point);
            if(myLines.get(myPoint.toInt()).size()==0)
                myLines.remove(myPoint.toInt());
        }
        //删除该点及其路径
        myLines.remove(point.toInt());
        return myLines.size();
    }

    public String save(){
        StringBuilder log = new StringBuilder();
        log.append("JDrawLine").append(System.getProperty("line.separator"));
        log.append("color:").append(color.getRGB()).append("stroke:").append(stroke.getLineWidth())
                .append(System.getProperty("line")).append(System.getProperty("line.separator"));
        int i=0;
//        for (MyPoint point : myPoints){
//            log.append(point.px).append(" ").append(point.py).append(" ");
//            if(++i==10){
//                i=0; log.append(System.getProperty("line.separator"));
//            }
//        }

        return log.toString();
    }
}
