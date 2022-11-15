package window.area;

import window.ErrorDialog;
import window.UserInterface;
import window.area.part.Board;

import javax.swing.*;
import java.awt.*;

public class InterfaceMenuBar extends JMenuBar {
    public InterfaceMenuBar(UserInterface parent) {
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




        JMenu beginOption = new JMenu("开始");
        JMenuItem createPage = new JMenuItem("创建新幻灯片");
        JMenuItem deletePage = new JMenuItem("删除当期幻灯片");
        JMenuItem upPage = new JMenuItem("前移当前幻灯片");
        JMenuItem downPage = new JMenuItem("后移当前幻灯片");
        JMenuItem designPage = new JMenuItem("页面设计");
        beginOption.add(createPage);
        beginOption.add(deletePage);
        beginOption.addSeparator();
        beginOption.add(upPage);
        beginOption.add(downPage);
        beginOption.addSeparator();
        beginOption.add(designPage);

        JMenu plotItem = new JMenu("绘图");

        // 创建新文件
        newFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择要保存的位置", FileDialog.SAVE);
            fileDialog.setVisible(true);
            if(fileDialog.getFile() == null){
                return;
            }
            String openFilePath = fileDialog.getDirectory()+fileDialog.getFile();
            /*

                初始化配置文件

            */
            System.out.println(openFilePath);
            parent.ManagementSystem.creatNewWindow(openFilePath);

        });


        // 打开其他文件
        openFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择要打开的文件", FileDialog.LOAD);
            fileDialog.setVisible(true);
            // 用户选择的文件路径
            String openFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            parent.ManagementSystem.creatNewWindow(openFilePath);
            // parent.readFile(openFilePath);

        });


        //保存
        saveFile.addActionListener(e -> parent.save(parent.path));


        //另存为
        saveAsFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择保存的路径", FileDialog.SAVE);
            fileDialog.setVisible(true);
            String openFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            if (parent.ManagementSystem.isOpenfile(openFilePath)) {
                JDialog jDialog = new ErrorDialog("文件已被占用无法覆盖");
                jDialog.setVisible(true);
            } else{
                parent.save(openFilePath);
                System.out.println(openFilePath);
            }
        });


        createPage.addActionListener(e -> {
            parent.addPage(new Board());
        });
        deletePage.addActionListener(e ->{
            parent.deletePage();
        });

        upPage.addActionListener(e ->{
            parent.upPage();
        });
        downPage.addActionListener(e ->{
            parent.downPage();
        });

        designPage.addActionListener(e ->{

        });

        add(fileOption);
        add(beginOption);
        add(plotItem);
    }
}



