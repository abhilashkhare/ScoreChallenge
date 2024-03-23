package utils;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Properties;

public class LocatorUtils {
    private static final Properties locators = new Properties();

    static {
        try (InputStream input = LocatorUtils.class.getClassLoader().getResourceAsStream("locators.properties")) {
            locators.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load locators.properties file", ex);
        }
    }

    public static By getLocator(String name) {
        String locator = locators.getProperty(name);
        String[] parts = locator.split(":", 2);
        switch (parts[0]) {
            case "By.id":
                return By.id(parts[1]);
            case "By.xpath":
                return By.xpath(parts[1]);
            case "By.accessibilityId":
                return MobileBy.AccessibilityId(parts[1]);
            // Add other cases as needed
            default:
                throw new IllegalArgumentException("Unknown locator type in locators.properties: " + parts[0]);
        }
    }
}
