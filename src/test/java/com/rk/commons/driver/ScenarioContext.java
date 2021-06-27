package com.rk.commons.driver;

import com.rk.helper.ExplicitWait;
import com.rk.helper.JavaScriptHelper;
import com.rk.utils.PageObjectManager;
import com.rk.utils.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    // todo: remove WebDriverManager from this class
    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;

    public WebDriver driver;
    public ExplicitWait wait;
    public JavaScriptHelper js;
    private Map<String, Object> container;
    private static final Logger logger = LogManager.getLogger(ScenarioContext.class);

    public void initialize() throws Throwable {
        logger.info("Initializing scenario");

        this.webDriverManager = new WebDriverManager();
        this.driver = webDriverManager.getDriver();
        this.wait = new ExplicitWait(driver);
        this.js = new JavaScriptHelper(driver);
        this.pageObjectManager = new PageObjectManager(driver);
        this.container = new HashMap<>();
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public void tearDown() {
        if (webDriverManager != null) {
            webDriverManager.closeDriver();
            webDriverManager = null;
        }

        if (pageObjectManager != null) {
            pageObjectManager = null;
        }
    }

    public <T> void setToContainer(String key, T data) {
        container.put(key, data);
    }

    public <T> T getFromContainer(String key) {
        return (T) container.get(key);
    }
}
