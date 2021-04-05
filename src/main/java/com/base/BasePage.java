package com.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class BasePage {

    public WebDriver driver;

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


}
