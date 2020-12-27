package com.company.lib.ui;

import com.company.lib.Platform;
import io.appium.java_client.AppiumDriver;

abstract public class MyListsPageObject extends MainPageObject {

     public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    protected static String
    ADD_TO_LIST_BUTTON,
    ARTICLE_LIST_TITLE,
    DELETE_FIRST_ARTICLE_TITLE,
    SECOND_ARTICLE_TITLE,
    ARTICLE_BY_TITLE_TPL,
    SAVED_BUTTON;

    public void myListOpening(String articles_list_title){
        waitForElementAndClick( // Нажатие на [VIEW LIST] и открытие переченя списков статей
                ADD_TO_LIST_BUTTON,
                "Cannot open My lists screen",
                5
        );
    }

    public void swipeArticleToDelete(String article_title){
        swipeElementToTheLeft( // Удаление первой статьи
                DELETE_FIRST_ARTICLE_TITLE,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(DELETE_FIRST_ARTICLE_TITLE,"Cannot find saved article for swipe");
        }

        waitForElementNotPresent(
                DELETE_FIRST_ARTICLE_TITLE,
                "Cannot delete saved article",
                5
        );
    }

    public void articleAvailabilityCheck(String second_word_for_search){
        waitForElementPresent( // Убеждаемся, что вторая статься осталась
                SECOND_ARTICLE_TITLE,
                "Cannot find saved article titled " + second_word_for_search + "",
                10
        );

        waitForElementAndClick(  //Открытие статьи. по сути и есть верификация по тайтлу.
                SECOND_ARTICLE_TITLE,
                "Cannot open the article titled " + second_word_for_search + "",
                10
        );
    }
    public void mySavedArticlesFolderOpening(){
        waitForElementAndClick(
              SAVED_BUTTON,
                "Cannot find saved articles list",
              5);


    }
}
