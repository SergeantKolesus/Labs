package Hw3.PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    public MainPage(WebDriver webDriver)
    {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "user-icon")
    WebElement userIcon;

    @FindBy(id = "name")
    WebElement loginTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(id = "login-button")
    WebElement loginButton;


    public void Login(String[] logingData)
    {
        userIcon.click();
        loginTextBox.sendKeys(logingData[0]);
        passwordTextBox.sendKeys(logingData[1]);
        loginButton.click();
    }

    @FindBy(id = "user-name")
    WebElement usernameLabel;

    public String GetUsername()
    {
        return usernameLabel.getText();
    }

    @FindBy(xpath = "//header/div/nav/ul[1]/li/a")
    List<WebElement> headerLabels;

    public List<WebElement> GetHeaderLabels()
    {
        return headerLabels;
    }

    @FindBy(className = "benefit-icon")
    List<WebElement> images;

    public List<WebElement> GetImages()
    {
        return images;
    }

    @FindBy(className = "benefit-txt")
    List<WebElement> imagesDescriptions;

    public List<WebElement> GetImagesDescriptions()
    {
        return imagesDescriptions;
    }

    @FindBy(tagName = "h3")
    WebElement mainHeader;

    public WebElement GetMainHeader()
    {
        return mainHeader;
    }

    @FindBy(name = "jdi-text")
    WebElement underHeaderText;

    public WebElement GetUnderHeaderText()
    {
        return underHeaderText;
    }

    @FindBy(id = "second_frame")
    WebElement iframe;

    public WebElement GetIframe()
    {
        return iframe;
    }

    @FindBy(id = "epam-logo")
    WebElement logo;

    public WebElement GetIframedImage()
    {
        return logo;
    }

    @FindBy(xpath = "//main//a")
    WebElement subheader;

    public WebElement GetSubheader()
    {
        return subheader;
    }

    public String GetLink()
    {
        return subheader.getAttribute("href");
    }

    @FindBy(id = "mCSB_1")
    WebElement leftSection;

    public WebElement GetLeftSection()
    {
        return leftSection;
    }

    @FindBy(tagName = "footer")
    WebElement footer;

    public WebElement GetFooter()
    {
        return footer;
    }

}
