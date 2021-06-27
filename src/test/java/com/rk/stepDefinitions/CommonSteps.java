package com.rk.stepDefinitions;

import com.rk.commons.configuration.Configuration;
import com.rk.commons.driver.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.rk.utils.WebDriverManager;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CommonSteps extends StepDefinitionsBase {

    public CommonSteps(ScenarioContext context) throws Throwable {
        super(context);
    }

    private WebElement findElement(String needle) throws Throwable {

        EventFiringWebDriver driver = scenarioContext.getWebDriverManager().getDriver();
        WebElement found = null;

        Integer retry = 0;
        while (retry < 20) {
            if (found == null)
                try {
                    found = driver.findElement(By.id(needle));
                } catch (Exception e) {
                }
            if (found == null)
                try {
                    found = driver.findElement(By.name(needle));
                } catch (Exception e) {
                }
            if (found == null)
                try {
                    found = driver.findElement(By.className(needle));
                } catch (Exception e) {
                }
            if (found == null)
                try {
                    found = driver.findElement(By.xpath("//*[contains(text(),'" + needle + "')]"));
                } catch (Exception e) {
                }
            if (found != null)
                break;

            retry++;
            Thread.sleep(500);
        }

        return found;
    }

    @Given("^I open the \"([^\"]*)\" page$")
    public void i_open_the_param_page() throws Throwable {

        EventFiringWebDriver driver = scenarioContext.getWebDriverManager().getDriver();
        String baseURL = Configuration.environment.url;
        driver.get(baseURL);

    }

    @Given("^I open the url \"([^\"]*)\"$")
    public void i_open_the_url(String url) throws Throwable {

        EventFiringWebDriver driver = scenarioContext.getWebDriverManager().getDriver();
        String baseURL = Configuration.environment.url;
        String absoluteURL = baseURL;

        absoluteURL += "/" + url;

        driver.get(absoluteURL);

    }

    @When("^I (add|set) \"([^\"]*)\" into the \"([^\"]*)\" field$")
    public void i_addset_param_into_the_inputfield_param(String action, String value, String inputField)
            throws Throwable {

        WebElement input = findElement(inputField);

        if (action.toLowerCase().trim() == "add") {
            input.sendKeys(value);
        } else {
            input.clear();
            input.sendKeys(value);
        }
    }

    @When("I click on the {string} (link|button|element)")
    public void i_click_on_the_param_linkbuttonelement(String identifier) throws Throwable {
        WebElement element = findElement(identifier);
        element.click();
    }

    @When("I double click on the {string} (link|button|element)")
    public void i_double_click_on_the_param_linkbuttonelement(String identifier) throws Throwable {
        WebElement element = findElement(identifier);
        element.click();
        Thread.sleep(250);
        element.click();
    }

    @Then("^I take a screenshot \"([^\"]*)\"$")
    public void i_take_a_screenshot(String screenshotFilename) throws Throwable {

        String scenarioName = Configuration.environment.url;

        WebDriverManager webDriverManager = scenarioContext.getWebDriverManager();
        webDriverManager.takeScreenshot(scenarioName, screenshotFilename);
    }
}