package window.area;

import MyComponent.myLine.MyPoint;
import window.part.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfaceRight extends JScrollPane {
    final JPanel rightPane;

    private Board board;
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
                    rightPane.setPreferredSize(new Dimension(board.getWidth(),board.getHeight()));
                    board.setBounds((rightPane.getWidth()-board.getWidth())/2,(rightPane.getHeight()-board.getHeight())/2,board.getWidth(),board.getHeight());
                }
                repaint();
            }
        });
        rightPane.setBackground(Color.LIGHT_GRAY);
    }

    // 当新建或切换page时，加入对应的board
    public void updateBoard(Board b){
        if(board != null)
            rightPane.remove(board);

        board = b;
        board.setLocation((rightPane.getWidth()-board.getWidth())/2,(rightPane.getHeight()-board.getHeight())/2);
        //更新初始鼠标截获面
        board.resetBoardGlassPane();

        //添加新画板
        rightPane.add(board);
        repaint();
    }

    public Board getBoard(){return board;}

    //改变Board大小
    public void resizeBoardSize(int w,int h){
        if(board == null)
            return;
        w = w > 0 ? w:board.getWidth();
        h = h > 0 ? h:board.getWidth();
        board.setBoardSize(w,h);
        board.setSize(w,h);
        board.setLocation((rightPane.getWidth()-board.getWidth())/2,(rightPane.getHeight()-board.getHeight())/2);
    }

    public MyPoint getBoardSize(){
        int w =0 ,h=0;
        if(board!=null){
            w = board.getWidth();
            h = board.getHeight();
        }
        return new MyPoint(w,h);
    }
}
