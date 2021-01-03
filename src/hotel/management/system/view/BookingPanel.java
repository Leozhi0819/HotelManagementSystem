package hotel.management.system.view;

import hotel.management.system.bean.new_room_booking;
import hotel.management.system.constant.SqlCons;
import hotel.management.system.constant.ViewCons;
import hotel.management.system.util.SqlUtil;
import hotel.management.system.util.ViewUtils;
import hotel.management.system.widget.MyTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Types;

public class BookingPanel extends JPanel {
    /**
     *
     */
    private JScrollPane roomTypeScrollPane;
    private JScrollPane roomInfoScrollPane;
    private static JTable roomTypeTable;
    private static JTable bookingInfoTable;
    private new_room_booking new_booking;


    public BookingPanel() {
        this.new_booking = null;
        this.roomTypeScrollPane = new JScrollPane();
        this.roomInfoScrollPane = new JScrollPane();
        this.setBounds(0, 0, 1600, 800);

        new_booking = new new_room_booking();

        // 创建选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1600, 740);
        tabbedPane.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(tabbedPane);

        // 房间项目面板
        JPanel roomPanel = new JPanel();
        tabbedPane.addTab(ViewCons.BOOKING_MANAGE, roomPanel);
        roomPanel.setLayout(null);

        // 房间类型表格
        roomInfoScrollPane = new JScrollPane();
        roomInfoScrollPane.setBounds(60, 100, 710, 420);
        roomPanel.add(roomInfoScrollPane);

        // 房间类型标签
        JLabel roomLabel = new JLabel("所有房间状态");
        roomLabel.setBounds(380, 40, 130, 50);
        roomPanel.add(roomLabel);

        // 房间预定标签
        JLabel BookingLabel = new JLabel("房间预定状态");
        BookingLabel.setBounds(1150, 40, 130, 50);
        roomPanel.add(BookingLabel);

        // 操作按钮
        // 预定
        JButton addTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.BOOKING, 320);
        roomPanel.add(addTypeBtn);
        addTypeBtn.addActionListener(event -> new BookingDialog(this).setVisible(true));
        updateTableInfo();

        // 预定信息表格
        roomTypeScrollPane = new JScrollPane();
        roomTypeScrollPane.setBounds(830, 100, 710, 420);
        roomPanel.add(roomTypeScrollPane);
        updateTableInfo();

        // 取消预定
        JButton deleteTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.CANCAL_BOOKING, 1110);
        roomPanel.add(deleteTypeBtn);
        deleteTypeBtn.addActionListener(event -> {
            if (roomTypeTable.getSelectedColumn() < 0) {
                JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
            } else {
                String[] column = new String[]{new_booking.getId_code()};
                SqlUtil.update(column, new int[]{Types.CHAR}, SqlCons.BOOKING_DELETE);
                updateTableInfo();

            }
        });
    }

    /**
     * 更新表格信息
     */
    public void updateTableInfo() {
        // 表格元素居中显示
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        Object[][] roomTypeBody = SqlUtil.query(null, null, SqlCons.ROOM_BOOKING);
        roomTypeTable = new JTable(new MyTableModel(roomTypeBody, ViewCons.BOOKING_TABLE_HEAD));
        roomTypeTable.setDefaultRenderer(Object.class, renderer);
        roomTypeScrollPane.setViewportView(roomTypeTable);
        // 添加表格监听
        roomTypeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = roomTypeTable.getSelectedRow();
                // 改变表行背景色
                roomTypeTable.setSelectionBackground(new Color(233, 233, 233));
                new_booking.setName(roomTypeTable.getValueAt(row, 0).toString());
                new_booking.setId_code(roomTypeTable.getValueAt(row, 1).toString());
                new_booking.setDate(roomTypeTable.getValueAt(row, 2).toString());
                new_booking.setRoomtype(roomTypeTable.getValueAt(row, 3).toString());
            }
        });

        Object[][] roomInfoBody = SqlUtil.query(null, null, SqlCons.ROOM_INFO_QUERY);
        bookingInfoTable = new JTable(new MyTableModel(roomInfoBody, ViewCons.ROOM_INFO_TABLE_HEAD));
        bookingInfoTable.setDefaultRenderer(Object.class, renderer);
        roomInfoScrollPane.setViewportView(bookingInfoTable);
    }
}
