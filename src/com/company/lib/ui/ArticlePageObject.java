package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
    FIRST_ARTICLE_TITLE_TEXT = "//*[contains(@text,'Java (programming language)')]",
    SECOND_ARTICLE_TITLE_TEXT = "//*[contains(@text,'German industrial metal band')]",
    FOOTER_ELEMENT = "//*[@text = 'View page in browser']",
    FIRST_ARTICLE_DESCRIPTION = "//*[contains(@text,'Java (programming language)')]",
    SAVE_BUTTON = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark'][@text='Save']",
    ADD_TO_LIST_BUTTON = "//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']",
    GOT_IT_BUTTON = "//*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']",
    FIRST_TEXT_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
    SECOND_TEXT_INPUT = "//*[@resource-id='org.wikipedia:id/secondary_text_input']",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
    super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.xpath(FIRST_ARTICLE_TITLE_TEXT),"Cannot find article title on the page", 5);
    }

    public WebElement waitForTitleElementSecondArticle(){
        return this.waitForElementPresent(By.xpath(SECOND_ARTICLE_TITLE_TEXT),"Cannot find article title on the page", 5);
    }

    public String getArticleTitle(){ //получение тайтла статьи
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20
        );
    }

public void addFirstArticleToMyList(String articles_list_title, String articles_list_description){

    this.waitForElementPresent(
            By.xpath(FIRST_ARTICLE_DESCRIPTION),
            "Cannot find article title!",
            5
    );

    this.waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
            By.xpath(SAVE_BUTTON),
            "Cannot find the button to open article options",
            5
    );

    this.waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
            By.xpath(ADD_TO_LIST_BUTTON),
            "Cannot find option to add article to the reading list",
            5
    );

    this.waitForElementAndClick(  //Нажатие на кнопку [GOT IT] для закрытия всплывающего поп апа "Add articles to the list...
            By.xpath(GOT_IT_BUTTON),
            "Cannot find option to add article to the reading list",
            5
    );

    this.waitForElementAndSendKeys( //Название раздела
            By.xpath(FIRST_TEXT_INPUT),
            articles_list_title,
            "Cannot find 'Name of this list' input field",
            5
    );

    this.waitForElementAndSendKeys(  //Описание раздела
            By.xpath(SECOND_TEXT_INPUT),
            articles_list_description,
            "Cannot find 'Description (optional)' input field",
            5
    );

    this.waitForElementAndClick( // Нажатие на кнопку [OK] на поп апе.
            By.xpath(MY_LIST_OK_BUTTON),
            "Cannot tap [X] button",
            5
    );

    this.waitForElementAndClick(
            By.xpath(NAVIGATE_UP_BUTTON),
            "Cannot close article, cannot find [<-] button",
            5
    );
}

    public void addSecondArticleToMyList(String articles_list_title, String articles_list_description) {

        this.waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
                By.xpath(SAVE_BUTTON),
                "Cannot find the button to open article options",
                5
        );

        this.waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find option to add article to the reading list",
                5
        );

        this.waitForElementAndClick( //Добавление второй статьи в уже существующий список
                By.xpath("//android.widget.TextView[@text='" + articles_list_title + "']"),
                "Cannot find option to add article to the reading list",
                5
        );
    }
}
