package hotel.management.system.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author leozhi
 */
public class ViewUtils {
    public JFrame createFrame(String title) {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        return frame;
    }

    public static JButton createToolButton(URL url, String btnText, int x) {
        ImageIcon icon = new ImageIcon(url);
        icon.setImage(icon.getImage().getScaledInstance(66, 66, Image.SCALE_DEFAULT));
        JButton button = new JButton(btnText, icon);
        button.setBounds(x, 10, 90, 100);
        // 设置按钮背景透明
        button.setContentAreaFilled(false);
        // 去除按钮边框
        button.setFocusPainted(false);
        // 文本水平居中，垂直居下
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // 设置字体
        button.setFont(new Font("微软雅黑", Font.BOLD, 12));
        return button;
    }

    public static JButton createRoomSettingsButton(URL url, String btnText, int x) {
        ImageIcon icon = null;
        if (url != null) {
            icon = new ImageIcon(url);
        }
        JButton button = new JButton(btnText, icon);
        button.setBounds(x, 560, 150, 25);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        return button;
    }
}
