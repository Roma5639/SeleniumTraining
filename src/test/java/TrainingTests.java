
import com.base.BaseTest;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import okhttp3.internal.http.StatusLine;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.OpenPage;

import javax.security.auth.login.Configuration;
import com.codeborne.selenide.WebDriverRunner;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class TrainingTests extends BaseTest {

    @Test
    public void addRemoveElement() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/add_remove_elements/");
        getDriver().findElement(By.xpath("//button[@onclick='addElement()']")).click();
//6 sec pause
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getDriver().findElement(By.xpath("//button[@onclick='deleteElement()']")).click();
    }


    @Test
    public void popupInput() {

        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/basic_auth");
        getDriver().get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String congrats = getDriver().findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(true, congrats.equals("Congratulations! You must have the proper credentials."));

    }


    @Test
    protected void brokenImages() {
        Integer iBrokenImageCount = 0;

        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/broken_images");

        iBrokenImageCount = 0;
        List<WebElement> image_list = driver.findElements(By.cssSelector("img[src*=\"jpg\"]"));
        /* Print the total number of images on the page */
        System.out.println("The page under test has " + image_list.size() + " images");

        for (WebElement img : image_list) {
            if (img != null) {
                if (img.getAttribute("naturalWidth").equals("0")) {
                    System.out.println(img.getAttribute("outerHTML") + " is broken.");
                    iBrokenImageCount++;
                }
            }
        }
        System.out.println("The page has " + iBrokenImageCount + " broken images");
    }

    @Test
    protected void challengingDOM() {

        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/challenging_dom");

        String innerText = getDriver().findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();

        String innerText2 = getDriver().findElement(By.xpath("//div[@class=\"large-10 columns\"]/table/tbody/tr[7]/td[7]")).getText();

        System.out.println(innerText + "  " + innerText2);
    }




    @Test
    protected void checkBoxes() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/checkboxes");
        //getDriver().findElement(By.cssSelector("//input[@type='checkbox'][2]"));
        WebElement checkBoxSelected = getDriver().findElement(By.xpath("//input[@type='checkbox'][2]"));
        boolean isSelected = checkBoxSelected.isSelected();
        // performing click operation if element is not selected
        if (isSelected == true) {
            checkBoxSelected.click();
        }
    }

    @Test
    protected void contextMenu() {
        Actions actions = new Actions(getDriver());
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/context_menu");
        WebElement contextElement = getDriver().findElement(By.xpath("//*[@id=\"hot-spot\"]"));
        actions.contextClick(contextElement).perform();
        Alert alert = getDriver().switchTo().alert();

        // PAUSE 3 SEC

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // click on OK to accept with accept()
        alert.accept();
        getDriver().quit();
    }

    @Test
    public void digestAuth() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/basic_auth");
        getDriver().get("http://admin:admin@the-internet.herokuapp.com/digest_auth");
        String congrats = getDriver().findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(true, congrats.equals("Congratulations! You must have the proper credentials."));

    }

    @Test
    public void checkingElementOnThePage() {
        //WebDriverWait wait = new WebDriverWait(getDriver(), 4);
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/disappearing_elements");
        List<WebElement> dynamicElement = getDriver().findElements(By.xpath("//a[@href=\"/gallery/\"]"));
        if (dynamicElement.size() == 0) {
            getDriver().navigate().refresh();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gallery']")));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getDriver().findElement(By.xpath("//a[@href=\"/gallery/\"]")).click();
        } else {
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gallery']")));
            getDriver().findElement(By.xpath("//a[@href=\"/gallery/\"]")).click();
        }
    }

    @Test
    public void dragDrop() throws InterruptedException, AWTException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/drag_and_drop");
        Thread.sleep(3000);
        WebElement un = getDriver().findElement(By.cssSelector(".column:nth-of-type(1)"));
        Robot r=new Robot();
        r.mouseMove(500, 350);
        Thread.sleep(2000);
        Actions act=new Actions(getDriver());
        WebElement From=getDriver().findElement(By.xpath("//*[@id='column-a']"));
        //Element on which need to drop.
        WebElement To=getDriver().findElement(By.xpath("//*[@id='column-b']"));
        //Dragged and dropped.
        act.dragAndDrop(From, To).release().build().perform();
    }
