import Listeners.CustomListeners;
import Listeners.RetryTestcase;
import org.example.Flight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

@Listeners(CustomListeners.class)
public class Testcases  {

    WebDriver driver;
    @Test(priority =0,retryAnalyzer = RetryTestcase.class)
    void Login() throws IOException {
        Properties obj = Flight.setUp();
        Properties obj2 = Flight.load();

        driver = new ChromeDriver();

        String url = "https://www.amazon.in";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement login = driver.findElement(By.xpath(obj2.getProperty("amazon.login.xpath")));
        Reporter.log("Clicking on the login element in the main page",true);
        login.click();

        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

        WebElement email = driver.findElement(By.id(obj2.getProperty("amazon.email.Id")));
        WebElement continueButton = driver.findElement(By.id(obj2.getProperty("amazon.continueButton.Id")));

        email.clear();
        Reporter.log("Entering the email/Mobile",true);
        email.sendKeys(obj.getProperty("Login"));
        Reporter.log("Clicking Continue button",true);
        continueButton.click();

        WebElement password = driver.findElement(By.id(obj2.getProperty("amazon.password.Id")));
        WebElement loginButton = driver.findElement(By.id(obj2.getProperty("amazon.loginButton.Id")));
        password.clear();

        Reporter.log("entering the password",true);
        password.sendKeys(obj.getProperty("Password"));

        Reporter.log("Clicking login button",true);
        loginButton.click();
//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.loginTitle.xpath"))));

        WebElement loginTitle = driver.findElement(By.xpath(obj2.getProperty("amazon.loginTitle.xpath")));

        Assert.assertEquals("Hello, Shobhit",loginTitle.getText());
        Reporter.log("Logged in successfully and Welcome message verified");

    }

    @Test(priority =1,retryAnalyzer = RetryTestcase.class)
    void searchItem() throws IOException {
        Properties obj2 = Flight.load();

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        WebElement searchTab = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTab.xpath")));
        searchTab.sendKeys("Apple iPhone 13 (128GB) - Midnight");
        Reporter.log("Searching Apple iphone by entering text in Search box ",true);

        WebElement searchTabIcon = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTabIcon.xpath")));
        searchTabIcon.click();
        Reporter.log("Clicking on search Icon ",true);

        WebElement searchItem = driver.findElement(By.xpath(obj2.getProperty("amazon.searchItem.xpath")));
        searchItem.click();
        Reporter.log("Clicking on search Item ",true);

        String searchItemText = searchItem.getText();
        Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight",searchItemText);
        Reporter.log("Verifying search item displayed on screen",true);

    }
    @Test(priority =2,retryAnalyzer = RetryTestcase.class)
    void addToCart() throws IOException {
        Properties obj2 = Flight.load();

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement addToCart = driver.findElement(By.xpath(obj2.getProperty("amazon.addToCart.xpath")));
        addToCart.click();
        Reporter.log("Clicking on add To cart Button ",true);

        Assert.assertTrue(addToCart.isDisplayed());
        Reporter.log("Verifying addToCart button displayed on screen",true);

    }
    @Test(priority =3,retryAnalyzer = RetryTestcase.class)
    void checkOut() throws IOException {
        Properties obj2 = Flight.load();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.checkOut.xpath"))));


//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"attach-sidesheet-checkout-button\"]/span/input"))));

        WebElement checkOut = driver.findElement(By.xpath(obj2.getProperty("amazon.checkOut.xpath")));

        Assert.assertTrue(checkOut.isDisplayed());
        Reporter.log("Verifying checkOut button displayed on screen",true);
        Reporter.log("Clicking on checkOut Button ",true);
//        checkOut.click();

    }

    @Test(priority =4,retryAnalyzer = RetryTestcase.class)
    void verifyCartProductSameAsSelected() throws IOException {
        Properties obj2 = Flight.load();

        WebElement Cart = driver.findElement(By.xpath(obj2.getProperty("amazon.Cart.xpath")));
        Cart.click();
//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.cartItem.xpath"))));


        WebElement cartItem = driver.findElement(By.xpath(obj2.getProperty("amazon.cartItem.xpath")));

        Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight",cartItem.getText());
        Reporter.log("Verifying cart item name same as selected",true);


    }

