package window.part;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//小窗口包含白板和按钮
public class Page extends JButton {
    public static  double AspectrRatio = 4/5.0;
    public Board board;
    // 副本 TODO
    private Board copyBoard;

    private boolean redo = false;
    // 缩略图
    public Image thumbnail;
    public Page(Board B){
        board = B;
        copyBoard = new Board(B);
        board.addBoardUpdateListener(e->{
            updateCopyBoard();
        });
        setBackground(Color.white);
        setFocusable(false);
    }

    public void updateCopyBoard(){
        redo = false;
        copyBoard = new Board(board);
    }

    public void undo(){
        if(!redo) {
            redo = true;
            Board temp = board;
            board = copyBoard;
            board.addBoardUpdateListener(e -> {
                updateCopyBoard();
            });
            copyBoard = temp;
            board.repaint();
            updateImage();
        }
    }

    public void redo(){
        if(redo){
            Board temp = board;
            board = copyBoard;
            board.addBoardUpdateListener(e->{
                updateCopyBoard();
            });
            copyBoard = temp;
            redo = false;
        }
        board.repaint();
        updateImage();
    }

    public void updateImage(){
        BufferedImage temp = new BufferedImage(board.getWidth(),board.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = temp.createGraphics();
        board.paintAll(g2d);
        thumbnail = temp.getScaledInstance(getParent().getWidth() * 4 / 5, (int) (getParent().getWidth() * 4 / 5 *Page.AspectrRatio), Image.SCALE_AREA_AVERAGING);
        ImageIcon icon = new ImageIcon(thumbnail);
        setIcon(icon);
    }

}