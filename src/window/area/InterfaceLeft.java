package window.area;

import window.Board;
import window.area.part.Boarder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

//左侧滚动窗口
public class InterfaceLeft extends JScrollPane {
    ArrayList<Boarder> boarders = new ArrayList<>();
    public JPanel leftPane;                 //内容条(装Board内容块)
    int ckeckedNum = 0;
    public InterfaceLeft() {
        super();
        leftPane = new JPanel();
        getViewport().setView(leftPane);
        //滚动页面
        addComponentListener(new ComponentAdapter() {
            //重设小窗口大小
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                leftPane.setPreferredSize(new Dimension(getWidth()/2,getHeight()*+2));
                deploy();
                repaint();
            }
        });

        //测试
        leftPane.setBackground(Color.green);
        leftPane.setLayout(null);
    }

    // 增加内容块
    public void addBoarder(Board B, int index) {
        Boarder boarder = new Boarder(B);
        boarders.add(index, boarder);
        add(boarder);
        deploy();
    }

    //重新排布小窗口顺序
    void deploy() {
        for (int i = 0; i < boarders.size(); i++)
            boarders.get(i).board.setBounds(0, i * getWidth(), getWidth(), getWidth());
    }

    //重绘
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}


