package utils;

import org.apache.log4j.PropertyConfigurator;

import static utils.PropertiesHelper.getInstance;

public class EnvironmentContext {
    public static PropertiesHelper propertiesHelper = getInstance();

    public static String baseUrl;
    public static String username;
    public static String password;
    public static String selectOption;
    public static String expectedResult;
    public static String hubUrl;
    public static String browser;
    public static Boolean localRun;

    public static void configureConstant() {
        baseUrl = propertiesHelper.getProperty("baseUrl");
        username = propertiesHelper.getProperty("username");
        password = propertiesHelper.getProperty("password");
        selectOption = propertiesHelper.getProperty("selectOption");
        expectedResult = propertiesHelper.getProperty("expectedResult");
        hubUrl = propertiesHelper.getProperty("hubUrl");
        browser = propertiesHelper.getProperty("browser");
        localRun = Boolean.parseBoolean(propertiesHelper.getProperty("localRun"));
    }

    public static void configureLog4j() {
        PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
    }
}
