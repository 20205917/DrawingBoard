package window.area;

import window.area.part.Board;
import window.area.part.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//左侧滚动窗口
public class InterfaceLeft extends JScrollPane {
    ArrayList<Page> pages = new ArrayList<>();
    public JPanel leftPane;                 //内容条(装Board内容块)

    public InterfaceLeft() {
        super();
        leftPane = new JPanel();
        leftPane.setLayout(new FlowLayout());

        getViewport().setView(leftPane);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);           //水平不显示

        //滚动页面
        addComponentListener(new ComponentAdapter() {
            //重设小窗口大小
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                //页面
                leftPane.setPreferredSize(new Dimension(getWidth(),(int) (pages.size()*(getWidth()  *Page.AspectrRatio))));
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,getWidth() / 15);
                leftPane.setLayout(fl);

                //按钮
                for (Page page : pages)
                    page.setPreferredSize(new Dimension(getWidth() * 4 / 5, (int) (getWidth() * 4 / 5 *Page.AspectrRatio)));

                repaint();
            }
        });

        //测试
        leftPane.setBackground(Color.green);

        Board []bs = new Board[10];
        for (int i = 0;i<10;i++){
            bs[i] = new Board();
            addPage(bs[i],i);
        }
    }

    // 增加页面(与主界面交互)
    public void addPage(Board B, int index) {
        Page page = new Page(B);
        pages.add(index, page);
        page.setPreferredSize(new Dimension(leftPane.getWidth(),leftPane.getHeight()/4));
        leftPane.add(page);

        page.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        deploy();
    }

    //重新排布小窗口顺序
    void deploy() {
        for (int i=0;i < pages.size();i++)
        {
            pages.get(i).setText(" "+i);
            leftPane.add(pages.get(i));
        }//pages.get(i).set(0, i * getWidth(), getWidth(), getWidth());
    }

    //重绘
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

