package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.Utils;

public class CalendarScreen extends BaseScreen {
    IOSDriver driver;

    private final By ADD_BUTTON = MobileBy.id("Add");
    private final By DELETE_BUTTON = MobileBy.iOSNsPredicateString("label == 'Delete Event'");
    private final By TODAY_BUTTON = MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Today'`]");
    private final String VALIDATION_XPATH_PATTERN = "label contains '%s' and label contains '%s' and label contains '%s'";
    private final String DELETE_XPATH_PATTERN = "label == '%s'";

    public CalendarScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private void tapAddButton() {
        waitAndClick(ADD_BUTTON);
    }

    public void navigateToNewEventScreen() {
        tapAddButton();
    }

    public void validateEventIsCreated(String eventName, String locationName, String startTime) {
        waitAndClick(TODAY_BUTTON);
        Assert.assertTrue("Event is not created", isElementPresentWithinTime(MobileBy.iOSNsPredicateString(String.format(VALIDATION_XPATH_PATTERN, eventName, locationName, Utils.formatTime(startTime))), 10));
    }

    public boolean deleteEvent(String eventName) {
        waitAndClick(TODAY_BUTTON);
        By eventBy = MobileBy.iOSNsPredicateString(String.format(DELETE_XPATH_PATTERN, eventName));
        if (isElementPresentWithinTime(eventBy, 10)) {
            longTap(eventBy);
            waitAndClick(DELETE_BUTTON);
            waitAndClick(DELETE_BUTTON);
        }
        if (!isElementPresentWithinTime(eventBy, 10)) {
            return true;
        }
        return false;
    }
}
