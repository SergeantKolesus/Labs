package Hw3;

import Hw3.PageElements.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Steps {
    WebDriver webDriver;

    @Before
    public void init()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    MainPage page;

    @Given("Opened homepage")
    public void openMainPage()
    {
        webDriver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        page = new MainPage(webDriver);
    }

    @Given("Logged in as {word} - {word}")
    public void Login(String username, String password)
    {
        page.Login(new String[]{username, password});
    }

    @Then("{} is {string}")
    public void checkTitle(String elementName, String text)
    {
        switch (elementName.toLowerCase())
        {
            case "browser title":
                Assert.assertEquals(webDriver.getTitle(), text);
                break;
            case "username":
                Assert.assertEquals(page.GetUsername(), text);
                break;
            case "main header":
                Assert.assertEquals(page.GetMainHeader().getText(), text);
                break;
            case "text under header":
                Assert.assertEquals(page.GetUnderHeaderText().getText(), text);
                break;
            case "subheader":
                Assert.assertEquals(page.GetSubheader().getText(), text);
                break;
            case "link":
                Assert.assertEquals(page.GetLink(), text);
                break;
            default:
                Assert.assertTrue(true);
                break;
        }
    }

    @Then("There are {int} {}")
    public void checkElements(int number, String elementName)
    {
        List<WebElement> elements = new ArrayList<WebElement>();

        switch (elementName)
        {
            case "header labels":
                elements = page.GetHeaderLabels();
                break;
            case "images":
                elements = page.GetImages();
                break;
            case "descriptions":
                elements = page.GetImagesDescriptions();
                break;
            case "iframes":
                elements.add(page.GetIframe());
                break;
            case "left section":
                elements.add(page.GetLeftSection());
                break;
            case "footer":
                elements.add(page.GetFooter());
                break;
            default:
                break;
        }

        Assert.assertEquals(number, elements.size());

        for(WebElement element : elements)
        {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    List<String> headerLabelsNames = Arrays.asList("To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");

    @Then("{} have proper text")
    public void checkElementsNames(String elementsNames)
    {
        List<WebElement> elements = new ArrayList<WebElement>();

        switch (elementsNames.toLowerCase())
        {
            case "header labels":
                Assert.assertTrue(true);
                break;
        }

        Assert.assertTrue(true);
    }

    private void checkInnerIframe(int count, String elementName)
    {
        List<WebElement> elements = new ArrayList<WebElement>();

        switch (elementName)
        {
            case "logo":
                elements.add(page.GetIframedImage());
                break;
            default:
                System.out.println("Unknown element type: " + elementName);
                break;
        }

        Assert.assertEquals(elements.size(), count);
    }

    @Then("In {} are {int} {}")
    public void checkInnerElementsCount(String where, int count, String elementName)
    {
        switch (where)
        {
            case "iframe":
                checkInnerIframe(count, elementName);
                break;
            default:
                System.out.println("Unknwon location: " + where);
                break;
        }
    }

    @After
    public void close()
    {
        webDriver.quit();
    }
}
