package com.rk.cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/rk/features/",
        glue = {"com/rk/stepDefinitions"},
        plugin = {"pretty", "html:target/report/cucumber", "json:target/report/json/login.json",
                "unused:target/unused.log"},
        monochrome = true,
        strict = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}