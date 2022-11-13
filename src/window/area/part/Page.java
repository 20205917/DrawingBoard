package window.area.part;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//小窗口包含白板和按钮
public class Page extends JButton {
    public static  double AspectrRatio = 3/4.0;
    public Board board;
    // 缩略图
    public Image thumbnail;
    public Page(Board B){
        board = B;
        setBackground(Color.white);
        setFocusable(false);
//        setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
//        setBorderPainted(false);//不打印边框
//        setBorder(null);//除去边框
//        setText(null);//除去按钮的默认名称
//        setFocusPainted(false);//除去焦点的框
//        setContentAreaFilled(false);//除去默认的背景填充
//        setText("点击");
    }

    public void updateImage(){
        BufferedImage temp = new BufferedImage(board.getWidth(),board.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = temp.createGraphics();
        board.paintAll(g2d);
        thumbnail = temp.getScaledInstance(getBounds().width, getBounds().height, Image.SCALE_AREA_AVERAGING);
        ImageIcon icon = new ImageIcon(thumbnail);
        setIcon(icon);
    }
}