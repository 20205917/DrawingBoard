package JPanels.JGraph;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel {
    protected Color color = Color.black;
    protected Stroke stroke;
    public JGraph(){};
    public JGraph(Color color, Stroke stroke){
        this.stroke = stroke;
        this.color = color;
    }
    public boolean isClecked(int X,int Y){
        return false;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
