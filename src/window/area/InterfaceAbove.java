package window.area;

import window.area.part.Board;
import window.area.part.selects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.regex.Pattern;

public class InterfaceAbove extends JPanel {

    private InterfaceRight rightArea;

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

        bPen.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Pen);
        });

        bText.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Text);
        });

        bRubber.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Rubber);
        });

        bMouse.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Mouse);
        });

        return toolsTable;
    }

    public JPanel setTextTable() {
        JPanel textTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        textTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        // 文字字体
        String[] styleData = {"黑体", "宋体", "Times New Roman", "Arial"};
        JComboBox<String> cbTextFont = new JComboBox<>(styleData);
        cbTextFont.setSelectedItem(2);

        // 文字字号
        String[] sizeData = {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50"};
        JComboBox<String> cbTextSize = new JComboBox<>(sizeData);
        cbTextSize.setSelectedItem(4);

        // 字形设置
        Button bBold = new Button("Bold");
        Button bItalic = new Button("Italic");
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
        addComponent(textTable, bBold, gbl, gbc);
        addComponent(textTable, bItalic, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(textTable, bUnderline, gbl, gbc);
        cbTextFont.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbTextSize.getSelectedItem() != null && rightArea.board != null)
                    rightArea.board.setTextStyle((String) cbTextFont.getSelectedItem());
            }
        });

        cbTextSize.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbTextSize.getSelectedItem() != null && rightArea.board != null)
                    rightArea.board.setTextSize(Integer.parseInt((String) cbTextSize.getSelectedItem()));
            }
        });

        bBold.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setIsBold();
        });

        bItalic.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setIsItalic();
        });

        bUnderline.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setIsUnderline();
        });


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
        String[] shapes = {"Rect", "Oval", "Line", "Triangle", "Square", "IsoscelesLadder"};
        SearchComboBox cbSearch = new SearchComboBox(shapes);
//        JComboBox<Object> cbSearch = new JComboBox<>(shapes);
//        Button bMore = new Button("More");

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
        addComponent(shapeTable, cbSearch, gbl, gbc);

        bRect.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Rect);
        });

        bOval.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Oval);
        });

        bLine.addActionListener(e -> {
            if (rightArea.board != null)
                rightArea.board.setSelection(selects.Line);
        });

        cbSearch.addItemListener(new SearchComboBoxListener(cbSearch));


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
        Button bMore = new Button("More");


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

        setColorButtonListener(bBlack);
        setColorButtonListener(bGrey);
        setColorButtonListener(bBrown);
        setColorButtonListener(bRed);
        setColorButtonListener(bOrange);
        setColorButtonListener(bYellow);
        setColorButtonListener(bGreen);
        setColorButtonListener(bBlue);
        setColorButtonListener(bPurple);

        return colorTable;
    }

    public JPanel setSearchTable() {
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
        // JPanel searchTable = setSearchTable();

        // 粗细
        String[] thickness = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<Object> cbThickness = new JComboBox<>(thickness);
        cbThickness.setSelectedItem(0);
        cbThickness.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbThickness.getSelectedItem() != null && rightArea.board != null)
                    rightArea.board.setDrawLineStroke(Integer.parseInt((String) cbThickness.getSelectedItem()));
            }
        });


        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = outer;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(this, toolsTable, gbl, gbc);
        addComponent(this, textTable, gbl, gbc);
        addComponent(this, shapeTable, gbl, gbc);
        addComponent(this, cbThickness, gbl, gbc);
        addComponent(this, colorTable, gbl, gbc);
        //addComponent(this, searchTable, gbl, gbc);


    }


    public InterfaceAbove(int width, int height) {

    }

    public void setOuter(Insets outer) {
        this.outer = outer;
    }

    public void setInner(Insets inner) {
        this.inner = inner;
    }

    class ButtonEvent implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    class SearchComboBox extends JComboBox {
        private final ComboBoxModel allItems;

        public SearchComboBox(String[] items) {
            super(items);
            allItems = getModel();
            setEditable(true);
        }

        public SearchComboBox(JComboBox jcb) {
            super((ComboBoxModel) jcb);
            allItems = getModel();
            setEditable(true);
        }

        public String getText() {
            return ((JTextField) (getEditor().getEditorComponent())).getText();
        }

        public void setText(String s) {
            ((JTextField) (getEditor().getEditorComponent())).setText(s);
        }

        public Boolean isInItems(String input) {
            String toSearch = ".*" + input + ".*";
            for (int i = 0; i < getModel().getSize(); i++) {
                if (Pattern.matches(toSearch, (String) getModel().getElementAt(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    class SearchComboBoxListener implements KeyListener, ItemListener {

        private JTextField editor;
        private ComboBoxModel items;
        private SearchComboBox parent;

        public SearchComboBoxListener(SearchComboBox jcb) {
            parent = jcb;
            parent.addItemListener(this);
            items = jcb.getModel();
            editor = (JTextField) jcb.getEditor().getEditorComponent();
            editor.addKeyListener(this);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            char ch = e.getKeyChar();
            String input = editor.getText();
            showResult(input);
        }

        public void showResult(String input) {
            Vector<String> opts;
            opts = getResult(input);
            if (parent != null && opts != null) {
                items = new DefaultComboBoxModel(opts);
                parent.setModel(items);
                parent.showPopup();
                parent.setText(input);
            } else if (opts == null) {
                assert parent != null;
                items = parent.allItems;
                parent.setModel(items);
                parent.showPopup();
                parent.setText(input);
            }

        }

        public Vector<String> getResult(String input) {
            Vector<String> res = new Vector<>();
            String pattern = "^.*(?i)" + input + ".*$";
            for (int i = 0; i < parent.allItems.getSize(); i++) {
                Object itemObj = parent.allItems.getElementAt(i);
                if (itemObj != null) {
                    String item = itemObj.toString();
                    if (Pattern.matches(pattern, item))
                        res.add(item);
                }
            }
            return res;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // TODO
            }
        }
    }

    public void setColorButtonListener(Button b) {
        b.addActionListener(new ButtonEvent() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                super.actionPerformed(actionEvent);
                if (rightArea.board != null)
                    rightArea.board.setDrawLineColor(b.getBackground());
            }
        });
    }

    public void setRightArea(InterfaceRight rightArea) {
        this.rightArea = rightArea;
    }
}
