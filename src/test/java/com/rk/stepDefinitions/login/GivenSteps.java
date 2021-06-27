package com.rk.stepDefinitions.login;

import com.rk.stepDefinitions.CommonSteps;
import com.rk.stepDefinitions.StepDefinitionsBase;
import com.rk.commons.driver.ScenarioContext;
import io.cucumber.java.en.Given;

public class GivenSteps extends StepDefinitionsBase {
    public GivenSteps(ScenarioContext context) throws Throwable {
        super(context);
    }

    @Given("User logged in as {string}")
    public void iLoggedInAs(String username) throws Throwable {
        CommonSteps givenSteps = new CommonSteps(scenarioContext);
        givenSteps.i_open_the_param_page();
        Pages().loginPage().loginWithAnyUser(username);
    }
}