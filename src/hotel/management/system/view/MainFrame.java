package hotel.management.system.view;

import hotel.management.system.constant.ViewConstant;
import hotel.management.system.util.SqlUtil;
import hotel.management.system.util.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leozhi
 */
public class MainFrame extends JFrame {
    static JPanel containerPanel;
    static CardLayout cardLayout;
    public MainFrame() {
        super("宾馆客房管理系统");
        // 设置窗口大小
        this.setBounds(0, 0, 1600, 900);
        // 窗口居中显示
        this.setLocationRelativeTo(null);
        // 禁止改变窗口大小
        this.setResizable(false);
        getContentPane().setLayout(null);
        // 关闭按钮操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        containerPanel = new JPanel();
        containerPanel.setBounds(0, 120, 1600, 800);
        cardLayout = new CardLayout();
        containerPanel.setLayout(cardLayout);
        this.add(containerPanel);

        initToolbar();
        initTimer();

        BookingPanel bookingPanel = new BookingPanel();
        CheckinPanel checkinPanel = new CheckinPanel();
        CheckoutPanel checkoutPanel = new CheckoutPanel();
        InquirePanel inquirePanel = new InquirePanel();
        TurnoverPanel turnoverPanel = new TurnoverPanel();
        SettingsPanel settingsPanel = new SettingsPanel();
        containerPanel.add(bookingPanel, "booking");
        containerPanel.add(checkinPanel, "checkin");
        containerPanel.add(checkoutPanel, "checkout");
        containerPanel.add(inquirePanel, "inquire");
        containerPanel.add(turnoverPanel, "turnover");
        containerPanel.add(settingsPanel, "settings");
    }

    /**
     * 初始化工具栏
     */
    private void initToolbar() {
        // 客房预订
        URL bookingUrl = this.getClass().getResource(ViewConstant.BOOKING_ICON_PATH);
        JButton bookingBtn = ViewUtils.createToolButton(bookingUrl, ViewConstant.BOOKING_BUTTON_TEXT, 10);
        getContentPane().add(bookingBtn);
        bookingBtn.addActionListener(event -> cardLayout.show(containerPanel, "booking"));
        // 住宿管理
        URL checkinUrl = this.getClass().getResource(ViewConstant.CHECKIN_ICON_PATH);
        JButton checkinBtn = ViewUtils.createToolButton(checkinUrl, ViewConstant.CHECKIN_BUTTON_TEXT, 115);
        getContentPane().add(checkinBtn);
        checkinBtn.addActionListener(event -> cardLayout.show(containerPanel, "checkin"));
        // 退房结帐
        URL checkoutUrl = this.getClass().getResource(ViewConstant.CHECKOUT_ICON_PATH);
        JButton checkoutBtn = ViewUtils.createToolButton(checkoutUrl, ViewConstant.CHECKOUT_BUTTON_TEXT, 220);
        getContentPane().add(checkoutBtn);
        checkoutBtn.addActionListener(event -> cardLayout.show(containerPanel, "checkout"));
        // 客户查询
        URL inquireUrl = this.getClass().getResource(ViewConstant.INQUIRE_ICON_PATH);
        JButton inquireBtn = ViewUtils.createToolButton(inquireUrl, ViewConstant.INQUIRE_BUTTON_TEXT, 325);
        getContentPane().add(inquireBtn);
        inquireBtn.addActionListener(event -> cardLayout.show(containerPanel, "inquire"));
        // 营业统计
        URL turnoverUrl = this.getClass().getResource(ViewConstant.TURNOVER_ICON_PATH);
        JButton turnoverBtn = ViewUtils.createToolButton(turnoverUrl, ViewConstant.TURNOVER_BUTTON_TEXT, 430);
        getContentPane().add(turnoverBtn);
        turnoverBtn.addActionListener(event -> cardLayout.show(containerPanel, "turnover"));
        // 系统设置
        URL settingsUrl = this.getClass().getResource(ViewConstant.SETTINGS_ICON_PATH);
        JButton settingsBtn = ViewUtils.createToolButton(settingsUrl, ViewConstant.SETTINGS_BUTTON_TEXT, 535);
        getContentPane().add(settingsBtn);
        settingsBtn.addActionListener(event -> cardLayout.show(containerPanel, "settings"));
        // 关于我们
        URL aboutUrl = this.getClass().getResource(ViewConstant.ABOUT_ICON_PATH);
        JButton aboutBtn = ViewUtils.createToolButton(aboutUrl, ViewConstant.ABOUT_BUTTON_TEXT, 640);
        getContentPane().add(aboutBtn);
        aboutBtn.addActionListener(event -> {
            // TODO 添加按钮点击事件
            System.out.println(ViewConstant.ABOUT_BUTTON_TEXT);
        });
        // 退出系统
        URL exitUrl = this.getClass().getResource(ViewConstant.EXIT_ICON_PATH);
        JButton exitBtn = ViewUtils.createToolButton(exitUrl, ViewConstant.EXIT_BUTTON_TEXT, 745);
        getContentPane().add(exitBtn);
        exitBtn.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(null, ViewConstant.EXIT_CONFIRM_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String sql = "UPDATE login_status SET status='未登录' WHERE user_id=1;";
                SqlUtil.update(null, null, sql);
                JOptionPane.showMessageDialog(null, ViewConstant.EXIT_SUCCESS_MESSAGE);
                System.exit(0);
            }
        });
    }

    /**
     * 初始化时间显示栏
     */
    private void initTimer() {
        JLabel time = new JLabel();
        time.setForeground(Color.decode("#7784BD"));
        time.setBounds(1000, 10, 600, 100);
        time.setFont(new Font("微软雅黑", Font.BOLD, 40));
        getContentPane().add(time);
        this.setTimer(time);
    }

    private void setTimer(JLabel time) {
        final JLabel varTime = time;
        Timer timeAction = new Timer(100, e -> {
            long timeMillis = System.currentTimeMillis();
            // 转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            varTime.setText(simpleDateFormat.format(new Date(timeMillis)));
        });
        timeAction.start();
    }
}