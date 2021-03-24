package page;

import com.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenPage extends BasePage {
    /**
     * Constructor for the class with using PageObjects
     *
     * @param driver
     */
    public OpenPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

//    /**
//     * All WebElements are identified by @FindBy annotation
//     */
//    @FindBy(name = "uid")
//    private WebElement userID;
//    @FindBy(name = "password")
//    private WebElement userPassword;
//    @FindBy(name = "btnLogin")
//    private WebElement loginButton;
//    @FindBy (css = "tr.heading3 > td:nth-child(1)")
//    private WebElement userCred;
//
//
//    public void navigateToSite(String site) {
//        super.navigateToSite(site);
//    }
//
//    /**
//     * Submit form with all required params
//     * setUserInput method is predefined in the parrent class
//     * @param strID
//     * @param strPassword
//     */
//    public void loginToSite(String strID, String strPassword){
//        setUserInput(userID, strID);
//        setUserInput(userPassword, strPassword);
//        mouseClick(loginButton);
//    }
//
//    public String getElementText() {
//        return getElementText(userCred);
//    }
