package com.company.lib.ui.android;

import com.company.lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
    FIRST_ARTICLE_TITLE_TEXT = "xpath://*[contains(@text,'Java (programming language)')]";
    SECOND_ARTICLE_TITLE_TEXT = "xpath://*[contains(@text,'German industrial metal band')]";
    FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']";
    FIRST_ARTICLE_DESCRIPTION = "xpath://*[contains(@text,'Java (programming language)')]";
    SAVE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
    ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
    GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
    FIRST_TEXT_INPUT = "id:org.wikipedia:id/text_input";
    SECOND_TEXT_INPUT = "id:org.wikipedia:id/secondary_text_input";
    MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    ARTICLE_LIST_TITLE = "xpath://android.widget.TextView[@text='1-st articles list for reading']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
