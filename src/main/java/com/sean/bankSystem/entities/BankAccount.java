package com.sean.bankSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    // Fields.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categories category;

    @Column(unique = true, nullable = false,updatable = false, length = 240)
    private String description;

    @Column(nullable = false,updatable = false, length = 240)
    private String title;

    @Column(updatable = true)
    private long balance;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "customers_vs_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;


    public BankAccount(int id, String name, Categories category, String description, String title, long balance) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.title = title;
        this.balance = balance;
    }

    public BankAccount(int id, String name, Categories category, String description, String title) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.title = title;
    }

    public BankAccount(int id, String name, Categories category, long balance) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.balance = balance;
    }

    public BankAccount(String name, Categories category, String description, String title) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.title = title;
    }

    // Getters && Setters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && balance == that.balance && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, title, balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void addCustomer(Customer customer){
        if (customers == null){
            customers = new ArrayList<>();
        }
        customers.add(customer);
    }
}
