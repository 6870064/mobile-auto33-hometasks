package com.company.lib.ui.ios;

import com.company.lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        FIRST_ARTICLE_TITLE_TEXT = "id:Java (programming language)";
        SECOND_ARTICLE_TITLE_TEXT = "xpath://*[contains(@text,'German industrial metal band')]";
        FOOTER_ELEMENT = "id:View article in browser";
        FIRST_ARTICLE_DESCRIPTION = "id:Java (programming language)";
        ADD_TO_LIST_BUTTON = "id:Save for later";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        FIRST_TEXT_INPUT = "id:org.wikipedia:id/text_input";
        SECOND_TEXT_INPUT = "id:org.wikipedia:id/secondary_text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        NAVIGATE_UP_BUTTON = "id:Back";
        ARTICLE_LIST_TITLE = "xpath://android.widget.TextView[@text='1-st articles list for reading']";
        LOG_IN_TO_SYNC_BUTTON = "id:Log in to sync your saved articles";
        CLOSE_LOG_IN_POP_UP_BUTTON = "id:close";
        ADD_ARTICLE_TO_THE_LIST = "Add 'Java (programming language)' to a reading list?";
        ADD_BUTTON = "id:Add";
        BACK_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
