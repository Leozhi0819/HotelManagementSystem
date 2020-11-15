package hotel.management.system.constant;

/**
 * @author leozhi
 */
public class SqlConstant {
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE = "jdbc:mysql://127.0.0.1:3306/hms";
    public static final String USER = "root";
    public static final String PASSWORD = "19980819";

    public static final String LOGIN_STATUS = "SELECT status FROM login_status WHERE s_id = 1;";
}
