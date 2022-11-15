package window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class Management {
    HashMap<String , UserInterface> openedSet = new HashMap<>();

    public Management(){

    }

    public void creatNewWindow(String path){
        synchronized (this){
            Management management = this;

            if(management.openedSet.containsKey(path)){
                JDialog  dialog = new ErrorDialog("该文件已经打开");
                dialog.setVisible(true);
            }
            //未打开创建线程以启动新窗口
            else {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        UserInterface userInterface = new UserInterface(management);
                        management.openedSet.put(path,userInterface);
                        userInterface.load(path, userInterface);
                        userInterface.setVisible(true);
                        // 关闭时将窗口信息从openSet中移除
                        userInterface.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                super.windowClosed(e);
                                management.openedSet.remove(path, userInterface);
                            }
                        });
                    }
                });
            }


        }
    }
    void openFile(){
        Start s = new Start();
        s.addWindowListener(new WindowAdapter() {
            String openFilePath;

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                openFilePath = s.getOpenFilePath();

                System.out.println(openFilePath);
                //openFileName = s.getOpenFileName();
                if(openFilePath == null) {
                    //文件打开失败
                }
                else if(!openedSet.containsKey(openFilePath) ){
                    //?未加文件类型判断
                    //文件未被打开
                    UserInterface userInterface = new UserInterface(new Management());
                    System.out.println(openFilePath);
                    userInterface.getMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            openFile();
                        }
                    });
                    openedSet.put(openFilePath,userInterface);
                    userInterface.setVisible(true);
                }
            }
        });
    }

    public boolean isOpenfile(String filepath){
        return openedSet.containsKey(filepath);
    }
}
