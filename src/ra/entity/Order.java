package ra.entity;


import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private String receiverName;
    private String phoneNumber;
    private String address;
    private double total;
    private OrderStatus orderStatus;
    private List<OrderDetail> ordersDetails;
    private Date orderAt;
    private Date deliverAt;

    // Constructor
    public Order(int orderId, int userId, String receiverName, String phoneNumber,
                 String address, double total, OrderStatus orderStatus,
                 List<OrderDetail> ordersDetails, Date orderAt, Date deliverAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.orderStatus = orderStatus;
        this.ordersDetails = ordersDetails;
        this.orderAt = orderAt;
        this.deliverAt = deliverAt;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrderDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public Date getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(Date orderAt) {
        this.orderAt = orderAt;
    }

    public Date getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(Date deliverAt) {
        this.deliverAt = deliverAt;
    }
    public enum OrderStatus {
        WAITING,
        CONFIRM,
        DELIVERY,
        SUCCESS,
        CANCEL
    }

}
