package window.otherwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Start extends JFrame {
    private String openFilePath = null;                //打开的文件地址
    public Start (){
        setLayout(new FlowLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-getWidth()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //组件
        JButton open = new JButton("打开文件");
        JButton newfile = new JButton("新建");

        //给打开文件按钮添加事件
        newfile.addActionListener((e) -> {
            FileDialog fileDialog = new FileDialog(this, "选择要保存的位置", FileDialog.SAVE);
            fileDialog.setVisible(true);
            if(fileDialog.getFile() == null)
                return;
            openFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(openFilePath));
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        open.addActionListener((e) -> {
            FileDialog fileDialog = new FileDialog(this, "选择要打开的文件", FileDialog.LOAD);
            fileDialog.setVisible(true);
            // 用户选择的文件路径
            openFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            dispose();
        });

        //添加按钮到frame中
        add(open);
        add(newfile);
        addWindowListener(new WindowAdapter() {
        });

        //设置frame最佳大小并可见
        this.pack();
        setResizable(false);
        this.setVisible(true);
    }


    public String getOpenFilePath() {
        return openFilePath;
    }

}
