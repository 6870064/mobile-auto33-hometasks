package com.company.lib.ui;
import com.company.lib.Platform;
import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
    SEARCH_CANCEL_BUTTON,
    BACK_BUTTON,
    EXPLORE_BUTTON;

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void backNavigation(){

        if (Platform.getInstance().isAndroid()) {
            driver.hideKeyboard();
            this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click '[x] button'", 5);
        } else {
            this.waitForElementAndClick(
                    BACK_BUTTON,
                    "Cannot tap [<] Back button to close pop up",
                    12
            );
        }
    }
}
