package hotel.management.system.dao;

import hotel.management.system.util.ConstantUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leozhi
 */
public class DatabaseConnection {
    /**
     * 连接数据库
     */
    public static Connection getDatabaseConnection() {
        Connection connection = null;
        // 注册驱动
        try {
            Class.forName(ConstantUtils.SqlConstant.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 连接数据库
        try {
            connection = DriverManager.getConnection(ConstantUtils.SqlConstant.DATABASE,
                    ConstantUtils.SqlConstant.USER, ConstantUtils.SqlConstant.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
