package Hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sun.awt.windows.WEmbeddedFrame;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondHalf {
    private WebDriver webDriver;

    final String c_mainPageTitle = "Home Page";

    @BeforeClass
    public void init()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        Assert.assertTrue(true);
    }

    @Test(testName = "Enter page")
    public void EnterPage()
    {
        try {
            URL mainPage = new URL("https://jdi-testing.github.io/jdi-light/index.html" );
            webDriver.navigate().to(mainPage);
            Assert.assertEquals(webDriver.getTitle(), c_mainPageTitle);
        }
        catch (MalformedURLException exc)
        {
            System.out.println(exc);
            Assert.fail();
        }
    }

    /**
     * Combines three tests from first half to used soft asserts
     */
    @Test(testName = "Perform login",
    dependsOnMethods = {"EnterPage"})
    public void PerformLogin()
    {
        String name = "Roman";
        String password = "Jdi1234";

        WebElement loginButton = webDriver.findElement(By.id("user-icon"));
        SoftAssert softAssert = new SoftAssert();
        loginButton.click();

        loginButton = webDriver.findElement(By.id("login-button"));
        webDriver.findElement(By.id("name")).sendKeys(name);
        webDriver.findElement(By.id("password")).sendKeys(password);
        loginButton.click();

        WebElement usernameLbl = webDriver.findElement(By.id("user-name"));

        softAssert.assertTrue(usernameLbl.isDisplayed());
        softAssert.assertEquals(usernameLbl.getAttribute("innerHTML").toLowerCase(), "roman iovlev");
        softAssert.assertEquals(webDriver.getTitle(), c_mainPageTitle);
        softAssert.assertAll();
    }

    @Test(testName = "Check services drop down in header",
    dependsOnMethods = {"EnterPage"})
    public void CheckServicesHeader()
    {
        List<String> options = Arrays.asList("support", "dates", "complex table", "simple table",
                "table with pages", "different elements");
        List<String> dropDownPositionsNames = new ArrayList<String>();
        SoftAssert softAssert = new SoftAssert();
        WebElement dropDownList = webDriver.findElement(By.className("dropdown"));
        dropDownList.click();
        List<WebElement> dropDownPositions = dropDownList.findElements(By.tagName("li"));

        for(WebElement dropDownPosition : dropDownPositions)
        {
            dropDownPositionsNames.add(dropDownPosition.findElement(By.tagName("a")).getAttribute("innerHTML").toLowerCase().trim());
            softAssert.assertTrue(dropDownPosition.isDisplayed());
        }

        for(String name : options)
        {
            if(dropDownPositionsNames.contains(name))
            {
                softAssert.assertTrue(true);
            }
            else
            {
                softAssert.assertEquals("noting", name);
            }
            //softAssert.assertTrue(options.contains(name));
            try {
                options.remove(name);
            }
            catch (Exception exc){}
        }

        softAssert.assertAll();
    }

    @Test(testName = "Check services drop down in left side",
    dependsOnMethods = {"EnterPage"})
    public void CheckServicesLeft()
    {
        List<String> options = Arrays.asList("support", "dates", "complex table", "simple table",
                "table with pages", "different elements");
        List<String> dropDownPositionsNames = new ArrayList<String>();
        SoftAssert softAssert = new SoftAssert();
        WebElement dropDownList = webDriver.findElement(By.xpath("//*[@class='sidebar-menu']/li[3]"));
        dropDownList.click();
        List<WebElement> dropDownPositions = dropDownList.findElements(By.tagName("li"));

        //Reporter.log("Found drop down positions" + dropDownPositions.size().);

        for(WebElement dropDownPosition : dropDownPositions)
        {
            String name = dropDownPosition.findElement(By.tagName("span")).getAttribute("innerHTML").toLowerCase().trim();
            dropDownPositionsNames.add(name);
            softAssert.assertTrue(dropDownPosition.isDisplayed());
        }

        for(String name : options)
        {
            if(dropDownPositionsNames.contains(name))
            {
                softAssert.assertTrue(true);
            }
            else
            {
                softAssert.assertEquals("noting", name);
            }
            //softAssert.assertTrue(options.contains(name));
            try {
                options.remove(name);
            }
            catch (Exception exc){}
        }

        softAssert.assertAll();
    }

    @Test(testName = "Check different elements page through header",
    dependsOnMethods = {"CheckServicesHeader"})
    public void CheckDiffElements()
    {
        WebElement dropDown = webDriver.findElement(By.className("dropdown"));
        dropDown.click();
        dropDown.findElement(By.xpath("//li[8]")).click();
    }

    /**
     * Includes tasks 8, 9, 10 to use more soft asserts
     */
    @Test(testName = "Check different elements page content",
    dependsOnMethods = {"CheckDiffElements"})
    public void CheckDiffElementsContent()
    {
        SoftAssert softAssert = new SoftAssert();
        int checkBoxesCount = 4;
        int radioButtonsCount = 4;
        //int dropdownsCount = 1;
        int buttonsCount = 2;

        List<WebElement> checkBoxes = webDriver.findElements(By.className("label-checkbox"));
        List<WebElement> radioButtons = webDriver.findElements(By.className("label-radio"));
        List<WebElement> buttons = new ArrayList<WebElement>();
        WebElement dropdown = webDriver.findElement(By.className("colors"));

        buttons.add(webDriver.findElement(By.xpath("//main//input[@class='uui-button']")));
        buttons.add(webDriver.findElement(By.xpath("//main//button")));

        softAssert.assertEquals(checkBoxes.size(), checkBoxesCount);
        softAssert.assertEquals(radioButtons.size(), radioButtonsCount);
        softAssert.assertEquals(buttons.size(), buttonsCount);

        for(WebElement checkBox : checkBoxes)
        {
            softAssert.assertTrue(checkBox.isDisplayed());
        }

        for(WebElement radioButton : radioButtons)
        {
            softAssert.assertTrue(radioButton.isDisplayed());
        }

        for(WebElement button : buttons)
        {
            softAssert.assertTrue(button.isDisplayed());
            //Reporter.log("Button: " + button.getAttribute("value"), true);
        }

        softAssert.assertTrue(dropdown.isDisplayed());

        WebElement leftSection = webDriver.findElement(By.name("navigation-sidebar"));
        WebElement rightSection = webDriver.findElement(By.name("log-sidebar"));

        softAssert.assertTrue(leftSection.isDisplayed());
        softAssert.assertTrue(rightSection.isDisplayed());

        softAssert.assertAll();
    }

    /**
     * Unites 11 and 12 test tasks
     */
    @Test(testName = "Check logging of checkboxes",
    dependsOnMethods = {"CheckDiffElementsContent"})
    public void CheckCheckboxesLogging()
    {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> checkBoxes = webDriver.findElements(By.className("label-checkbox"));
        WebElement log = webDriver.findElement(By.className("panel-body-list"));
        WebElement waterCheckBox = checkBoxes.get(0);
        WebElement windCheckBox = checkBoxes.get(2);


        waterCheckBox.click();
        windCheckBox.click();

        String[] logs = log.getText().split("\n");

        softAssert.assertTrue(logs[1].contains("Water: condition changed to true"));
        softAssert.assertTrue(logs[0].contains("Wind: condition changed to true"));

        softAssert.assertAll();
    }

    @Test(testName = "Check logging of radiobutton",
    dependsOnMethods = {"CheckDiffElementsContent"})
    public void CheckRadiobuttonLogging()
    {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> radioButtons = webDriver.findElements(By.className("label-radio"));
        WebElement log = webDriver.findElement(By.className("panel-body-list"));
        WebElement selen = radioButtons.get(3);

        selen.click();

        String logString = log.getText().split("\n")[0];

        softAssert.assertTrue(logString.contains("metal: value changed to Selen"));

        softAssert.assertAll();
    }

    @Test(testName = "Check logging of radiobutton",
    dependsOnMethods = {"CheckDiffElementsContent"})
    public void CheckDropdownLogging()
    {
        SoftAssert softAssert = new SoftAssert();

        WebElement dropdown = webDriver.findElement(By.xpath("//main//select"));
        WebElement log = webDriver.findElement(By.className("panel-body-list"));

        dropdown.click();
        dropdown.findElement(By.xpath("*[last()]")).click();

        String[] logs =log.getText().split("\n");
        String logString = logs[0];

        softAssert.assertTrue(logString.contains("Colors: value changed to Yellow"));

        softAssert.assertAll();
    }

    @Test(testName = "Check logging of radiobutton",
    dependsOnMethods = {"CheckCheckboxesLogging"})
    public void CheckCheckboxesLoggingReverse()
    {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> checkBoxes = webDriver.findElements(By.className("label-checkbox"));
        WebElement log = webDriver.findElement(By.className("panel-body-list"));
        WebElement waterCheckBox = checkBoxes.get(0);
        WebElement windCheckBox = checkBoxes.get(2);


        waterCheckBox.click();
        windCheckBox.click();

        String[] logs = log.getText().split("\n");

        softAssert.assertTrue(logs[1].contains("Water: condition changed to false"));
        softAssert.assertTrue(logs[0].contains("Wind: condition changed to false"));

        softAssert.assertAll();
    }

    @AfterClass
    public void closeDriver()
    {
        webDriver.close();
    }
}
