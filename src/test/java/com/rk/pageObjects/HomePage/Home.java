package com.rk.pageObjects.HomePage;

import com.rk.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends BasePage {

    @FindBy(css = "a[data-csa-c-content-id=nav_ya_signin]")
    public WebElement signInButton;

    public Home(WebDriver driver) {
        super(driver);
    }
}
