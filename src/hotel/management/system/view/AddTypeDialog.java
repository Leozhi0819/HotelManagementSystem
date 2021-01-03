package hotel.management.system.view;

import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;

/**
 * @author leozhi
 */
public class AddTypeDialog extends JDialog {
    private final JTextField roomType, roomPrice, bedCount;
    public AddTypeDialog(SettingsPanel jPanel) {
        this.setTitle(ViewCons.ADD_TYPE_BUTTON);
        this.setModal(true);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel roomTypeLabel = new JLabel("房间类型：");
        roomTypeLabel.setBounds(100, 30, 80, 30);
        this.add(roomTypeLabel);
        roomType = new JTextField();
        roomType.setBounds(180, 30, 220, 30);
        this.add(roomType);

        JLabel roomPriceLabel = new JLabel("预设单价：");
        roomPriceLabel.setBounds(100, 80, 80, 30);
        this.add(roomPriceLabel);
        roomPrice = new JTextField();
        roomPrice.setBounds(180, 80, 220, 30);
        this.add(roomPrice);

        JLabel bedCountLabel = new JLabel("床位数量：");
        bedCountLabel.setBounds(100, 130, 80, 30);
        this.add(bedCountLabel);
        bedCount = new JTextField();
        bedCount.setBounds(180, 130, 220, 30);
        this.add(bedCount);

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 200, 100, 30);
        confirmBtn.addActionListener(e -> {
            if ("".equals(roomType.getText()) || "".equals(roomPrice.getText())) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
                String[] column = {roomType.getText(), roomPrice.getText(), bedCount.getText()};
                int[] type = {Types.CHAR, Types.DOUBLE, Types.INTEGER};
                SqlUtil.update(column, type, SqlCons.ROOM_TYPE_INSERT);
                jPanel.updateTableInfo();
                // 更新入住界面的选项面板
                CheckinPanel.updatePaneInfo();
                this.dispose();
            }
        });
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(300, 200, 100, 30);
        cancelBtn.addActionListener(e -> this.dispose());
        this.add(confirmBtn);
        this.add(cancelBtn);
    }
}
