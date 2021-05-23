package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ItemOperations extends Page {

    public ItemOperations(WebDriver driver) {
        super(driver);
    }
    public Item addToBasket(Integer itemNumber){
        waitUntilPageLoad(driver);
        //Creating new item
        Item item = new Item();
        //Locating item
        By itemNum = new By.ByXPath("/html/body/div[5]/div[1]/div/div[2]/div[3]/div[2]/ul/li["+itemNumber+"]");
        //Scrolling to item
        WebElement scrollTo = driver.findElement(itemNum);
        scrollToElement(scrollTo);
        //Adding item to basket respect to item number
        addItemToBasket(scrollTo,itemNumber);
        //Getting price respect to extra or normal condition
        By itemPriceExtra = new By.ByXPath("/html/body/div[4]/div[1]/div/div[2]/div[4]/div[2]/ul/li["+itemNumber+"]/a/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/p");
        By itemPriceNormal = new By.ByXPath("/html/body/div[5]/div[1]/div/div[2]/div[3]/div[2]/ul/li["+itemNumber+"]/a/div/div/div[1]/div[2]/div/div[1]/div/div/p");
        By itemPrice;
        //Setting Price
        if(!driver.findElements(itemPriceExtra).isEmpty()){
            itemPrice = itemPriceExtra;
        }else{
            itemPrice = itemPriceNormal;
        }
        //Formatting string price
        String itemPriceString = driver.findElement(itemPrice).getText();
        itemPriceString = itemPriceString.replaceAll("[.TL]", "");
        itemPriceString = itemPriceString.replace(",",".");
        //Setting search price to item
        item.setPriceOnSearch(Float.parseFloat(itemPriceString));
        //Returning item
        return item;
    }


    public void addItemToBasket(WebElement item, Integer itemNumber){
        waitUntilPageLoad(driver);
        //Locating basket basket link
        By itemToBasket =new By.ByXPath("/html/body/div[5]/div[1]/div/div[2]/div[3]/div[2]/ul/li["+itemNumber+"]/a/div/div/div[1]/div[3]");
        //Move to item
        Actions action = new Actions(driver);
        action.moveToElement(item).pause(1000).build().perform();
        //Move to addtobasket and click
        WebElement scrollToBasket = driver.findElement(itemToBasket);
        action.moveToElement(scrollToBasket).click(scrollToBasket).pause(1000).build().perform();
    }


}
