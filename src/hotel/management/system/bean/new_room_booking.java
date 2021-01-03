package hotel.management.system.bean;

public class new_room_booking {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId_code() {
        return id_code;
    }
    public void setId_code(String id_code) {
        this.id_code = id_code;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getRoomtype() {
        return roomtype;
    }
    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }
    public new_room_booking() {
        super();
        this.name = name;
        this.id_code = id_code;
        this.date = date;
        this.roomtype = roomtype;
    }
    private String name;
    private String id_code;
    private String date;
    private String roomtype;
}