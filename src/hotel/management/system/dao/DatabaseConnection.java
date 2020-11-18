package hotel.management.system.dao;

import hotel.management.system.constant.SqlConstant;

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
            Class.forName(SqlConstant.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 连接数据库
        try {
            connection = DriverManager.getConnection(SqlConstant.DATABASE, SqlConstant.USER, SqlConstant.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
