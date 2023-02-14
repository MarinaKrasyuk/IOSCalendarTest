package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseScreen {
    IOSDriver driver;
    WebDriverWait wait;

    BaseScreen(IOSDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    protected boolean isElementPresentWithinTime(By by, long time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private ExpectedCondition<MobileElement> elementIdDisplayed(By by) {
        return driver -> {
            List<MobileElement> listMobileElemets;
            listMobileElemets = driver.findElements(by);
            if (listMobileElemets.size() > 0 && listMobileElemets.get(0).isDisplayed()) {
                return listMobileElemets.get(0);
            } else return null;
        };
    }

    protected MobileElement findElementByWithWait(By by) {
        return wait.until(elementIdDisplayed(by));
    }

    public void waitAndClick(By by) {
        findElementByWithWait(by).click();
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void clearAndEnterTextToInput(By by, String text) {
        findElementByWithWait(by).clear();
        findElementByWithWait(by).sendKeys(text);
    }

    protected void enterTextToInput(WebElement input, String text) {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(text);
    }

    public void longTap(By element) {
        MobileElement event = findElementByWithWait(element);
        int y = event.getCenter().getY();
        int x = event.getCenter().getX();

        new TouchAction(driver)
                .longPress(PointOption.point(x, y))
                .release()
                .perform();
    }
}
