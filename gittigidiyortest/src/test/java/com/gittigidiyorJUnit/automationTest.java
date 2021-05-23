package com.gittigidiyorJUnit;
import com.gittigidiyorTestinium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class automationTest {
    private static final Logger logger = LogManager.getLogger(automationTest.class);
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //Setting up driver and maximizing window
        String driverPath = "";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void stage01_testOpenMainPage() {
        //Opening mainpage
        String URL = "https://www.gittigidiyor.com/";
        GoToURL goToDesiredPage = new GoToURL(driver);
        goToDesiredPage.moveTo(URL);
        //Getting current url
        String CurrentUrl = goToDesiredPage.getCurrentURL();
        //Testing if this is desired page
        assertEquals(CurrentUrl, "https://www.gittigidiyor.com/");
        //logging
        if (CurrentUrl.equals("https://www.gittigidiyor.com/")) {
            logger.info("( stage01_testOpenMainPage )Actual location : " + CurrentUrl);
        } else {
            logger.error("( stage01_testOpenMainPage )Actual location : " + CurrentUrl);
        }
    }
    @Test
    public void stage02_testOpenLoginPage() {
        //Opening loginpage
        GoToURL goToDesiredPage = new GoToURL(driver);
        HomePage homePage = goToDesiredPage.loginPage();
        //Getting current url
        String currentUrl = homePage.getCurrentURL();
        //Testing if this is desired page
        assertEquals(currentUrl, "https://www.gittigidiyor.com/uye-girisi?s=1");
        //logging
        if (currentUrl.equals("https://www.gittigidiyor.com/uye-girisi?s=1")) {
            logger.info("( stage02_testOpenLoginPage )Actual location : " + currentUrl);
        } else {
            logger.error("( stage02_testOpenLoginPage )Actual location : " + currentUrl);
        }
    }
    @Test
    public void stage03_testLogin() {
        //Setting parameters for login
        String userName = "";
        String password = "";
        //Sign in
        SignIn signIn = new SignIn(driver);
        HomePage homePage = signIn.loginValidUser(userName, password);
        //Get login name
        String loginName = homePage.getLoginName();
        //Testing if login name is correct
        assertEquals(loginName, (userName));
        //logging
        if (loginName.equals(userName)) {
            logger.info("Loged in succesfully.");
            logger.info("( stage02_testOpenLoginPage )Actual username : " + loginName + password);
        } else {
            logger.error("Unable to login.");
            logger.error("( stage02_testOpenLoginPage )Actual username : " + loginName);
        }
    }

    @Test
    public void stage04_searchAndPage() {
        //Setting search keyword and page paramter
        String keyWord = "bilgisayar";
        Integer pageNumber = 2;
        //Searching with parameters
        SearchKey searchKey = new SearchKey(driver);
        HomePage homePage = searchKey.search(keyWord, pageNumber);
        //Getting current url
        String currentUrl = homePage.getCurrentURL();
        //Testing if this is desired page
        assertEquals(currentUrl, "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
        //logging
        if (currentUrl.equals("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2")) {
            logger.info("( stage02_testOpenLoginPage )Actual location : " + currentUrl);
        } else {
            logger.error("( stage02_testOpenLoginPage )Actual location : " + currentUrl);
        }
    }

    @Test
    public void stage05_addItemCheckPrice() {
        //Creating random variable
        Random r = new Random();
        int productNumber = r.nextInt(49) + 1;
        //Adding item to basket
        ItemOperations itemOperations = new ItemOperations(driver);
        Item item = itemOperations.addToBasket(productNumber);
        //Go to basket page
        GoToURL goToDesiredPage = new GoToURL(driver);
        BasketPage basketPage = goToDesiredPage.basketPage();
        //Getting item price on search page
        item = basketPage.addBasketPrice(item);
        //Checking if search page price and basket price equals
        assertEquals(item.getPriceOnSearch(), item.getPriceOnBasket());
        if (item.getPriceOnSearch().equals(item.getPriceOnBasket())) {
            logger.info("Price on search page and basket page matches = " + item.getPriceOnSearch());
        } else {
            logger.error("Price on search page and basket page didnt match.(Check discount)");
            logger.info("Search page price= " + item.getPriceOnBasket());
            logger.info("Search page price= " + item.getPriceOnSearch());
        }
    }

    @Test
    public void stage06_basketChangeQuantity() {
        //Giving quantity parameter
        String toAmount = "2";
        //Changing quantity
        BasketPage basketPage = new BasketPage(driver);
        String action = basketPage.changeQauntity(toAmount);

        //Checking if action is succesful by minding stock
        //logging
        if (action.equals("Has stock.")) {
            String pageQuantity = basketPage.getItemQuantity();
            if (pageQuantity.equals(toAmount)) {
                logger.info("Changed quantity succesfully to= " + toAmount);
            }
        } else {
            logger.error("Max amount is 1. Select another item.");
        }
        //Waiting a bit after changing quantity
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    @Test
    public void stage07_emptyBasket() {
        //Deleting the item
        BasketPage basketPage = new BasketPage(driver);
        basketPage.deleteItem();
        //Gettin empty string if exist
        String isEmpty = basketPage.BasketEmpty();
        //Checking if basket is empty
        assertEquals(isEmpty, "Sepetinizde ürün bulunmamaktadır.");
        //logging
        if (isEmpty.equals("Sepetinizde ürün bulunmamaktadır.")) {
            logger.info("Succesfully cleared basket .");
        } else {
            logger.error("Unable to clear basket.");
        }
    }
}
