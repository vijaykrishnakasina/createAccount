package com.bendakai.createGoogleAccount.service;

import org.springframework.stereotype.Service;

/**
 * Created by ADMIN on 5/7/2016.
 */
@Service
public class Captcha {

    public String solveCaptcha(String imageURL){
        System.out.println("solving image captcha \n"+imageURL);
        return "";
    }
}
