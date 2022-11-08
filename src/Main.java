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
        board.setBounds(50,50,300,300);
        //白色画板


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