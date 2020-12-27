package com.company.lib.ui.ios;

import com.company.lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static{
        SKIP_BUTTON = "id:Skip";
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
//        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia'])";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia'])";
        SEARCH_CANCEL_BUTTON = "id:clear mini";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_SUBSTRING2_TPL = "xpath://XCUIElementTypeLink[contains(@name,'German industrial metal band')]";
        SEARCH_RESULT_ELEMENT = "XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_LOCATOR = "xpath://XCUIElementTypeLink[@name='Java (programming language) Object-oriented programming language']";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
