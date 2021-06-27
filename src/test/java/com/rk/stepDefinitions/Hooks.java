package com.rk.stepDefinitions;

import com.rk.commons.driver.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends StepDefinitionsBase {

    public Hooks(ScenarioContext context) throws Throwable {
        super(context);
    }

    @Before
    public void setUpScenario(Scenario scenario) throws Throwable {
        scenarioContext.initialize();
        scenarioContext.setToContainer("scenarioName", scenario.getName());
    }

    @After()
    public void tearDownScenario() {
        if (scenarioContext == null) {
            return;
        }
        scenarioContext.tearDown();
    }
}
