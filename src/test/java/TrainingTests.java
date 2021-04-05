import com.base.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.http.JsonHttpResponseCodec;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.OpenPage;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;

import static org.apache.commons.lang3.ArrayUtils.isSorted;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

//import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;


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
        Robot r = new Robot();
        r.mouseMove(500, 350);
        Thread.sleep(2000);
        Actions act = new Actions(getDriver());
        WebElement From = getDriver().findElement(By.xpath("//*[@id='column-a']"));
        //Element on which need to drop.
        WebElement To = getDriver().findElement(By.xpath("//*[@id='column-b']"));
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
    public void entryAdd() {
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
        Robot robot = new Robot();
        robot.mouseMove(400, 400);
        // robot.mouseMove(100, 300);
        Thread.sleep(3000);
        robot.mouseMove(200, 110);
        Thread.sleep(3000);
        getDriver().switchTo().defaultContent();

        Thread.sleep(3000);
        robot.mouseMove(400, 400);
        Thread.sleep(3000);
        Actions act = new Actions(getDriver());
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

    @Test
    public void uploadFile() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/upload");
        getDriver().findElement(By.xpath("//*[@id=\"file-upload\"]")).sendKeys("/Users/romanbondarenko/Downloads/hr_report-2.zip");
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//*[@id=\"file-submit\"]")).click();

    }

    @Test
    public void loginPage() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/login");

        getDriver().findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
        getDriver().findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");

        getDriver().findElement(By.xpath("//*[@type='submit']")).click();
        //Thread.sleep(4000);

        WebDriverWait waitMessage = new WebDriverWait(getDriver(), 10);
        WebElement successMessage = getDriver().findElement(By.xpath("//*[@id='flash']"));
        waitMessage.until(ExpectedConditions.visibilityOfAllElements(successMessage));

        String congrats = getDriver().findElement(By.xpath("//*[@class='subheader']")).getText();
        Assert.assertEquals("Welcome to the Secure Area. When you are done click logout below.", congrats);

    }

    @Test
    public void horizontalSlider() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/horizontal_slider");


        WebElement slider = getDriver().findElement(By.xpath("//*[@type='range']"));
        Actions move = new Actions(getDriver());
        Action action = move.dragAndDropBy(slider, 30, 0).build();
        action.perform();
    }

    @Test
    public void hoversCheck() throws AWTException, InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/hovers");

        Actions hover = new Actions(getDriver());

        WebElement elemToHover = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));

        hover.moveToElement(elemToHover).moveToElement(getDriver().findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img")))
                .click()
                .build()
                .perform();
        Thread.sleep(2000);

        Robot robot = new Robot();
        robot.mouseMove(450, 465);

        getDriver().findElement(By.xpath("//div/div[2]/div/a[contains(text(), 'View')] ")).click();
    }

    @Test
    public void inputs() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/inputs");
        getDriver().findElement(By.xpath("//input[@type='number']")).sendKeys("2345");
    }

    @Test
    public void dropdownMenu() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/jqueryui/menu#");
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ui-id-2']")));
        dropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-id-4']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-id-8']"))).click();

    }

    @Test
    public void jsAlerts() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/javascript_alerts");
        //FIRST ALERT
        getDriver().findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        Thread.sleep(2000);
        getDriver().switchTo().alert().accept();
        //SECOND ALERT
        getDriver().findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Thread.sleep(2000);
        getDriver().switchTo().alert().accept();
        Thread.sleep(3000);
        //THIRD ALERT
        getDriver().findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Thread.sleep(3000);
        Alert promptAlert = getDriver().switchTo().alert();
        String alertText = promptAlert.getText();
        System.out.println("Alert text is " + alertText);
        //Send some text to the alert
        promptAlert.sendKeys("Test");
        Thread.sleep(2000);
        promptAlert.accept();
    }

    @Test
    public void newWindow() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/windows");
        getDriver().findElement(By.xpath("//*[text()='Click Here']")).click();
        //SWITCH TO THE NEW TAB
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(1));
        //ASSERT TEXT AND CLOSE THE NEW WINDOW
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='example']")));
        String newWindow = getDriver().findElement(By.xpath("//*[@class='example']")).getText();
        Assert.assertEquals("New Window", newWindow);
        getDriver().close();
        //BACK TO THE FIRST WINDOW
        getDriver().switchTo().window(tabs2.get(0));

    }

    @Test
    public void frames() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/nested_frames");
        getDriver().switchTo().frame("frame-top").getPageSource();
        String s = getDriver().switchTo().frame("frame-right").getPageSource();
        System.out.println(s);

    }

    @Test
    public void floatingMenu() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/floating_menu");
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0, 6000)");
        Thread.sleep(5000);
        getDriver().findElement(By.xpath("//a[@href='#contact']")).click();
    }

    @Test
    public void statusCodes() throws ClientProtocolException, IOException {
//        OpenPage testSearchField = new OpenPage(getDriver());
//        testSearchField.navigateToSite("http://the-internet.herokuapp.com/status_codes");

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet("http://the-internet.herokuapp.com/status_codes/404"));
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void sortTable() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/tables");
        getDriver().findElement(By.xpath("//*[@id=\"table1\"]/thead/tr/th[4]/span")).click();
        WebElement table = getDriver().findElement(By.xpath("//*[@id=\"table1\"]/thead/tr/th[4]/span"));
        List<WebElement> rows = table.findElements(By.xpath("//*[@id='table1']/tbody/tr/td[4]"));
        String[] linkText = new String[rows.size()];
        int i = 0;

