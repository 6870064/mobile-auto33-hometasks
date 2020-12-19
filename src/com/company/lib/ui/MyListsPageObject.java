package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }
    public void myListOpening(String articles_list_title){
        waitForElementAndClick( // Нажатие на [VIEW LIST] и открытие переченя списков статей
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot open My lists screen",
                5
        );

        waitForElementAndClick( //Открытие списка статей с сохраненными двумя статьями
                By.xpath("//android.widget.TextView[@text='" + articles_list_title + "']"),
                "Cannot open articles list titled '" + articles_list_title + "'",
                5
        );
    }

    public void swipeArticleToDelete(String first_word_for_search_description){
        swipeElementToTheLeft( // Удаление первой статьи
                By.xpath("//*[@text='" + first_word_for_search_description + "']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + first_word_for_search_description+ "']"),
                "Cannot delete saved article",
                5
        );
    }

    public void articleAvailabilityCheck(String second_word_for_search){
        waitForElementPresent( // Убеждаемся, что вторая статься осталась
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + second_word_for_search + "']"),
                "Cannot find saved article titled " + second_word_for_search + "",
                10
        );

        waitForElementAndClick(  //Открытие статьи. по сути и есть верификация по тайтлу.
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + second_word_for_search + "']"),
                "Cannot open the article titled " + second_word_for_search + "",
                10
        );
    }
}
