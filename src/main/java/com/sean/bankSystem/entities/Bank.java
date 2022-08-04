package com.sean.bankSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Bank {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, updatable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, updatable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    public Bank(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Bank(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Bank(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Bank(int id) {
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id && Objects.equals(name, bank.name) && Objects.equals(email, bank.email) && Objects.equals(password, bank.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
