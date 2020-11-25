package hotel.management.system.widget;

import javax.swing.table.DefaultTableModel;

/**
 * @author leozhi
 */
public class MyTableModel extends DefaultTableModel {
    public MyTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /**
     * 设置表格单元格不可编辑
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
