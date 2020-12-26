package com.company.lib.ui.android;

import com.company.lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static{
    SKIP_BUTTON = "xpath://*[contains(@text,'SKIP')]";
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";
    SEARCH_RESULT_BY_SUBSTRING2_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='German industrial metal band']";
    SEARCH_RESULT_ELEMENT = "xpath://*[resource-id='org.wikipedia:id/search_results_list']/*[resource-id=org.wikipedia:id/page_list_item_description']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    SEARCH_RESULT_LOCATOR = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
