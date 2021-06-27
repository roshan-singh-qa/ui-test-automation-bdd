package com.rk.helper;

import com.rk.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private WebDriver driver;
    public static Logger log = LogManager.getLogger(JavaScriptHelper.class);

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
        log.debug("JavaScriptHelper : " + this.driver.hashCode());
    }

    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script, args);
    }

    public void scrollToElemet(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])",
                element.getLocation().x, element.getLocation().y);
        log.info(element);
    }

    public void scrollToElemet(By locator) {
        scrollToElemet(driver.findElement(locator));
        log.info(locator);
    }

    public void scrollToElemetAndClick(By locator) {
        WebElement element = driver.findElement(locator);
        scrollToElemet(element);
        element.click();
        log.info(locator);
    }

    public void scrollToElemetAndClick(WebElement element) {
        scrollToElemet(element);
        element.click();
        log.info(element);
    }

    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView(true)", element);
        log.info(element);
    }

    public void scrollIntoView(By locator) {
        scrollIntoView(driver.findElement(locator));
        log.info(locator);
    }

    public void scrollIntoViewAndClick(By locator) {
        WebElement element = driver.findElement(locator);
        scrollIntoView(element);
        element.click();
        log.info(locator);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        element.click();
        log.info(element);
    }

    public void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }

    public void highlightElement(By locator) {
        highlightElement(driver.findElement(locator));
    }

    public void highlightElement(WebElement element) {
        if (!Configuration.driver.shouldHighlightElement) {
            return;
        }

        try {
            executeScript("arguments[0].setAttribute('style', arguments[1]); ",
                    element, "border: 4px solid " + Configuration.driver.highlightColor + ";");
        } catch (Exception exception) {
            log.debug("Exception occurred while highlighting element: " + exception);
        }
    }

    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        executeScript("arguments[0].setAttribute('" + attributeName + "', arguments[1]); ",
                element, attributeValue + ";");
    }

    public void setZoomLevel(double zoomLevel) {
        executeScript("document.body.style.zoom = '" + zoomLevel + "'");
    }
}