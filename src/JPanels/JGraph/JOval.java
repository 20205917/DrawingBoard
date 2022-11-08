package JPanels.JGraph;

import java.awt.*;

public class JOval extends JGraph {
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
        int x = X -(getX()+weight);
        int y = Y -(getY()+height);
        return Math.pow(x,2) / Math.pow(weight,2) + Math.pow(x,2) / Math.pow(height,2) <1;

    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
