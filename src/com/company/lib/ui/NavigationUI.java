package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void backNavigation(){
        driver.hideKeyboard();
        driver.navigate().back();
    }
}
