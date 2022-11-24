package MyComponent;

import MyComponent.myLine.MyPoint;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public interface MyComponent {
    //边框判断间隔范围为，用于位移拉伸
    int gap = 5;
    int boarderSize = 4;
    int drawGap = 2;

    HashMap<MyComponent,MyPoint> protectPoints = new HashMap<>();
    HashMap<MyComponent,MyPoint> movePoints = new HashMap<>();

    void resize(MyPoint A, MyPoint B);

    String save();

    MyComponent clone();

    int getX();
    int getY();
    int getWidth();
    int getHeight();

    boolean isEnabled();
    void setEnabled(boolean b);

    void setCursor(Cursor cursor);
    Cursor getCursor();

    void requestFocus();
    boolean isFocusOwner();

    void setBorder(Border border);
    void setLocation(int a,int b);
    void setBounds(int a,int b,int c,int d);

    void addMouseListener(MouseListener l);
    void addMouseMotionListener(MouseMotionListener l);
    void addFocusListener(FocusListener l);



    default String saveBounds(){
        StringBuilder log =  new StringBuilder();

        log.append("location:").append(getX()).append(" ").append(getY()).append(System.getProperty("line.separator"));
        log.append("size:").append(getWidth()).append(" ").append(getHeight()).append(System.getProperty("line.separator"));
        // log.append("#####").append(System.getProperty("line.separator"));
        return log.toString();
    }

    default Cursor currentCursorStyle(int W,int H,int X,int Y){

        //靠左
        boolean OnLeft = X < W-X;
        //靠上
        boolean OnTop = Y < H-Y;

        int wgap = Math.min(X,W-X);
        int hgap = Math.min(Y,H-Y);
        if(wgap < gap && hgap <gap){
            if(OnLeft && OnTop ) return new Cursor(Cursor.NW_RESIZE_CURSOR);
            else if(OnLeft)return new Cursor(Cursor.SW_RESIZE_CURSOR);
            else if(OnTop)return new Cursor(Cursor.NE_RESIZE_CURSOR);
            else return new Cursor(Cursor.SE_RESIZE_CURSOR);
        }
        else if(wgap < gap && hgap >gap){
            if(OnLeft)
                return new Cursor(Cursor.W_RESIZE_CURSOR);
            else
                return new Cursor((Cursor.E_RESIZE_CURSOR));
        }
        else if(wgap > gap && hgap <gap) {
            if (OnTop)
                return new Cursor(Cursor.N_RESIZE_CURSOR);
            else
                return new Cursor((Cursor.S_RESIZE_CURSOR));
        }
        else
            return new Cursor(Cursor.MOVE_CURSOR);
    }


    //图形的拖动和放大缩小
    default void addListener(){

       addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                MyPoint protectPoint = switch (getCursor().getType()){
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
                protectPoints.put(MyComponent.this,protectPoint);
                MyPoint movePoint = new MyPoint(e.getXOnScreen(),e.getYOnScreen());
                movePoints.put(MyComponent.this,movePoint);
                requestFocus();
                setCursor(currentCursorStyle(getWidth(),getHeight(),e.getX(),e.getY()));
            }

           //被双击或者选择双击时，进入可编辑状态
           @Override
           public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               if(e.getClickCount()>=2 )
                   setEnabled(true);
           }

           //活动点的清除
           @Override
           public void mouseReleased(MouseEvent e){
                super.mouseReleased(e);
                protectPoints.remove(MyComponent.this);
                movePoints.remove(MyComponent.this);
           }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MyPoint protectPoint = protectPoints.get(MyComponent.this);
                MyPoint movePoint = movePoints.get(MyComponent.this);

                //如果不在选定状态无法移动
                if(!isEnabled())
                    return;
                switch (getCursor().getType()){
                    //拖拽
                    case Cursor.MOVE_CURSOR -> {
                        setLocation(getX()+e.getXOnScreen()-movePoint.px, getY()+e.getYOnScreen()-movePoint.py);
                        movePoints.put(MyComponent.this,new MyPoint(e.getXOnScreen(),e.getYOnScreen()));
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

            //鼠标形状的改变
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //鼠标形状
                if(isEnabled())
                    setCursor(currentCursorStyle(getWidth(),getHeight(),e.getX(),e.getY()));
            }
        });

        //失去焦点时，进入不可编辑状态
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setBorder(BorderFactory.createMatteBorder(boarderSize, boarderSize, boarderSize, boarderSize, Color.black));
            }
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                setEnabled(false);
                setBorder(null);
            }
        });

    }

}
