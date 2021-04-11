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
import java.util.List;

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

    @Then("Browser title is {string}")
    public void checkTitle(String title)
    {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    @Then("Username is {string}")
    public void checkUsername(String username)
    {
        Assert.assertEquals(username, page.GetUsername());
    }

    @Then("There are {int} {string}")
    public void checkElements(int number, String elementName)
    {
        List<WebElement> elements = new ArrayList<WebElement>();

        switch (elementName)
        {
            case "header labels":
                elements = page.GetHeaderLabels();
        }

        Assert.assertEquals(number, elements.size());
    }

    @After
    public void close()
    {
        webDriver.quit();
    }
}
