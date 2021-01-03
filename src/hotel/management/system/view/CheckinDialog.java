package hotel.management.system.view;

import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;

/**
 * @author leozhi
 */
public class CheckinDialog extends JDialog {
    public CheckinDialog(String roomNumber) {
        this.setTitle(ViewCons.CHECKIN_DIALOG_TITLE);
        this.setModal(true);
        this.setSize(500, 360);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // 房间号
        JLabel roomNumberLabel1 = new JLabel("房间号：");
        roomNumberLabel1.setBounds(100, 30, 150, 30);
        this.add(roomNumberLabel1);
        JLabel roomNumberLabel2 = new JLabel(roomNumber);
        roomNumberLabel2.setBounds(250, 30, 150, 30);
        this.add(roomNumberLabel2);

        // 入住人
        JLabel customerNameLabel = new JLabel("入住人：");
        customerNameLabel.setBounds(100, 80, 150, 30);
        this.add(customerNameLabel);
        JTextField customerName = new JTextField();
        customerName.setBounds(250, 80, 150, 30);
        this.add(customerName);

        // 身份证
        JLabel customerNoLabel = new JLabel("身份证：");
        customerNoLabel.setBounds(100, 130, 150, 30);
        this.add(customerNoLabel);
        JTextField customerNo = new JTextField();
        customerNo.setBounds(250, 130, 150, 30);
        this.add(customerNo);

        // 联系方式
        JLabel customerPhoneLabel = new JLabel("联系方式：");
        customerPhoneLabel.setBounds(100, 180, 150, 30);
        this.add(customerPhoneLabel);
        JTextField customerPhone = new JTextField();
        customerPhone.setBounds(250, 180, 150, 30);
        this.add(customerPhone);

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 250, 100, 30);
        confirmBtn.addActionListener(e -> {
            String name = customerName.getText();
            String no = customerNo.getText();
            String phone = customerPhone.getText();
            if ("".equals(name) || "".equals(no) || "".equals(phone)) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
                String[] checkInRecordColumn = {roomNumber, no};
                int[] checkInRecordTypes = {Types.CHAR, Types.CHAR};
                String[] customerInfoColumn = {name, no, phone};
                int[] customerInfoTypes = {Types.CHAR, Types.CHAR, Types.CHAR};
                String updateStatusSql = "UPDATE room_info SET room_status='已入住' WHERE room_info.room_number=" + roomNumber;
                SqlUtil.update(checkInRecordColumn, checkInRecordTypes, SqlCons.CHECKED_IN_RECORD_INSERT);
                SqlUtil.update(customerInfoColumn, customerInfoTypes, SqlCons.CUSTOMER_INFO_INSERT);
                SqlUtil.update(null, null, updateStatusSql);
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
