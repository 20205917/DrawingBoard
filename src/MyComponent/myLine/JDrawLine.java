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
        Graphics2D g2d = setGraphics2D(g);
        for(int i : myLines.keySet()){
            MyPoint A = new MyPoint(i);
            for (MyPoint B : myLines.get(i))
                g2d.drawLine(A.px, A.py, B.px, B.py);
        }
        g2d.dispose();
    }

    //添加经过点,添加及绘画
    public void addAndDrawPoint(MyPoint newPoint,Graphics g){
        Graphics2D g2d = setGraphics2D(g);

        if(frontPoint == null){
            frontPoint = newPoint;
            myLines.put(newPoint.toInt(),new HashSet<>());
            return;
        }
        //old->new
        myLines.get(frontPoint.toInt()).add(newPoint);

        if(!myLines.containsKey(newPoint.toInt())){
            myLines.put(newPoint.toInt(),new HashSet<>());
        }
        //new->old
        myLines.get(newPoint.toInt()).add(frontPoint);
        g2d.drawLine(frontPoint.px, frontPoint.py,newPoint.px,newPoint.py);
        frontPoint = newPoint;

        g2d.dispose();
    }

    //返回 0 代表无线条
    public boolean deletePoint(MyPoint point,int path){
        boolean ifDelete = false;
        //得到擦除的区域
        MyPoint[] checkArea = MyPoint.getPointArea(point,path);

        //遍历擦除点
        for (MyPoint myPoint : checkArea){
            if(!myLines.containsKey(myPoint.toInt()))
                continue;
            ifDelete = true;
            //删除到达myPoint的线，如果该线起点无其他路径删除点
            for(MyPoint point1 : myLines.get(myPoint.toInt())) {
                HashSet<MyPoint> a = myLines.get(point1.toInt());
                if (a != null) {
                    a.remove(myPoint);
                    if (a.size() == 0)
                        myLines.remove(point1.toInt());
                }
            }
            //删除该点及其路径
            myLines.remove(myPoint.toInt());
        }
        return ifDelete;
    }

    //判断是否为空
    public boolean isEmpty(){return myLines.isEmpty();}

    //设置画笔
    private Graphics2D setGraphics2D(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setBackground(null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
        g2d.setColor(color);
        g2d.setStroke(stroke);
        return g2d;
    }

    public String save(){
        StringBuilder log = new StringBuilder();
        log.append("JDrawLine").append(System.getProperty("line.separator"));
        log.append("color:").append(color.getRGB()).append("stroke:").append(stroke.getLineWidth())
                .append(System.getProperty("line")).append(System.getProperty("line.separator"));
//        int i=0;
//        for (MyPoint point : myPoints){
//            log.append(point.px).append(" ").append(point.py).append(" ");
//            if(++i==10){
//                i=0; log.append(System.getProperty("line.separator"));
//            }
//        }

        return log.toString();
    }
}
