package window.area.part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane{
    HashMap<String,JComponent> jcomponentSet = new HashMap<>();
    private int Bwidth = 300;
    private int Bheight = 200;
    public Board(){
        //int theTop = 1;
        setLayout(null);
        //白色画板

        JPanel background = new JPanel();
        background.setSize(new Dimension(getWidth(),getHeight()));
        background.setBackground(Color.red);
        background.setSize(600,600);
        add(background,DEFAULT_LAYER,-1);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(Bwidth,Bheight);
            }
        });
    }
    public boolean addComponent(String logs){
        return true;
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
