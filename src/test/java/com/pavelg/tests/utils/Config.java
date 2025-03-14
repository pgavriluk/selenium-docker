package com.pavelg.tests.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() {
        // load default properties
        properties = loadProperties();

        // check for any override
        for(String key : properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }
        // print
        logger.info("Test Properties");
        logger.info("-".repeat(65));
        for (String key : properties.stringPropertyNames()){
            logger.info("{}={}", key, properties.getProperty(key));
        }
        logger.info("-".repeat(65));
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) {
            logger.error("Unable to read the properties file {}", DEFAULT_PROPERTIES, e);
        }

        return properties;
    }

}
