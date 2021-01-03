package hotel.management.system.view;

import hotel.management.system.bean.Consumption;
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

/**
 * @author leozhi
 */
public class CheckoutPanel extends JPanel {
    private JScrollPane consumptionScrollPane;
    private static JTable consumptionTable;
    private final Consumption consumption;

    // 身份证号
    private String customerNo = "";

    public CheckoutPanel() {
        this.consumptionScrollPane = new JScrollPane();
        this.setBounds(0, 0, 1600, 800);
        this.setBackground(Color.pink);

        consumption = new Consumption();

        // 创建选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0,0,1600,740);
        tabbedPane.setBackground(Color.black);
        setLayout(null);
        this.add(tabbedPane);

        // 顾客住房信息面板
        tabbedPane.setBounds(20,20,1500,650);
        tabbedPane.setBackground(Color.black);
        setLayout(null);
        setLayout(null);
        this.add(tabbedPane);

        // 面板
        JPanel consumptionPanel = new JPanel();
        tabbedPane.addTab(ViewCons.Consumption_Housing_Info, consumptionPanel);
        consumptionPanel.setLayout(null);

        // 顾客住房信息表格
        consumptionScrollPane = new JScrollPane();
        consumptionScrollPane.setBounds(0,0,1400,550);
        consumptionPanel.add(consumptionScrollPane);

        // 退房结账
        JButton deleteTypeBtn = ViewUtils.createRoomSettingsButton(null, ViewCons.CHECKOUT, 600);
        consumptionPanel.add(deleteTypeBtn);
        deleteTypeBtn.addActionListener(event -> {
            if (consumptionTable.getSelectedColumn() < 0) {
                JOptionPane.showMessageDialog(null, "请选择要退房的顾客！");
            } else {
                int result = JOptionPane.showConfirmDialog(null,
                        "您确定要给该顾客退房结账吗？", "退房结账", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // 退房结账
                    String checkOutSql  = "DELETE FROM check_in_record WHERE check_in_record.customer_no=" + customerNo;
                    System.out.println(checkOutSql);
//                    String[] column = new String[]{consumption.getRoom_number()};
                    // 删除房间类型
                    SqlUtil.update(null, null, checkOutSql);
                    // 删除该类型的所有房间
                    // SqlUtil.update(column, new int[]{Types.CHAR}, SqlCons.ROOM_TYPE_ALL_DELETE);
                    updateTableInfo();
                }
            }
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

        Object[][] consumptionBody = SqlUtil.query(null, null, SqlCons.CHECKOUT_QUERY);
        consumptionTable = new JTable(new MyTableModel(consumptionBody, ViewCons.Information));
        consumptionTable.setDefaultRenderer(Object.class, renderer);
        consumptionScrollPane.setViewportView(consumptionTable);
        // 添加表格监听
        consumptionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = consumptionTable.getSelectedRow();
                customerNo = consumptionTable.getValueAt(row, 2).toString();
                // 改变表行背景色
                consumptionTable.setSelectionBackground(new Color(233, 233, 233));
                consumption.setRecord_id(Integer.parseInt(consumptionTable.getValueAt(row, 0).toString()));
                consumption.setRoom_number(consumptionTable.getValueAt(row, 1).toString());
                consumption.setCustomer_no(consumptionTable.getValueAt(row, 2).toString());
                consumption.setCheck_in_date(consumptionTable.getValueAt(row, 3).toString());
                consumption.setDays(consumptionTable.getValueAt(row, 4).toString());
            }
        });


    }

}
