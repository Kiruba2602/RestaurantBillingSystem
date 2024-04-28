package ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.*;
import model.*;

public class MainFrame {
    private CustomerDAO customerDAO;
    private MenuItemDAO menuItemDAO;
    private OrderDAO orderDAO;
    private BillDAO billDAO;
    private Scanner scanner;

    public MainFrame() throws SQLException {
        customerDAO = new CustomerDAO();
        menuItemDAO = new MenuItemDAO();
        orderDAO = new OrderDAO();
        billDAO = new BillDAO();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("\nMenu:");
            System.out.println("1. Display menu items");
            System.out.println("2. Display customers");
            System.out.println("3. Process order");
            System.out.println("4. Display bills");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayMenuItems();
                    break;
                case 2:
                    displayCustomers();
                    break;
                case 3:
                    processOrder();
                    break;
                case 4:
                    displayBills();
                    break;
                case 5:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void displayMenuItems() {
        List<MenuItem> menuItems = menuItemDAO.getMenuItems();
        System.out.println("Menu items:");
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem);
        }
    }

    private void displayCustomers() {
        List<Customer> customers = customerDAO.getCustomers();
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void processOrder() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer phone number: ");
        String contact = scanner.nextLine();

        Customer customer = customerDAO.getCustomers();
        if (customer == null) {
            customer = new Customer(0, customerName, contact);
            customerDAO.addCustomer(customer);
        }

        List<MenuItem> menuItems = menuItemDAO.getMenuItems();
        System.out.println("Menu items:");
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem);
        }

        System.out.print("Enter the ID of the menu item you want to order: ");
        int menuItemId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        MenuItem menuItem = MenuItemDAO.getMenuItemById(menuItemId);
        if (menuItem == null) {
            System.out.println("Invalid menu item ID.");
            return;
        }

        System.out.print("Enter the quantity of the menu item you want to order: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Order order = new Order(customer, menuItem, quantity);
        orderDAO.addOrder(order);

        Bill bill = new Bill(order);
        billDAO.addBill(bill);

        System.out.println("Order processed successfully.");
        System.out.println("Bill: " + bill);
    }

    private void displayBills() {
        List<Bill> bills = billDAO.getBills();
        System.out.println("Bills:");
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
            mainFrame.displayMenu();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}