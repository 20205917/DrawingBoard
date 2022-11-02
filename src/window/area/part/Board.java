package window.area.part;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane{
    HashMap<String,JComponent> jcomponentSet = new HashMap<>();
    public double AspectrRatio = 4/3.0;
    public Board(){
        //int theTop = 1;
        setLayout(null);
        //白色画板

        JPanel background = new JPanel();
        background.setSize(new Dimension(getWidth(),getHeight()));
        background.setBackground(Color.red);
        background.setSize(200,200);
        add(background,DEFAULT_LAYER,-1);
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
    public int getSpecificheight(int x,int y){
        return x>y*AspectrRatio? y: (int)(x/AspectrRatio);
    }
}
