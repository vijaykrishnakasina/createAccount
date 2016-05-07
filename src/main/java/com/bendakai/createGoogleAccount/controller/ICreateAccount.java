package com.bendakai.createGoogleAccount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ADMIN on 5/7/2016.
 */
@RestController
public interface ICreateAccount {
    @RequestMapping("/helloWorld")
    public String createAccount();
}
