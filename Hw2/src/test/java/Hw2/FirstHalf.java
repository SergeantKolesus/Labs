package Hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstHalf {

    final String c_mainPageTitle = "Home Page";

    private WebDriver webDriver;

    @BeforeClass
    public void init()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test(testName = "Enter page")
    public void EnterPage()
    {
        try {
            URL mainPage = new URL("https://jdi-testing.github.io/jdi-light/index.html");
            webDriver.navigate().to(mainPage);
            Assert.assertEquals(webDriver.getTitle(), c_mainPageTitle);
        }
        catch (MalformedURLException exc)
        {
            System.out.println(exc);
            Assert.fail();
        }
    }

    @Test(testName = "Perform login",
    dependsOnMethods = {"EnterPage"})
    public void PerformLogin()
    {
        String name = "Roman";
        String password = "Jdi1234";

        WebElement loginButton = webDriver.findElement(By.id("user-icon"));
        loginButton.click();

        loginButton = webDriver.findElement(By.id("login-button"));
        webDriver.findElement(By.id("name")).sendKeys(name);
        webDriver.findElement(By.id("password")).sendKeys(password);
        loginButton.click();

        WebElement usernameLbl = webDriver.findElement(By.id("user-name"));

        Assert.assertTrue(usernameLbl.isDisplayed());
    }

    @Test(testName = "Check username",
    dependsOnMethods = {"PerformLogin"})
    public void CheckUsername()
    {
        WebElement usernameLbl = webDriver.findElement(By.id("user-name"));
        Assert.assertEquals(usernameLbl.getAttribute("innerHTML").toLowerCase(), "roman iovlev");
    }

    @Test(testName = "Check postlogin page",
    dependsOnMethods = {"CheckUsername"})
    public void CheckPostlogin()
    {
        Assert.assertEquals(webDriver.getTitle(), c_mainPageTitle);
    }

    @Test(testName = "Check header",
    dependsOnMethods = {"EnterPage"})
    public void CheckHeader()
    {
        String[] names = {"HOME", "CONTACT FORM", "SERVICE", "METALS &AMP; COLORS"};
        List<WebElement> headerElements = webDriver.findElements(By.xpath("//header/div/nav/ul[1]/li/a"));
        List<String> headerElementsNames = new ArrayList<String>();

        Assert.assertEquals(names.length, headerElements.size());

        for(WebElement headerElement : headerElements)
        {
            String elementTitle = headerElement.getAttribute("innerHTML").split("<")[0].trim().toUpperCase();
            headerElementsNames.add(elementTitle);
        }

        for(String name : names)
        {
            Assert.assertTrue(headerElementsNames.contains(name));
            headerElementsNames.remove(name);
        }
    }

    @Test(testName = "Check images",
    dependsOnMethods = {"EnterPage"})
    public void CheckImages()
    {
        List<WebElement> icons = webDriver.findElements(By.className("benefit-icon"));

        Assert.assertEquals(icons.size(), 4);

        for(WebElement icon : icons)
        {
            Assert.assertTrue(icon.isDisplayed());
        }
    }

    @Test(testName = "Check images texts",
    dependsOnMethods = {"CheckImages"})
    public void CheckImagesTexts()
    {
        String[] properTexts = {"To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};

        List<WebElement> textsElements = webDriver.findElements(By.className("benefit-txt"));
        List<String> texts = new ArrayList<String>();

        Assert.assertEquals(textsElements.size(), 4);

        for(WebElement textElement : textsElements)
        {
            String text = textElement.getAttribute("innerHTML").replaceAll("\\s*<br>\\s*", "\n").trim();
            texts.add(text);
            Assert.assertTrue(textElement.isDisplayed());
        }


        for(String text : properTexts)
        {
            Assert.assertTrue(texts.contains(text));
            texts.remove(text);
        }
    }


    @Test(testName = "Check main header",
    dependsOnMethods = {"EnterPage"})
    public void MainHeaderText()
    {
        List<WebElement> headers = webDriver.findElements(By.tagName("h3"));
        WebElement textElement = webDriver.findElement(By.name("jdi-text"));
        WebElement topHeader = headers.get(0);
        String properTitleText = "EPAM FRAMEWORK WISHES…";
        String properText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT" +
                " UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS " +
                "NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE " +
                "CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

        Assert.assertEquals(topHeader.getAttribute("innerHTML").trim().toUpperCase(), properTitleText);

        Assert.assertTrue(topHeader.isDisplayed());
        Assert.assertTrue(textElement.isDisplayed());

        String text = textElement.getAttribute("innerHTML").trim().toUpperCase().replaceAll("\\s+|\n", " ");

        Assert.assertEquals(text, properText);
    }

    @Test(testName = "Check iframe existence",
    dependsOnMethods = {"EnterPage"})
    public void CheckIframe()
    {
        WebElement iframe = webDriver.findElement(By.id("second_frame"));
        Assert.assertTrue(iframe.isDisplayed());

    }

    @Test(testName = "Check iframe content",
    dependsOnMethods = {"CheckIframe"})
    public void TestIframeContent()
    {
        WebElement iframe = webDriver.findElement(By.id("second_frame"));
        WebElement logo;

        webDriver.switchTo().frame(iframe);

        logo = webDriver.findElement(By.id("epam-logo"));
        Assert.assertTrue(logo.isDisplayed());

        webDriver.switchTo().parentFrame();
    }

    @Test(testName = "Check subheader text",
    dependsOnMethods = {"EnterPage"})
    public void SubheaderText()
    {
        String properText = "JDI GITHUB";
        List<WebElement> headers = webDriver.findElements(By.tagName("h3"));
        WebElement subheader = headers.get(1).findElement(By.tagName("a"));

        Assert.assertTrue(subheader.isDisplayed());
        Assert.assertEquals(subheader.getAttribute("innerHTML").trim().toUpperCase(), properText);
    }

    @Test(testName = "Check subheader link",
    dependsOnMethods = {"SubheaderText"})
    public void SubheaderLink()
    {
        WebElement subheader = webDriver.findElements(By.tagName("h3")).get(1).findElement(By.tagName("a"));
        String link = "https://github.com/epam/JDI";

        Assert.assertEquals(subheader.getAttribute("href"), link);
    }

    @Test(testName = "Check left section existence",
    dependsOnMethods = {"EnterPage"})
    public void LeftSection()
    {
        WebElement section = webDriver.findElement(By.id("mCSB_1"));
        Assert.assertTrue(section.isDisplayed());
    }

    @Test(testName = "Check footer existence",
    dependsOnMethods = {"EnterPage"})
    public void Footer()
    {
        WebElement section = webDriver.findElement(By.tagName("footer"));
        Assert.assertTrue(section.isDisplayed());
    }

    @AfterClass
    public void closeDriver()
    {
        webDriver.close();
    }
}
