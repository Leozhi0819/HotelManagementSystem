package hotel.management.system.view;

import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;

/**
 * @author leozhi
 */
public class BookingDialog extends JDialog {
    private final JTextField name,id_code,date;
    private final JComboBox<String> roomType;
    public BookingDialog(BookingPanel jPanel) {
        this.setTitle(ViewCons.BOOKING);
        this.setModal(true);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(100, 30, 80, 30);
        this.add(nameLabel);
        name = new JTextField();
        name.setBounds(180, 30, 220, 30);
        this.add(name);

        JLabel IDcardLabel = new JLabel("身份证号：");
        IDcardLabel.setBounds(100, 80, 80, 30);
        this.add(IDcardLabel);
        id_code = new JTextField();
        id_code.setBounds(180, 80, 220, 30);
        this.add(id_code);

        JLabel BookingDayLabel = new JLabel("预定日期：");
        BookingDayLabel.setBounds(100, 180, 80, 30);
        this.add(BookingDayLabel);
        date = new JTextField();
        date.setBounds(180, 180, 220, 30);
        this.add(date);

        JLabel roomTypeLabel = new JLabel("房间类型：");
        roomTypeLabel.setBounds(100, 130, 80, 30);
        this.add(roomTypeLabel);
        roomType = new JComboBox<>();
        roomType.setBounds(180, 130, 220, 30);
        Object[][] types = SqlUtil.query(null, null, SqlCons.ROOM_TYPE_QUERY);
        assert types != null;
        for (Object[] objects : types) {
            roomType.addItem((String) objects[0]);
        }
        this.add(roomType);


        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 250, 100, 30);
        confirmBtn.addActionListener(e -> {
            if ("".equals(name.getText())) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
//            	String roomTypes = Objects.requireNonNull(roomType.getSelectedItem()).toString();
                String[] column = {name.getText(),id_code.getText(),date.getText(),roomType.getSelectedItem().toString()};
                int[] type = {Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR};
                SqlUtil.update(column, type, SqlCons.BOOKING_INSERT);
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
}
