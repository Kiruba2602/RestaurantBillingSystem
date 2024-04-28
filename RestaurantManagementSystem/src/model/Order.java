package model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private int customerId;
    private double totalAmount;
    private Date orderDate;
    private Customer customer;
    private List<OrderDetail> orderDetails;

    // Constructor
    public Order(int id, int customerId, double totalAmount, Date orderDate, Customer customer, List<OrderDetail> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public Order() {
    	this.id = 0;
    	this.customerId = 0;
    	this.totalAmount = 0.0;
    }
	// Getters
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", orderDetails=" + orderDetails +
                '}';
    }

    // Override equals() and hashCode() methods for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                Double.compare(order.totalAmount, totalAmount) == 0 &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(orderDetails, order.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, totalAmount, orderDate, customer, orderDetails);
    }
}