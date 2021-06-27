package com.rk.stepDefinitions.Home;

import com.rk.commons.driver.ScenarioContext;
import com.rk.stepDefinitions.StepDefinitionsBase;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ThenSteps extends StepDefinitionsBase {

    public ThenSteps(ScenarioContext context) throws Throwable {
        super(context);
    }

    @Then("user will see sign option")
    public void userWillSeeSignOption() {
        Assert.assertTrue(Pages().loginPage().userName.isDisplayed());
    }
}
