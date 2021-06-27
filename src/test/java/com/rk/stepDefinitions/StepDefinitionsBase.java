package com.rk.stepDefinitions;

import com.rk.commons.driver.ScenarioContext;
import com.rk.utils.PageObjectManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//todo: incorrect name for class. This class is not base for steps only
public class StepDefinitionsBase {

    protected ScenarioContext scenarioContext;
    protected static Logger logger;
    public static int value;

    public StepDefinitionsBase(ScenarioContext context) throws Throwable {
        scenarioContext = context;
        logger = LogManager.getLogger(this);
        logger.info("Initialised");
    }

    protected PageObjectManager Pages() {
        return scenarioContext.getPageObjectManager();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}

