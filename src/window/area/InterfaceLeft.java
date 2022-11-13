package window.area;

import window.area.part.Board;
import window.area.part.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//左侧滚动窗口
public class InterfaceLeft extends JScrollPane {
    ArrayList<Page> pages = new ArrayList<>();

    public Page currentPage;
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
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10, getWidth() / 15);
                leftPane.setLayout(fl);

                //按钮
                for (Page page : pages)
                    page.setPreferredSize(new Dimension(getWidth() * 4 / 5, (int) (getWidth() * 4 / 5 *Page.AspectrRatio)));

                repaint();
            }
        });

        //测试
        leftPane.setBackground(Color.green);

        // 每隔一秒更新缩略图
        int delay = 1000;
        ActionListener draw =new ActionListener() {//创建一个监听事件
            public void actionPerformed(ActionEvent evt) {
                if(currentPage != null)
                    currentPage.updateImage();
            }

        };
        new Timer(delay,draw).start();//创建一个时间计数器，每一秒触发一次

//        Board []bs = new Board[10];
//        for (int i = 0;i<10;i++){
//            bs[i] = new Board();
//            addPage(bs[i],i);
//        }
    }

    // 增加页面(与主界面交互)
    public Page addPage(Board B, int index) {
        B.setBackground(Color.white);
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
        return page;
    }

    //重新排布小窗口顺序
    void deploy() {
        for (int i=0;i < pages.size();i++)
        {
            pages.get(i).setText(" ");
            leftPane.add(pages.get(i));
        }//pages.get(i).set(0, i * getWidth(), getWidth(), getWidth());
    }

    public int getPagesNum(){
        return pages.size();
    }

    public Page getPage(int index){
        return pages.get(index);
    }

    public void rePaint(){
        leftPane.setPreferredSize(new Dimension(getWidth(),(int) (pages.size()*(getWidth()  *Page.AspectrRatio))));
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,getWidth() / 15);
        leftPane.setLayout(fl);

        //按钮
        for (Page page : pages)
            page.setPreferredSize(new Dimension(getWidth() * 4 / 5, (int) (getWidth() * 4 / 5 *Page.AspectrRatio)));

        repaint();
    }

    //重绘
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

