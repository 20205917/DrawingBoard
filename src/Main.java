import MyComponent.myLine.JDrawLine;
import MyComponent.textarea.JMyTextArea;
import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setLayout(null);
        jf.setSize(800,600);
        Board board = new Board();
        board.setBounds(50,70,700,500);

        JMyTextArea jTextArea = new JMyTextArea();
        JMyTextArea jTextArea1 = new JMyTextArea();
        jTextArea.setBounds(100,100,200,50);
        board.add(jTextArea,1,0);
        jTextArea.setOpaque(false);

        jTextArea1.setBounds(100,200,200,50);
        board.add(jTextArea1,1,0);
        jTextArea1.setOpaque(false);
        jf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(jTextArea.getSelectedText());
                System.out.println(jTextArea1.getSelectedText());
            }
        });

        jTextArea.getSelectedText();
        jf.add(board);
        jf.setVisible(true);
    }


}
class JF extends JFrame{
    ArrayList<JDrawLine> jDrawLines;
    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}