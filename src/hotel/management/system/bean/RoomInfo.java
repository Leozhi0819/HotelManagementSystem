package hotel.management.system.bean;

/**
 * @author leozhi
 */
public class RoomInfo {
    private String roomNumber;
    private String roomType;
    private String roomStatus;
    private String roomPhone;
    private String wifiPassword;
    private double price;

    public RoomInfo() {
    }

    public RoomInfo(String roomNumber, String roomType, String roomStatus, String roomPhone, String wifiPassword, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPhone = roomPhone;
        this.wifiPassword = wifiPassword;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomPhone() {
        return roomPhone;
    }

    public void setRoomPhone(String roomPhone) {
        this.roomPhone = roomPhone;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
