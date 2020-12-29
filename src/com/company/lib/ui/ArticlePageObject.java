package com.company.lib.ui;

import com.company.lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
    FIRST_ARTICLE_TITLE_TEXT,
    ADDED_ARTICLE_TITLE_TEXT,
    SECOND_ARTICLE_TITLE_TEXT,
    FOOTER_ELEMENT,
    FIRST_ARTICLE_DESCRIPTION,
    SAVE_BUTTON,
    ADD_TO_LIST_BUTTON,
    GOT_IT_BUTTON,
    FIRST_TEXT_INPUT,
    SECOND_TEXT_INPUT,
    MY_LIST_OK_BUTTON,
    NAVIGATE_UP_BUTTON,
    ARTICLE_LIST_TITLE,
    CLOSE_SYNC_POP_UP_BUTTON;


    public ArticlePageObject(RemoteWebDriver driver) {
    super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(FIRST_ARTICLE_TITLE_TEXT,"Cannot find article title on the page", 5);
    }

    public WebElement waitForTitleElementSecondArticle(){
        return this.waitForElementPresent(SECOND_ARTICLE_TITLE_TEXT,"Cannot find article title on the page", 5);
    }

    public String getArticleTitle(){ //получение тайтла статьи
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter(){

        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    20
            );
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
            "Cannot find the end of the article",
            40);
        }

    }

public void addFirstArticleToMyList(String articles_list_title, String articles_list_description){

    this.waitForElementPresent(
           FIRST_ARTICLE_DESCRIPTION,
            "Cannot find article title!",
            5
    );

    this.waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
            SAVE_BUTTON,
            "Cannot find the button to open article options",
            5
    );

    this.waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
            ADD_TO_LIST_BUTTON,
            "Cannot find option to add article to the reading list",
            5
    );

    this.waitForElementAndClick(  //Нажатие на кнопку [GOT IT] для закрытия всплывающего поп апа "Add articles to the list...
            GOT_IT_BUTTON,
            "Cannot find option to add article to the reading list",
            5
    );

    this.waitForElementAndSendKeys( //Название раздела
            FIRST_TEXT_INPUT,
            articles_list_title,
            "Cannot find 'Name of this list' input field",
            5
    );

    this.waitForElementAndSendKeys(  //Описание раздела
            SECOND_TEXT_INPUT,
            articles_list_description,
            "Cannot find 'Description (optional)' input field",
            5
    );

    this.waitForElementAndClick( // Нажатие на кнопку [OK] на поп апе.
            MY_LIST_OK_BUTTON,
            "Cannot tap [X] button",
            5
    );

    this.waitForElementAndClick(
            NAVIGATE_UP_BUTTON,
            "Cannot close article, cannot find [<-] button",
            5
    );
}

    public void addSecondArticleToMyList(String articles_list_title, String articles_list_description) {

        this.waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
                SAVE_BUTTON,
                "Cannot find the button to open article options",
                5
        );

        this.waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
               ADD_TO_LIST_BUTTON,
                "Cannot find option to add article to the reading list",
                5
        );

        this.waitForElementAndClick( //Добавление второй статьи в уже существующий список
                ARTICLE_LIST_TITLE,
                "Cannot find option to add article to the reading list",
                5
        );
    }

    public void addArticleToSaved(){

    this.waitForElementAndClick(
    SAVE_BUTTON,
    "Cannot close article, cannot find option to add article to the reading list",
    12
        );

        this.waitForElementAndClick(
                CLOSE_SYNC_POP_UP_BUTTON,
                "Cannot tap [x] button on 'Sync your saved articles' pop up",
                10
        );
    }
    public void addSecondArticleToSaved(){

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot close article, cannot find option to add article to the reading list",
                12
        );
    }
}
