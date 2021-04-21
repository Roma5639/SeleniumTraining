package page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Zadacha9PageObject extends BasePage {

    public Zadacha9PageObject(WebDriver driver) {
        super(driver);
    }

    public By searchField = By.xpath("//*[@id='keyword']");
    public By searchButton = By.xpath("//button[@class='btn btn-default'] ");
    public By searchResultGrid = By.xpath("//*[@id='pwls_srchresult']");
    public By prices = By.xpath("//div[@class='reg-price']");
    public String bookSiteURL = "https://www.powells.com/";

    public void inputText() {
        String myText = "sartre a life";
        super.inputTextIntoField(myText, searchField);
    }

    public  void clickSearchButton(){
        super.click(searchButton);
    }

    public void sortColumnOrderCheck() {

        super.getTable(searchResultGrid, prices);


    }
}