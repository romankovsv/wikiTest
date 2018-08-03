package framework;

import java.util.HashMap;
import java.util.Map;

public enum BrowserType {
    FIREFOX, CHROME;

    private static Map<String, BrowserType> browsersMap = new HashMap<String, BrowserType>();

    static {
        browsersMap.put("chrome", BrowserType.CHROME);
        browsersMap.put("firefox", BrowserType.FIREFOX);
    }

    public static BrowserType Browser(String name) {
        BrowserType browserType = null;
        if (name != null) {
            browserType = browsersMap.get(name.toLowerCase().trim());
            if (browserType == null) {
                throw new RuntimeException(String.format("There are no such browser :" + name));
            }
        }

        return browserType;
    }

}
