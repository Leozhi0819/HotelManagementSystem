package hotel.management.system.util;

import hotel.management.system.dao.DataManager;
import hotel.management.system.dao.DataTable;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author leozhi
 */
public class SqlUtil {
    /**
     * 增、删、改
     */
    public static void update(String[] column, int[] type, String sql) {
        DataManager dataManager = new DataManager();
        try {
            boolean flag = dataManager.updateOrAdd(column, type, sql);
            if (flag) {
                System.out.println("操作完成");
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "要添加的数据已存在！", "数据冲突", JOptionPane.WARNING_MESSAGE);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查
     */
    public static Object[][] query(String[] column, int[] type, String sql) {
        DataManager dataManager = new DataManager();
        try {
            DataTable dataTable = dataManager.getResultData(column, type, sql);
            Object[][] res = new Object[dataTable.getRowCount()][dataTable.getColumnCount()];
            if (dataTable.getRowCount() > 0) {
                for (int i = 0; i < dataTable.getRowCount(); i++) {
                    for (int j = 0; j < dataTable.getColumnCount(); j++) {
                        res[i][j] = dataTable.getRow()[i][j];
                    }
                }
                return res;
            } else {
                System.out.println("查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
