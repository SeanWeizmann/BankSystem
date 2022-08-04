package com.sean.bankSystem.repositories;

import com.sean.bankSystem.entities.BankAccount;
import com.sean.bankSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {


    boolean existsByEmail(String email);
//    List<BankAccount> findAllCustomerBankAccountsById(int id);
    boolean existsByEmailAndPassword(String email, String password);


}
