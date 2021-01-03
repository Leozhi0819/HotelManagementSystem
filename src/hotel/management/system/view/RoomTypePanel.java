package hotel.management.system.view;

import hotel.management.system.bean.CheckInInfo;
import hotel.management.system.bean.CustomerInfo;
import hotel.management.system.bean.RoomInfo;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author leozhi
 */
public class RoomTypePanel extends JPanel {
    public RoomTypePanel(String typeName) {
        String roomInfoSql = "SELECT * FROM room_info INNER JOIN room_type ON room_info.room_type=room_type.type WHERE room_info.room_type='" + typeName +"'";
        Object[][] roomList = SqlUtil.query(null, null, roomInfoSql);
        ArrayList<CheckInInfo> roomInfoArrayList = getRoomInfo(roomList);

        ImageIcon imageIcon = null;
        if (roomInfoArrayList != null) {
            for (CheckInInfo checkInInfo : roomInfoArrayList) {
                switch (checkInInfo.getRoomInfo().getRoomStatus()) {
                    case "空闲":
                        imageIcon = new ImageIcon(this.getClass().getResource("img/icon/idle.gif"));
                        break;
                    case "已预订":
                        imageIcon = new ImageIcon(this.getClass().getResource("img/icon/reserved.gif"));
                        break;
                    case "已入住":
                        imageIcon = new ImageIcon(this.getClass().getResource("img/icon/checkingin.gif"));
                        break;
                    case "维护中":
                        imageIcon = new ImageIcon(this.getClass().getResource("img/icon/maintain.gif"));
                    default:
                }
                JButton btn = new JButton(checkInInfo.getRoomInfo().getRoomNumber(), imageIcon);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                // 设置按钮透明背景
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.addActionListener(event -> {
                    CheckinPanel.updateCheckInInfo(checkInInfo);
                });
                btn.setPreferredSize(new Dimension(80, 100));
                this.add(btn);
            }
        }
    }

    private ArrayList<CheckInInfo> getRoomInfo(Object[][] table) {
        if (table == null) {
            return null;
        }
        ArrayList<CheckInInfo> res = new ArrayList<>();
        for (Object[] objects : table) {
            CheckInInfo checkInInfo = new CheckInInfo();
            RoomInfo roomInfo = new RoomInfo();
            // id、房间号、房型、状态、电话、wifi、type_id、房型、价格、床数
            roomInfo.setRoomNumber(objects[1].toString());
            roomInfo.setRoomStatus(objects[3].toString());
            roomInfo.setRoomPhone(objects[4].toString());
            roomInfo.setWifiPassword(objects[5].toString());
            roomInfo.setPrice(Double.parseDouble((String) objects[8]));
            checkInInfo.setRoomInfo(roomInfo);
            res.add(checkInInfo);
            if (objects[3].toString().equals(ViewCons.ROOM_STATUS[2])) {
                String customerInfoSql = "SELECT * FROM customer_info INNER JOIN check_in_record ON customer_info.customer_no=check_in_record.customer_no WHERE room_number=" + objects[1].toString();
                Object[][] customerList = SqlUtil.query(null, null, customerInfoSql);
                getCustomerInfo(customerList, checkInInfo);
            }
            // 输出表 System.out.println(Arrays.toString(objects));
        }
        return res;
    }

    private void getCustomerInfo(Object[][] table, CheckInInfo checkInInfo) {
        if (table == null) {
            return;
        }
        for (Object[] objects : table) {
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setCustomerName(objects[1].toString());
            customerInfo.setCustomerNo(objects[2].toString());
            customerInfo.setCustomerPhone(objects[3].toString());
            checkInInfo.setCustomerInfo(customerInfo);
            checkInInfo.setCheckInDate(objects[6].toString());

            System.out.println(Arrays.toString(objects));
        }
    }
}
