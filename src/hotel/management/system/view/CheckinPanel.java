package hotel.management.system.view;

import hotel.management.system.bean.CheckInInfo;
import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author leozhi
 */
public class CheckinPanel extends JPanel {
    private static JTabbedPane tabbedPane;
    private JLabel item1, item2, item3, item4, item5, item6, item7, item8;
    private static JLabel value1, value2, value3, value4, value5, value6, value7, value8;
    private static String checkedRoomNumber = "";
    public CheckinPanel() {
        this.setBounds(0, 0, 1600, 800);
        // 创建选项卡面板
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(360, 0, 1300, 740);
        tabbedPane.setBackground(Color.ORANGE);
        this.setLayout(null);
        this.add(tabbedPane);

        Font font1 = new Font("微软雅黑", Font.BOLD, 17);
        Font font2 = new Font("微软雅黑", Font.BOLD, 15);
        // 入住信息
        // 顾客姓名、房间单价、房间电话、房间状态、入住时间、已住时间、已交押金、应收金额
        JLabel roomTypeLabel = new JLabel("房型");
        roomTypeLabel.setBounds(30, 20, 200, 30);
        roomTypeLabel.setForeground(Color.RED);

        roomTypeLabel.setFont(font1);
        this.add(roomTypeLabel);
        String[] itemTitles = {"顾客姓名：", "房间单价：", "房间电话：","房间状态", "入住时间：", "已住时间：", "已交押金：", "应收金额："};
        JLabel[] titleLabels = {item1, item2, item3, item4, item5, item6, item7, item8};
        for (int i = 0; i < itemTitles.length; i++) {
            titleLabels[i] = new JLabel();
            titleLabels[i].setText(itemTitles[i]);
            titleLabels[i].setFont(font2);
            titleLabels[i].setBounds(30, 30 * i + 50, 130, 18);
            this.add(titleLabels[i]);
        }
        value1 = new JLabel();
        value2 = new JLabel();
        value3 = new JLabel();
        value4 = new JLabel();
        value5 = new JLabel();
        value6 = new JLabel();
        value7 = new JLabel();
        value8 = new JLabel();
        JLabel[] valueLabels = {value1, value2, value3, value4, value5, value6, value7, value8};
        for (int i = 0; i < valueLabels.length; i++) {
            valueLabels[i].setText("");
            valueLabels[i].setFont(font2);
            valueLabels[i].setBounds(120, 30 * i + 50, 190, 18);
            this.add(valueLabels[i]);
        }

        JButton checkinBtn = new JButton("办理入住");
        checkinBtn.setBounds(30, 360, 240, 30);
        checkinBtn.addActionListener(event -> {
            if (!"".equals(checkedRoomNumber)) {
                if (ViewCons.ROOM_STATUS[2].equals(value4.getText())) {
                    JOptionPane.showMessageDialog(null, "该房间已有顾客入住！");
                } else {
                    new CheckinDialog(checkedRoomNumber).setVisible(true);
                }
            }
        });
        this.add(checkinBtn);
        updatePaneInfo();
    }

    public static void updateCheckInInfo(CheckInInfo checkInInfo) {
        checkedRoomNumber = checkInInfo.getRoomInfo().getRoomNumber();
        value1.setText("");
        value2.setText(String.valueOf(checkInInfo.getRoomInfo().getPrice()));
        value3.setText(checkInInfo.getRoomInfo().getRoomPhone());
        value4.setText(checkInInfo.getRoomInfo().getRoomStatus());
        if (ViewCons.ROOM_STATUS[2].equals(checkInInfo.getRoomInfo().getRoomStatus())) {
            String sql = "SELECT customer_no,check_in_date,days FROM check_in_record WHERE room_number=" + checkInInfo.getRoomInfo().getRoomNumber();
            Object[][] res = SqlUtil.query(null, null, sql);
            for (Object[] row : res) {

            }
            value5.setText(String.valueOf(checkInInfo.getCheckInDate()));
            value6.setText(String.valueOf(checkInInfo.getDays()));
        }
        value7.setText("");
        value8.setText("");
    }

    public static void updatePaneInfo() {
        tabbedPane.removeAll();
        // 获取房型列表
        Object[][] roomTypeBody = SqlUtil.query(null, null, SqlCons.ROOM_TYPE_QUERY);
        String[] roomType = new String[roomTypeBody.length];
        for (int i = 0; i < roomTypeBody.length; i++) {
            roomType[i] = (String)roomTypeBody[i][0];
        }

        // 房型面板
        for (String s : roomType) {
            JPanel roomTypePanel = new RoomTypePanel(s);
            tabbedPane.addTab(s, roomTypePanel);
            // 创建流布局，间距50
            roomTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 50));
        }
    }
}
