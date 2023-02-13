package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Flight {
    public static Properties setUp() throws IOException {

        File src=new File(System.getProperty("user.dir")+"\\application.properties");
        FileInputStream objfile=new FileInputStream(src);
        Properties obj=new Properties();
        obj.load(objfile);
        System.out.println("application.properties file loaded with path as:"+" "+System.getProperty("user.dir")+"\\application.properties");

        return obj;
    }

    public static Properties load() throws IOException {

        File source = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Object_repo.properties");
        FileInputStream objectfile = new FileInputStream(source);
        Properties obj2 = new Properties();
        obj2.load(objectfile);
        System.out.println("Object_repo.properties file loaded with path as:" + " " + System.getProperty("user.dir") + "\\src\\main\\resources\\Object_repo.properties");

        return obj2;
    }


}
