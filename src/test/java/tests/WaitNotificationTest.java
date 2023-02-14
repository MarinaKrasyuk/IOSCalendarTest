package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import screens.CalendarScreen;
import screens.NewEventScreen;
import screens.NotificationScreen;
import screens.OnboardingScreen;
import utils.RunnerExtension;

import java.sql.Timestamp;
@ExtendWith(RunnerExtension.class)
public class WaitNotificationTest extends BaseTest {
    private OnboardingScreen onboardingScreen;
    private CalendarScreen calendarScreen;
    private NewEventScreen newEventScreen;
    private NotificationScreen notificationScreen;


    @BeforeEach
    public void setUp()  {
        eventName = "Event for Current time";
        onboardingScreen = new OnboardingScreen(driver);
        calendarScreen = new CalendarScreen(driver);
        newEventScreen = new NewEventScreen(driver);
        notificationScreen = new NotificationScreen(driver);
    }

    @Test
    @AllureId("102")
    @Description("Set up Calendar event for Current time plus 1 minute and validation Notification")
    @Severity(SeverityLevel.NORMAL)
    public void waitNotificationTest() {
        onboardingScreen.completeOnboarding();
        calendarScreen.navigateToNewEventScreen();
        setUpEventToTheCalendar(6,10);

        validationPushNotificationIsReceived();
    }

    private void validationPushNotificationIsReceived() {
        while (new Timestamp(System.currentTimeMillis()).toLocalDateTime().isBefore(notificationTime)) {
        }
        driver.terminateApp("com.apple.mobilecal");

        showNotifications();

        Assert.assertTrue("Notification is not received", notificationScreen.validateNotificatinIsReceived(eventName, locationName,startTime));
        hideNotifications();

        driver.activateApp("com.apple.mobilecal");
}


    @AfterEach
    public void cleanUp() {
        calendarScreen.deleteEvent(eventName);
    }
}
