package com.sean.bankSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @JsonIgnore
    @JoinColumn(name = "num_of_accounts")
    private int numOfAccount;


    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "customers_vs_accounts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<BankAccount> accounts;

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters && Setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumOfAccount() {
        return numOfAccount;
    }

    public void setNumOfAccount(int numOfAccount) {
        this.numOfAccount = numOfAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password) && Objects.equals(accounts, customer.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, accounts);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", owns= " + numOfAccount + " bank accounts" +
                '}';
    }
}
