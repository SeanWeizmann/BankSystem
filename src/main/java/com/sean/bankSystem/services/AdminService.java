package com.sean.bankSystem.services;

import com.sean.bankSystem.entities.Bank;
import com.sean.bankSystem.entities.BankAccount;
import com.sean.bankSystem.entities.Customer;
import com.sean.bankSystem.exceptions.InvalidException;
import com.sean.bankSystem.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService extends ClientService {


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addBank(Bank bank) throws InvalidException {
        if (bankRepo.existsByEmail(bank.getEmail())){
            throw new InvalidException("email already in use");
        }
        if (bankRepo.existsByName(bank.getName())){
            throw new InvalidException("Name already in use");
        }
        bankRepo.save(bank);
    }

    public List<Bank> getAllBanks(){
        return bankRepo.findAll();
    }

    public Bank getBankById(int id) throws NotFoundException {
        if (!bankRepo.existsById(id)){
            throw new NotFoundException("ID -" + id +"- not exists");
        }
        return bankRepo.findById(id).get();
    }

    public void changePassWord(Bank bank) throws NotFoundException {
        if (!bankRepo.existsById(bank.getId())){
            throw new NotFoundException("Bank with -" + bank.getId() + "- does not exist");
        }
        bankRepo.save(bank);
    }

    public void deleteBank(int id) throws NotFoundException {
        if (!bankRepo.existsById(id)){
            throw new NotFoundException("Bank with ID -" + id + "- does not exist");
        }
        bankRepo.deleteById(id);
    }


    public void addCustomer(Customer customer) throws InvalidException {
        if (customerRepo.existsByEmail(customer.getEmail())){
            throw new InvalidException("email -" + customer.getEmail() + "- already in use");
        }
        customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer getCustomerById(int id) throws NotFoundException {
        if (!customerRepo.existsById(id)){
            throw new NotFoundException("Customer with ID -" + id + "- does not exist");
        }
        return customerRepo.findById(id).get();
    }

    public void deleteCustomer(int id) throws NotFoundException {
        if (!customerRepo.existsById(id)){
            throw new NotFoundException("Customer with ID -" + id + "- does not exist");
        }
        customerRepo.deleteById(id);
    }

    public void addAccount(BankAccount bankAccount){
        accountRepo.save(bankAccount);
    }

    public List<BankAccount> getAllAccounts(){
        return accountRepo.findAll();
    }

    public BankAccount getAccountById(int id) throws NotFoundException {
        if (!accountRepo.existsById(id)){
            throw new NotFoundException("Account with ID -" + id + "- does not exist");
        }
        return accountRepo.findById(id).get();
    }

    public void updateAccount(BankAccount bankAccount) throws NotFoundException {
        if (!accountRepo.existsById(bankAccount.getId())){
            throw new NotFoundException("Customer with ID -" + bankAccount.getId() + "- does not exist");
        }
        accountRepo.save(bankAccount);
    }

    public void deleteAccount(int id) throws NotFoundException {
        if (!accountRepo.existsById(id)){
            throw new NotFoundException("Customer with ID -" + id + "- does not exist");
        }
        accountRepo.deleteById(id);
    }

}
