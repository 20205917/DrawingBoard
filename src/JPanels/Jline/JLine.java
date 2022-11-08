package JPanels.Jline;

import javax.swing.*;
import java.awt.*;

public abstract class JLine extends JPanel {
    protected static double ClickEffectiveRange = 5;
    protected Color color = new Color(0);
    public boolean isClecked(int X,int Y){
        return false;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void paint(Graphics g)
    {
        super.paint(g);
    }

}
