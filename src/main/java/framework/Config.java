package framework;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final Properties properties = new Properties();
    private final String browser;
    private final String url;


    public Config() {
        try (InputStream propertyStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(propertyStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while loading config.properties", e);
        }
        browser = getConfig("browser");
        url = getConfig("url");
    }

    public String getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }

    private String getConfig(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            if (properties.getProperty(key) != null) {
                return properties.getProperty(key);
            } else if (properties.getProperty(key) == null) {
                return null;
            } else {
                throw new RuntimeException("Configuration value not found for key '" + key + "'");
            }
        }
        return value;
    }

}