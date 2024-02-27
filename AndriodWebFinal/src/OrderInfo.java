public class OrderInfo {
    private int id;
    private String username;
    private String phoneNumber;
    private String dietInfo;
    private String totalPrice;

    public OrderInfo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDietInfo() {
        return dietInfo;
    }

    public void setDietInfo(String dietInfo) {
        this.dietInfo = dietInfo;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
