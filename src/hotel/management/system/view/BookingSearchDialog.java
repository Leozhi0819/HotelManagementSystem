package hotel.management.system.view;

import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.sql.Types;

/**
 * @author leozhi
 */
public class BookingSearchDialog extends JDialog {
    private final JTextField name,id_code,date,roomtype;
    public BookingSearchDialog(BookingPanel jPanel) {
        this.id_code = new JTextField();
		this.date = new JTextField();
		this.roomtype = new JTextField();
		this.setTitle(ViewCons.BOOKING_SEARCH_NAME);
        this.setModal(true);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(100, 80, 80, 30);
        this.add(nameLabel);
        name = new JTextField();
        name.setBounds(180, 80, 220, 30);
        this.add(name);

       

        JButton confirmBtn = new JButton("确定");
        confirmBtn.setBounds(100, 200, 100, 30);
        confirmBtn.addActionListener(e -> {
            if ("".equals(name.getText())) {
                JOptionPane.showMessageDialog(null, "输入数据不能为空！");
            } else {
            	String sql ="SELECT * FROM new_room_booking WHERE name=?";
//            	System.out.println(sql);
//             	System.out.println(name.getText());
            	String[] column = {name.getText()};
                int[] type = {Types.CHAR, Types.CHAR, Types.DATE, Types.CHAR};
                SqlUtil.query(column, type, sql);               
            	jPanel.updateTableInfo();
            	
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
