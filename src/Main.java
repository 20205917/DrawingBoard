import window.Management;
import window.UserInterface;

public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface(new Management());
        userInterface.setVisible(true);
    }
}
