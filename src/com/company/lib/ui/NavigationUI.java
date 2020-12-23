package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    private static final String
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";

    public void backNavigation(){
        driver.hideKeyboard();
            this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click '[x] button'", 5);
    }
}
