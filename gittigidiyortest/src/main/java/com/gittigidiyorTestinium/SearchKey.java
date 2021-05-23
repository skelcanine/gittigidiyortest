package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchKey extends Page {
    //Setting searchbar and submit location
    private final By searchBar =new By.ByXPath("/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input");
    private final By submit =new By.ByXPath("/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[2]/button");


    public SearchKey(WebDriver driver){
        super(driver);

    }

    public HomePage search(String keyWord, Integer pageNumber){
        waitUntilPageLoad(driver);


        //Entering keyword and clicking submit button
        driver.findElement(searchBar).sendKeys(keyWord);
        driver.findElement(submit).click();
        //Locating page number
        By pageNum = new By.ByXPath("/html/body/div[4]/div[1]/div/div[2]/div[5]/ul/li["+pageNumber+"]/a");
        waitUntilPageLoad(driver);
        //Scrolling to page number
        WebElement scrollTo = driver.findElement(pageNum);
        scrollToElement(scrollTo);
        //Clicking the page number
        driver.findElement(pageNum).click();
        //Returning page
        return new HomePage(driver);
    }
}
