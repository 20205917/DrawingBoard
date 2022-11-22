import window.Management;
import window.UserInterface;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface(new Management());
        userInterface.setVisible(true);
    }
}
