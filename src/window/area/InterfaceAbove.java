package window.area;

import javax.swing.*;
import java.awt.*;

public class InterfaceAbove extends JPanel {
    private Insets inner = new Insets(2, 2, 2, 2);
    private Insets outer = new Insets(0, 2, 0, 2);

    public Insets getInner() {
        return inner;
    }

    public Insets getOuter() {
        return outer;
    }




    public static void addComponent(Container container, Component c, GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints) {
        gridBagLayout.setConstraints(c, gridBagConstraints);
        container.add(c);
    }

    public JPanel setToolsTable() {
        JPanel toolsTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        toolsTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        // 功能选择
        Button bPen = new Button("Pen");
        Button bText = new Button("Text");
        Button bRubber = new Button("Rubber");
        Button bMouse = new Button("Mouse");

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(toolsTable, bPen, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(toolsTable, bText, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(toolsTable, bRubber, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(toolsTable, bMouse, gbl, gbc);

        return toolsTable;
    }

    public JPanel setTextTable() {
        JPanel textTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        textTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        // 文字字体
        JComboBox<Object> cbTextFont = new JComboBox<>();

        // 文字字号
        JComboBox<Object> cbTextSize = new JComboBox<>();

        // 字形设置
        Button bThicken = new Button("Thicken");
        Button bIncline = new Button("Incline");
        Button bUnderline = new Button("Underline");

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        addComponent(textTable, cbTextFont, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(textTable, cbTextSize, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(textTable, bThicken, gbl, gbc);
        addComponent(textTable, bIncline, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(textTable, bUnderline, gbl, gbc);

        return textTable;
    }

    public JPanel setShapeTable() {
        JPanel shapeTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        shapeTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        Button bRect = new Button("Rect");
        Button bOval = new Button("Oval");
        Button bLine = new Button("Line");
        Button bCurve = new Button("Curve");

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(shapeTable, bRect, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(shapeTable, bOval, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(shapeTable, bLine, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(shapeTable, bCurve, gbl, gbc);

        return shapeTable;
    }

    public JPanel setColorTable() {
        JPanel colorTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        colorTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        // 颜色
        Button bBlack = new Button();
        bBlack.setBackground(Color.black);
        Button bGrey = new Button();
        bGrey.setBackground(Color.gray);
        Button bBrown = new Button();
        bBrown.setBackground(Color.darkGray);
        Button bRed = new Button();
        bRed.setBackground(Color.red);
        Button bOrange = new Button();
        bOrange.setBackground(Color.orange);
        Button bYellow = new Button();
        bYellow.setBackground(Color.yellow);
        Button bGreen = new Button();
        bGreen.setBackground(Color.green);
        Button bBlue = new Button();
        bBlue.setBackground(Color.blue);
        Button bPurple = new Button();
        bPurple.setBackground(Color.magenta);
        Button bMore = new Button("...");


        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(colorTable, bBlack, gbl, gbc);
        addComponent(colorTable, bGrey, gbl, gbc);
        addComponent(colorTable, bBrown, gbl, gbc);
        addComponent(colorTable, bRed, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(colorTable, bOrange, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(colorTable, bYellow, gbl, gbc);
        addComponent(colorTable, bGreen, gbl, gbc);
        addComponent(colorTable, bBlue, gbl, gbc);
        addComponent(colorTable, bPurple, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(colorTable, bMore, gbl, gbc);

        return colorTable;
    }

    public JPanel setSearchTable(){
        JPanel SearchTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        SearchTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        // 搜索框
        JTextField tfSearch = new JTextField();

        Button bConfirm = new Button("Confirm");
        Button bClear = new Button("Clear");

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(SearchTable, tfSearch, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(SearchTable, bClear, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(SearchTable, bConfirm, gbl, gbc);

        return SearchTable;
    }
    public InterfaceAbove() {
        //测试
        setBackground(Color.white);
        // 负责为上方工具栏提供控件
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel toolsTable = setToolsTable();
        JPanel textTable = setTextTable();
        JPanel shapeTable = setShapeTable();
        JPanel colorTable = setColorTable();
        JPanel searchTable = setSearchTable();

        // 粗细
        JComboBox<Object> cbThickness = new JComboBox<>();



        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = outer;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(this, toolsTable, gbl, gbc);
        addComponent(this, textTable, gbl, gbc);
        addComponent(this, shapeTable, gbl, gbc);
        addComponent(this, cbThickness, gbl, gbc);
        addComponent(this, colorTable, gbl, gbc);
        addComponent(this, searchTable, gbl, gbc);


    }


    public InterfaceAbove(int width, int height) {

    }

    public void setOuter(Insets outer) {
        this.outer = outer;
    }

    public void setInner(Insets inner) {
        this.inner = inner;
    }
}
