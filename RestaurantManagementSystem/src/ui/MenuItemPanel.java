package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.MenuItem;
import dao.MenuItemDAO;

public class MenuItemPanel {
    private List<MenuItem> menuItems;
    private MenuItemDAO menuItemDAO;
    private Scanner scanner;

    public MenuItemPanel() throws SQLException {
        menuItems = new ArrayList<>();
        menuItemDAO = new MenuItemDAO();
        scanner = new Scanner(System.in);
        // populate menuItems with data
        loadMenuItems();
    }

    private void loadMenuItems() {
        menuItems = menuItemDAO.getMenuItems();
    }

    public void display() {
        System.out.println("Displaying Menu Items:");
        for (MenuItem menuItem : menuItems) {
            System.out.printf("%d: %s - $%.2f%n", menuItem.getId(), menuItem.getName(), menuItem.getPrice());
        }
    }

    public void addMenuItem() {
        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter menu item category: ");
        String category = scanner.nextLine();
        System.out.print("Enter menu item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        MenuItem menuItem = new MenuItem(0, name, category, price);
        menuItemDAO.addMenuItem(menuItem);
        loadMenuItems();
    }

    public void updateMenuItem() {
        System.out.print("Enter menu item ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        MenuItem menuItem = getMenuItemById(id);
        if (menuItem != null) {
            System.out.print("Enter new menu item name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new menu item category: ");
            String category = scanner.nextLine();
            System.out.print("Enter new menu item price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            menuItem.setName(name);
            menuItem.setCategory(category);
            menuItem.setPrice(price);
            menuItemDAO.updateMenuItem(menuItem);
            loadMenuItems();
        } else {
            System.out.println("Menu item not found.");
        }
    }

    public void deleteMenuItem() {
        System.out.print("Enter menu item ID to delete: ");
        int id = scanner.nextInt();
        MenuItem menuItem = getMenuItemById(id);
        if (menuItem != null) {
            menuItemDAO.deleteMenuItem(id);
            loadMenuItems();
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private MenuItem getMenuItemById(int id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId() == id) {
                return menuItem;
            }
        }
        return null;
    }
}