package MyComponent.textarea;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;

import javax.swing.*;
import java.awt.*;

public class JMyTextArea extends JTextArea implements MyComponent {
    public JMyTextArea(Color Fontcolor,String name,int style,int size){
        setCaretColor(Fontcolor);
        setFont(new Font(name,style,size));
    }
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));
    }

}
