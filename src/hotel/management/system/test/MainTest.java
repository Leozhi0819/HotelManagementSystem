package hotel.management.system.test;

import hotel.management.system.constant.ViewCons;
import hotel.management.system.view.MainFrame;

import javax.swing.*;

/**
 * @author leozhi
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(ViewCons.METAL_STYLE);
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        LoginFrame loginFrame = new LoginFrame();
//        loginFrame.setVisible(true);
        new MainFrame().setVisible(true);
    }
}
