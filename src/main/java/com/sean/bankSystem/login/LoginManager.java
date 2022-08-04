package com.sean.bankSystem.login;

import com.sean.bankSystem.exceptions.LoginException;
import com.sean.bankSystem.services.AdminService;
import com.sean.bankSystem.services.BankService;
import com.sean.bankSystem.services.ClientService;
import com.sean.bankSystem.services.CustomerService;
import com.sean.bankSystem.util.DataUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;



@Service
public class LoginManager {

    public static ClientService login(String email, String password, ClientType clientType, ApplicationContext ctx) throws LoginException {
        switch (clientType) {
            case ADMINISTRATOR:
                ClientService adminService = ctx.getBean(AdminService.class);
                if (adminService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }

                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged ");
                return adminService;

            case BANK:
                BankService companyService = ctx.getBean(BankService.class);
                if (companyService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }
                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged ");
                return companyService;

            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }
                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged");
                return customerService;
        }
        throw new LoginException("please try again");
    }
}