//Storing List elements text into String array
        for (WebElement a : rows) {
            linkText[i] = a.getText().replace("$", "");
            i++;
        }
        //System.out.println("Strings = " + Arrays.toString(linkText));

        double[] money = new double[linkText.length];
        for (int j = 0; j < linkText.length; j++) {
            money[j] = Double.parseDouble(linkText[j]);
        }
        System.out.println("doubles = " + Arrays.toString(money));

        if (isSorted(money)) {
            System.out.println("Is in order");
        } else
            System.out.println("Is NOT in order");


        getDriver().quit();
    }


    @Test
    public void keyPresses() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/key_presses");
        String mySymbol = "m";
        getDriver().findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(mySymbol);
        boolean stringsCheck;

        String inputCheck = getDriver().findElement(By.xpath("//*[@id=\"result\"]")).getText();

        stringsCheck = inputCheck.equalsIgnoreCase("You entered: "+ mySymbol);
        System.out.println("entered symbol is equal to the returned one. Result:  " + stringsCheck );



    }


    @Test
    public void notificationMessage() throws InterruptedException {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://the-internet.herokuapp.com/notification_message_rendered");

        //Press the "Click here" button
        getDriver().findElement(By.xpath("//*[text()='Click here']")).click();
        Thread.sleep(3000);

        //Compare the notification
        String actualMessage = getDriver().findElement(By.xpath("//*[@id=\"flash\"]")).getText().replace("×", "");
        String expectedMessage = "Action unsuccesful, please try again";

        assertEquals(actualMessage.trim(), expectedMessage.trim());



    }




    @Test
    public void purchaseAndCartChek() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://automationpractice.com/");

        //Open the T-shirts section
        getDriver().findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@class='logo img-responsive']")));

        //hover the needed item
        WebElement elemToHover = getDriver().findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"));

        Actions hover = new Actions(getDriver());
        //add the item to the cart
        hover.moveToElement(elemToHover).moveToElement(getDriver().findElement(By.xpath("//*[text()='Add to cart']")))
                .click()
                .build()
                .perform();

        //wait until the item will be added into the cart
        WebDriverWait waitContinue = new WebDriverWait(getDriver(), 10);
        waitContinue.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='icon-ok']")));

        //press "Continue" button
        getDriver().findElement(By.xpath("//span[@title='Continue shopping']")).click();

        WebElement dresses = getDriver().findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));

        //scroll the page up (to see the top nav menu)
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0, - 2000)");

        //hover the dress item to see "add to cart" button
        Actions dressesHover = new Actions(getDriver());
        dressesHover.moveToElement(dresses).build().perform();

        WebDriverWait waitDresses = new WebDriverWait(getDriver(), 10);

        //wait the dress section
        waitDresses.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a")));
        getDriver().findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a")).click();

        WebElement dressToHover = getDriver().findElement(By.xpath("//img[@alt='Printed Dress']"));

        //add the dress to the cart
        hover.moveToElement(dressToHover).moveToElement(getDriver().findElement(By.xpath("//*[text()='Add to cart']")))
                .click()
                .build()
                .perform();
        //wait the confirmation the dress was added successfully
        waitDresses.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='icon-ok']")));

        //make sure we have 2 items in the cart, now
        String cartString = getDriver().findElement(By.xpath("//*[@id='layer_cart']//span[@style='display: inline;']")).getText();
        Assert.assertEquals("There are 2 items in your cart.", cartString);

    }

    @Test
    public void storeInfoCheck() {
        OpenPage testSearchField = new OpenPage(getDriver());
        testSearchField.navigateToSite("http://automationpractice.com/");

        //Address check
        String addressCheck = getDriver().findElement(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[1]")).getText();
        Assert.assertEquals("Selenium Framework, Research Triangle Park, North Carolina, USA", addressCheck);

        //Phone number check
        String phoneCheck = getDriver().findElement(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]")).getText();
        Assert.assertEquals("Call us now: (347) 466-7432", phoneCheck);

        //Email  check
        String emailCheck = getDriver().findElement(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]")).getText();
        Assert.assertEquals("Email: support@seleniumframework.com", emailCheck);
    }

}








