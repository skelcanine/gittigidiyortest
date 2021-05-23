package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends Page {
    //Define location of username
    private final By loginName = new By.ByXPath("/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]/span");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public String getLoginName() {
        waitUntilPageLoad(driver);
        //Getting username
        return driver.findElement(loginName).getText();
    }


}