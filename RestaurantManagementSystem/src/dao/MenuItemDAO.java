package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MenuItem;

public class MenuItemDAO {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/restaurantinfo";
    private String username = "root";
    private String password = "Karan@8925";

    public MenuItemDAO() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menuitems";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt("id"));
                menuItem.setName(rs.getString("foodname"));
                menuItem.setCategory(rs.getString("category"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        String query = "INSERT INTO menuitems (foodname, category, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, menuItem.getName());
            pstmt.setString(2, menuItem.getCategory());
            pstmt.setDouble(3, menuItem.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(MenuItem menuItem) {
        String query = "UPDATE menuitems SET foodname = ?, category = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, menuItem.getName());
            pstmt.setString(2, menuItem.getCategory());
            pstmt.setDouble(3, menuItem.getPrice());
            pstmt.setInt(4, menuItem.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        String query = "DELETE FROM menuitems WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}