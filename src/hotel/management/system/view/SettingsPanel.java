package hotel.management.system.view;

import hotel.management.system.dao.DataTable;
import hotel.management.system.util.SqlUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author leozhi
 */
public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        this.setBounds(0, 0, 1600, 800);

        // 创建选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1600, 740);
        tabbedPane.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(tabbedPane);

        // 房间项目面板
        JPanel roomPanel = new JPanel();
        tabbedPane.addTab("房间项目设置", roomPanel);
        // 创建下拉框
        String[] options = {"不可使用", "可供使用", "维护中", "已预订"};
        JComboBox comboBox = new JComboBox(options);
        comboBox.setBounds(200, 8, 90, 24);
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        comboBox.setVisible(true);
        roomPanel.setLayout(null);
        roomPanel.add(comboBox);
        JLabel roomStatusLabel = new JLabel("结帐后房间状态：");
        roomStatusLabel.setBounds(60, 10, 170, 20);
        roomStatusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        roomPanel.add(roomStatusLabel);

        JLabel label = new JLabel("结帐后");
        label.setBounds(394, 10, 72, 18);
        roomStatusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        roomPanel.add(label);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 70, 700, 200);
        roomPanel.add(scrollPane);
        DataTable dataTable = SqlUtil.query(null, null, "SELECT * FROM room_type");
        Object[][] roomTypeBody = null;
        if (dataTable != null) {
            roomTypeBody = new Object[dataTable.getRowCount()][dataTable.getColumnCount()];
            for (int i = 0; i < dataTable.getRowCount(); i++) {
                for (int j = 0; j < dataTable.getColumnCount(); j++) {
                    roomTypeBody[i][j] = dataTable.getRow()[i][j];
                }
            }
        }
        String[] roomTypeTableHead = {"房间类型", "预设单价", "床位数量"};
        JTable roomTypeTable = new JTable(new DefaultTableModel(roomTypeBody, roomTypeTableHead));
        scrollPane.setViewportView(roomTypeTable);
    }
}
