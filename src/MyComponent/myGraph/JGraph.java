package MyComponent.myGraph;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class JGraph extends JPanel implements MyComponent {
    protected Color color = Color.black;
    private volatile  MyPoint screenPoint = new MyPoint(0,0);
    private volatile  MyPoint protectPoint = new MyPoint(0,0);
    protected BasicStroke stroke;
    public JGraph(){}
    public JGraph(Color color, BasicStroke stroke){
        this.stroke = stroke;
        this.color = color;
        setOpaque(false);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                switch (getCursor().getType()){
                    case Cursor.MOVE_CURSOR ->  new MyPoint(getX(),getY());
                    //左平移
                    case Cursor.W_RESIZE_CURSOR -> new MyPoint(getX()+getWidth(),getY());
                    //右平移
                    case Cursor.E_RESIZE_CURSOR -> new MyPoint(getX(),getY());
                    //上平移
                    case Cursor.N_RESIZE_CURSOR -> new MyPoint(getX(),getY()+getHeight());
                    //下平移
                    case Cursor.S_RESIZE_CURSOR -> new MyPoint(getX(),getY());
                    //左上平移
                    case Cursor.NW_RESIZE_CURSOR -> new MyPoint(getX()+getWidth(),getY()+getHeight());
                    //右上平移
                    case Cursor.NE_RESIZE_CURSOR -> new MyPoint(getX(),getY()+getHeight());
                    //左下平移
                    case Cursor.SW_RESIZE_CURSOR -> new MyPoint(getX()+getWidth(),getY());
                    //右下平移
                    case Cursor.SE_RESIZE_CURSOR -> new MyPoint(getX(),getY());
                    default -> new MyPoint(0,0);
                };
                screenPoint = new MyPoint(e.getXOnScreen(),e.getYOnScreen());

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int moveX = e.getXOnScreen()-screenPoint.px;
                int moveY = e.getYOnScreen()-screenPoint.py;
                //screenPoint = new MyPoint(e.getXOnScreen(),e.getYOnScreen());
                switch (getCursor().getType()){
                    //拖拽
                    case Cursor.MOVE_CURSOR -> {
                        setLocation(getX()+moveX, getY()+moveY);
                    }
                    //左扩展
                    case Cursor.W_RESIZE_CURSOR ->{setBounds(Math.min(protectPoint.px,Point.px),getY(),getWidth()-moveX,getHeight());System.out.println("W");}
                    //右扩展
                    case Cursor.E_RESIZE_CURSOR ->{setBounds(getX(),getY(),getWidth()+moveX,getHeight());System.out.println("E");}
                    //上扩展
//                    case Cursor.N_RESIZE_CURSOR ->setBounds();
//                    //下扩展
//                    case Cursor.S_RESIZE_CURSOR ->setBounds();
                    //左上扩展
                    case Cursor.NW_RESIZE_CURSOR ->resize(protectPoint,);
//                    //右上扩展
//                    case Cursor.NE_RESIZE_CURSOR ->setBounds();
//                    //左下扩展
//                    case Cursor.SW_RESIZE_CURSOR ->setBounds();
//                    //右下扩展
//                    case Cursor.SE_RESIZE_CURSOR ->setBounds();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //图形大小
                setCursor(currentCursorStyle(getWidth(),getHeight(),e.getX(),e.getY()));
            }
        });
    }
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));
    }
    public void setColor(Color color) {
        this.color = color;
    }
}

