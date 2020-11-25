package hotel.management.system.constant;

/**
 * @author leozhi
 */
public class SqlCons {
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE = "jdbc:mysql://127.0.0.1:3306/hms_db";
    public static final String USER = "root";
    public static final String PASSWORD = "19980819";

    /**
     * 插入语句
     */
    public static final String USER_ADMIN_INSERT = "INSERT INTO user_info VALUES(0,'admin','admin');";
    public static final String ROOM_TYPE_INSERT  = "INSERT INTO room_type(type,price,bed_count)VALUES(?,?,?);";
    public static final String ROOM_INFO_INSERT  = "INSERT INTO room_info VALUES(?,?,?,?,?);";

    /**
     * 删除语句
     */
    public static final String ROOM_TYPE_DELETE = "DELETE FROM room_type WHERE type=?";

    /**
     * 更新语句
     */
    public static final String LOGIN_STATUS_UPDATE = "UPDATE login_status SET status=? WHERE s_id=1;";
    public static final String ROOM_TYPE_UPDATE    = "UPDATE room_type SET type=?,price=?,bed_count=? WHERE type_id=?";
    public static final String LOGGED              = "已登录";
    public static final String NOT_LOGGED          = "未登录";

    /**
     * 查询语句
     */
    public static final String USER_NAME_QUERY      = "SELECT username FROM user_info";
    public static final String USER_INFO_QUERY      = "SELECT * FROM user_info WHERE username=? AND password=?;";
    public static final String USER_ID_QUERY        = "SELECT id FROM user_info WHERE username=?;";
    public static final String ROOM_TYPE_QUERY = "SELECT type,price,bed_count FROM room_type;";
    public static final String ROOM_TYPE_ID_QUERY   = "SELECT type_id FROM room_type WHERE type=?;";
    public static final String ROOM_INFO_QUERY      = "SELECT room_number,room_type,room_status,wifi_password FROM room_info;";
    public static final String LOGIN_STATUS         = "SELECT status FROM login_status WHERE s_id=1;";
}
