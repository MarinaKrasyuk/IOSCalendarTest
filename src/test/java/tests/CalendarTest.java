package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import screens.CalendarScreen;
import screens.NewEventScreen;
import screens.OnboardingScreen;
import utils.RunnerExtension;

@ExtendWith(RunnerExtension.class)
public class CalendarTest extends BaseTest {

    private String eventName;
    private String locationName;
    private String startTime;
    private String endTime;
    private OnboardingScreen onboardingScreen;
    private CalendarScreen calendarScreen;
    private NewEventScreen newEventScreen;
    @BeforeEach
    public void setUp(){
        eventName = "Test Event";
        locationName = "Minsk,Belarus";
        onboardingScreen = new OnboardingScreen(driver);
        calendarScreen = new CalendarScreen(driver);
        newEventScreen = new NewEventScreen(driver);
    }
    @AllureId("101")
    @Description("Set up Calendar event for Current time plus 1 hour")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createCalendarEventTest() {
        onboardingScreen.completeOnboarding();
        calendarScreen.navigateToNewEventScreen();
        setUpEventToTheCalendar(60, 90);
    }

    @AfterEach
    public void cleanUp() {
        if (calendarScreen.deleteEvent(eventName)) {
            System.out.println("Event is deleted");
        }
        else System.out.println("Event is not deleted");
    }

}
