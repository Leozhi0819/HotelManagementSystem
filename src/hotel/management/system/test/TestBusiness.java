package hotel.management.system.test;

import hotel.management.system.dao.DataManager;
import hotel.management.system.dao.DataTable;

import java.sql.SQLException;
import java.sql.Types;

/**
 * @author leozhi
 */
public class TestBusiness {
    static String searchSql = "SELECT * FROM customer_info";
    static String insertSql = "INSERT INTO customer_info(customer_id, customer_name, customer_sex, customer_phone)VALUE(?, ?, ?, ?)";
    static String deleteSql = "DELETE FROM customer_info WHERE customer_name = ?";
    static String updateSql = "UPDATE customer_info SET customer_sex = ? WHERE customer_name = ?";

    public static void main(String[] args) {
        // insertData();
        // deleteData();
        // searchData();
    }

    private static void deleteData() {
        DataManager dataManager = new DataManager();
        String[] column = new String[]{"lisi"};
        int[] type = new int[]{Types.CHAR};
        try {
            boolean flag = dataManager.updateOrAdd(column, type, deleteSql);
            if (flag) {
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData() {
        DataManager dataManager = new DataManager();
        String[] column = new String[]{"123456789123456789", "lisi", "男", "12345678910"};
        int[] type = new int[]{Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR};

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
