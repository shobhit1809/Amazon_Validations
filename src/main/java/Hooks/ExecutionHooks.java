package Hooks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class ExecutionHooks {

    public static boolean login(){

        System.setProperty("webdriver.chrome.driver","C:\\Users\\CR876AU\\Flight_System_Validations\\chromedriver.exe");
        boolean flag= false;
        WebDriver driver = new ChromeDriver();

        System.out.println(System.getProperty("webdriver.chrome.driver"));

        String url = "https://www.amazon.in";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


        WebElement login = driver.findElement(By.xpath("//a[@id=\"nav-link-accountList\"]"));
        System.out.println("Clicking on the login element in the main page");
        login.click();

        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

        WebElement email = driver.findElement(By.xpath("//input[@id=\"ap_email\"]"));
        WebElement continueButton = driver.findElement(By.xpath("//input[@id=\"continue\"]"));

        email.clear();
        System.out.println("Entering the email/Mobile");
        email.sendKeys("9711982668");
        System.out.println("Clicking Continue button");
        continueButton.click();

        WebElement password = driver.findElement(By.xpath("//input[@id=\"ap_password\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@id=\"signInSubmit\"]"));
        password.clear();
        System.out.println("entering the password");
        password.sendKeys("Global@123");

        System.out.println("Clicking login button");
        loginButton.click();

        WebElement loginTitle = driver.findElement(By.xpath("//span[@id=\"nav-link-accountList-nav-line-1\"]"));
        Assert.assertEquals("Hello, Shobhit",loginTitle.getText());

        if(loginTitle.isDisplayed()){
            flag =true;
        }
        return flag;
    }

}
