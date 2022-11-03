import JPanels.Jline.JDrawLine;
import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setLayout(null);
        jf.setSize(600,600);
        Board board = new Board();
        board.setSize(300,300);
        jf.add(board);
        board.add(jp,JLayeredPane.DEFAULT_LAYER,0);
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