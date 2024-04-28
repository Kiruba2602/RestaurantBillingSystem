package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.MenuItem;
import model.Order;

public class OrderDAO {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/restaurantinfo";
    private String username = "root";
    private String password = "Karan@8925";

    public OrderDAO() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(0, 0, 0, null, null, null);
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderDate(rs.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO orders (customer_id, total_amount, order_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDouble(2, order.getTotalAmount());
            pstmt.setDate(3, order.getOrderDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // other CRUD operations
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?")) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    public Order getOrderById(int id) {
        String query = "SELECT * FROM orders WHERE id = ?";
        Order order = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderDate(rs.getDate("order_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    
    public MenuItem getMenuItemById(int menuItemId) {
        MenuItem menuItem = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu_items WHERE id = ?")) {
            statement.setInt(1, menuItemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                menuItem = new MenuItem();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItem;
    }

    public void updateOrder(Order order) {
        String query = "UPDATE orders SET customer_id = ?, total_amount = ?, order_date = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDouble(2, order.getTotalAmount());
            pstmt.setDate(3, order.getOrderDate());
            pstmt.setInt(4, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}