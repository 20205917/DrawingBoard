package window;

import window.area.InterfaceAbove;
import window.area.InterfaceLeft;
import window.area.InterfaceRight;
import window.area.part.UserMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class UserInterface extends JFrame {

    private static final int MinWith = 400;
    private static final int MinHeight = 250;
    //控制系统
    public Management ManagementSystem;
    //
    public String path;
    //菜单栏
    public UserMenuBar menuBar;
    //工具栏
    public InterfaceAbove aboveArea;
    //操作窗口
    public InterfaceRight rightArea;
    //滚动界面，装左侧预览小窗口
    public InterfaceLeft leftArea;
    //画板集合
    HashMap<String, JPanel> allGraph = new HashMap<>();




    public UserInterface(Management parent) {
        ManagementSystem = parent;
        //主界面格式设置
        setBounds(200, 200, MinWith * 2, MinHeight * 2);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        //菜单
        menuBar = new UserMenuBar(this);

        //工具栏
        aboveArea = new InterfaceAbove();
        add(aboveArea);

        //操作窗口
        rightArea = new InterfaceRight();
        add(rightArea);

        // 将副本传入以控制board
        aboveArea.setRightArea(rightArea);

        //滚动界面，装左侧预览小窗口
        leftArea = new InterfaceLeft();
        add(leftArea);

        //布局管理
        int x = getContentPane().getWidth();
        int y = getContentPane().getHeight();
        aboveArea.setBounds(0, 0, x, (int) (y * 0.2));
        leftArea.setBounds(0, (int) (y * 0.2), (int) (x * 0.25), (int) (y * 0.8));
        rightArea.setBounds((int) (x * 0.25), (int) (y * 0.2), (int) (x * 0.75), (int) (y * 0.8));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int x = getContentPane().getWidth();
                int y = getContentPane().getHeight();
                aboveArea.setBounds(0, 0, x, (int) (y * 0.2));
                leftArea.setBounds(0, (int) (y * 0.2), (int) (x * 0.25), (int) (y * 0.8));
                rightArea.setBounds((int) (x * 0.25), (int) (y * 0.2), (int) (x * 0.75), (int) (y * 0.8));
            }
        });

        // 菜单功能的实现
        // 创建新的page并添加到左侧



        //主界面设计
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public  void save(String Path){
        path = Path;

    }

    public  void Load(String Path){
        path = Path;
    }
}
