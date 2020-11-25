package hotel.management.system.view;

import hotel.management.system.bean.RoomType;
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

/**
 * @author leozhi
 */
public class SettingsPanel extends JPanel {
    private final JScrollPane roomTypeScrollPane;
    private final JScrollPane roomInfoScrollPane;
    private static JTable roomTypeTable;
    private static JTable roomInfoTable;
    private final RoomType roomType;
    public SettingsPanel() {
        this.setBounds(0, 0, 1600, 800);

        roomType = new RoomType();

        // 创建选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1600, 740);
        tabbedPane.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(tabbedPane);

        // 房间项目面板
        JPanel roomPanel = new JPanel();
        tabbedPane.addTab(ViewCons.ROOM_SETTINGS, roomPanel);
        roomPanel.setLayout(null);

        // 房间类型表格
        roomTypeScrollPane = new JScrollPane();
        roomTypeScrollPane.setBounds(60, 100, 710, 420);
        roomPanel.add(roomTypeScrollPane);

        // 操作按钮
        // 添加类型
        JButton addTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.ADD_TYPE_BUTTON, 80);
        roomPanel.add(addTypeBtn);
        addTypeBtn.addActionListener(event -> new AddTypeDialog(this).setVisible(true));
        // 修改类型
        JButton modifyTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.MODIFY_TYPE_BUTTON, 340);
        roomPanel.add(modifyTypeBtn);
        modifyTypeBtn.addActionListener(event -> {
            if (roomTypeTable.getSelectedColumn() < 0) {
                // todo: 常量
                JOptionPane.showMessageDialog(null, "请选择要修改的数据！");
            } else {
                new ModifyTypeDialog(this, roomType).setVisible(true);
            }
        });
        // 删除类型
        JButton deleteTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.DELETE_TYPE_BUTTON, 600);
        roomPanel.add(deleteTypeBtn);
        deleteTypeBtn.addActionListener(event -> {
            if (roomTypeTable.getSelectedColumn() < 0) {
                JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
            } else {
                // todo 删除所有房间信息表中的数据
                String[] column = new String[]{roomType.getRoomType()};
                SqlUtil.update(column, new int[]{Types.CHAR}, SqlCons.ROOM_TYPE_DELETE);
                updateTableInfo();
            }
        });

        // 房间信息表格
        roomInfoScrollPane = new JScrollPane();
        roomInfoScrollPane.setBounds(830, 100, 710, 420);
        roomPanel.add(roomInfoScrollPane);

        // 操作按钮
        // 添加房间
        JButton addRoomBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.ADD_ROOM_BUTTON, 850);
        roomPanel.add(addRoomBtn);
        addRoomBtn.addActionListener(event -> new AddRoomDialog(this).setVisible(true));

        // 修改房间
        JButton modifyRoomBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.MODIFY_ROOM_BUTTON, 1110);
        roomPanel.add(modifyRoomBtn);
        modifyRoomBtn.addActionListener(event -> {
            System.out.println(ViewCons.MODIFY_ROOM_BUTTON);
            updateTableInfo();
        });

        // 删除房间
        JButton deleteRoomBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.DELETE_ROOM_BUTTON, 1370);
        roomPanel.add(deleteRoomBtn);
        deleteRoomBtn.addActionListener(event -> {
            System.out.println(ViewCons.DELETE_ROOM_BUTTON);
        });

        updateTableInfo();
    }

    /**
     * 更新表格信息
     */
    public void updateTableInfo() {
        // 表格元素居中显示
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        Object[][] roomTypeBody = SqlUtil.query(null, null, SqlCons.ROOM_TYPE_QUERY);
        roomTypeTable = new JTable(new MyTableModel(roomTypeBody, ViewCons.ROOM_TYPE_TABLE_HEAD));
        roomTypeTable.setDefaultRenderer(Object.class, renderer);
        roomTypeScrollPane.setViewportView(roomTypeTable);
        // 添加表格监听
        roomTypeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = roomTypeTable.getSelectedRow();
                // 改变表行背景色
                roomTypeTable.setSelectionBackground(new Color(233, 233, 233));
                roomType.setRoomType(roomTypeTable.getValueAt(row, 0).toString());
                roomType.setRoomPrice(Double.parseDouble(roomTypeTable.getValueAt(row, 1).toString()));
                roomType.setBedCount(Integer.parseInt(roomTypeTable.getValueAt(row, 2).toString()));
            }
        });

        Object[][] roomInfoBody = SqlUtil.query(null, null, SqlCons.ROOM_INFO_QUERY);
        roomInfoTable = new JTable(new MyTableModel(roomInfoBody, ViewCons.ROOM_INFO_TABLE_HEAD));
        roomInfoTable.setDefaultRenderer(Object.class, renderer);
        roomInfoScrollPane.setViewportView(roomInfoTable);
    }
}
