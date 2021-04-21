package page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZadachaStoreInfoCheck extends BasePage {

    public ZadachaStoreInfoCheck(WebDriver driver) {
        super(driver);
    }


    public By addressField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[1]");
    public String addressText = "Selenium Framework, Research Triangle Park, North Carolina, USA";
    public By phoneField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]");
    public String phoneText = "Call us now: (347) 466-7432";
    public By emailField = By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]");
    public String emailText = "Email: support@seleniumframework.com";

    public void addressFieldCheck() {
        String s = addressText;
        super.infoCheck(s, addressField);

    }

    public void phoneFieldCheck() {
        String s = phoneText;
        super.infoCheck(s, phoneField);
    }

    public void emailFieldCheck() {
        String s = emailText;
        super.infoCheck(s, emailField);
    }

}
