package com.rk.stepDefinitions.Home;

import com.rk.commons.driver.ScenarioContext;
import com.rk.stepDefinitions.CommonSteps;
import com.rk.stepDefinitions.StepDefinitionsBase;
import io.cucumber.java.en.Given;

public class GivenSteps extends StepDefinitionsBase {

    public GivenSteps(ScenarioContext context) throws Throwable {
        super(context);
    }

    @Given("user is present on home page")
    public void homePage() throws Throwable {
        CommonSteps givenSteps = new CommonSteps(scenarioContext);
        givenSteps.i_open_the_param_page();
    }
}
