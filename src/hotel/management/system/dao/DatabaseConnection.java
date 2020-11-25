package hotel.management.system.dao;

import hotel.management.system.constant.SqlCons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leozhi
 */
public class DatabaseConnection {
    public static Connection getDatabaseConnection() {
        Connection connection = null;
        // 注册驱动
        try {
            Class.forName(SqlCons.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 连接数据库
        try {
            connection = DriverManager.getConnection(SqlCons.DATABASE, SqlCons.USER, SqlCons.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
