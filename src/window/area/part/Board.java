package window.area.part;

import JPanels.Jline.JDrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

enum choose{
    pen,
    text,
    rubber,
    mouse,
    rect,
    oval,
    line,
    curve
}
//单页画布
public class Board extends JLayeredPane{
    //图形集合
    HashMap<String,JComponent> jcomponentSet = new HashMap<>();
    //画图笔记集合
    ArrayList<JDrawLine> jDrawLines = new ArrayList<>();
    //大小
    private int Bwidth = 300;
    private int Bheight = 200;

    private int selection = 1;      //之后换枚举
    private boolean ismake = false;

    private Color drawLineColor = Color.black;
    private BasicStroke drawLineStroke = new BasicStroke(3);
    public Board(){
        //int theTop = 1;
        setLayout(null);
        //白色画板
        JPanel background = new JPanel();
        background.setSize(new Dimension(getWidth(),getHeight()));
        background.setBackground(Color.red);
        background.setSize(Bwidth,Bheight);
        add(background,DEFAULT_LAYER,-1);



        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(ismake){
                    switch (selection){
                        case 1->{
                            jDrawLines.get(jDrawLines.size()-1).addPoint(e.getX(),e.getY());
                            jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics());
                        }
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                switch (selection){
                    case 1:{
                        ismake = true;
                        jDrawLines.add(new JDrawLine(drawLineColor,drawLineStroke));
                        jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                ismake = false;
                jDrawLines.get(jDrawLines.size()-1).drawLine(getGraphics());
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(Bwidth,Bheight);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (JDrawLine jDrawLine : jDrawLines)
            jDrawLine.drawLine(g);
    }


    public String save(){
        StringBuffer logs = new StringBuffer();
        for (String s : jcomponentSet.keySet())
        {
            JComponent c = jcomponentSet.get(s);
            logs.append(s).append(getLayer(c)).append(getPosition(c));
        }
        return logs.toString();
    }



    public int getBheight() {
        return Bheight;
    }

    public void setBheight(int bheight) {
        Bheight = bheight;
    }

    public int getBwidth() {
        return Bwidth;
    }

    public void setBwidth(int bwidth) {
        Bwidth = bwidth;
    }
}
