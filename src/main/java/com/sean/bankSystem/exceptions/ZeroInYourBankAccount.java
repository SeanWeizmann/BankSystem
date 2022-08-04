package com.sean.bankSystem.exceptions;

public class ZeroInYourBankAccount extends Exception {

    public ZeroInYourBankAccount() {
        super();
    }

    public ZeroInYourBankAccount(String message) {
        super(message);
    }

    public ZeroInYourBankAccount(String message, Throwable cause) {
        super(message, cause);
    }

    public ZeroInYourBankAccount(Throwable cause) {
        super(cause);
    }

    protected ZeroInYourBankAccount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
