package com.rk.stepDefinitions.Home;

import com.rk.commons.driver.ScenarioContext;
import com.rk.stepDefinitions.StepDefinitionsBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class WhenSteps extends StepDefinitionsBase {

    public WhenSteps(ScenarioContext context) throws Throwable {
        super(context);
    }

    @When("user go to sign-in page")
    public void userGoToSignInPage() {
        Pages().homePage().signInButton.click();
    }
}
