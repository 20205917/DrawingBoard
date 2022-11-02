package window.area;

import window.area.part.Board;
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
    //int ckeckedNum = 0;
    public InterfaceLeft() {
        super();
        leftPane = new JPanel();
        leftPane.setLayout(new FlowLayout());

        getViewport().setView(leftPane);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);           //水平不显示

        //滚动页面
        addComponentListener(new ComponentAdapter() {
            //重设小窗口大小
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,getWidth() / 10);
                leftPane.setLayout(fl);
                int hsum = 0;
                for (Boarder boarder : boarders){
                    int height = (int) (getWidth() * 4 / 5/boarder.board.AspectrRatio);
                    boarder.setPreferredSize(new Dimension(getWidth() * 4 / 5, height));
                    hsum+=height+10;
                }


                leftPane.setPreferredSize(new Dimension(getWidth(),hsum));

                repaint();
            }
        });

        //测试
        leftPane.setBackground(Color.green);

        Board []bs = new Board[10];
        for (int i = 0;i<10;i++){
            bs[i] = new Board();
            addBoarder(bs[i],i);
        }
    }

    // 增加内容块
    public void addBoarder(Board B, int index) {
        Boarder boarder = new Boarder(B);
        boarders.add(index, boarder);
        boarder.setPreferredSize(new Dimension(leftPane.getWidth(),leftPane.getHeight()/4));
        add(boarder);
        deploy();
    }

    //重新排布小窗口顺序
    void deploy() {
        for (Boarder boarder : boarders) leftPane.add(boarder);
            //boarders.get(i).set(0, i * getWidth(), getWidth(), getWidth());
    }

    //重绘
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

