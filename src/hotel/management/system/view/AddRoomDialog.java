package hotel.management.system.view;

import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;
import java.util.Objects;

/**
 * @author leozhi
 */
public class AddRoomDialog extends JDialog {
    private final JTextField roomNumber, wifiPassword;
    private final JComboBox<String> roomType;
    public AddRoomDialog(SettingsPanel jPanel) {
        this.setTitle(ViewCons.ADD_ROOM_BUTTON);
        this.setModal(true);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel roomNumberLabel = new JLabel("房间号：");
        roomNumberLabel.setBounds(100, 30, 80, 30);
        this.add(roomNumberLabel);
        roomNumber = new JTextField();
        roomNumber.setBounds(180, 30, 220, 30);
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
        this.add(roomType);

        JLabel wifiPasswordLabel = new JLabel("Wifi密码：");
        wifiPasswordLabel.setBounds(100, 130, 80, 30);
        this.add(wifiPasswordLabel);
        wifiPassword = new JTextField();
        wifiPassword.setBounds(180, 130, 220, 30);
        this.add(wifiPassword);

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 250, 100, 30);
        confirmBtn.addActionListener(e -> {
            if ("".equals(roomNumber.getText())) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
                String password = "".equals(wifiPassword.getText()) ? "12345678" : wifiPassword.getText();
                String roomTypes = Objects.requireNonNull(roomType.getSelectedItem()).toString();
                String[] column = {roomNumber.getText(), roomTypes, roomNumber.getText(), password};
                int[] type = {Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR};
                SqlUtil.update(column, type, SqlCons.ROOM_INFO_INSERT);
                jPanel.updateTableInfo();
                // 更新入住界面的选项面板
                CheckinPanel.updatePaneInfo();
                this.dispose();
            }
        });
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 250, 100, 30);
        cancelBtn.addActionListener(e -> this.dispose());
        this.add(confirmBtn);
        this.add(cancelBtn);
    }
}
