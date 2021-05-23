package com.gittigidiyorTestinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public Item addBasketPrice(Item item){
        //Item price location
        By itemPriceOnBasket = new By.ByXPath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div/div[1]/div/div[5]/div[2]/div[3]/p");
        //Handling the price
        String itemPriceString = driver.findElement(itemPriceOnBasket).getText();
        itemPriceString = itemPriceString.replaceAll("[.TL]", "");
        itemPriceString = itemPriceString.replace(",",".");
        //setting basket price to item
        item.setPriceOnBasket(Float.parseFloat(itemPriceString));
        //returning item
        return item;
    }

    public String changeQauntity(String i) {
        //Quantity location
        By selectQuantity =new  By.ByXPath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select");
        WebElement selectQuantityElement = driver.findElement(selectQuantity);
        //Getting max amount stock of item
        Integer maxAmount = Integer.parseInt(selectQuantityElement.getAttribute("data-maxamount"));
        //Setting quantity by handling stock
        if (maxAmount>=2) {
            Select drpQuantity = new Select(selectQuantityElement);
            drpQuantity.selectByValue(i);
            return "Has stock.";
        }
        return "Max amount is too low.";

    }

    public String getItemQuantity() {
        //Quantity location
        By selectQuantity =new  By.ByXPath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select");
        //Getting quantity value
        String quantity = driver.findElement(selectQuantity).getAttribute("value");
        //Returning value
        return quantity;
    }
    public void deleteItem(){
        //Delete element location
        By deleteItemButton =new By.ByXPath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a");
        WebElement deleteElement = driver.findElement(deleteItemButton);
        //Clicking element
        Actions action = new Actions(driver);
        action.click(deleteElement).pause(1000).build().perform();
    }
    public String BasketEmpty(){
        //Creating parameter
        String isEmpty="";
        //Locating basket string
        By basketString =new By.ByXPath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[2]/h2");
        //Checking if element exist
        if(!driver.findElements(basketString).isEmpty()){
            isEmpty = driver.findElement(basketString).getText();
        }
        //Returning string
        return isEmpty;
    }
}
