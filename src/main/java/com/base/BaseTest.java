package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static WebDriver driver;
    private static ThreadLocal <WebDriver> DRIVER_CONTAINER = new ThreadLocal<WebDriver>();

    public String onlineClothesStore = "http://automationpractice.com/";


    @BeforeClass
    public static void setupDriver(){

        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        DRIVER_CONTAINER.set(driver);

    }

    public WebDriver getDriver(){
        return DRIVER_CONTAINER.get();
    }

//    @AfterMethod
//    public void quit(){
//
//        if (driver !=null){
//            driver.manage().deleteAllCookies();
//            driver.quit();
//            DRIVER_CONTAINER.remove();
//        }
//    }
}
