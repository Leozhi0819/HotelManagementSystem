package hotel.management.system.constant;

/**
 * @author leozhi
 */
public class ViewCons {
    /**
     * 界面风格
     */
    public static final String METAL_STYLE           = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String WINDOWS_STYLE         = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String WINDOWS_CLASSIC_STYLE = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    public static final String MOTIF_STYLE           = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String MAC_STYLE             = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    public static final String GTK_STYLE             = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

    /**
     * Dialog 信息
     */
    public static final String SYNTACTIC_ERROR = "SQL 语句执行错误！\n";
    public static final String HAS_LOGGED      = "已登录";

    /**
     * 资源存放路径
     */
    public static final String BOOKING_ICON_PATH  = "img/icon/icon_booking.png";
    public static final String CHECKIN_ICON_PATH  = "img/icon/icon_checkin.png";
    public static final String CHECKOUT_ICON_PATH = "img/icon/icon_checkout.png";
    public static final String INQUIRE_ICON_PATH  = "img/icon/icon_inquire.png";
    public static final String TURNOVER_ICON_PATH = "img/icon/icon_turnover.png";
    public static final String SETTINGS_ICON_PATH = "img/icon/icon_settings.png";
    public static final String ABOUT_ICON_PATH    = "img/icon/icon_about.png";
    public static final String EXIT_ICON_PATH     = "img/icon/icon_exit.png";

    /**
     * 界面文本
     * 登录界面
     */
    public static final String LOGIN_USER_NAME     = "用户名:";
    public static final String LOGIN_USER_PASSWORD = "密码:";
    public static final String LOGIN_BUTTON        = "登录";
    public static final String LOGIN_EXIT_BUTTON   = "退出";
    public static final String LOGIN_NULL_PASSWORD = "请输入登陆密码！";
    public static final String LOGIN_FAIL          = "登陆失败！";
    public static final String LOGIN_SUCCESS       = "登录成功！";

    public static final String BOOKING_BUTTON_TEXT  = "客房预订";
    public static final String CHECKIN_BUTTON_TEXT  = "住宿管理";
    public static final String CHECKOUT_BUTTON_TEXT = "退房结帐";
    public static final String INQUIRE_BUTTON_TEXT  = "客户查询";
    public static final String TURNOVER_BUTTON_TEXT = "营业统计";
    public static final String SETTINGS_BUTTON_TEXT = "系统设置";
    public static final String ABOUT_BUTTON_TEXT    = "关于我们";
    public static final String EXIT_BUTTON_TEXT     = "退出系统";

    /**
     * 系统设置界面
     */
    public static final String ROOM_SETTINGS          = "房间设置";
    public static final String[] ROOM_TYPE_COMBOBOX   = {"不可使用", "可供使用", "维护中", "已预订"};
    public static final String[] ROOM_TYPE_TABLE_HEAD = {"房间类型", "预设单价", "床位数量"};
    public static final String[] ROOM_INFO_TABLE_HEAD = {"房间号", "房间类型", "房间状态", "Wifi密码"};
    public static final String ADD_TYPE_BUTTON        = "添加类型";
    public static final String MODIFY_TYPE_BUTTON     = "修改类型";
    public static final String DELETE_TYPE_BUTTON     = "删除类型";
    public static final String[] ROOM_STATUS          = {"空闲", "已预订", "使用中", "维护中"};
    public static final String ADD_ROOM_BUTTON        = "添加房间";
    public static final String MODIFY_ROOM_BUTTON     = "修改房间";
    public static final String DELETE_ROOM_BUTTON     = "删除房间";

    /**
     * 退出系统
     */
    public static final String EXIT_TITLE = "退出系统";
    public static final String EXIT_CONFIRM_MESSAGE = "您确定要退出系统吗？";
    public static final String EXIT_SUCCESS_MESSAGE = "已退出，欢迎下次使用！";
}
