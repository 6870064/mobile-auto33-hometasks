package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {


    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public static String
    ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action",
   ARTICLE_LIST_TITLE = "xpath://android.widget.TextView[@text='1-st articles list for reading']",
   DELETE_FIRST_ARTICLE_TITLE = "xpath://*[@text='Object-oriented programming language']",
    SECOND_ARTICLE_TITLE =  "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Rammstein']"


    ;

    public void myListOpening(String articles_list_title){
        waitForElementAndClick( // Нажатие на [VIEW LIST] и открытие переченя списков статей
                ADD_TO_LIST_BUTTON,
                "Cannot open My lists screen",
                5
        );

        waitForElementAndClick( //Открытие списка статей с сохраненными двумя статьями
                ARTICLE_LIST_TITLE,
                "Cannot open articles list titled '" + articles_list_title + "'",
                5
        );
    }

    public void swipeArticleToDelete(String first_word_for_search_description){
        swipeElementToTheLeft( // Удаление первой статьи
                DELETE_FIRST_ARTICLE_TITLE,
                "Cannot find saved article"
        );

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
}
