package window.area.part;

import MyComponent.MyComponent;
import MyComponent.myLine.JDrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;


//单页画布
public class Board extends JLayeredPane{
    //图形集合
    HashMap<String,MyComponent> GraphSet = new HashMap<>();
    //画图笔轨迹集合
    public ArrayList<JDrawLine> jDrawLines = new ArrayList<>();

    //控制图形创建
    protected int selection = 3;      //之后换枚举

    //图形颜色
    protected Color drawLineColor = Color.blue;
    protected BasicStroke drawLineStroke = new BasicStroke(3);


    //当前图形
    public MyComponent chooseGraph;


    public Board(){
        setLayout(null);
        //白色画板
        JPanel background = new JPanel();
        background.setBackground(Color.white);
        add(background,DEFAULT_LAYER,0);


        //处理生成图形时，截获鼠标事件
        BoardGlassPane boardGlassPane = new BoardGlassPane(this);
        //一般情况不截取
        add(boardGlassPane,FRAME_CONTENT_LAYER,0);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.setSize(getWidth(),getHeight());
                boardGlassPane.setSize(getWidth(),getHeight());
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
        StringBuilder logs = new StringBuilder();

        return logs.toString();
    }
}

/*提醒
selection为鼠标时应当设置GlassPane为顶部
 */