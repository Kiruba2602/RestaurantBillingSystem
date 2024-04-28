package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bill;
import model.Order;

public class BillDAO {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/restaurantinfo";
    private String username = "root";
    private String password = "Karan@8925";

    public BillDAO() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public List<Bill> getBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bills";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public void addBill(Bill bill) {
        String query = "INSERT INTO bills (order_id, total_amount) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bill.getOrderId());
            pstmt.setDouble(2, bill.getTotalAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bill getBillById(int id) {
        Bill bill = null;
        String query = "SELECT * FROM bills WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
    
    public Order getOrderById(int orderId) {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                double totalAmount = rs.getDouble("total_amount");
                Order order = new Order(id, customerId, totalAmount, null, null, null);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBill(Bill bill) {
        String query = "UPDATE bills SET order_id = ?, total_amount = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bill.getOrderId());
            pstmt.setDouble(2, bill.getTotalAmount());
            pstmt.setInt(3, bill.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBill(int id) {
        String query = "DELETE FROM bills WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}