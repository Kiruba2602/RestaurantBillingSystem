package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Bill;
import dao.BillDAO;
import model.Order;

public class BillPanel {
    private List<Bill> bills;
    private BillDAO billDAO;
    private Scanner scanner;

    public BillPanel() throws SQLException {
        bills = new ArrayList<>();
        billDAO = new BillDAO();
        scanner = new Scanner(System.in);
        // populate bills with data
        loadBills();
    }

    private void loadBills() {
        bills = billDAO.getBills();
    }

    public void display() {
        System.out.println("Displaying Bills:");
        for (Bill bill : bills) {
            System.out.printf("%d: Customer: %s, Total: $%.2f%n", bill.getId(), bill.getOrder().getCustomer().getName(), bill.getTotalAmount());
        }
    }

    public void addBill() {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Order order = billDAO.getOrderById(orderId);
        if (order != null) {
            Bill bill = new Bill();
            bill.setOrder(order);
            bill.setTotalAmount(order.getTotalAmount());
            billDAO.addBill(bill);
            System.out.println("Bill added.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public void updateBill() {
        System.out.print("Enter bill ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Bill bill = billDAO.getBillById(id);
        if (bill != null) {
            System.out.print("Enter new total amount: ");
            double totalAmount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            bill.setTotalAmount(totalAmount);
            billDAO.updateBill(bill);
            System.out.println("Bill updated.");
        } else {
            System.out.println("Bill not found.");
        }
    }

    public void deleteBill() {
        System.out.print("Enter bill ID: ");
        int id = scanner.nextInt();
        Bill bill = billDAO.getBillById(id);
        if (bill != null) {
            billDAO.deleteBill(id);
            System.out.println("Bill deleted.");
        } else {
            System.out.println("Bill not found.");
        }
    }
}