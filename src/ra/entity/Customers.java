package ra.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int count = 1;
    private int customerId;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private List<CartItem> cart;
    private boolean isBlocked;
    private boolean role;

    // Constructor mặc định
    public Customers() {
        this.customerId = count++;
        this.cart = new ArrayList<>(); // Mặc định là rỗng khi thêm mới
        this.isBlocked = true; // Mặc định là không khóa
        this.role = false; // Mặc định là user
    }

    // Constructor với tất cả các thuộc tính
    public Customers(int customerId, String lastName, String firstName, String username, String password, String email, String phone, String address, List<CartItem> cart, boolean isBlocked, boolean role) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password; // Mã hóa mật khẩu (cần xử lý mã hóa trong thực tế)
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cart = cart;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    // Getter và Setter
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Mã hóa mật khẩu (cần xử lý mã hóa trong thực tế)
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public static void setCount(int count) {
        Customers.count = count;
    }

    // Phương thức để lấy giá trị count
    public static int getCount() {
        return count;
    }
}
