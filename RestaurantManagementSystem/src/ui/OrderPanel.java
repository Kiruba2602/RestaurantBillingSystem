package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Customer;
import model.MenuItem;
import model.Order;
import model.OrderDetail;
import dao.OrderDAO;
import dao.OrderDetailDAO;

public class OrderPanel {
    private List<Order> orders;
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private Scanner scanner;

    public OrderPanel() throws SQLException {
        orders = new ArrayList<>();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        scanner = new Scanner(System.in);
        // populate orders with data
        loadOrders();
    }

    private void loadOrders() {
        orders = orderDAO.getOrders();
    }

    public void display() {
        System.out.println("Displaying Orders:");
        for (Order order : orders) {
            System.out.printf("%d: Customer: %s, Date: %s%n", order.getId(), order.getCustomer().getName(), order.getOrderDate());
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                System.out.printf("\t%s x %d%n", orderDetail.getMenuItem().getName(), orderDetail.getQuantity());
            }
        }
    }

    public void addOrder() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Customer customer = orderDAO.getCustomerById(customerId);
        if (customer != null) {
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
            orderDAO.addOrder(order);
            int orderId = order.getId();
            System.out.println("Order added. Order ID: " + orderId);
            addOrderDetails(orderId);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private void addOrderDetails(int orderId) {
        boolean done = false;
        while (!done) {
            System.out.println("Enter menu item ID and quantity (or 0 to finish):");
            int menuItemId = scanner.nextInt();
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (menuItemId == 0) {
                done = true;
            } else {
                MenuItem menuItem = orderDAO.getMenuItemById(menuItemId);
                if (menuItem != null) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(orderId);
                    orderDetail.setMenuItem(menuItem);
                    orderDetail.setQuantity(quantity);
                    orderDetailDAO.addOrderDetail(orderDetail);
                } else {
                    System.out.println("Menu item not found.");
                }
            }
        }
    }

    public void updateOrder() {
        System.out.print("Enter order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Order order = orderDAO.getOrderById(id);
        if (order != null) {
            System.out.print("Enter new customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            Customer customer = orderDAO.getCustomerById(customerId);
            if (customer != null) {
                order.setCustomer(customer);
                orderDAO.updateOrder(order);
                System.out.println("Order updated.");
            } else {
                System.out.println("Customer not found.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    public void deleteOrder() {
        System.out.print("Enter order ID: ");
        int id = scanner.nextInt();
        Order order = orderDAO.getOrderById(id);
        if (order != null) {
            orderDAO.deleteOrder(id);
            System.out.println("Order deleted.");
        } else {
            System.out.println("Order not found.");
        }
    }
}