package com.rk.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.rk.commons.configuration.Configuration;
import com.rk.commons.driver.listeners.ConsolePrinterEventListener;
import com.rk.commons.utils.Resource;
import com.rk.commons.driver.listeners.LoggerEventListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

// todo: change class name
public class WebDriverManager {

    private EventFiringWebDriver eventFiringWebdriver;

    public EventFiringWebDriver getDriver() {
        if (eventFiringWebdriver != null) {
            return eventFiringWebdriver;
        }
        WebDriver driver;
        //todo: implement path to ie webdriver
        switch (Configuration.driver.browserType) {
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", getPathToWebDriver());
                driver = createIeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", getPathToWebDriver());
                driver = createChromeDriver();
                break;
        }

        setUpWebDriver(driver);
        return registerListeners(driver);
    }

    private EventFiringWebDriver registerListeners(WebDriver driver) {
        var wrappedWebDriver = new EventFiringWebDriver(driver);
        var defaultListener = new LoggerEventListener(driver);
        wrappedWebDriver.register(defaultListener);
        if (Configuration.driver.verbose == true) {
            var consolePrtEventListener = new ConsolePrinterEventListener(driver);
            wrappedWebDriver.register(consolePrtEventListener);
        }
        this.eventFiringWebdriver = wrappedWebDriver;
        return wrappedWebDriver;
    }

    public void closeDriver() {
        if (eventFiringWebdriver == null) return;
        eventFiringWebdriver.close();
        eventFiringWebdriver.quit();
        eventFiringWebdriver = null;
    }

    private String getPathToWebDriver() {
        String pathToDriver;

        switch (Configuration.driver.os) {
            case LINUX:
                pathToDriver = new Resource().getPathToFile("drivers/linux/chromedriver");
                break;
            case MACOS:
                pathToDriver = new Resource().getPathToFile("/resources/drivers/macos/chromedriver");
            default:
                pathToDriver =  System.getProperty("user.dir")+"//src//test//resources//drivers//windows//chromedriver.exe";
                        //new Resource().getPathToFile("drivers/windows/chromedriver.exe");
                break;
        }
        return pathToDriver;
    }

    private WebDriver createChromeDriver() {
        var options = new ChromeOptions();

        setHeadlessMode(options);
        setIncognitoMode(options);

        options.addArguments("disable-gpu");
        options.addArguments("--disable-print-preview");

        return new ChromeDriver(options);
    }

    private void setHeadlessMode(ChromeOptions options) {
        if (Configuration.driver.isHeadless) {
            options.addArguments("--headless");
            options.addArguments("window-size=1200,1100");
        }
    }

    //todo: implement incognito mode for other browsers
    private void setIncognitoMode(ChromeOptions options) {
        if (Configuration.driver.isIncognito) {
            options.addArguments("--incognito");
        }
    }

    private WebDriver createFirefoxDriver() {
        var options = new FirefoxOptions();

        var driver = new FirefoxDriver(options);
        return driver;
    }

    private WebDriver createIeDriver() {
        var options = new InternetExplorerOptions();
        return new InternetExplorerDriver(options);
    }

    public void takeScreenshot(String contextPart, String descriptionPart) throws IOException {
        var src = ((TakesScreenshot) eventFiringWebdriver).getScreenshotAs(OutputType.FILE);
        var imageFileName = new SimpleDateFormat("yyyy-MM-dd_HH-ss").format(new GregorianCalendar().getTime());

        if (contextPart != null) {
            if (contextPart.isEmpty() == false) imageFileName += " " + contextPart;
        }

        if (descriptionPart != null) {
            if (descriptionPart.isEmpty() == false) imageFileName += " " + descriptionPart;
        }

        imageFileName += ".png";

        FileUtils.copyFile(src, new File("target/screenshots/" + imageFileName));
    }

    private void setUpWebDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Configuration.timeout.page, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Configuration.timeout.page, TimeUnit.SECONDS);
    }

}
