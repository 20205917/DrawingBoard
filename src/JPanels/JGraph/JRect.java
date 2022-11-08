package JPanels.JGraph;


import JPanels.MyPoint;

import java.awt.*;

public class JRect extends JGraph {
    private int Gwidth = 0;
    private int Gheight = 0;

    public JRect(){}
    public JRect(Color color,Stroke stroke){
        super(color,stroke);
    }

    @Override
    public void resize(MyPoint A, MyPoint B) {
        super.resize(A, B);
        Gwidth = Math.abs(A.px-B.px);
        Gheight = Math.abs(A.py-B.py);
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Gwidth,Gheight);
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D g2d = (Graphics2D) g.create();
//        //设置画笔
//        g2d.setBackground(null);
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(color);
//        g2d.setStroke(stroke);
//
//        g2d.drawRect(getX(),getY(),Gwidth,Gheight);
//        g2d.dispose();
//    }

    @Override
    public boolean isClecked(int X,int Y) {

        return X>getX() && Y>getY() && X< Gwidth +getX() &&Y< Gheight +getY();

    }



    public void setGheight(int gheight) {
        this.Gheight = gheight;
    }

    public void setGwidth(int gwidth) {
        this.Gwidth = gwidth;
    }
}
