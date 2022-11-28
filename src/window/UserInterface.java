package window;

import window.area.*;
import window.part.Board;
import window.part.Page;
import window.toolbox.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.util.HashSet;

public class UserInterface extends JFrame {

    private static final int MinWith = 400;
    private static final int MinHeight = 250;
    //控制系统
    public Management ManagementSystem;
    //文件路径
    public String path;
    //菜单栏
    public InterfaceMenuBar menuBar;
    //工具栏
    public InterfaceAbove aboveArea;
    //操作窗口
    public InterfaceRight rightArea;
    //滚动界面，装左侧预览小窗口
    public InterfaceLeft leftArea;
    //画板集合
    HashSet<Board> allBoard = new HashSet<>();
    //工具盒
    public ToolBox toolBox = new ToolBox();

    public UserInterface(Management parent) {
        ManagementSystem = parent;
        //主界面格式设置
        setBounds(200, 200, MinWith * 2, MinHeight * 2);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        //菜单
        menuBar = new InterfaceMenuBar(this);

        //工具栏
        aboveArea = new InterfaceAbove(toolBox);
        add(aboveArea);

        //操作窗口
        rightArea = new InterfaceRight();
        add(rightArea);

        // 将副本传入以控制board
        aboveArea.setRightArea(rightArea);

        //滚动界面，装左侧预览小窗口
        leftArea = new InterfaceLeft();
        add(leftArea);


        //布局管理
        int x = getContentPane().getWidth();
        int y = getContentPane().getHeight();
        aboveArea.setBounds(0, 0, x, (int) (y * 0.2));
        leftArea.setBounds(0, (int) (y * 0.2), (int) (x * 0.25), (int) (y * 0.8));
        rightArea.setBounds((int) (x * 0.25), (int) (y * 0.2), (int) (x * 0.75), (int) (y * 0.8));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int x = getContentPane().getWidth();
                int y = getContentPane().getHeight();
                aboveArea.setBounds(0, 0, x, (int) (y * 0.2));
                leftArea.setBounds(0, (int) (y * 0.2), (int) (x * 0.25), (int) (y * 0.8));
                rightArea.setBounds((int) (x * 0.25), (int) (y * 0.2), (int) (x * 0.75), (int) (y * 0.8));
            }
        });
        //对工具箱经行监听，更新board状态
        toolBox.addToolBoxUpdateListener(e->{
            if(rightArea.getBoard()!=null){
                rightArea.getBoard().resetBoardGlassPane();
                if(rightArea.getBoard().getChooseGraph() != null){
                    leftArea.getCurrentPage().updateCopyBoard();
                    rightArea.getBoard().getChooseGraph().changeToolBox(toolBox);
                }
            }
        }

        );
        //主界面设计
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    public void addPage(Board board) {              //新建幻灯片
        if(leftArea.getPagesNum()>50)
            return;
        Page page = leftArea.addPage(board, leftArea.getPagesNum());
        allBoard.add(page.board);

        //点击切换监听器
        page.addActionListener(e1 -> {
            rightArea.updateBoard(page.board);
            leftArea.setCurrentPage(page);
        });

        //如果是最后一张，新增即刻选中
        if (leftArea.getPagesNum() == 1) {
            rightArea.updateBoard(page.board);
            leftArea.setCurrentPage(page);
        }
    }

    public void deletePage(){
        //幻灯片数量大于0时可删除
        if(leftArea.getPagesNum() <= 0) return;

        Page page = leftArea.deletePage();
        allBoard.remove(page.board);

        rightArea.updateBoard(leftArea.getCurrentPage().board);
    }

    public void upPage(){
        leftArea.upPage();
    }

    public void downPage(){
        leftArea.downPage();
    }

    public void save(String Path) {
        path = Path;
//        {
//            for (Board board : allBoard)
//                System.out.print(board.save());
//        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Board board : allBoard) {
                writer.write(board.save());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String filePath, UserInterface userInterface){
        path = filePath;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath)));
            String inData;
            StringBuilder allData = new StringBuilder();
            while ((inData = in.readLine()) != null)
                allData.append(inData).append('\n');
            // System.out.println(allData);
            if (allData.toString().equals("")) {
                return;
            }
            String[] boardsData = (allData.toString()).split("\n\n");

            for (String board : boardsData) {

                String[] boardData = board.split("#####\n");

                Board newBoard = new Board(boardData[0], toolBox);
                for (int i = 1; i < boardData.length; i++) {
                    newBoard.addGraphic(boardData[i]);
                }
                userInterface.addPage(newBoard);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String Path, UserInterface userInterface) {
        path = Path;
        setTitle(path.substring(path.lastIndexOf('\\') + 1, path.indexOf('.')));
        readFile(path, userInterface);
    }
}
