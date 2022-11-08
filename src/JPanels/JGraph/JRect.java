package JPanels.JGraph;

import java.awt.*;

public class JRect extends JGraph {
    private int weight = 0;
    private int height = 0;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillOval(this.getX(),this.getY(),weight,height);
    }

    @Override
    public boolean isClecked(int X,int Y) {

        return X>getX() && Y>getY() && X<weight+getX() &&Y<height+getY();

    }



    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
