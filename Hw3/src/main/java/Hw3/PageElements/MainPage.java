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

}
