package hotel.management.system.view;

import hotel.management.system.bean.RoomInfo;
import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;
import java.util.Objects;

/**
 * @author leozhi
 */
public class ModifyRoomDialog extends JDialog {
    private final JTextField roomNumber, wifiPassword;
    private final JComboBox<String> roomType, roomStatus;
    ModifyRoomDialog(SettingsPanel jPanel, RoomInfo roomInfo) {
        this.setTitle(ViewCons.MODIFY_ROOM_BUTTON);
        this.setModal(true);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        String roomId = getRoomId(roomInfo);

        JLabel roomNumberLabel = new JLabel("房间号：");
        roomNumberLabel.setBounds(100, 30, 80, 30);
        this.add(roomNumberLabel);
        roomNumber = new JTextField();
        roomNumber.setBounds(180, 30, 220, 30);
        roomNumber.setText(roomInfo.getRoomNumber());
        this.add(roomNumber);

        JLabel roomTypeLabel = new JLabel("房间类型：");
        roomTypeLabel.setBounds(100, 80, 80, 30);
        this.add(roomTypeLabel);
        roomType = new JComboBox<>();
        roomType.setBounds(180, 80, 220, 30);
        Object[][] types = SqlUtil.query(null, null, SqlCons.ROOM_TYPE_QUERY);
        assert types != null;
        for (Object[] objects : types) {
            roomType.addItem((String) objects[0]);
        }
        roomType.setSelectedItem(roomInfo.getRoomType());
        this.add(roomType);

        JLabel roomStatusLabel = new JLabel("房间状态：");
        roomStatusLabel.setBounds(100, 130, 80, 30);
        this.add(roomStatusLabel);
        roomStatus = new JComboBox<>();
        roomStatus.setBounds(180, 130, 220, 30);
        for (String status : ViewCons.ROOM_STATUS) {
            roomStatus.addItem(status);
        }
        roomStatus.setSelectedItem(roomInfo.getRoomStatus());
        this.add(roomStatus);

        JLabel wifiPasswordLabel = new JLabel("Wifi密码：");
        wifiPasswordLabel.setBounds(100, 180, 80, 30);
        this.add(wifiPasswordLabel);
        wifiPassword = new JTextField();
        wifiPassword.setBounds(180, 180, 220, 30);
        wifiPassword.setText(roomInfo.getWifiPassword());
        this.add(wifiPassword);

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 250, 100, 30);
        confirmBtn.addActionListener(e -> {
            if ("".equals(roomNumber.getText())) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
                String password = "".equals(wifiPassword.getText()) ? "12345678" : wifiPassword.getText();
                String status = Objects.requireNonNull(roomStatus.getSelectedItem()).toString();
                String roomTypes = Objects.requireNonNull(roomType.getSelectedItem()).toString();
                String[] column = {roomNumber.getText(), roomTypes, status, roomNumber.getText(), password, roomId};
                int[] type = {Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.INTEGER};
                SqlUtil.update(column, type, SqlCons.ROOM_INFO_UPDATE);
                jPanel.updateTableInfo();
                this.dispose();
            }
        });
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 250, 100, 30);
        cancelBtn.addActionListener(e -> this.dispose());
        this.add(confirmBtn);
        this.add(cancelBtn);
    }

    /**
     * 获取房间 id
     */
    private String getRoomId(RoomInfo roomInfo) {
        String[] column = new String[]{roomInfo.getRoomNumber()};
        Object[][] objects = SqlUtil.query(column, new int[]{Types.CHAR}, SqlCons.ROOM_ID_QUERY);
        assert objects != null;
        return objects[0][0].toString();
    }
}
