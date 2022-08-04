package com.sean.bankSystem.repositories;

import com.sean.bankSystem.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<BankAccount, Integer> {



    boolean existsByCustomersIdAndId(int customerId, int accountId);
    List<BankAccount> findAllCustomerBankAccountsById(int id);
//    boolean existsById(int id);


}
