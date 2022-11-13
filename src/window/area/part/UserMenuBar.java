package window.area.part;

import window.ErrorDialog;
import window.UserInterface;

import javax.swing.*;
import java.awt.*;

public class UserMenuBar extends JMenuBar {
    public UserMenuBar(UserInterface parent) {
        parent.setJMenuBar(this);
        JMenu fileOption = new JMenu("文件");
        JMenuItem newFile = new JMenuItem("新建");
        JMenuItem openFile = new JMenuItem("打开");
        JMenuItem saveFile = new JMenuItem("保存");
        JMenuItem saveAsFile = new JMenuItem("另存为");
        fileOption.add(newFile);
        fileOption.add(openFile);
        fileOption.add(saveFile);
        fileOption.add(saveAsFile);


        JMenu plotItem = new JMenu("绘图");
        JMenu insertItem = new JMenu("插入");
        JMenuItem createPage = new JMenuItem("创建幻灯片");


        //
        newFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择要保存的位置", FileDialog.SAVE);
            fileDialog.setVisible(true);
            String openFilePath = fileDialog.getDirectory()+fileDialog.getFile();
            /*

                初始化配置文件

            */
            System.out.println(openFilePath);
            parent.ManagementSystem.creatNewWindow(openFilePath);
        });


        //打开其他文件
        openFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择要打开的文件", FileDialog.LOAD);
            fileDialog.setVisible(true);
            //用户选择的文件路径
            parent.ManagementSystem.creatNewWindow(fileDialog.getDirectory());
        });


        //保存
        saveFile.addActionListener(e -> parent.save(parent.path));


        //另存为
        saveAsFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择保存的路径", FileDialog.SAVE);
            fileDialog.setVisible(true);
            String openFilePath = fileDialog.getDirectory();
            if (parent.ManagementSystem.isOpenfile(openFilePath)) {
                JDialog jDialog = new ErrorDialog("文件已被占用无法覆盖");
                jDialog.setVisible(true);
            } else
                parent.save(openFilePath);
        });


        createPage.addActionListener(e -> {
            parent.addPage();
        });


        add(fileOption);
        add(insertItem).add(createPage);
        add(plotItem);
    }
}



