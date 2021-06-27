package com.rk.cucumberOptions;

import com.rk.commons.utils.Resource;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class App {
    public static void main(String[] args) {
        TestNG testngRunner = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(new Resource().getPathToFile("configs/testng.xml"));
        testngRunner.setTestSuites(suites);

        //Run tests
        testngRunner.run();

        //Produce Report
    }
}