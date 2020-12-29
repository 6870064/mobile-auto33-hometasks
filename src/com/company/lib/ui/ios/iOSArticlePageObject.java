package com.company.lib.ui.ios;

import com.company.lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        FIRST_ARTICLE_TITLE_TEXT = "id:Java (programming language)";
        SECOND_ARTICLE_TITLE_TEXT = "id:German industrial metal band";
        FOOTER_ELEMENT = "id:View article in browser";
        FIRST_ARTICLE_DESCRIPTION = "id:Java (programming language)";
        ADDED_ARTICLE_TITLE_TEXT= "id:Java (programming language) Object-oriented programming language";
        SAVE_BUTTON = "id:Save for later";
        ADD_TO_LIST_BUTTON = "Add to…";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_OK_BUTTON = "id:Create reading list"; //Create reading list button
        NAVIGATE_UP_BUTTON = "id:Back";
        ARTICLE_LIST_TITLE = "xpath://android.widget.TextView[@text='1-st articles list for reading']";
        CLOSE_SYNC_POP_UP_BUTTON = "id:places auth close";
    }

    public iOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
