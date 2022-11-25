package window.area;

import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Slide extends JFrame {

    public Slide(Board[] boards) {
        setUndecorated(true);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        setLayout(null);

        JPanel mainPane = new JPanel();
        mainPane.setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        //卡片布局管理器
        CardLayout cardLayout = new CardLayout();
        mainPane.setLayout(cardLayout);
        for (int i=0;i<boards.length;i++)
            mainPane.add("第"+i+"z张",new MyPanel(boards[i]));

//        JButton back = new JButton("<-");
//        back.setContentAreaFilled(false);
//        back.setBorderPainted(false);
//        back.setBounds(0, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
//        add(back);
//
//        JButton forward = new JButton("->");
//        forward.setContentAreaFilled(false);
//        forward.setBorderPainted(false);
//        forward.setBounds(buttonSize, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
//        add(forward);
//
//        JButton quit = new JButton("x");
//        quit.setContentAreaFilled(false);
//        quit.setBorderPainted(false);
//        quit.setBounds(2 * buttonSize, getToolkit().getScreenSize().height - buttonSize, buttonSize, buttonSize);
//        add(quit);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.next(mainPane);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

            }
        });
        add(mainPane);
        setVisible(true);
    }
}

class MyPanel extends JPanel {
    public BufferedImage showImage;

    public MyPanel(Board board) {
        int w = board.getWidth();
        int h = board.getHeight();
        showImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = showImage.createGraphics();
        board.paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(showImage, 0, 0,getWidth(),getHeight(), null);
    }
}