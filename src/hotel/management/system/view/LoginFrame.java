package hotel.management.system.view;

import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Types;

/**
 * @author leozhi
 * 登陆界面
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        super("系统登录");
        this.setBounds(0, 0, 700, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setLayout(null);

        // 创建默认用户 用户：admin 密码：admin
        Object[][] user = SqlUtil.query(null, null, SqlCons.USER_NAME_QUERY);
        if (user == null) {
            SqlUtil.update(null, null, SqlCons.USER_ADMIN_INSERT);
        }

        // 用户名
        JLabel userNameLabel = new JLabel(ViewCons.LOGIN_USER_NAME);
        userNameLabel.setBounds(100, 200, 100, 40);
        userNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        getContentPane().add(userNameLabel);

        JComboBox<String> userNameBox = new JComboBox<>();
        userNameBox.setBounds(200, 200, 400, 40);
        Object[][] userName = SqlUtil.query(null, null, SqlCons.USER_NAME_QUERY);
        assert userName != null;
        for (Object s : userName[0]) {
            userNameBox.addItem((String) s);
        }
        userNameBox.setFont(new Font("宋体", Font.BOLD, 18));
        getContentPane().add(userNameBox);

        // 密码
        JLabel userPasswordLabel = new JLabel(ViewCons.LOGIN_USER_PASSWORD);
        userPasswordLabel.setBounds(100, 280, 100, 40);
        userPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        getContentPane().add(userPasswordLabel);

        JPasswordField userPasswordField = new JPasswordField();
        userPasswordField.setBounds(200, 280, 400, 40);
        getContentPane().add(userPasswordField);

        // 登录按钮
        JButton loginBtn = new JButton(ViewCons.LOGIN_BUTTON);
        loginBtn.setBounds(160, 350, 120, 40);
        loginBtn.setFocusPainted(false);
        getContentPane().add(loginBtn);
        loginBtn.addActionListener(event -> {
            if (userPasswordField.getPassword().length == 0) {
                // 密码为空
                JOptionPane.showMessageDialog(null, ViewCons.LOGIN_NULL_PASSWORD);
            } else {
                String[] userInfo = new String[2];
                userInfo[0] = (String)userNameBox.getSelectedItem();
                userInfo[1] = String.valueOf(userPasswordField.getPassword());
                int[] dataType = new int[]{Types.CHAR, Types.CHAR};
                // 查询登录信息
                Object[][] res = SqlUtil.query(userInfo, dataType, SqlCons.USER_INFO_QUERY);
                if (res == null) {
                    // 密码错误警告，密码输入框初始化
                    JOptionPane.showMessageDialog(null, ViewCons.LOGIN_FAIL);
                    userPasswordField.setText("");
                } else {
                    String[] loginStatus = new String[]{SqlCons.LOGGED};
                    // 更新登录状态
                    SqlUtil.update(loginStatus, new int[]{Types.CHAR}, SqlCons.LOGIN_STATUS_UPDATE);
                    JOptionPane.showMessageDialog(null, ViewCons.LOGIN_SUCCESS);
                    // 创建主界面
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    dispose();
                }
            }
        });

        // 退出按钮
        JButton exitBtn = new JButton(ViewCons.LOGIN_EXIT_BUTTON);
        exitBtn.setBounds(440, 350, 120, 40);
        exitBtn.setFocusPainted(false);
        getContentPane().add(exitBtn);
        exitBtn.addActionListener(event -> {
            int exitResult = JOptionPane.showConfirmDialog(null, ViewCons.EXIT_CONFIRM_MESSAGE,
                    ViewCons.EXIT_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (exitResult == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, ViewCons.EXIT_SUCCESS_MESSAGE);
                System.exit(0);
            }
        });
    }
}
