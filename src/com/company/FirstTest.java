package com.company;

import com.company.lib.CoreTestCase;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FirstTest extends CoreTestCase {

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

    @Test
    public void testAmountOfNotEmptySearch(){

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                word_for_assert_title_test,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + word_for_assert_title_test + "",
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                word_for_empty_search,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath(empty_search_result_locator),
                "Cannot find empty result text by the request " + word_for_empty_search,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by the request " + word_for_empty_search
        );
    }

    @Test
    public void testChangesScreenOrientationOnSearchResults(){

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + first_word_for_search_description + "')]"),
                "Cannot find " + first_word_for_search_description + " topic searching by " + first_word_for_search+ "",
                5
        );

        String title_before_rotation = waitForElementsAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find artiles' title",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementsAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find artile title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementsAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find artile title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='" + first_word_for_search_description + "']"),
                "Cannot find find 'Search Wikipedia' input",
                5
        );

        driver.runAppInBackground(10);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='" + first_word_for_search_description + "']"),
                "Cannot find the article after returning from background",
                5
        );
    }

    @Test
    public void testCompareOneArticleTitle(){ //Ex.3
        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article title!",
                5
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testCompareArticleTitle(){ // Ex4*: Тест: проверка слов в поиске
        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        WebElement title_element = waitForElementPresent(
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

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + first_word_for_search_description + "')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article title!",
                5
        );

        waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark'][@text='Save']"),
                "Cannot find the button to open article options",
                5
        );

        waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']"),
                "Cannot find option to add article to the reading list",
                5
        );

        waitForElementAndClick(  //Нажатие на кнопку [GOT IT] для закрытия всплывающего поп апа "Add articles to the list...
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']"),
                "Cannot find option to add article to the reading list",
                5
        );

        waitForElementAndSendKeys( // Название 1-ой статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                articles_list_title,
                "Cannot find 'Name of this list' input field",
                5
        );

        waitForElementAndSendKeys(  // Описание 1-ой статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']"),
                articles_list_description,
                "Cannot find 'Description (optional)' input field",
                5
        );

        waitForElementAndClick( // Нажатие на кнопку [OK] на поп апе.
                By.xpath("//*[@text='OK']"),
                "Cannot tap [X] button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find [<-] button",
                5
        );

        driver.hideKeyboard();
        driver.navigate().back();

        waitForElementAndClick( // Открытие вкладки Explore.
                By.xpath("//android.widget.FrameLayout[@content-desc='Explore']"),
                "Cannot find navigation button to Explore",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                second_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + second_word_for_search_description + "')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + second_word_for_search + "')]"),
                "Cannot find article title!",
                5
        );

        waitForElementAndClick( //Нажатие на кнопку [Save] в левом нижнем углу экрана для сохранения статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark'][@text='Save']"),
                "Cannot find the button to open article options",
                5
        );

        waitForElementAndClick(  //Нажатие на кнопку [ADD TO LIST] на всплывающем поп апе для сохранения статьи
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']"),
                "Cannot find option to add article to the reading list",
                5
        );

        waitForElementAndClick( //Добавление второй статьи в уже существующий список
                By.xpath("//android.widget.TextView[@text='" + articles_list_title + "']"),
                "Cannot find option to add article to the reading list",
                5
        );

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

        swipeElementToTheLeft( // Удаление первой статьи
                By.xpath("//*[@text='" + first_word_for_search_description + "']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + first_word_for_search_description+ "']"),
                "Cannot delete saved article",
                5
        );
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

    @Test
    public void testAssertTitleTest(){  // Ex6: Тест: assert title

        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + first_word_for_search,
                10
        );
        int amount_of_search_results = assertElementPresent(
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

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                first_word_for_search,
                "Cannot find search input",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        waitForElementAndClick(  //место падения первого теста.
                By.xpath("//*[contains(@text,'" + first_word_for_search_description + "')]"),
                "Cannot find " + first_word_for_search_description + " topic searching by " + first_word_for_search+ "",
                5
        );
    }

    @Test
    public void testChangesScreenOrientationSecond() { // Ex7*: Поворот экрана Тест. Второй тест, идущий в Portrait orientation вслед за "упавшим" первым
        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        element_to_skip.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                second_word_for_search,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + second_word_for_search_description + "')]"),
                "Cannot find " + second_word_for_search_description + " topic searching by " + second_word_for_search+ "",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSections) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSections);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by,error_message,5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUP(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).
                waitAction(timeOfSwipe).
                moveTo(x, end_y).
                release().
                perform();
    }

    protected void swipeUpQuick() {
        swipeUP(2000);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;

        }
    }

    protected void swipeElementToTheLeft(By by, String error_message) { //удаление элемента свайпом справа налево
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.
                press(right_x, middle_y).
                waitAction(350).
                moveTo(left_x,middle_y).
                release().perform();
    }

    private int getAmountOfElements(By by) { //Метод, определяющий кол-во элементов, которые мы нашли.
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An Element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private int assertElementPresent(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementsAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
