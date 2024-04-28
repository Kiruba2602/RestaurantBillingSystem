package model;

import java.util.Objects;

public class Bill {
    private int id;
    private Order order;
    private int quantity;
    private double totalAmount;

    // Constructor
    public Bill(int id, Order order, int quantity, double totalAmount) {
        this.id = id;
        this.order = order;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Bill() {
		this.id = 0;
		this.quantity = 0;
		this.totalAmount = 0.0;
	}

	// Getters
    public int getId() {
        return id;
    }
    
    public int getOrderId() {
    	return order.getId();
    }

    public Order getOrder() {
        return order;
    }


    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setOrderId(int orderId) {
    	this.order.setId(orderId);;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", order=" + order +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                '}';
    }

    // Override equals() and hashCode() methods for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                Objects.equals(order, bill.order) &&
                quantity == bill.quantity &&
                Double.compare(bill.totalAmount, totalAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, quantity, totalAmount);
    }
}