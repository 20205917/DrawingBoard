import window.Start;
import window.UserInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class Management {
    HashMap<String , UserInterface> openedSet = new HashMap<>();

    public Management(){
        NewInterface();
    }

    void NewInterface(){
        Start s = new Start();
        s.addWindowListener(new WindowAdapter() {
            String openFilePath;
            //String openFileName;
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
                    UserInterface userInterface = new UserInterface(openFilePath);

                    openedSet.put(openFilePath,userInterface);
                    userInterface.setVisible(true);
                }
            }
        });
    }
}
