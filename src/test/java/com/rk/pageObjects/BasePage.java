package com.rk.pageObjects;

import com.rk.commons.configuration.Configuration;
import com.rk.helper.ExplicitWait;
import com.rk.helper.JavaScriptHelper;
import com.rk.commons.driver.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;

public class BasePage {
    public static Logger log = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected ExplicitWait wait;
    protected JavaScriptHelper js;
    protected Actions action;

    @FindBy(xpath = "//a[contains(text(),'Admin')]")
    WebElement Admin;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new ExplicitWait(driver);
        this.js = new JavaScriptHelper(driver);
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickMenuAdmin() throws InterruptedException {
        Admin.click();
        wait.forPage();
    }

    public void click(WebElement webElement) {
        click(webElement, Configuration.timeout.explicit);
    }

    public void click(WebElement webElement, int timeOutInSeconds) {
        log.debug("Wait and click:" + webElement);
        wait.forElementToBeClickable(webElement, timeOutInSeconds, Configuration.timeout.poolingInterval);
        js.highlightElement(webElement);
        js.clickElement(webElement);
    }

    public void click(By locator) {
        click(locator, Configuration.timeout.explicit);
    }

    public void click(By locator, int timeoutInSeconds) {
        log.debug("Wait and click: " + locator);
        wait.forElementToBeClickable(locator, timeoutInSeconds);
        js.highlightElement(locator);
        js.clickElement(driver.findElement(locator));
    }

    public Actions clickByAction(WebElement element) {
        log.debug("Click by action: " + element);
        wait.forElementToBeVisible(element);
        js.highlightElement(element);
        action.click(element).perform();
        return action;
    }

    public Actions doubleClick(WebElement element) {
        log.debug("Double click on: " + element);
        wait.forElementToBeVisible(element);
        js.highlightElement(element);
        action.moveToElement(element);
        action.doubleClick().perform();
        return action;
    }

    public void selectByVisibleIndex(WebElement element, int value) {
        log.debug("Select value with index " + value + " from element: " + element);
        wait.forElementToBeVisible(element);
        js.highlightElement(element);
        Select select = new Select(element);
        select.selectByIndex(value);
    }

    public void selectReactSelectionListOpt(String wantedOption, By menuLocator) {
        WebElement element = driver.findElement(menuLocator)
                .findElement(By.xpath(String.format(".//div[text()='%s']", wantedOption)));
        element.click();
    }

    public void selectByValue(WebElement element, String value) {
        log.debug("Select value " + value + " from element: " + element);
        Select select = new Select(element);
        js.highlightElement(element);
        select.selectByValue(value);
    }

    public void selectByVisibleText(WebElement element, String value) {
        log.debug("Select value by visible text: " + value + " from element: " + element);
        Select select = new Select(element);
        js.highlightElement(element);
        select.selectByVisibleText(value);
    }

    public void sendKeys(WebElement webElement, String value, int timeOutInSeconds) {
        log.debug("Send keys: " + value + " to element: " + webElement);
        click(webElement, timeOutInSeconds);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void sendKeys(WebElement webElement, String value) {
        sendKeys(webElement, value, Configuration.timeout.explicit);
    }

    public void sendKeysByAction(WebElement webElement, String value) {
        log.debug("Send keys by action: " + value + " to element: " + webElement);
        webElement.click();
        action.sendKeys(value).perform();
    }

    public void arrowDown(WebElement element, int ArrowDownCount) {
        element.click();
        for (int i = 0; i < ArrowDownCount; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
    }

    public Actions hoverOn(WebElement element) {
        log.debug("Hover on: " + element);
        wait.forElementToBeVisible(element);
        js.highlightElement(element);
        action.moveToElement(element).perform();
        return action;
    }

    //todo: Remove method from this class
    public void waitAndAssertEquals(WebElement webElement, long timeOutInSeconds, String expectedText) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), expectedText);
    }

    //todo: Remove method from this class
    public void waitAndAssertVisibility(long timeOut, WebElement element, String errorText) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            Assert.fail(errorText);
        }
    }

    public void switchOutOfIframe() {
        driver.switchTo().defaultContent();
    }

    public void switchToIframe(WebElement iframeElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        try {
            webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
        } catch (TimeoutException ex) {
            log.debug("Exception occurred during switch to iframe");
        }
    }

    public void tryClickHandleIframe(WebElement elementToClick, WebElement iframeElement, int timeoutSecs) {
        try {
            click(elementToClick, timeoutSecs);
        } catch (NoSuchElementException ex) {
            switchToIframe(iframeElement);
            click(elementToClick, timeoutSecs);
        }
    }

    protected void takeScreenshot(String screenshotFilename) throws Throwable {
        var tc = new ScenarioContext();
        var scenarioName = tc.<String>getFromContainer("scenarioName");

        var webDriverManager = tc.getWebDriverManager();
        webDriverManager.takeScreenshot(scenarioName, screenshotFilename);
    }

    public String getInnerHTML(WebElement webElement) {
        return webElement.getAttribute("innerHTML");
    }

    public String getAttribute(WebElement element, String attributeName) {
        log.debug("Get attribute: " + attributeName);
        return element.getAttribute(attributeName);
    }

    public int getAttributeAsInt(WebElement element, String attributeName) {
        log.debug("Convert attribute to int: " + attributeName);
        return Integer.parseInt(element.getAttribute(attributeName));
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void dragAndDropByJs(WebElement sourceElement, WebElement destinationElement) {
        log.debug("Move element: " + sourceElement + " to element: " + destinationElement);
        js.executeScript("function createEvent(typeOfEvent) {" +
                        "var event = document.createEvent(\"CustomEvent\");" +
                        "event.initCustomEvent(typeOfEvent,true, true, null);" +
                        "event.dataTransfer = { data: {},setData: function (key, value) {" +
                        "this.data[key] = value; }, getData: function (key) {" +
                        "return this.data[key]; } }; return event;" + "}" +
                        " function dispatchEvent(element, event, transferData) {" +
                        "if (transferData !== undefined) { event.dataTransfer = transferData;" +
                        "} if (element.dispatchEvent) { element.dispatchEvent(event);" +
                        "} else if (element.fireEvent) { element.fireEvent(\"on\" + event.type, event);" +
                        "} } function simulateHTML5DragAndDrop(element, destination) {" +
                        "var dragStartEvent =createEvent('dragstart'); dispatchEvent(element, dragStartEvent);" +
                        "var dropEvent = createEvent('drop'); dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);" +
                        "var dragEndEvent = createEvent('dragend'); dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);" +
                        "} var source = arguments[0]; var destination = arguments[1];" +
                        "simulateHTML5DragAndDrop(source,destination);", sourceElement,
                destinationElement);
    }

    public void executeJavascript(String string) {
        js.executeScript(string);
    }

    public void executeJavascript(String string, WebElement element) {
        js.executeScript(string, element);
    }

    public void scrollToElement(WebElement element) {
        js.scrollToElemet(element);
    }

    public void setAttribute(WebElement element, String attributeName, String value) {
        log.debug("Set value: " + value + " to attribute: " + attributeName + " in element: " + element);
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "')", element);
    }

    public String getText(WebElement element) {
        return (String) js.executeScript("return arguments[0].textContent", element);
    }

    public void pressArrowDownAndEnter(int ArrowDownCount) {
        action = new Actions(driver);
        for (int i = 0; i < ArrowDownCount; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.sendKeys(Keys.ENTER).build().perform();
    }

    public void pressEnter() {
        action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
    }
}
