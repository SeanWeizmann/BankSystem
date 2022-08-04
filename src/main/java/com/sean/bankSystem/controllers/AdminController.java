package com.sean.bankSystem.controllers;

import com.sean.bankSystem.entities.Bank;
import com.sean.bankSystem.exceptions.InvalidException;
import com.sean.bankSystem.login.ClientType;
import com.sean.bankSystem.services.AdminService;
import com.sean.bankSystem.services.BankService;
import com.sean.bankSystem.services.CustomerService;
import com.sean.bankSystem.tokenVerifyLogin.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.security.PrivateKey;

@RestController
@RequestMapping("/Administrator")
@RequiredArgsConstructor
@ResponseBody
@CrossOrigin
public class AdminController {

    private final AdminService adminService;
    private final BankService bankService;
    private final CustomerService customerService;
    @Autowired
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public String login(@RequestParam String email, String password, @RequestParam ClientType clientType) throws LoginException {
        if (adminService.login(email, password)) {
            return jwtUtil.generateToken(email, password, clientType);
        } else {
            throw new LoginException("----WRONG PASSWORD OR EMAIL----");
        }
    }

    @PostMapping("/add_bank")
    public ResponseEntity<?> addBank(@RequestBody Bank bank, String token) throws InvalidException {
        if (!jwtUtil.isTokenExpired(token)) {
            throw new InvalidException("your permission is limited, please reconnect");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)) {
            throw new InvalidException("What Are U Doing HERE ? ");
        }
        adminService.addBank(bank);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
