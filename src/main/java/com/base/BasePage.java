package com.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public static WebDriver driver;

    private static String elementText  = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateToSite(String site){
        driver.get(site);
    }

    {
    }
        // Determine if an array is sorted in ascending order
        //
        public  boolean isSorted ( double[] array )
        {
            boolean inOrder = true;

            // scan the list starting at index 0
            for (int j = 0; j < array.length - 1 && inOrder; j++) {
                // check the pair starting at j
                inOrder = array[j] <= array[j + 1];
            }

            return inOrder;
        }


//    public static String textFromElement(By args){
//        elementText = driver.findElement(By.xpath()).getText();
//        return elementText;
//    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void waitForVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

    }

    public void moveToElement(By by){
        Actions hover = new Actions(driver);
        //add the item to the cart

        WebElement webElement = driver.findElement(by);
        hover.moveToElement(webElement)
                .build()
                .perform();
    }
    public void moveToElementAndClick(By by){
        Actions hover = new Actions(driver);
        //add the item to the cart

        WebElement webElement = driver.findElement(by);
        hover.moveToElement(webElement)
                .click()
                .build()
                .perform();
    }


}
