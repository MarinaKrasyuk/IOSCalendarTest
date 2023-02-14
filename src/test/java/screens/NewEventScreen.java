package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import utils.Utils;

public class NewEventScreen extends BaseScreen{
    IOSDriver driver;

    private final By EVENT_NAME_INPUT = MobileBy.iOSNsPredicateString("name == \"Title\"");
    private final By START_TIME_BUTTON = MobileBy.iOSClassChain("**/XCUIElementTypeCell[`label contains \"Starts\"`]/XCUIElementTypeStaticText[2]");
    private final By TIME_PICKER = MobileBy.iOSNsPredicateString("label == \"Time Picker\"");
    private final By AM_MARKER_FORMAT = MobileBy.iOSNsPredicateString("label == \"AM\"");
    private final By PM_MARKER_FORMAT = MobileBy.iOSNsPredicateString("label == \"PM\"");
    private final By END_TIME_BUTTON = MobileBy.iOSClassChain("**/XCUIElementTypeCell[`label contains \"Ends\"`]/XCUIElementTypeStaticText[2]");
    private final By SAVE_BUTTON = MobileBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Add\"`][1]");
    private final By ADD_LOCATION_BUTTON = MobileBy.iOSNsPredicateString("label == \"Location\"");
    private final By SEARCH_INPUT = MobileBy.iOSNsPredicateString("label == \"Enter Location\"");
    private final By LOCATION_BUTTON= MobileBy.iOSClassChain("**/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]");//MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'Minsk,Belarus')]");
    private final By ALERT_BUTTON = MobileBy.iOSNsPredicateString("label == \"Alert\"");
    private final By ALERT_5_MINUTES = MobileBy.iOSNsPredicateString("label == \"5 minutes before\"");

    public NewEventScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private void enterEventName(String eventName) {
        clearAndEnterTextToInput(EVENT_NAME_INPUT, eventName);
    }

    public void setUpEvent(String eventName,String locationName, String startTime, String endTime) {
        enterEventName(eventName + "\n");
        addLocation(locationName);
        waitAndClick(START_TIME_BUTTON);

        waitAndClick(TIME_PICKER);
        setUpTime(startTime);

        waitAndClick(END_TIME_BUTTON);
        setUpTime(endTime);

        addNotification();

        waitAndClick(driver.findElement(SAVE_BUTTON));
    }

    public void addNotification() {
        waitAndClick(ALERT_BUTTON);
        waitAndClick(ALERT_5_MINUTES);
    }

    private void setUpTime (String time) {
        String[] resultTime = Utils.separateMarkerFormat(time);
        enterTextToInput(driver.findElement(TIME_PICKER), resultTime[0] + "\n");
        MobileElement markerFormat = (MobileElement) driver.findElement(AM_MARKER_FORMAT);

        if (markerFormat.isSelected() && !resultTime[1].equals(markerFormat.getAttribute("name"))) {
            waitAndClick(PM_MARKER_FORMAT);
        }
    }

    public void addLocation(String locationName){
        waitAndClick(driver.findElement(ADD_LOCATION_BUTTON));
        clearAndEnterTextToInput(SEARCH_INPUT, locationName);
        driver.findElement(LOCATION_BUTTON).click();
    }
}
