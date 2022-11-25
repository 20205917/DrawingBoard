package window.area;

import window.area.part.Board;
import window.area.part.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//左侧滚动窗口
public class InterfaceLeft extends JScrollPane {

    //当前页面，无选中为-1
    private Page currentPage = null;
    private final JPanel leftPane;                 //内容条(装Board内容块)

    public InterfaceLeft() {
        super();
        leftPane = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics g1 = g.create();
                g1.setFont(new Font("黑体", Font.BOLD, getWidth() / 10));
                int count = leftPane.getComponentCount();
                int size = g1.getFont().getSize();
                for (int i = 0; i < count; i++)
                    g1.drawString("" + (i + 1), 0, (int) (getWidth() * Page.AspectrRatio * (i + 0.2)));
                g1.dispose();
            }
        };
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
                leftPane.setPreferredSize(new Dimension(getWidth(), (int) (leftPane.getComponentCount() * (getWidth() * Page.AspectrRatio))));
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER, getWidth() / 5, (int) (getWidth() / 5 * Page.AspectrRatio));
                leftPane.setLayout(fl);

                //按钮
                for (Component page : leftPane.getComponents())
                    page.setPreferredSize(new Dimension(getWidth() * 4 / 5, (int) (getWidth() * 4 / 5 * Page.AspectrRatio)));

            }
        });

        // 每隔一秒更新缩略图
        int delay = 1000;
        new Timer(delay, e -> {
            if (getCurrentPage() != null)
                getCurrentPage().updateImage();
        }).start();//创建一个时间计数器，每一秒触发一次
    }


    //对Pages的操作
    public int getPagesNum() {
        return leftPane.getComponentCount();
    }

    // 增加幻灯片(与主界面交互)
    public Page addPage(Board B, int index) {

        Page page = new Page(B);
        page.setPreferredSize(new Dimension(getWidth() * 4 / 5, (int) (getWidth() * 4 / 5 * Page.AspectrRatio)));

        //滚动窗口调整
        leftPane.add(page, index);
        leftPane.setPreferredSize(new Dimension(getWidth(), (int) (leftPane.getComponentCount() * (getWidth() * Page.AspectrRatio))));

        revalidate();
        return page;
    }

    //删除幻灯片
    public Page deletePage() {
        if (currentPage == null)
            return null;

        Page page = currentPage;
        int index = leftPane.getComponentZOrder(page) - 1;

        //如果删除最后一张则指向前一张幻灯片
        if (index == -1) index++;
        setCurrentPage((Page) leftPane.getComponent(index));

        //滚动窗口调整
        leftPane.remove(page);
        leftPane.setPreferredSize(new Dimension(getWidth(), (int) (leftPane.getComponentCount() * (getWidth() * Page.AspectrRatio))));

        revalidate();
        return page;
    }

    public void upPage() {
        if (currentPage == null) return;

        //重新排布小窗口顺序
        leftPane.add(currentPage, leftPane.getComponentZOrder(currentPage) - 1);

        revalidate();
    }

    public void downPage() {
        if (currentPage == null) return;

        //重新排布小窗口顺序
        leftPane.add(currentPage, leftPane.getComponentZOrder(currentPage) + 1);

        revalidate();
    }


    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page page) {
        if (currentPage != null) {
            currentPage.setBorderPainted(false);
        }
        currentPage = page;
        currentPage.setBorderPainted(true);
    }

    public JPanel getLeftPane() {
        return leftPane;
    }

    public Board getSpecificBoard(int index){
        return ((Page)leftPane.getComponent(index)).board;
    }
    public Board[] getBoards() {
        Component[] pages = leftPane.getComponents();
        int size = leftPane.getComponentCount();
        Board[] boards = new Board[size];
        for (int i = 0; i < size; i++) {
            boards[i] = ((Page)pages[i]).board;
        }
        return boards;
    }

}

