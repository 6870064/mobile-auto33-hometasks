package com.company;
import com.company.lib.CoreTestCase;
import com.company.lib.SearchPageObject;
import com.company.lib.ui.ArticlePageObject;
import com.company.lib.ui.MainPageObject;
import com.company.lib.ui.NavigationUI;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    String search_var = "Search Wikipedia";
    String error_var = "Cannot find 'Search Wikipedia' input";
    String articles_list_title = "1-st articles list for reading";
    String articles_list_description = "1-st articles list for reading description";
    String first_word_for_search = "Java";
    String first_word_for_search_description = "Object-oriented programming language";
    String second_word_for_search = "Rammstein";
    String second_word_for_search_description = "German industrial metal band";
    String word_for_assert_title_test = "Rammstein discography";
    String search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    String word_for_empty_search = "zedqazss pewwqsd frtd zzz";
    String empty_search_result_locator = "//*[@text='No results']";

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.waitForSearchResult(first_word_for_search_description);

    }

    @Test
    public void  testCancelSearch(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSwipeArticle(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We've found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(word_for_empty_search);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCompareOneArticleTitle(){ //Ex.3
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

       ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
       String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testCompareArticleTitle(){ // Ex4*: Тест: проверка слов в поиске

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'" +first_word_for_search+ "')]"),
                "Cannot find article title!",
                5
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "" +first_word_for_search+ "",
                article_title
        );
    }

    @Test
    public void testSaveArticlesToMyList(){ //Ex5.Тест: сохранение двух статей

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addFirstArticleToMyList(articles_list_title, articles_list_description);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.backNavigation();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(second_word_for_search);
        SearchPageObject.clickByArticleWithSubstringSecondArticle(second_word_for_search_description);
        ArticlePageObject.waitForTitleElementSecondArticle();
        ArticlePageObject.addSecondArticleToMyList(articles_list_title, articles_list_description);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + second_word_for_search_description + "')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

//        MainPageObject.waitForElementPresent(
//                By.xpath("//*[contains(@text,'" + second_word_for_search + "')]"),
//                "Cannot find article title!",
//                5
//        );
//
//        MainPageObject.waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
//                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark'][@text='Save']"),
//                "Cannot find the button to open article options",
//                5
//        );
//
//        MainPageObject.waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
//                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']"),
//                "Cannot find option to add article to the reading list",
//                5
//        );
//
//        MainPageObject.waitForElementAndClick( //Добавление второй статьи в уже существующий список
//                By.xpath("//android.widget.TextView[@text='" + articles_list_title + "']"),
//                "Cannot find option to add article to the reading list",
//                5
//        );

        MainPageObject.waitForElementAndClick( // Нажатие на [VIEW LIST] и открытие переченя списков статей
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot open My lists screen",
                5
        );

        MainPageObject.waitForElementAndClick( //Открытие списка статей с сохраненными двумя статьями
                By.xpath("//android.widget.TextView[@text='" + articles_list_title + "']"),
                "Cannot open articles list titled '" + articles_list_title + "'",
                5
        );

        MainPageObject.swipeElementToTheLeft( // Удаление первой статьи
                By.xpath("//*[@text='" + first_word_for_search_description + "']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + first_word_for_search_description+ "']"),
                "Cannot delete saved article",
                5
        );
        MainPageObject.waitForElementPresent( // Убеждаемся, что вторая статься осталась
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + second_word_for_search + "']"),
                "Cannot find saved article titled " + second_word_for_search + "",
                10
        );

        MainPageObject.waitForElementAndClick(  //Открытие статьи. по сути и есть верификация по тайтлу.
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + second_word_for_search + "']"),
                "Cannot open the article titled " + second_word_for_search + "",
                10
        );
    }

    @Test
    public void testAssertTitleTest(){  // Ex6: Тест: assert title

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + first_word_for_search,
                10
        );
        int amount_of_search_results = MainPageObject.assertElementPresent(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We have found too few results of searching",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testChangesScreenOrientationFirst(){ // Ex7*: Поворот экрана Тест. Первый тест, который падает после смены ориентации

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        MainPageObject.waitForElementAndClick(  //место падения первого теста.
                By.xpath("//*[contains(@text,'" + first_word_for_search_description + "')]"),
                "Cannot find " + first_word_for_search_description + " topic searching by " + first_word_for_search+ "",
                5
        );
    }

    @Test
    public void testChangesScreenOrientationSecond() { // Ex7*: Поворот экрана Тест. Второй тест, идущий в Portrait orientation вслед за "упавшим" первым
        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                second_word_for_search,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + second_word_for_search_description + "')]"),
                "Cannot find " + second_word_for_search_description + " topic searching by " + second_word_for_search+ "",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

}
