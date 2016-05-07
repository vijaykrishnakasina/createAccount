package com.bendakai.createGoogleAccount.controller.gmail;

import com.bendakai.createGoogleAccount.controller.ICreateAccount;
import com.bendakai.createGoogleAccount.service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ADMIN on 5/7/2016.
 */

@RestController
public class createGmailAccount implements ICreateAccount {

    @Autowired
    CreateAccountService createAccountService;

    @Override
    public String createAccount() {
        System.out.println("from helloWorld");
        createAccountService.createAccount();
        return "hello world";
    }
}
