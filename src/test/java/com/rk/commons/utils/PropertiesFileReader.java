package com.rk.commons.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

public class PropertiesFileReader {

    public static final Logger LOG = LogManager.getLogger(PropertiesFileReader.class);

    public Properties readPropertyFile(String configurationFileName) {
        var properties = new Properties();
        var inputStream = getClass().getClassLoader().getResourceAsStream(configurationFileName);

        try {
            properties.load(inputStream);
        } catch (Exception e) {
            LOG.debug("Exception occurred while loading properties file");
            e.printStackTrace();
        }

        return properties;
    }

}
