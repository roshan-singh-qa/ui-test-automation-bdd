package com.rk.pageObjects;


import com.rk.commons.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    WebDriver driver;

    @FindBy(name = "email")
    public WebElement userName;

    @FindBy(css = "input[name='password']")
    public WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    public boolean isElementPresent(WebElement element) {
        try {
            if (element.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) {
        wait.forPage();
        userName.clear();
        userName.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void loginWithAnyUser(String user) {
        String username = Configuration.users.get(user).username;
        String password = Configuration.users.get(user).password;
        login(username, password);
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        loginButton.click();
    }
}
