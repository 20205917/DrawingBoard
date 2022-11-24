package window.area;

import MyComponent.myGraph.MyGraphType;
import window.ErrorDialog;
import window.UserInterface;
import window.area.part.Board;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        JMenu polygon = new JMenu("多边形");
        JMenuItem triangle = new JMenuItem("三角形");
        JMenuItem rect = new JMenuItem("矩形");
        JMenuItem square = new JMenuItem("正方形");
        JMenuItem isoscelesLadder = new JMenuItem("梯形");
        JMenu nonPolygon = new JMenu("非多边形");
        JMenuItem circle = new JMenuItem("圆形");
        JMenuItem oval = new JMenuItem("椭圆形");
        JMenuItem roundRect = new JMenuItem("圆角矩形");
        JMenu linear = new JMenu("线形");
        JMenuItem line = new JMenuItem("线条");
        polygon.add(triangle);
        polygon.add(rect);
        polygon.add(square);
        polygon.add(isoscelesLadder);
        plotItem.add(polygon);
        nonPolygon.add(circle);
        nonPolygon.add(oval);
        nonPolygon.add(roundRect);
        plotItem.add(nonPolygon);
        linear.add(line);
        plotItem.add(linear);

        JMenu operatorOption = new JMenu("操作");
        JMenuItem copy = new JMenuItem("复制");
        JMenuItem paste = new JMenuItem("粘贴");
        JMenuItem delete = new JMenuItem("删除");
        operatorOption.add(copy);
        operatorOption.add(paste);
        operatorOption.add(delete);

        // 创建新文件
        newFile.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(parent, "选择要保存的位置", FileDialog.SAVE);
            fileDialog.setVisible(true);
            if(fileDialog.getFile() == null){
                return;
            }
            String openFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(openFilePath));
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


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
            if (parent.ManagementSystem.isOpenFile(openFilePath)) {
                JDialog jDialog = new ErrorDialog("文件已被占用无法覆盖");
                jDialog.setVisible(true);
            } else{
                parent.save(openFilePath);
                System.out.println(openFilePath);
            }
        });


        createPage.addActionListener(e -> {
            parent.addPage(new Board(parent.toolBox));
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

        // 绘制
        triangle.addActionListener(e -> {
            parent.toolBox.setGraphType(MyGraphType.Triangle);
        });

        square.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.Square);
        });

        rect.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.Rect);
        });

        isoscelesLadder.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.IsoscelesLadder);
        });

        circle.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.Circle);
        });

        oval.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.Oval);
        });

        roundRect.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.roundRect);
        });

        line.addActionListener(e->{
            parent.toolBox.setGraphType(MyGraphType.Line);
        });

        // 复制
        copy.addActionListener(e -> {
            parent.rightArea.board.copy();
        });
        // 粘贴
        paste.addActionListener(e -> {
            parent.rightArea.board.paste();
        });
        // 删除
        delete.addActionListener(e -> {
            parent.rightArea.board.delete();
        });


        add(fileOption);
        add(beginOption);
        add(plotItem);
        add(operatorOption);
    }
}



