package com.company.lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/dzmitryviachaslavau/Documents/MobileAutomationCourse/HomeTasks/mobile-auto33-task02/apks/org.wikipedia.com.apk");
        capabilities.setCapability("orientation", "PORTRAIT"); // добавлена Capability на ориентацию

        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }
}
