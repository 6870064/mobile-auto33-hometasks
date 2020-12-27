package com.company.lib.ui.ios;

import com.company.lib.ui.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        BACK_BUTTON = "id:Back";
        EXPLORE_BUTTON = "id:Explore";
    }

   public iOSNavigationUI(AppiumDriver driver){
       super(driver);
   }
}
