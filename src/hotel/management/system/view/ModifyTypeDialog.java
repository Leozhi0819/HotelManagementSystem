package hotel.management.system.view;

import hotel.management.system.bean.RoomType;
import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;

/**
 * @author leozhi
 */
public class ModifyTypeDialog extends JDialog {
    private final JTextField roomTypeText, roomPrice, bedCount;

    ModifyTypeDialog(SettingsPanel jPanel, RoomType roomType) {
        this.setTitle(ViewCons.MODIFY_TYPE_BUTTON);
        this.setModal(true);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        String roomTypeId = getRoomTypeId(roomType);

        JLabel roomTypeLabel = new JLabel("房间类型：");
        roomTypeLabel.setBounds(100, 30, 80, 30);
        this.add(roomTypeLabel);
        roomTypeText = new JTextField();
        roomTypeText.setBounds(180, 30, 220, 30);
        roomTypeText.setText(roomType.getRoomType());
        this.add(roomTypeText);

        JLabel roomPriceLabel = new JLabel("预设单价：");
        roomPriceLabel.setBounds(100, 80, 80, 30);
        this.add(roomPriceLabel);
        roomPrice = new JTextField();
        roomPrice.setBounds(180, 80, 220, 30);
        roomPrice.setText(String.valueOf(roomType.getRoomPrice()));
        this.add(roomPrice);

        JLabel bedCountLabel = new JLabel("床位数量：");
        bedCountLabel.setBounds(100, 130, 80, 30);
        this.add(bedCountLabel);
        bedCount = new JTextField();
        bedCount.setBounds(180, 130, 220, 30);
        bedCount.setText(String.valueOf(roomType.getBedCount()));
        this.add(bedCount);

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 200, 100, 30);
        confirmBtn.addActionListener(e -> {
            String[] column = {roomTypeText.getText(), roomPrice.getText(), bedCount.getText(), roomTypeId};
            int[] type = {Types.CHAR, Types.DOUBLE, Types.INTEGER, Types.INTEGER};
            SqlUtil.update(column, type, SqlCons.ROOM_TYPE_UPDATE);
            jPanel.updateTableInfo();
            // 更新入住界面的选项面板
            CheckinPanel.updatePaneInfo();
            this.dispose();
        });
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 200, 100, 30);
        cancelBtn.addActionListener(e -> this.dispose());
        this.add(confirmBtn);
        this.add(cancelBtn);
    }

    /**
     * 获取类型id
     */
    private String getRoomTypeId(RoomType roomType) {
        String[] column = new String[]{roomType.getRoomType()};
        Object[][] objects = SqlUtil.query(column, new int[]{Types.CHAR}, SqlCons.ROOM_TYPE_ID_QUERY);
        assert objects != null;
        return objects[0][0].toString();
    }
}
