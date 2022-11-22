import window.Management;
import window.UserInterface;

public class Main {
    public static void main(String[] args) {
//        JFrame jFrame = new JFrame();
//        jFrame.setLayout(new FlowLayout());
//        jFrame.setSize(400,800);
//        JLabel[] jLabels = new JLabel[10];
//
//        for (int i=0 ;i<10;i++){
//            jLabels[i] = new JLabel();
//            jLabels[i].setPreferredSize(new Dimension(300,50));
//            jLabels[i].setText(""+i);
//            jFrame.add(jLabels[i]);
//        }
//        jFrame.add(jLabels[9],4);
//        jFrame.setVisible(true);
        UserInterface userInterface = new UserInterface(new Management());
        userInterface.setVisible(true);
    }
}
