import window.area.part.Board;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setLayout(null);
        jf.setSize(600,600);
        Board board = new Board();
        board.setSize(300,300);
        jf.add(board);


        jf.setVisible(true);
    }
}