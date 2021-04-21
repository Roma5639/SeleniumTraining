package page;

import com.base.BasePage;
import com.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Zadacha8PageObject extends BasePage {

    public Zadacha8PageObject(WebDriver driver) {
        super(driver);
    }


    public By tShirtsButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a");
    public By brandLogo = By.xpath("//img[@class='logo img-responsive']");
    public By tShirtItem = By.xpath("//img[@title='Faded Short Sleeve T-shirts']");
    public By addToCartButton = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span");
    public By successMessage = By.xpath("//i[@class='icon-ok']");
    public By continueButton = By.xpath("//span[@title='Continue shopping']");
    public By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
    public By dressesItem = By.xpath("//img[@title='Printed Dress']");
    public By blockTopMenu = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a");
    public By eveningDressButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a");
    public By cartSuccessText = By.xpath("//*[@id='layer_cart']//span[@style='display: inline;']");

    public void tShirtsClick(){
        super.click(tShirtsButton);

    }

    public void waitForLogo(){
        super.waitForVisibility(brandLogo);

    }


    public void hoverTShirtsAndClickAddToCart(){
        super.hoverTShirts(tShirtItem);
        super.tShirtsClick(addToCartButton);
    }



    public void waitForSuccessMessage(){
        super.waitForVisibility(successMessage);

    }

//    public void waitPopup(){
//        super.waitForVisibility((By) driver.findElement(successMessage));
//
//    }
    public void continueButtonClick(){
        super.click(continueButton);

    }

    public void hoverDressAndClickAddToCart(){
        super.hoverDress(dressesItem);
        super.dressClick(addToCartButton);
    }

    public void scrollPageDown(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, - 2000)");
    }

    public void dressesHover(){
        super.dressesHover(dressesButton);

    }

    public void waitForBlockTopMenu(){
        super.waitForVisibility(blockTopMenu);

    }
    public void eveningDressClick(){
        super.click(eveningDressButton);

    }
    public void cartTextCheck(){
        String s = "There are 2 items in your cart.";
        super.textCheck(s, cartSuccessText);

    }

}
