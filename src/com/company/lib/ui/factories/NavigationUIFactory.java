package com.company.lib.ui.factories;

import com.company.lib.Platform;
import com.company.lib.ui.NavigationUI;
import com.company.lib.ui.android.AndroidNavigationUI;
import com.company.lib.ui.ios.iOSNavigationUI;
import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }
    }
}
