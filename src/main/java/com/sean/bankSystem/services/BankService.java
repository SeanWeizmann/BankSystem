package com.sean.bankSystem.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService extends ClientService {

    @Override
    public boolean login(String email, String password) {
        return !bankRepo.existsByEmailAndPassword(email, password);
    }


}
