package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

public class Utils extends BaseTest {

    public static String formatTime(String time) {
        if (time.charAt(0) == '0') {
            return time.substring(1);
        }
        return time;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG () {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    public static String[] separateMarkerFormat(String time) {
        String[] result = time.split("\\s+");
        return result;
    }

}
