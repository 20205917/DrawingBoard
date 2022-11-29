package window.area;

import MyComponent.myGraph.MyGraphType;
import MyComponent.SearchComboBox;
import window.part.selects;
import window.toolbox.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.regex.Pattern;

public class InterfaceAbove extends JPanel {
    ToolBox toolBox;
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

        bPen.addActionListener(e -> toolBox.setSelection(selects.Pen));

        bText.addActionListener(e -> toolBox.setSelection(selects.CreatTextArea));

        bRubber.addActionListener(e -> toolBox.setSelection(selects.Rubber));

        bMouse.addActionListener(e -> toolBox.setSelection(selects.Mouse));

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
                if (cbTextSize.getSelectedItem() != null )
                    toolBox.setTextStyle((String) cbTextFont.getSelectedItem());
            }
        });

        cbTextSize.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbTextSize.getSelectedItem() != null )
                    toolBox.setTextSize(Integer.parseInt((String) cbTextSize.getSelectedItem()));
            }
        });

        bBold.addActionListener(e -> toolBox.setIsBold());

        bItalic.addActionListener(e -> toolBox.setIsItalic());

        bUnderline.addActionListener(e -> toolBox.setIsUnderline());

        return textTable;
    }

    public JPanel setShapeTable() {
        JPanel shapeTable = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        shapeTable.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        Button bRect = new Button("Rect");
        Button bSquare = new Button("Square");
        Button bOval = new Button("Oval");
        JButton bHollow = new JButton("Hollow");
        bHollow.setBorder(BorderFactory.createLoweredBevelBorder());
        Button bCircle = new Button("Circle");
        String[] shapes = {"Rect", "Square", "Triangle", "IsoscelesLadder", "Oval", "Circle", "RoundRect", "Line"};
        SearchComboBox cbSearch = new SearchComboBox(shapes);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = inner;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addComponent(shapeTable, bRect, gbl, gbc);
        addComponent(shapeTable, bSquare, gbl, gbc);
        addComponent(shapeTable, bOval, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(shapeTable, bCircle, gbl, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        addComponent(shapeTable, bHollow, gbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addComponent(shapeTable, cbSearch, gbl, gbc);

        bRect.addActionListener(e -> {
            toolBox.setSelection(selects.CreatJGraph);
            toolBox.setGraphType(MyGraphType.Rect);
        });

        bOval.addActionListener(e -> {
            toolBox.setSelection(selects.CreatJGraph);
            toolBox.setGraphType(MyGraphType.Oval);
        });

        bHollow.addActionListener(e -> {
            toolBox.switchHollow();
            if(bHollow.getBorder() != BorderFactory.createLoweredBevelBorder())
                bHollow.setBorder(BorderFactory.createLoweredBevelBorder());
            else
                bHollow.setBorder(BorderFactory.createRaisedBevelBorder());
        });

        bSquare.addActionListener(e -> {
            toolBox.setSelection(selects.CreatJGraph);
            toolBox.setGraphType(MyGraphType.Square);
        });

        bCircle.addActionListener(e -> {
                toolBox.setSelection(selects.CreatJGraph);
                toolBox.setGraphType(MyGraphType.Circle);
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
        Button bMore = new Button();
        bMore.setBackground(Color.white);


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
        setColorButtonListener(bMore);

        return colorTable;
    }


    public InterfaceAbove(ToolBox toolBox) {
        //加载工具和
        this.toolBox = toolBox;
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
                if (cbThickness.getSelectedItem() != null)
                    toolBox.setDrawLineStroke(Integer.parseInt((String) cbThickness.getSelectedItem()));
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

    public void setOuter(Insets outer) {
        this.outer = outer;
    }

    public void setInner(Insets inner) {
        this.inner = inner;
    }


    class SearchComboBoxListener implements KeyListener, ItemListener {

        private final JTextField editor;
        private ComboBoxModel items;
        private final SearchComboBox parent;

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
                toolBox.setSelection(selects.CreatJGraph);
                toolBox.setGraphType(MyGraphType.valueOf((String) e.getItem()));
            }
        }
    }

    public void setColorButtonListener(Button b) {
        b.addActionListener(actionEvent -> toolBox.setDrawLineColor(b.getBackground()));
    }

    public void setRightArea(InterfaceRight rightArea) {
        this.rightArea = rightArea;
    }
}
