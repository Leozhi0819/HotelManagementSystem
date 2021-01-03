package hotel.management.system.test;

import hotel.management.system.constant.ViewCons;
import hotel.management.system.view.MainFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

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
        InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 12));
//        LoginFrame loginFrame = new LoginFrame();
//        loginFrame.setVisible(true);
        new MainFrame().setVisible(true);

    }

    // 全局字体设置
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
