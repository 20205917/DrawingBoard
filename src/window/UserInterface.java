package window;

import window.area.InterfaceAbove;
import window.area.InterfaceLeft;
import window.area.InterfaceRight;
import window.area.part.USerGlassPane;
import window.area.part.UserMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class UserInterface extends JFrame {
    private static final int MinWith = 400;
    private static final int MinHeight =250;
    HashMap<String,JPanel> allGraph = new HashMap<>();

    public UserInterface(){}
    public UserInterface(String openFilepath){
        //主界面格式设置
        setBounds(200,200, MinWith*2, MinHeight*2);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);


        //菜单
        JMenuBar menuBar = new UserMenuBar();
        JMenu fileItem = new JMenu("文件");
        JMenu plotItem = new JMenu("绘图");
        JMenu insertItem = new JMenu("插入");
        menuBar.add(fileItem).add(insertItem).add(plotItem);
        setJMenuBar(menuBar);

        //三个中间容器
        //工具栏
        JPanel aboveArea = new InterfaceAbove();
        add(aboveArea);

        //操作窗口
        JPanel rightArea = new InterfaceRight();
        add(rightArea);

        //滚动界面，装左侧预览小窗口
        InterfaceLeft leftArea = new InterfaceLeft();
        add(leftArea);

        //布局管理
        int x = getContentPane().getWidth();
        int y = getContentPane().getHeight();
        aboveArea.setBounds(0,0,x, (int) (y*0.2));
        leftArea.setBounds(0,(int) (y*0.2),(int)(x*0.25),(int) (y*0.8));
        rightArea.setBounds((int)(x*0.25),(int) (y*0.2),(int) (x*0.75), (int) (y*0.8));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int x = getContentPane().getWidth();
                int y = getContentPane().getHeight();
                aboveArea.setBounds(0,0,x, (int) (y*0.2));
                leftArea.setBounds(0,(int) (y*0.2),(int)(x*0.25),(int) (y*0.8));
                rightArea.setBounds((int)(x*0.25),(int) (y*0.2),(int) (x*0.75), (int) (y*0.8));
            }
        });
        {
            //测试
        }

        //主界面设计

        USerGlassPane uSerGlassPane = new USerGlassPane();
        setGlassPane(uSerGlassPane);
        setVisible(true);



    }

}
