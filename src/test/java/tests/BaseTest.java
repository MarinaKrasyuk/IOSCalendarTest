package tests;

import driver.Driver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import screens.CalendarScreen;
import screens.NewEventScreen;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BaseTest {

    public static IOSDriver driver;
    LocalDateTime startDateTime;
    LocalDateTime notificationTime;
    String startTime;
    String endTime;
    public static String eventName;
    public static String locationName;
    private static CalendarScreen calendarScreen;
    private static NewEventScreen newEventScreen;

    @BeforeAll
    public static void initialDriver() throws MalformedURLException {
        driver = Driver.getDriver();
        eventName = "Test Event";
        locationName = "Minsk,Belarus";
        calendarScreen = new CalendarScreen(driver);
        newEventScreen = new NewEventScreen(driver);
    }

    void setUpEventToTheCalendar(int deltaStartTime, int deltaEndTime) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

        startDateTime = timestamp.toLocalDateTime().plusMinutes(deltaStartTime);
        notificationTime = startDateTime.minusMinutes(5);
        startTime = formatter.format(startDateTime);

        LocalDateTime endDateTime = startDateTime.plusMinutes(deltaEndTime);
        endTime = formatter.format(endDateTime);

        newEventScreen.setUpEvent(eventName, locationName, startTime, endTime);
        calendarScreen.validateEventIsCreated(eventName, locationName, startTime);
    }
    void showNotifications() {
        manageNotifications(true);
    }

    void hideNotifications() {
        manageNotifications(false);
    }

    private void manageNotifications(Boolean show) {
        int yMargin = 5;
        Dimension screenSize = driver.manage().window().getSize();
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction(driver);
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.perform();
    }
    @AfterAll
    public static void  tearDown() {
        Driver.closeDriver(driver);
    }
}
