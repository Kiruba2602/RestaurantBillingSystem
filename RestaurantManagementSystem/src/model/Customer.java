package model;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String contact;

    // Constructor
    public Customer() {
    	this.id = 0;
    	this.name = "";
    	this.contact = "";
    }
    public Customer(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    // Override equals() and hashCode() methods for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(contact, customer.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contact);
    }
}