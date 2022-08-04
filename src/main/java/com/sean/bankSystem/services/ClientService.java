package com.sean.bankSystem.services;

import com.sean.bankSystem.repositories.AccountRepo;
import com.sean.bankSystem.repositories.BankRepo;
import com.sean.bankSystem.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected BankRepo bankRepo;

    @Autowired
    protected AccountRepo accountRepo;

    @Autowired
    protected CustomerRepo customerRepo;

    public abstract boolean login(String email, String password);


}
