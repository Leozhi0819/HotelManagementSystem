package hotel.management.system.bean;

/**
 * @author leozhi
 */
public class CustomerInfo {
    private String customerNo;
    private String customerName;
    private String customerPhone;

    public CustomerInfo() {
    }

    public CustomerInfo(String customerNo, String customerName, String customerPhone) {
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
