package com.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class BasePage {

    public static WebDriver driver;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void navigateToSite(String site) {
        driver.get(site);
    }

    {
    }

    // Determine if an array is sorted in ascending order
    //
    public boolean isSorted(double[] array) {
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

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    public void hoverTShirts(By by) {
        Actions hover = new Actions(driver);
        hover.moveToElement(driver.findElement(by)).moveToElement(driver.findElement(by))
                .build()
                .perform();
    }

    public void tShirtsClick(By by) {
        Actions hover = new Actions(driver);

        hover.moveToElement(driver.findElement(by)).moveToElement(driver.findElement(by))
                .click()
                .build()
                .perform();
    }

    public void hoverDress(By by) {
        Actions hover = new Actions(driver);
        hover.moveToElement(driver.findElement(by)).moveToElement(driver.findElement(by))
                .build()
                .perform();
    }

    public void dressClick(By by) {
        Actions hover = new Actions(driver);

        hover.moveToElement(driver.findElement(by)).moveToElement(driver.findElement(by))
                .click()
                .build()
                .perform();
    }

    public void scrollPageDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, - 2000)");
    }

    public void dressesHover(By by) {
        Actions hover = new Actions(driver);

        hover.moveToElement(driver.findElement(by)).moveToElement(driver.findElement(by))
                .build()
                .perform();
    }

    public void textCheck(String s, By by) {
        String cartString = driver.findElement(by).getText();
        Assert.assertEquals(s, cartString);
    }

    public void infoCheck(String s, By by) {
        String cartString = driver.findElement(by).getText();
        Assert.assertEquals(s, cartString);
    }

    public void inputTextIntoField(String myText, By by) {
        driver.findElement(by).sendKeys(myText);
    }


    //TABLE ORDER CHECK


    public void getTable(By searchGrid, By columnName) {
        WebElement storeItems = driver.findElement(searchGrid);

            List<WebElement> linkList = storeItems.findElements(columnName);

            for (WebElement link : linkList) {
                link.getText();
            }
            double[] prices = new double[linkList.size()];
            for (int j = 0; j < linkList.size(); j++) {
                prices[j] = Double.parseDouble(linkList.get(j).getText().replace("$", ""));
            }
            int count = 0;
            double sum = 0;
            for (int x = 0; x < prices.length; x++) {
                if (prices[x] > 20) {
                    count++;
                    {
                        sum += prices[x];
                    }

                }
            }
            System.out.print("All items according to the search result => ");
            System.out.println(Arrays.toString(prices));
            System.out.println("The number of books more expensive than 20 is: " + count);
            System.out.println("The amount of all books more expensive than 20 is: $" + sum);

        }
    }


