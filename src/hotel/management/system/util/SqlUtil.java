package hotel.management.system.util;

import hotel.management.system.dao.DataManager;
import hotel.management.system.dao.DataTable;

import java.sql.SQLException;

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
            e.printStackTrace();
        }
    }

    /**
     * 查
     */
    public static DataTable query(String[] column, int[] type, String sql) {
        DataManager dataManager = new DataManager();
        try {
            DataTable dataTable = dataManager.getResultData(column, type, sql);
            if (dataTable != null && dataTable.getRowCount() > 0) {
                return dataTable;
            } else {
                System.out.println("查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
