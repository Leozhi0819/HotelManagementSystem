package hotel.management.system.test;

import hotel.management.system.dao.DataManager;
import hotel.management.system.dao.DataTable;

import java.sql.SQLException;
import java.sql.Types;

/**
 * @author leozhi
 */
public class TestBusiness {
    static String searchSql = "SELECT * FROM score";
    static String insertSql = "INSERT INTO score(name, age, score)VALUE(?, ?, ?)";
    static String deleteSql = "DELETE FROM score WHERE id = ?";
    static String updateSql = "UPDATE score set name = ? WHERE id = ?";

    public static void main(String[] args) {
        insertData();
        searchData();
    }

    private static void insertData() {
        DataManager dataManager = new DataManager();
        String[] column = new String[]{"zhangsan", "23", "89.5"};
        int[] type = new int[]{Types.CHAR, Types.INTEGER, Types.DOUBLE};

        try {
            boolean flag = dataManager.updateOrAdd(column, type, insertSql);
            if (flag) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void searchData() {
        DataManager dataManager = new DataManager();
        String[] column = null;
        int[] type = null;

        try {
            DataTable dataTable = dataManager.getResultData(column, type, searchSql);
            if (dataTable != null && dataTable.getRowCount() > 0) {
                for (int i = 0; i < dataTable.getRowCount(); i++) {
                    for (int j = 0; j < dataTable.getColumnCount(); j++) {
                        System.out.println(dataTable.getRow()[i][j] + "\t");
                        System.out.println();
                    }
                }
            } else {
                System.out.println("查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
