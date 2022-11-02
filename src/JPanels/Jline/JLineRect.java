package JPanels.Jline;

import java.awt.*;

public class JLineRect extends JLine {
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


        return X>getX()-ClickEffectiveRange &&
                Y>getY()-ClickEffectiveRange &&
                X<weight+getX()+ClickEffectiveRange&&
                Y<height+getY()+ClickEffectiveRange;

    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
