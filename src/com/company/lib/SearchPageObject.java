package com.company.lib;

import com.company.lib.ui.MainPageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver) { //берем драйвер из MainPageObject
        super(driver);
    }

    private static String getResultSearchElement (String substring){  //Template method
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void typeSkipElement() { //кликаем на кнопкку SKIP
      WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
      element_to_skip.click();
    }

    public void initSearchInput(){ //метод, инициализирующий процесс поиска: тапает по элементу и проверяет, что input действительно есть
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click 'Search Wikipedia' input", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line) { //метод, вводящий значение в строку
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INIT_ELEMENT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){ //проверяем результат поиска по значению текста
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " +substring);
    }




}
