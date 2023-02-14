package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class OnboardingScreen extends BaseScreen {
    IOSDriver driver;

    private final By ALLOW_MODULE_WINDOW = MobileBy.iOSClassChain("**/XCUIElementTypeAlert[`label == \"Allow “Calendar” to use your location?\"`]/XCUIElementTypeOther/XCUIElementTypeOther");
    private final By ALLOW_WHILE_USING_APP_BUTTON = MobileBy.iOSNsPredicateString("label == \"Allow While Using App\"");

    public OnboardingScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private void tapAllowButton() {
        waitAndClick(driver.findElement(ALLOW_WHILE_USING_APP_BUTTON));
    }

    public void completeOnboarding() {
        if(isElementPresentWithinTime(ALLOW_MODULE_WINDOW,5)){
            tapAllowButton();
        }
    }

}