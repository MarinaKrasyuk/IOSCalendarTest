package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import utils.Utils;

public class NotificationScreen extends BaseScreen{
    IOSDriver driver;

    private final String NOTIFICATION_XPATH_PATTERN = "label contains '%s' and label contains '%s' and label contains '%s'";

    public NotificationScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public boolean validateNotificatinIsReceived(String eventName, String locationName, String startTime) {
        return isElementPresentWithinTime(MobileBy.iOSNsPredicateString(String.format(NOTIFICATION_XPATH_PATTERN, eventName, locationName, Utils.formatTime(startTime))),10);
    }
}
