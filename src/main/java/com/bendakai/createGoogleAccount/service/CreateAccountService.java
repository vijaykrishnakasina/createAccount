package com.bendakai.createGoogleAccount.service;

import com.bendakai.createGoogleAccount.dto.Acccount;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ADMIN on 5/7/2016.
 */

@Service
public class CreateAccountService {

    @Autowired
    Captcha captcha;

    RandomStringUtils randomStringUtils = new RandomStringUtils();



    public Boolean createAccount() {
        Acccount account = genrateAccountDetails();
        Boolean accountCreated = createAccountBasedOnInfo(account);
        if(accountCreated){
            saveAccountDetails(account);
        }
        return accountCreated;
    }

    private Boolean createAccountBasedOnInfo(Acccount account) {
        Boolean accountCreated = Boolean.FALSE;
        System.out.println(account);
        WebDriver driver  = new FirefoxDriver();
        driver.navigate().to("https://accounts.google.com/SignUp");
        driver.manage().window().maximize();
        String appTitle = driver.getTitle();
        System.out.println("Application title is :: "+appTitle);
        driver.findElement(By.id("FirstName")).sendKeys(account.getFirstName());
        driver.findElement(By.id("LastName")).sendKeys(account.getLastName());
        driver.findElement(By.id("GmailAddress")).sendKeys(account.getEmail());
        driver.findElement(By.id("Passwd")).sendKeys(account.getPassword());
        driver.findElement(By.id("PasswdAgain")).sendKeys(account.getPassword());

        driver.findElement(By.id("BirthDay")).sendKeys(account.getBirthDay());
        driver.findElement(By.id("BirthYear")).sendKeys(account.getBirthYear());

        driver.findElement(By.xpath("//div[@role='listbox']")).sendKeys(account.getBirthMonth());
        driver.findElement(By.xpath("//div[@aria-activedescendant=':d']")).sendKeys(account.getGender());

        driver.findElement(By.xpath("//input[@id='TermsOfService']")).click();
        String captchaURL = driver.findElement(By.id("recaptcha_challenge_image")).getAttribute("src");
        String captchaValue = captcha.solveCaptcha(captchaURL);
        //TODO : Need to sove the captcha
        accountCreated = Boolean.TRUE;

        return accountCreated;
    }

    //TODO : This method still needs to be implemnted
    private void saveAccountDetails(Acccount account) {
        System.out.println("this method still nedds to be implemnted");
        Gson gson = new Gson();
        try(FileWriter fw = new FileWriter("outfilename.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(account);
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    private Acccount genrateAccountDetails() {
        Acccount acccount = new Acccount();
        acccount.setFirstName(randomStringUtils.randomAlphabetic(6));
        acccount.setLastName(randomStringUtils.randomAlphabetic(6));
        acccount.setEmail(acccount.getFirstName()+"."+acccount.getLastName()+"@gmail.com");
        acccount.setPassword("Labs@2016");
        acccount.setGender("Male");
        acccount.setBirthDay("01");
        acccount.setBirthMonth("January");
        acccount.setBirthYear("1992");
        acccount.setPhoneNumber(null);
        acccount.setReferenceEmail(null);
        acccount.setLocation("India");

        return acccount;
    }
}
