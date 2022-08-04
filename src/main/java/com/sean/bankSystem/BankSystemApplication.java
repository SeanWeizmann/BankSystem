package com.sean.bankSystem;

import com.sean.bankSystem.entities.Bank;
import com.sean.bankSystem.entities.BankAccount;
import com.sean.bankSystem.entities.Categories;
import com.sean.bankSystem.entities.Customer;
import com.sean.bankSystem.exceptions.InvalidException;
import com.sean.bankSystem.exceptions.NotFoundException;
import com.sean.bankSystem.services.AdminService;
import com.sean.bankSystem.services.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BankSystemApplication {

	public static void main(String[] args) throws InvalidException, NotFoundException {
//		SpringApplication.run(BankSystemApplication.class, args);

		ConfigurableApplicationContext ctx = SpringApplication.run(BankSystemApplication.class, args);
		AdminService adminService = ctx.getBean(AdminService.class);
		CustomerService customerService = ctx.getBean(CustomerService.class);

		try {
		adminService.addBank(new Bank("Bank of usa", "usa@gmail.com", "5555"));
		}catch (InvalidException err){
			System.out.println(err.getMessage());
		}
//
//
		adminService.addCustomer(new Customer("Sean", "sean@gmail.com", "1234"));
//
//		try {
//
//		adminService.changePassWord(new Bank(1,"911"));
//		System.out.println(adminService.getBankById(2));
//		}catch (NotFoundException e){
//			System.out.println(e.getMessage());
//		}
//
//
//		System.out.println(adminService.getAllBanks());

		System.out.println(adminService.getAllCustomers());

		adminService.addAccount(new BankAccount("Sean", Categories.BRONZE, "all right", "you can do better" ));

		customerService.addAccountPurchase(1, 1);

		customerService.deposit(1,1, 250000);

		customerService.deposit(1, 1, 55);

		try {

		customerService.withdraw(1, 1, 155);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
