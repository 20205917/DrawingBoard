package window.area;

import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfaceRight extends JScrollPane {
    JPanel rightPane;

    public Board board;
    public InterfaceRight() {
        super();
        rightPane = new JPanel();
        rightPane.setLayout(null);
        getViewport().setView(rightPane);


        board.setBounds(50,70,400,400);
        rightPane.add(board);

        //滚动页面
        addComponentListener(new ComponentAdapter() {
            //重设小窗口大小
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                //页面
                if(board!=null)   {

                    rightPane.setPreferredSize(new Dimension(board.getWidth(),board.getHeight()));
                    board.setBounds((rightPane.getWidth()-board.getWidth())/2,(rightPane.getHeight()-board.getHeight())/2,board.getWidth(),board.getHeight());
                }

                repaint();
            }
        });

        rightPane.setBackground(Color.LIGHT_GRAY);
    }
}
