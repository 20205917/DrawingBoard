import MyComponent.myLine.JDrawLine;
import window.Management;
import window.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Management());
        userInterface.setVisible(true);
    }
}
class JF extends JFrame{
    ArrayList<JDrawLine> jDrawLines;
    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}