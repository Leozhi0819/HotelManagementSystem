package hotel.management.system.bean;

public class Consumption {
    private int record_id;
    public Consumption() {
        super();
        this.record_id = record_id;
        this.room_number = room_number;
        this.customer_no = customer_no;
        this.check_in_date = check_in_date;
        this.days = days;
    }
    public int getRecord_id() {
        return record_id;
    }
    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }
    public String getRoom_number() {
        return room_number;
    }
    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }
    public String getCustomer_no() {
        return customer_no;
    }
    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }
    public String getCheck_in_date() {
        return check_in_date;
    }
    public void setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    private String room_number;
    private String customer_no;
    private String check_in_date;
    private String days;

}
