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

        //窗口设置
        setBackground(Color.black);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    Slide.this.dispose();
            }
        });
        if (boards.length==0) return;

        //卡片布局管理器
        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);
        for (int i=0;i<boards.length;i++)
            add("第"+i+"张",new MyPanel(boards[i]));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1)
                    cardLayout.next(getContentPane());
            }
        });
    }

    void setShowPage(int i){
        ((CardLayout)getContentPane().getLayout()).show(getContentPane(),"第"+i+"张");
    }
}

class MyPanel extends JPanel {
    public BufferedImage showImage;
    float radio;
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
        if(getWidth()*showImage.getHeight()>getHeight()*showImage.getWidth())
            setPreferredSize(new Dimension(getWidth(),getHeight()*showImage.getWidth()/showImage.getHeight()));
        else
            setPreferredSize(new Dimension(getWidth()*showImage.getHeight()/showImage.getWidth(),getHeight()));
        g.drawImage(showImage, 0, 0,getPreferredSize().width,getPreferredSize().height, null);
    }
}