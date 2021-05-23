package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoToURL extends Page {

    public GoToURL(WebDriver driver){
        super(driver);
    }

    public void moveTo (String URL){
        //Going to parameter url
        driver.get(URL);
        waitUntilPageLoad(driver);
    }

    public HomePage loginPage(){
        waitUntilPageLoad(driver);
        //Locating login buttons
        String hoverTargetXpath = "/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div";
        String clickTargetXpath = "/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div";
        //Hover and click to go login page
        hoverAndClick(driver, hoverTargetXpath, clickTargetXpath);
        //Return new homepage
        return new HomePage(driver);
    }

    public BasketPage basketPage(){
        waitUntilPageLoad(driver);
        By basketLink =new By.ByXPath("/html/body/div[3]/div[3]/div/div[4]/div[3]/a/div[2]");
        WebElement basketElement = driver.findElement(basketLink);
        Actions action = new Actions(driver);
        action.pause(1000).moveToElement(basketElement).pause(1000).click(basketElement).build().perform();

        return new BasketPage(driver);
    }


    public void hoverAndClick(WebDriver driver, String hoverTargetXpath, String clickTargetXpath){

        WebElement elementHover = driver.findElement(By.xpath(hoverTargetXpath));

        Actions action = new Actions(driver);
        action.moveToElement(elementHover).click(elementHover).pause(1000).build().perform();
        WebElement elementClick = driver.findElement(By.xpath(clickTargetXpath));
        action.moveToElement(elementClick).click(elementClick).pause(1000).build().perform();
    }


}
