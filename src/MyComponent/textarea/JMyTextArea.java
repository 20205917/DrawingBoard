package MyComponent.textarea;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.font.TextAttribute;


public class JMyTextArea extends JTextArea implements MyComponent {
    //默认文字提示
    private static final String hintText = "请输入";

    public JMyTextArea(Font font,Color color){
        super(hintText);
        setOpaque(false);
        //设置设定字形
        setFont(font);
        //设置常态（未选中）时的颜色
        setDisabledTextColor(color);
        //移动变形所需监听器
        addListener();

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                //获取焦点时，清空提示内容
                if(getText().equals(hintText)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，没有输入内容，显示提示内容
                if(getText().equals(""))
                    setText(hintText);
            }
        });
    }
    public void resize(MyPoint A, MyPoint B){
        setBounds(Math.min(A.px,B.px),Math.min(A.py,B.py),Math.abs(A.px-B.px),Math.abs(A.py-B.py));}

    @Override
    public String save() {
        StringBuilder log =  new StringBuilder();
        log.append("TextArea").append(System.getProperty("line.separator"));
        log.append("color:").append(getDisabledTextColor().getRGB()).append(System.getProperty("line.separator"));
        log.append("TextStyle:").append(getFont().getAttributes().get(TextAttribute.FAMILY)).append(System.getProperty("line.separator"));
        log.append("TextSize:").append(getFont().getSize()).append(System.getProperty("line.separator"));
        log.append("IsBold:").append(getFont().isBold()).append(System.getProperty("line.separator"));
        log.append("IsItalic:").append(getFont().isItalic()).append(System.getProperty("line.separator"));
        String isUnderline;
        if(getFont().getAttributes().get(TextAttribute.UNDERLINE) != null)
            isUnderline = "true";
        else
            isUnderline = "false";
        log.append("IsUnderline:").append(isUnderline).append(System.getProperty("line.separator"));
        log.append("Text-contain:").append(getText()).append(System.getProperty("line.separator"));
        log.append(saveBounds());
        return log.toString();
    }

    @Override
    public MyComponent clone() {
        JMyTextArea clone = new JMyTextArea(this.getFont(), this.getCaretColor());
        clone.setBounds(getX(), getY(), getWidth(), getHeight());
        clone.setText(this.getText());
        return clone;
    }

}
