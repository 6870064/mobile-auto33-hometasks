package com.company.lib.ui.factories;

import com.company.lib.Platform;
import com.company.lib.ui.SearchPageObject;
import com.company.lib.ui.android.AndroidSearchPageObject;
import com.company.lib.ui.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory {
    public static SearchPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else {
            return new iOSSearchPageObject(driver);
        }

    }
}
