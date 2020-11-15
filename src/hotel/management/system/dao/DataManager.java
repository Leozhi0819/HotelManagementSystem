package hotel.management.system.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author leozhi
 */
public class DataManager {
    private PreparedStatement pStmt;
    private final Connection connection;
    private ResultSet resultSet;

    /**
     * 创建数据库单例
     */
    private DataManager() {
        connection = DatabaseConnection.getDatabaseConnection();
    }

    private static class DataManagerHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return DataManagerHolder.INSTANCE;
    }

    /**
     * 执行修改添加操作
     */
    public boolean updateOrAdd(String[] column, int[] type, String sql) throws SQLException {
        if (!setStmtParam(column, type, sql)) {
            return false;
        } else {
            boolean flag = pStmt.executeUpdate() > 0;
            closeDatabase();
            return flag;
        }
    }

    /**
     * 获取查询结果集
     */
    public DataTable getResultData(String[] column, int[] type, String sql) throws SQLException {
        DataTable dataTable = new DataTable();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        if (!setStmtParam(column, type, sql)) {
            return null;
        }
        resultSet = pStmt.executeQuery();
        // 获取数据库列名
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int numberOfColumns = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            HashMap<String, String> rsTree = new HashMap<>();
            for (int r = 1; r < numberOfColumns + 1; r++) {
                rsTree.put(resultSetMetaData.getColumnName(r), resultSet.getObject(r).toString());
            }
            list.add(rsTree);
        }
        closeDatabase();
        dataTable.setDataTable(list);
        return dataTable;
    }

    /**
     * 参数设置
     */
    private boolean setStmtParam(String[] column, int[] type, String sql) throws NumberFormatException, SQLException {
        if (sql == null) {
            return false;
        }
        pStmt = connection.prepareStatement(sql);
        if (column != null && type != null && column.length != 0 && type.length != 0) {
            for (int i = 0; i < type.length; i++) {
                switch (type[i]) {
                    case Types.INTEGER:
                        pStmt.setInt(i + 1, Integer.parseInt(column[i]));
                        break;
                    case Types.BOOLEAN:
                        pStmt.setBoolean(i + 1, Boolean.parseBoolean(column[i]));
                        break;
                    case Types.CHAR:
                        pStmt.setString(i + 1, column[i]);
                        break;
                    case Types.DOUBLE:
                        pStmt.setDouble(i + 1, Double.parseDouble(column[i]));
                        break;
                    case Types.FLOAT:
                        pStmt.setFloat(i + 1, Float.parseFloat(column[i]));
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }

    /**
     * 关闭数据库
     */
    private void closeDatabase() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (pStmt != null) {
            pStmt.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}