    @Test(priority =5,retryAnalyzer = RetryTestcase.class)
    void addMultipleItemsSameProduct() throws IOException {
        Properties obj2 = Flight.load();

        WebElement searchTab = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTab.xpath")));
        searchTab.sendKeys("Apple iPhone 13 (128GB) - Midnight");

        WebElement searchTabIcon = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTabIcon.xpath")));
        searchTabIcon.click();

        WebElement searchItem = driver.findElement(By.xpath(obj2.getProperty("amazon.searchItem.xpath")));
        searchItem.click();

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));

        WebElement addToCart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.addToCart.xpath")));
        addToCart2.click();
        Reporter.log("Apple iphone added to the cart",true);

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.Cart.xpath"))));

        WebElement Cart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.Cart.xpath")));
        Cart2.click();
        Reporter.log("Apple iphone added to the cart again",true);

        WebElement quantity = driver.findElement(By.xpath(obj2.getProperty("amazon.quantity.xpath")));

        Assert.assertTrue(Integer.parseInt(quantity.getText())>1);
        Reporter.log("Verifying Apple iphone quantity is greater than 1",true);

    }

    @Test(priority =6,retryAnalyzer = RetryTestcase.class)
    void editQuantityOfProduct() throws IOException {
        Properties obj2 = Flight.load();

        WebElement quantity2 = driver.findElement(By.xpath(obj2.getProperty("amazon.quantity.xpath")));
        quantity2.click();

        WebElement qty = driver.findElement(By.xpath(obj2.getProperty("amazon.qty.xpath")));
        qty.click();
        Reporter.log("Quantity of Apple iphone changed",true);

        Assert.assertTrue(Integer.parseInt(quantity2.getText())==4);
        Reporter.log("Verifying Apple iphone quantity is changed to 4",true);

    }

    @Test(priority =7,retryAnalyzer = RetryTestcase.class)
    void addMultipleItemsDifferentProduct() throws IOException {
        Properties obj2 = Flight.load();

//        try {
//            Thread.sleep(10000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.searchTab.xpath"))));

        WebElement searchTab2 = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTab.xpath")));
        searchTab2.sendKeys("Lenovo Mouse");

        WebElement searchTabIcon2 = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTabIcon.xpath")));
        searchTabIcon2.click();

        WebElement searchItem2 = driver.findElement(By.xpath(obj2.getProperty("amazon.searchItem2.xpath")));
        searchItem2.click();
        Reporter.log("Searching Lenovo 600",true);

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(3));

        WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.addToCart.xpath"))));

        WebElement addToCart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.addToCart.xpath")));
        addToCart2.click();

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait3.until(ExpectedConditions.elementToBeClickable(Cart));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.Cart.xpath"))));

        WebElement Cart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.Cart.xpath")));
        Cart2.click();
        Reporter.log("Lenovo 600 added to the cart",true);

        Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight",driver.findElement(By.xpath("//span[@class=\"a-truncate-cut\" and contains(text(),\"Apple iPhone 13 (128GB) - Midnight\")]")).getText());
        Reporter.log("Verifying Apple iphone present in the cart",true);
        Assert.assertEquals("Lenovo 600 Bluetooth 5.0 Silent Mouse: Compact, Portable, Dongle-Free Multi-Device connectivity with Mi…",driver.findElement(By.xpath("//span[@class=\"a-truncate-cut\" and contains(text(),\"Lenovo 600 Bluetooth 5.0 Silent Mouse: Compact, Portable, Dongle-Free Multi-Device connectivity with Mi…\")]")).getText());
        Reporter.log("Verifying Lenovo 600 present in the cart",true);

    }

    @Test(priority =8,retryAnalyzer = RetryTestcase.class)
    void DeleteMultipleItemsDifferentProduct() throws IOException {
        Properties obj2 = Flight.load();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.deleteItem.xpath"))));

        WebElement deleteItem1 = driver.findElement(By.xpath(obj2.getProperty("amazon.deleteItem.xpath")));
        deleteItem1.click();
        Reporter.log("Clicking on delete button for Lenovo 600",true);
        Assert.assertEquals("Lenovo 600 Bluetooth 5.0 Silent Mouse: Compact, Portable, Dongle... was removed from Shopping Cart.",driver.findElement(By.xpath("(//span[@class=\"a-size-base\"])[1]")).getText());
        Reporter.log("Verifying Lenovo 600 removed from the cart",true);

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.deleteItem.xpath"))));

        WebElement deleteItem2 = driver.findElement(By.xpath(obj2.getProperty("amazon.deleteItem.xpath")));
        deleteItem2.click();
        Reporter.log("Clicking on delete button for Apple iphone",true);
        Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight was removed from Shopping Cart.",driver.findElement(By.xpath("(//span[@class=\"a-size-base\"])[1]")).getText());
        Reporter.log("Verifying Apple iPhone 13 removed from cart",true);

    }

    @Test(priority =9,retryAnalyzer = RetryTestcase.class)
    void deleteSingleItem() throws IOException {
        Properties obj2 = Flight.load();

        WebElement searchTab = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTab.xpath")));
        searchTab.sendKeys("Apple iPhone 13 (128GB) - Midnight");

        WebElement searchTabIcon = driver.findElement(By.xpath(obj2.getProperty("amazon.searchTabIcon.xpath")));
        searchTabIcon.click();

        WebElement searchItem = driver.findElement(By.xpath(obj2.getProperty("amazon.searchItem.xpath")));
        searchItem.click();

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(4));
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.addToCart.xpath"))));


        WebElement addToCart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.addToCart.xpath")));
        addToCart2.click();

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.Cart.xpath"))));

        WebElement Cart2 = driver.findElement(By.xpath(obj2.getProperty("amazon.Cart.xpath")));
        Cart2.click();

//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj2.getProperty("amazon.deleteItem.xpath"))));

        WebElement deleteItem2 = driver.findElement(By.xpath(obj2.getProperty("amazon.deleteItem.xpath")));
        deleteItem2.click();
        Reporter.log("Clicking on delete button for Apple iphone",true);
        Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight was removed from Shopping Cart.",driver.findElement(By.xpath("(//span[@class=\"a-size-base\"])[1]")).getText());
        Reporter.log("Verifying Apple iPhone 13 removed from cart",true);

    }
    @AfterClass
    void teardown(){
        driver.quit();
    }
}