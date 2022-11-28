package window.otherwindow;

import MyComponent.myLine.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class DesignPageDialog extends JDialog {
    JLabel widthLabel;
    JTextField widthText;
    JLabel heightLabel;
    JTextField heightText;
    JButton confirm;
    JButton cancel;
    public DesignPageDialog(JFrame father,MyPoint boardSize){
        super(father,true);
        setLayout(new FlowLayout(FlowLayout.CENTER,5,3));
        setSize(250,130);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-getWidth()/2);
        widthLabel = new JLabel("宽度:"+boardSize.px);widthLabel.setPreferredSize(new Dimension(90,25));add(widthLabel);
        widthText = new JTextField();widthText.setPreferredSize(new Dimension(90,25));add(widthText);
        heightLabel = new JLabel("高度:"+boardSize.py);heightLabel.setPreferredSize(new Dimension(90,25));add(heightLabel);
        heightText = new JTextField();heightText.setPreferredSize(new Dimension(90,25));add(heightText);
        confirm = new JButton("确认");confirm.setPreferredSize(new Dimension(90,25));add(confirm);
        cancel = new JButton("取消");cancel.setPreferredSize(new Dimension(90,25));add(cancel);

        //屏蔽掉非法输入
        widthText.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_DELETE &&( e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9))
                    e.consume();
            }});
        heightText.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_DELETE &&( e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9))
                    e.consume();
            }});

        cancel.addActionListener(e1 -> dispose());
        setResizable(false);
    }

    public void addConfimListener(ActionListener listener){
        confirm.addActionListener(listener);
    }
    public MyPoint getNewSize(){
        int w = -1,h = -1;
        if(!Objects.equals(widthText.getText(), ""))
            w = Math.min( 2000,Integer.parseInt(widthText.getText()));
        if(!Objects.equals(heightText.getText(), ""))
            h = Math.min( 2000,Integer.parseInt(heightText.getText()));
        return new MyPoint(w,h);
    }
}
