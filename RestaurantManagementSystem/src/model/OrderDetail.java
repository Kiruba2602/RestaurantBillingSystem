package model;

import java.util.Objects;

public class OrderDetail {
    private int id;
    private Order order;
    private MenuItem menuItem;
    private int quantity;
    private double price;

    // Constructor
    public OrderDetail(int id, Order order, MenuItem menuItem, int quantity, double price) {
        this.id = id;
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail() {
		this.id = 0;
		this.quantity = 0;
	}

	// Getters
    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return order.getId();
    }

    public int getMenuItemId() {
        return menuItem.getId();
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrderId(int orderId) {
        this.order.setId(orderId);
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItem.setId(menuItemId);
    }

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    // Override equals() and hashCode() methods for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        OrderDetail orderDetail = (OrderDetail) o;
        return id == orderDetail.id &&
                Objects.equals(order, orderDetail.order) &&
                Objects.equals(menuItem, orderDetail.menuItem) &&
                quantity == orderDetail.quantity &&
                Double.compare(orderDetail.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, menuItem, quantity, price);
    }
}