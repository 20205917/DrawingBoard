package window.area;

import window.area.part.Board;
import window.area.part.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

//左侧滚动窗口
public class InterfaceLeft extends JScrollPane {
    ArrayList<Page> pages = new ArrayList<>();

    //当前页面，无选中为-1
    private int currentPage = -1;
    public JPanel leftPane;                 //内容条(装Board内容块)

    public InterfaceLeft() {
        super();
        leftPane = new JPanel();
        leftPane.setBackground(Color.gray);
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

        // 每隔一秒更新缩略图
        int delay = 1000;
        new Timer(delay, e -> {
            if(getCurrentPage() != null)
                getCurrentPage().updateImage();
        }).start();//创建一个时间计数器，每一秒触发一次
    }


    //对Pages的操作
    public int getPagesNum(){
        return pages.size();
    }

    public Page getCurrentPage() {
        if(currentPage < 0)
            return null;
        return pages.get(currentPage);
    }

    public void setShowPage(Page page){
        if(page ==null)
            currentPage = -1;
        for (int i =0;i<pages.size();i++)
            if(pages.get(i).equals(page))
                currentPage = i;
    }
    public Page getPage(int index){
        return pages.get(index);
    }

    // 增加页面(与主界面交互)
    public Page addPage(Board B, int index) {
        Page page = new Page(B);
        pages.add(index, page);
        page.setPreferredSize(new Dimension(leftPane.getWidth(),leftPane.getHeight()/4));
        leftPane.add(page);
        repaint();
        return page;
    }

    //删除幻灯片
    public Page deletePage(){
        if(currentPage == -1)
            return null;
        Page page = getCurrentPage();
        pages.remove(currentPage);
        if(currentPage == pages.size())
            currentPage--;
        leftPane.remove(page);
        //deploy();
        return page;
    }

    public void upPage(){
        if(currentPage <= 0)
            return;
        Page page = pages.get(currentPage);
        pages.remove(currentPage);
        pages.add(--currentPage,page);
        //重新排布小窗口顺序
        //leftPane.add();
    }

    public void downPage(){
        if(currentPage >= pages.size())
            return;
        Page page = pages.get(currentPage);
        pages.remove(currentPage);
        pages.add(++currentPage,page);
        //deploy();
    }
}

