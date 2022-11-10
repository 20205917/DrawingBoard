package MyComponent.myGraph;

import MyComponent.myLine.MyPoint;
import MyComponent.MyComponent;

import javax.swing.*;
import java.awt.*;

public class JGraph extends JPanel implements MyComponent {
    protected Color color = Color.black;
    protected Stroke stroke;
    public JGraph(){}
    public JGraph(Color color, Stroke stroke){
        this.stroke = stroke;
        this.color = color;
        setOpaque(false);
    }
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));
    }
    public void setColor(Color color) {
        this.color = color;
    }
}

