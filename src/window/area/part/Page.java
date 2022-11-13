package window.area.part;

import javax.swing.*;
import java.awt.image.BufferedImage;

//小窗口包含白板和按钮
public class Page extends JButton {
    public static  double AspectrRatio = 3/4.0;
    public Board board;
    // 缩略图
    public BufferedImage thumbnail;
    public Page(Board B){
        board = B;
//        jb.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
//        jb.setBorderPainted(false);//不打印边框
//        jb.setBorder(null);//除去边框
//        jb.setText(null);//除去按钮的默认名称
//        jb.setFocusPainted(false);//除去焦点的框
//        setContentAreaFilled(false);//除去默认的背景填充

    }
}