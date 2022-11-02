package JPanels.Jline;

import java.awt.*;

public class JStraightLine extends JLine {
    private int A_x;
    private int A_y;
    private int B_x;
    private int B_y;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(A_x,A_y,B_x,B_y);
    }

    @Override
    public boolean isClecked(int X,int Y) {
        double k = (A_x-B_x)/(A_y-B_y);
        Double d = Math.abs(k*(X-A_x)-(Y-A_y)/Math.sqrt(Math.pow(k,2)+1));

        //判断三点一线
        return d<ClickEffectiveRange;
    }
}
