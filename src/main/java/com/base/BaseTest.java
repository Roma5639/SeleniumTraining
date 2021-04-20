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


    //PAGE OBJECTS

    //zadacha9

    public By keywordBy = By.xpath("//*[@id='keyword']");
    public By searchButton = By.xpath("//button[@class='btn btn-default'] ");
    public By searchResultGrid = By.xpath("//*[@id='pwls_srchresult']");
    public By prices = By.xpath("//div[@class='reg-price']");
    public String bookSiteURL =  "https://www.powells.com/";

    //zadacha8
    public String onlineClothesStore =  "http://automationpractice.com/";

    public By addressField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[1]");
    public String addressText = "Selenium Framework, Research Triangle Park, North Carolina, USA";
    public By phoneField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]");
    public String phoneText = "Call us now: (347) 466-7432";
    public By emailField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]");
    public String emailText = "Email: support@seleniumframework.com";

    public By tShirtsButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a");
    public By brandLogo = By.xpath("//img[@class='logo img-responsive']");
    public By tShirtItem = By.xpath("//img[@title='Faded Short Sleeve T-shirts']");
    public By addToCartButton = By.xpath("//*[text()='Add to cart']");
    public By successMessage = By.xpath("//i[@class='icon-ok']");
    public By continueButton = By.xpath("//span[@title='Continue shopping']");
    public By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
    public By blockTopMenu = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a");
    public By eveningDressButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a");
    public By cartSuccessText = By.xpath("//*[@id='layer_cart']//span[@style='display: inline;']");



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
