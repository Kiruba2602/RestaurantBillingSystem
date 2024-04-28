package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Customer;
import dao.CustomerDAO;

public class CustomerPanel {
    private List<Customer> customers;
    private CustomerDAO customerDAO;
    private Scanner scanner;

    public CustomerPanel() throws SQLException {
        customers = new ArrayList<>();
        customerDAO = new CustomerDAO();
        scanner = new Scanner(System.in);
        // populate customers with data
        loadCustomers();
    }

    private void loadCustomers() {
        customers = customerDAO.getCustomers();
    }

    public void display() {
        System.out.println("Displaying Customers:");
        for (Customer customer : customers) {
            System.out.printf("%d: %s - %s%n", customer.getId(), customer.getName(), customer.getContact());
        }
    }

    public void addCustomer() {
    	System.out.println("Enter customer Id: ");
    	int id = scanner.nextInt();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer contact: ");
        String contact = scanner.nextLine();
        Customer customer = new Customer(id, name, contact);
        customerDAO.addCustomer(customer);
        loadCustomers();
    }

    public void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Customer customer = getCustomerById(id);
        if (customer != null) {
            System.out.print("Enter new customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new customer contact: ");
            String contact = scanner.nextLine();
            customer.setName(name);
            customer.setContact(contact);
            customerDAO.updateCustomer(customer);
            loadCustomers();
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int id = scanner.nextInt();
        Customer customer = getCustomerById(id);
        if (customer != null) {
            customerDAO.deleteCustomer(id);
            loadCustomers();
        } else {
            System.out.println("Customer not found.");
        }
    }

    private Customer getCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}