package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignIn extends Page {
    //Defining locations of username,password and submit elements
    private final By usernameBy = By.id("L-UserNameField");

    private final By passwordBy = By.id("L-PasswordField");

    private final By signinBy = By.id("gg-login-enter");

    public SignIn(WebDriver driver){
        super(driver);
    }

    public HomePage loginValidUser(String userName, String password) {
        waitUntilPageLoad(driver);
        //Sending keys to desired locations and clicking to submit button
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(signinBy).click();
        //Returning new home page
        return new HomePage(driver);
    }

}