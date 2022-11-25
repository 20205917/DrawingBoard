package window.area;

import MyComponent.MyComponent;
import window.area.part.Board;
import window.area.part.Page;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Slide extends JFrame {
    public InterfaceLeft leftArea;
    // 当前页
    public Image current;
    public Image showImage;

    public Board board;

    // public JPanel slideImage;

    public int buttonSize = 50;

    public void setLeftArea(InterfaceLeft interfaceLeft) {
        leftArea = interfaceLeft;
    }

    public void play() {
        setUndecorated(true);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        setLayout(null);
        // board = leftArea.getSpecificBoard(0);
        board = new Board(leftArea.getSpecificBoard(0));
        MyPanel slideImage = new MyPanel();
        slideImage.setBoard(board);
        slideImage.setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        slideImage.setVisible(true);
        add(slideImage);

        JButton back = new JButton("<-");
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBounds(0, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
        add(back);

        JButton forward = new JButton("->");
        forward.setContentAreaFilled(false);
        forward.setBorderPainted(false);
        forward.setBounds(buttonSize, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
        add(forward);

        JButton quit = new JButton("x");
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setBounds(2 * buttonSize, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
        add(quit);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });

        setVisible(true);
    }


//    public void setSlideImage(Board board) {
//
//        BufferedImage temp = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//        Graphics2D g2d = (Graphics2D) slideImage.getGraphics();
//        g2d.drawImage(temp, 0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height, null);
//        slideImage.setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
//        slideImage.paintComponents(g2d);
//        slideImage.setVisible(true);
//        add(slideImage);
//        revalidate();
//    }

}

class MyPanel extends JPanel {
    public Board board;
    public Image showImage;

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage temp = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = (Graphics2D) temp.getGraphics();
        board.paintAll(g2d);
//        board.paintAll(g);

        //g.setClip(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);


        for (Component c : board.getComponents())
            System.out.println(c);
        showImage = temp.getScaledInstance(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height, Image.SCALE_AREA_AVERAGING);
        try {
            ImageIO.write(temp, "JPG", new File("d:/test.jpg"));
            System.out.println("OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.drawImage(showImage, 0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height, null);
    }
}