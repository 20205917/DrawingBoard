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
    private JPanel myJPanel;
    public Slide(Board[] boards) {

        //窗口设置
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);

        myJPanel = new JPanel();
        add(myJPanel);
        myJPanel.setBackground(Color.gray);
        //防止抢占焦点
        myJPanel.setFocusable(false);
        myJPanel.setBounds(0,0,getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);

        //卡片布局管理器
        CardLayout cardLayout = new CardLayout();
        myJPanel.setLayout(cardLayout);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    Slide.this.dispose();
            }
        });
        if (boards.length==0) return;

        for (int i=0;i<boards.length;i++)
            myJPanel.add("第"+i+"张",new MyPanel(boards[i]));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1)
                    cardLayout.next(myJPanel);
            }
        });
    }

    void setShowPage(int i){
        ((CardLayout)myJPanel.getLayout()).show(myJPanel,"第"+i+"张");
    }
}

class MyPanel extends JPanel {
    public BufferedImage showImage;
    public MyPanel(Board board) {
        setBackground(Color.black);
        int w = board.getWidth();
        int h = board.getHeight();
        showImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = showImage.createGraphics();
        board.paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int w = getToolkit().getScreenSize().width;
        int h = getToolkit().getScreenSize().height;

        if(w*showImage.getHeight()>h*showImage.getWidth())
            g.drawImage(showImage, (w-h*showImage.getWidth()/showImage.getHeight())/2, 0,h*showImage.getWidth()/showImage.getHeight(),h, null);
        else
            g.drawImage(showImage,0,(h-w*showImage.getHeight()/showImage.getWidth())/2 , w,w*showImage.getHeight()/showImage.getWidth(), null);
    }
}