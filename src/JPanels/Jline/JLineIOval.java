package JPanels.Jline;

import java.awt.*;

public class JLineIOval extends JLine {
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
        //有效点击外圈
        double result1 = Math.pow(x,2) / Math.pow(weight+ClickEffectiveRange,2) + Math.pow(y,2) / Math.pow(height+ClickEffectiveRange,2);
        //有效点击内圈
        double result2 = Math.pow(x,2) / Math.pow(weight-ClickEffectiveRange,2) + Math.pow(y,2) / Math.pow(height-ClickEffectiveRange,2);

        return result1<=1 && result2>=1;

    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
