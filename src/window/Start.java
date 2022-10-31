package window;

import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {
    private String openFilePath;                //打开的文件地址
    private String openFileName;                //打开的文件名称
    public Start (){

        //组件
        Button bopen = new Button("打开文件");
        FileDialog d1 = new FileDialog(this, "选择需要加载的文件", FileDialog.LOAD);

        //给打开文件按钮添加事件

        bopen.addActionListener((e) -> {
            d1.setVisible(true);
            //打印用户选择的文件路径和名称
            openFilePath = d1.getDirectory();
            openFileName = d1.getFile();
            this.dispose();
        });

        //添加按钮到frame中
        this.add(bopen);

        //设置frame最佳大小并可见
        this.pack();
        this.setVisible(true);
    }




    public String getOpenFilePath() {
        return openFilePath+openFileName;
    }

}
