package window.area;

import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfaceRight extends JScrollPane {
    JPanel rightPane;

    Board board;
    public InterfaceRight() {
        super();
        rightPane = new JPanel();
        rightPane.setLayout(null);
        getViewport().setView(rightPane);



        //滚动页面
        addComponentListener(new ComponentAdapter() {
            //重设小窗口大小
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                //页面
                if(board!=null)   {

                    rightPane.setPreferredSize(new Dimension(board.getBwidth(),board.getBheight()));
                    board.setBounds((rightPane.getWidth()-board.getBwidth())/2,(rightPane.getHeight()-board.getBheight())/2,board.getBwidth(),board.getBheight());
                }

                repaint();
            }
        });

        rightPane.setBackground(Color.LIGHT_GRAY);
    }
}
