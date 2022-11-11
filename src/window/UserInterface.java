package window;

import window.area.InterfaceAbove;
import window.area.InterfaceLeft;
import window.area.InterfaceRight;
import window.area.part.Board;
import window.area.part.Page;
import window.area.part.UserMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UserInterface extends JFrame {
    private static final int MinWith = 400;
    private static final int MinHeight = 250;
    HashMap<String, JPanel> allGraph = new HashMap<>();

    public UserInterface() {
    }


    public UserInterface(String openFilepath) {
        //主界面格式设置
        setBounds(200, 200, MinWith * 2, MinHeight * 2);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);


        //菜单
        JMenuBar menuBar = new UserMenuBar();
        JMenu fileItem = new JMenu("文件");
        JMenu plotItem = new JMenu("绘图");
        JMenu insertItem = new JMenu("插入");
        JMenuItem createPage = new JMenuItem("创建幻灯片");
        menuBar.add(fileItem);
        menuBar.add(insertItem).add(createPage);
        menuBar.add(plotItem);
        setJMenuBar(menuBar);

        //三个中间容器
        {
        }
        //工具栏
        InterfaceAbove aboveArea = new InterfaceAbove();
        add(aboveArea);

        //操作窗口
        InterfaceRight rightArea = new InterfaceRight();
        add(rightArea);

        // 将副本传入以控制board
        aboveArea.setRightArea(rightArea);

        //滚动界面，装左侧预览小窗口
        InterfaceLeft leftArea = new InterfaceLeft();
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
        createPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Page page = leftArea.addPage(new Board(), leftArea.getPagesNum());
                page.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rightArea.updateBoard(page.board);
                    }
                });
                leftArea.rePaint();
                if(leftArea.getPagesNum() == 1){
                    rightArea.updateBoard(page.board);
                }

            }
        });


        //主界面设计


        setVisible(true);

    }
}
