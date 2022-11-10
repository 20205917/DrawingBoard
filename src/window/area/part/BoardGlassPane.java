package window.area.part;

import MyComponent.myGraph.JLine;
import MyComponent.myGraph.JOval;
import MyComponent.myGraph.JRect;
import MyComponent.myLine.JDrawLine;
import MyComponent.myLine.MyPoint;
import MyComponent.textarea.JMyTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardGlassPane extends JPanel implements MouseListener, MouseMotionListener {
    private Board board;

    private boolean isMake = false;
    private MyPoint mousePressedPoint;

    public BoardGlassPane(Board b){
        board = b;
        setLayout(null);
        setOpaque(false);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedPoint = new MyPoint(e.getX(),e.getY());
        switch (board.selection){
            //鼠标选择
            case 0->{
                 System.out.println("点到底部了");
            }
            case 1->{
                isMake = true;
                board.jDrawLines.add(new JDrawLine(board.drawLineColor,board.drawLineStroke));
                board.jDrawLines.get(board.jDrawLines.size()-1).drawLine(getGraphics());
            }
            case 2,3,4,5->{
                isMake = true;
                //创建图形
                JComponent component = switch (board.selection){
                    //矩形
                    case 2 -> new JRect(board.drawLineColor,board.drawLineStroke);
                    //圆形
                    case 3 -> new JOval(board.drawLineColor,board.drawLineStroke);
                    //线段
                    case 4 -> new JLine(board.drawLineColor,board.drawLineStroke);
                    //文本框
                    case 5 ->new JMyTextArea(board.drawLineColor,"宋体",Font.PLAIN,5);
                    default -> new JPanel();//错误情况
                };

                board.chooseGraph.resize(mousePressedPoint,mousePressedPoint);
                board.add((Component) board.chooseGraph,JLayeredPane.DEFAULT_LAYER,0);
                for (int i=0;i<100;i++){
                    if(!board.GraphSet.containsKey("图形"+i)){
                        board.GraphSet.put("图形"+i,board.chooseGraph);
                        break;}
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isMake){
                    isMake = false;
                    switch (board.selection){
                        case 1->{board.jDrawLines.get(board.jDrawLines.size()-1).drawLine(getGraphics());}
                        case 2,3,4->{}
                    }
                }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isMake){
            switch (board.selection){
                case 1->{
                    board.jDrawLines.get(board.jDrawLines.size()-1).addPoint(e.getX(),e.getY());
                    board.jDrawLines.get(board.jDrawLines.size()-1).drawLine(getGraphics()); }
                case 2,3,4->{
                    board.chooseGraph.resize(mousePressedPoint,new MyPoint(e.getX(),e.getY()));
                }
                default -> {System.out.println( "创建失败"+board.selection);}
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        board.dispatchEvent(SwingUtilities.convertMouseEvent((BoardGlassPane)e.getSource(),e,board));
    }




}
