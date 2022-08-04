package com.sean.bankSystem.services;

import com.sean.bankSystem.entities.BankAccount;
import com.sean.bankSystem.entities.Customer;
import com.sean.bankSystem.exceptions.InvalidException;
import com.sean.bankSystem.exceptions.NotFoundException;
import com.sean.bankSystem.exceptions.ZeroInYourBankAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService extends ClientService {

    @Override
    public boolean login(String email, String password) {
        return !customerRepo.existsByEmailAndPassword(email, password);
    }

    public void addAccountPurchase(int customerId, int accountID) throws InvalidException {
        if (accountRepo.existsByCustomersIdAndId(customerId, accountID)) {
            throw new InvalidException("This account already been purchase");
        }
        BankAccount account = accountRepo.getById(accountID);

        Customer customer = customerRepo.getById(customerId);
        customer.setNumOfAccount(customer.getNumOfAccount() + 1);
        account.addCustomer(customerRepo.getById(customerId));
        accountRepo.save(account);
    }

    public List<BankAccount> getAllAccountsOfCustomer() {
        return accountRepo.findAllCustomerBankAccountsById(1);
    }



    public BankAccount getAccountById(int id) throws NotFoundException {
        if (!accountRepo.existsById(id)) {
            throw new NotFoundException("account with id -" + id + "- does not exist");
        }
        return accountRepo.findById(id).get();
    }

    public void updateAccountDetail(BankAccount bankAccount) throws NotFoundException {
        if (!accountRepo.existsById(bankAccount.getId())) {
            throw new NotFoundException("account with id -" + bankAccount.getId() + "- does not exist");
        }
        accountRepo.save(bankAccount);
    }

    public void deleteAccount(int id) throws NotFoundException {
        if (!accountRepo.existsById(id)) {
            throw new NotFoundException("account with id -" + id + "- does not exist");
        }
        accountRepo.deleteById(id);
    }

    public void deposit(int customerId, int accountId, long money) throws NotFoundException {
        if (!accountRepo.existsById(accountId)) {
            throw new NotFoundException("account with id -" + accountId + "- does not exist");
        }
        if (!customerRepo.existsById(customerId)) {
            throw new NotFoundException("account with id -" + customerId + "- does not exist");
        }
        BankAccount bankAccount;
        bankAccount = accountRepo.getById(accountId);
        long ABalance = bankAccount.getBalance();
        bankAccount.setBalance(ABalance + money);

    }

    public void withdraw(int customerId, int accountId, long money) throws NotFoundException, ZeroInYourBankAccount {
        if (!accountRepo.existsById(accountId)) {
            throw new NotFoundException("account with id -" + accountId + "- does not exist");
        }
        if (!customerRepo.existsById(customerId)) {
            throw new NotFoundException("account with id -" + customerId + "- does not exist");
        }
        BankAccount bankAccount;
        bankAccount = accountRepo.getById(accountId);
        long ABalance = bankAccount.getBalance();
        if (money > ABalance){
            throw new ZeroInYourBankAccount("You are running out of money, time to freak out ;)");
        }
        bankAccount.setBalance(ABalance - money);
    }



}
