package window.otherwindow;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {
    public ErrorDialog(String text){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel jLabel =  new JLabel(text);
        jLabel.setPreferredSize(new Dimension(100,100));
        add(jLabel);
        pack();
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-getWidth()/2);
    }

}
