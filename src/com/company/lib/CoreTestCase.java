package com.company.lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String
    PLATFORM_IOS = "ios",
    PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv(); //Устанавливаем capabilities через этот метод
        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {

        String platform = System.getenv("PLATFORM"); //С помощью этой строчки получаем значение переменной среды
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "10");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/dzmitryviachaslavau/Documents/MobileAutomationCourse/HomeTasks/mobile-auto33-hometasks/apks/org.wikipedia.com.apk");
            capabilities.setCapability("orientation", "PORTRAIT"); // добавлена Capability на ориентацию
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone X");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("app", "/Users/dzmitryviachaslavau/Documents/MobileAutomationCourse/HomeTasks/mobile-auto33-hometasks/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " +platform);
        }
        return capabilities;
    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds){
        driver.runAppInBackground(seconds);
    }

    protected void launchdApp(){
        driver.launchApp();
    }
}
