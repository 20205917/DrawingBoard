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
    private volatile  MyPoint movePoint = new MyPoint(0,0);
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
                protectPoint = switch (getCursor().getType()){
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
                movePoint = new MyPoint(e.getXOnScreen(),e.getYOnScreen());

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                switch (getCursor().getType()){
                    //拖拽
                    case Cursor.MOVE_CURSOR -> {
                        setLocation(getX()+e.getXOnScreen()-movePoint.px, getY()+e.getYOnScreen()-movePoint.py);
                        movePoint = new MyPoint(e.getXOnScreen(),e.getYOnScreen());
                    }
                    //左右扩展
                    case Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR
                            ->resize(protectPoint, new MyPoint(getX()+e.getX(),getY()+getHeight()));
                    //上下扩展
                    case Cursor.N_RESIZE_CURSOR,Cursor.S_RESIZE_CURSOR
                            ->resize(protectPoint, new MyPoint(getX()+getWidth(),getY()+e.getY()));
                    //角扩展
                    case Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR
                            ->resize(protectPoint, new MyPoint(getX()+e.getX(),getY()+e.getY()));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //鼠标形状
                setCursor(currentCursorStyle(getWidth(),getHeight(),e.getX(),e.getY()));
            }
        });
    }
    //参数A，B：对角两点。
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));
    }
    public void setColor(Color color) {
        this.color = color;
    }
}