package JPanels.JGraph;

import JPanels.MyPoint;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel {
    protected Color color = Color.black;
    protected Stroke stroke;
    public JGraph(){}
    public JGraph(Color color, Stroke stroke){
        this.stroke = stroke;
        this.color = color;
        setBackground(Color.green);
    }
    public void resize(MyPoint A,MyPoint B){}
    public boolean isClecked(int X,int Y){
        return false;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
