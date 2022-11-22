import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(400,800);
        JLabel[] jLabels = new JLabel[10];
        int i=0;
        for (JLabel jLabel : jLabels){
            jLabel = new JLabel();
            jLabel.setText(""+i++);
            jFrame.add(jLabel);
        }
        jFrame.setVisible(true);
        //UserInterface userInterface = new UserInterface(new Management());
        //userInterface.setVisible(true);
    }
}
