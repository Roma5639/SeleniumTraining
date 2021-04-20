package com.base;

import org.openqa.selenium.*;

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
}
