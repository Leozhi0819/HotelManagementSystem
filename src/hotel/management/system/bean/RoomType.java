package hotel.management.system.bean;

/**
 * @author leozhi
 */
public class RoomType {
    /**
     * 房间类型 ID
     * 房间类型
     * 房间单价
     * 床位数量
     */
    private int roomTypeId;
    private String roomType;
    private double roomPrice;
    private int bedCount;

    public RoomType() {
    }

    public RoomType(int roomTypeId, String roomType, double roomPrice, int bedCount) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.bedCount = bedCount;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
