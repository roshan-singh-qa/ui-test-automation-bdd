package com.rk.commons.configuration;

import com.rk.commons.configuration.enums.BrowserType;
import com.rk.commons.configuration.enums.OperatingSystem;
import com.rk.commons.configuration.sections.DriverDetails;
import com.rk.commons.configuration.sections.Environment;
import com.rk.commons.configuration.sections.Timeout;
import com.rk.commons.configuration.sections.User;
import com.rk.commons.utils.PropertiesFileReader;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Configuration {
    public static DriverDetails driver;
    public static Timeout timeout;
    public static Environment environment;
    public static HashMap<String, User> users = new HashMap<>();

    private static final String CONFIG_FILE_NAME = "config.file";
    private static final String USERS = "config.users";
    private static final String PAGE_TIMEOUT = "timeout.page";
    private static final String IMPLICIT_TIMEOUT = "timeout.implicit";
    private static final String EXPLICIT_TIMEOUT = "timeout.explicit";
    private static final String POOLING_INTERVAL_TIMEOUT = "timeout.poolingInterval";
    private static final String SHOULD_HIGHLIGHT_ELEMENT = "driverDetails.shouldHighlightElement";
    private static final String HIGHLIGHT_COLOR = "driverDetails.highlightColor";
    private static final String IS_HEADLESS = "driverDetails.isHeadless";
    private static final String DRIVER_TYPE = "driverDetails.browserType";
    private static final String DRIVER_OS = "driverDetails.os";
    private static final String VERBOSE = "driverDetails.verbose";
    private static final String IS_INCOGNITO = "driverDetails.isIncognito";
    private static final String ENVIRONMENT_NAME = "environment.name";
    private static final String ENVIRONMENT_URL = "environment.url";

    static {
        loadEnvironmentConfiguration();
    }

    private static void loadEnvironmentConfiguration() {
        var reader = new PropertiesFileReader();
        var properties = reader.readPropertyFile(getConfigurationFilePath());

        timeout.page = Integer.parseInt(properties.getProperty(PAGE_TIMEOUT));
        timeout.implicit = Integer.parseInt(properties.getProperty(IMPLICIT_TIMEOUT));
        timeout.explicit = Integer.parseInt(properties.getProperty(EXPLICIT_TIMEOUT));
        timeout.poolingInterval = Integer.parseInt(properties.getProperty(POOLING_INTERVAL_TIMEOUT));
        driver.shouldHighlightElement = Boolean.parseBoolean(properties.getProperty(SHOULD_HIGHLIGHT_ELEMENT));
        driver.highlightColor = properties.getProperty(HIGHLIGHT_COLOR);
        driver.isHeadless = Boolean.parseBoolean(properties.getProperty(IS_HEADLESS));
        driver.browserType = BrowserType.valueOf(properties.getProperty(DRIVER_TYPE).toUpperCase());
        driver.os = OperatingSystem.valueOf(properties.getProperty(DRIVER_OS).toUpperCase());
        driver.isIncognito = Boolean.parseBoolean(properties.getProperty(IS_INCOGNITO));
        driver.verbose = Boolean.parseBoolean(properties.getProperty(VERBOSE));
        environment.name = properties.getProperty(ENVIRONMENT_NAME);
        environment.url = properties.getProperty(ENVIRONMENT_URL);

        prepareUsers();
    }

    private static String getConfigurationFilePath() {
        var suite = getNgConfiguration();
        return suite.getParameter(CONFIG_FILE_NAME);
    }

    private static void prepareUsers() {
        var suite = getNgConfiguration();
        var usersParameter = suite.getParameter(USERS);

        split(usersParameter, ",").forEach(splittedUser -> {
            var credentials = split(splittedUser, "/");
            var user = new User(credentials.get(0), credentials.get(1));
            users.put(user.username, user);
        });
    }

    private static List<String> split(String stringToSplit, String delimiter) {
        return Stream.of(stringToSplit.split(delimiter))
                .map(fragment -> new String(fragment))
                .collect(Collectors.toList());
    }

    private static XmlTest getNgConfiguration() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
    }
}
