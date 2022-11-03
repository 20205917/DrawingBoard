import JPanels.Jline.JDrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

public class Main {
    static boolean jb = false;
    public static void main(String[] args) {


        LinkedList<JDrawLine> jDrawLines = new LinkedList<>();

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        JPanel[] boards = new JPanel[10];
        JButton jButton = new JButton("点击");
        jButton.setBackground(Color.red);
        jFrame.add(jButton);
        jFrame.addMouseListener(new MouseAdapter() {
            int m = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                jb = true;
                jDrawLines.add(new JDrawLine(Color.red,new BasicStroke(5)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                jb = false;
            }
        });
        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                jDrawLines.getLast().addPoint(e.getX(), e.getY());
            }
        });
        for (int i = 0; i < 10; i++){
            boards[i] = new JPanel();
            boards[i].add(new Label("" + i + "zhang"));
        }
        jFrame.add(boards[0]);
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
