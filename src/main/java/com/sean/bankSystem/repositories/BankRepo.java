package com.sean.bankSystem.repositories;

import com.sean.bankSystem.entities.Bank;
import com.sean.bankSystem.entities.BankAccount;
import com.sean.bankSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<Bank, Integer> {


    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
