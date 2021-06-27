package com.rk.commons.driver.listeners;

import com.rk.helper.JavaScriptHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class LoggerEventListener extends AbstractWebDriverEventListener {

    public final static Logger LOG = LogManager.getLogger(LoggerEventListener.class);
    private final JavaScriptHelper JS;

    public LoggerEventListener(WebDriver driver) {
        this.JS = new JavaScriptHelper(driver);
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        LOG.debug("Accepting alert");
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        LOG.debug("Dismissing alert");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOG.debug("Navigating to: " + url);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Looking for element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Element " + by + " found");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.debug("Clicking on element: " + element);
        JS.highlightElement(element);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        LOG.debug("Element: " + element + " has been clicked");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        LOG.debug("Changing value to: " + keysToSend + " in element: " + element);
        JS.highlightElement(element);
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        LOG.debug("Getting text from element: " + element);
        JS.highlightElement(element);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        LOG.debug("Switching to window: " + windowName);
    }

}
