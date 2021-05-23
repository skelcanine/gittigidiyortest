package com.gittigidiyorTestinium;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class Page {
    //Defining river and wait variables
    protected WebDriver driver;
    protected WebDriverWait wait;

    Page(WebDriver driver){
        //Constructing
        this.driver = driver;
        wait = new WebDriverWait(this.driver,30);
    }

    public String getCurrentURL(){
        //Getting current url
        return driver.getCurrentUrl();
    }
    public void scrollToElement(WebElement element){
        //Scroll to element
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Waiting until page load completely
    public void waitUntilPageLoad(WebDriver driver) {
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

}