//        WebDriverWait wait = new WebDriverWait(getDriver(), 120);
//        List <WebElement> adFrame = getDriver().findElements(By.cssSelector(".column:nth-of-type(1)"));
//        act.clickAndHold(getDriver().findElement(By.xpath("//*[@id='column-a']")))
//                .moveToElement(getDriver().findElement(By.xpath("//*[@id='column-b']")))
//                .release()
//                .build()
//                .perform();


//use to move cursor on particular element and passing control


        //int xaxis = un.getLocation().x;
        //int yaxis=un.getLocation().y;
        //int width = un.getSize().width;
       // int height= un.getSize().height;

       // r.mouseMove(xaxis+width/2, yaxis+height/2+70);

        // r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
       // Thread.sleep(2000);
       // r.mouseMove(500, 350);
       // r.keyRelease(InputEvent.BUTTON1_DOWN_MASK);
//        Actions act=new Actions(getDriver());
//        act.clickAndHold(getDriver().findElement(By.xpath("//*[@id='column-a']")))
//                .moveToElement(getDriver().findElement(By.xpath("//*[@id='column-b']")))
//                .release()
//                .build()
//                .perform();

    @Test
    public void dropDownList() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/dropdown");
        Select drpCountry = new Select(getDriver().findElement(By.id("dropdown")));
        drpCountry.selectByVisibleText("Option 1");
    }

    @Test
    public void changingContext() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/dynamic_content?with_content=static");
        Select drpCountry = new Select(getDriver().findElement(By.id("dropdown")));
        drpCountry.selectByVisibleText("Option 1");
    }

    @Test
    public void dynamicControl() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/dynamic_controls");
        getDriver().findElement(By.xpath("//*[text()='Remove']")).click();
        Thread.sleep(8000);

        List<WebElement> addElement = getDriver().findElements(By.xpath("//*[@id=\"checkbox\"]/input"));
        if (addElement.size() == 0) {
            // click on the search button
            ExpectedConditions.elementToBeClickable((By.xpath("//button[text()=\"Add\"]")));
            getDriver().findElement(By.xpath("//button[text()=\"Add\"]")).click();
        }
        //SECOND PART
        Thread.sleep(7000);
        getDriver().findElement(By.xpath("//button[text()=\"Enable\"]")).click();

        WebDriverWait waitText = new WebDriverWait(getDriver(), 10);

        WebElement textField = getDriver().findElement(By.xpath("//input[@type=\"text\"]"));

        waitText.until(ExpectedConditions.elementToBeClickable(textField));

        if (textField.isEnabled()) {
            textField.sendKeys("test");
        } else {
            System.out.println("The element is disabled");
        }
    }

    @Test
    public void entryAdd()  {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/entry_ad");
        WebDriverWait waitModal = new WebDriverWait(getDriver(), 10);

        WebElement modalName = getDriver().findElement(By.xpath("//*[text() = 'This is a modal window']\n"));

        waitModal.until(ExpectedConditions.visibilityOfAllElements(modalName));
        getDriver().findElement(By.xpath("//*[text() = 'Close']\n")).click();
    }
    @Test
    public void exitIntent() throws AWTException, InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/exit_intent");
        Thread.sleep(3000);
        Robot robot=new Robot();
        robot.mouseMove(400, 400);
        // robot.mouseMove(100, 300);
        Thread.sleep(3000);
        robot.mouseMove(200, 110);
        Thread.sleep(3000);
        getDriver().switchTo().defaultContent();

        Thread.sleep(3000);
        robot.mouseMove(400, 400);
        Thread.sleep(3000);
        Actions act=new Actions(getDriver());
        act.click(getDriver().findElement(By.xpath("//*[text() = 'Close']\n")))
                .release()
                .build()
                .perform();
    }
    @Test
    public void downloadFile() throws InterruptedException {

        String downloadFilepath = "/Users/username/Documents/workout/JAVA_STUDY/";

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadFilepath);


        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        prefs.put("download.directory_upgrade", true);
        //ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("prefs", prefs);
        //ChromeDriverManager.getInstance().setup();
       // Configuration.browser = "chrome";
        WebDriverRunner.setWebDriver(new ChromeDriver(options));


        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/download");

        List<WebElement> list = getDriver().findElements(By.xpath("//*[text()=\"index.jpg\"]"));

        WebElement el = list.get(list.size() - 1);
        el.click();

        Thread.sleep(3000);



    }

}







