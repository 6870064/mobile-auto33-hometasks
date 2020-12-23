package com.company.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_BY_SUBSTRING2_TPL,
    SEARCH_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    SEARCH_RESULT_LOCATOR;

    public SearchPageObject(AppiumDriver driver) { //берем драйвер из MainPageObject
        super(driver);
    }

    private static String getResultSearchElement (String substring){  //Template method
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementSecondArticle (String substring){  //Template method
        return SEARCH_RESULT_BY_SUBSTRING2_TPL.replace("{SUBSTRING}", substring);
    }

    public void typeSkipElement() { //кликаем на кнопкку SKIP
      WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
      element_to_skip.click();
    }

    public void initSearchInput(){ //метод, инициализирующий процесс поиска: тапает по элементу и проверяет, что input действительно есть
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click 'Search Wikipedia' input", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){ //метод, ожидающий появления кнопки [X] в строке поиска.
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search [X] button!", 5);
    }

    public void waitForCancelButtonToDisappear(){ //метод, ожидающий исчезновения кнопки [X] в строке поиска.
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search [X] button is still presented on the page", 5);
    }

    public void clickCancelSearch(){ //Метод клика по кнопке [X] Cancel Search
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still presented", 5);
    }

    public void typeSearchLine(String search_line) { //метод, вводящий значение в строку
        this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){ //проверяем результат поиска по значению текста
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " +substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring, 7);
    }

    public void waitForSearchResultSecondArticle (String substring){ //проверяем результат поиска второй статьи по значению текста
        String search_result_xpath = getResultSearchElementSecondArticle(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " +substring);
    }

    public void clickByArticleWithSubstringSecondArticle(String substring) { // кликаем по второй статье
        String search_result_xpath = getResultSearchElementSecondArticle(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring, 7);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertSearchResult(String first_word_for_search){
        waitForElementPresent(
                SEARCH_RESULT_LOCATOR,
                "Cannot find anything by the request " + first_word_for_search,
                10
        );
        int amount_of_search_results = assertElementPresent(
                SEARCH_RESULT_LOCATOR
        );
        Assert.assertTrue(
                "We have found too few results of searching",
                amount_of_search_results > 0
        );
    }
}
