package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderDetail;

public class OrderDetailDAO {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/restaurantinfo";
    private String username = "root";
    private String password = "Karan@8925";

    public OrderDetailDAO() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public List<OrderDetail> getOrderDetails() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_details";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setMenuItemId(rs.getInt("menu_item_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        String query = "INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getMenuItemId());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setDouble(4, orderDetail.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderDetail getOrderDetailById(int id) {
        OrderDetail orderDetail = null;
        String query = "SELECT * FROM order_details WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setMenuItemId(rs.getInt("menu_item_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        String query = "UPDATE order_details SET order_id = ?, menu_item_id = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getMenuItemId());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setDouble(4, orderDetail.getPrice());
            pstmt.setInt(5, orderDetail.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderDetail(int id) {
        String query = "DELETE FROM order_details WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}