package com.rk.utils;

import com.rk.pageObjects.HomePage.Home;
import com.rk.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;

    private LoginPage loginPage;
    private Home homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public Home homePage() {
        return (homePage == null) ? homePage = new Home(driver) : homePage;
    }
}