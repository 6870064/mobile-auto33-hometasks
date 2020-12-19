package com.company;

public class Layout {

    // @Test
    //    public void testAmountOfNotEmptySearch(){
    //
    //        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
    //        element_to_skip.click();
    //
    //        MainPageObject.waitForElementAndClick(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                "Cannot find 'Search Wikipedia' input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementAndSendKeys(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                word_for_assert_title_test,
    //                "Cannot find search input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementPresent(
    //                By.xpath(search_result_locator),
    //                "Cannot find anything by the request " + word_for_assert_title_test + "",
    //                15
    //        );
    //
    //        int amount_of_search_results = MainPageObject.getAmountOfElements(
    //                By.xpath(search_result_locator)
    //        );
    //
    //        Assert.assertTrue(
    //                "We found too few results!",
    //                amount_of_search_results > 0
    //        );
    //    }
    //
    //    @Test
    //    public void testAmountOfEmptySearch() {
    //
    //        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
    //        element_to_skip.click();
    //
    //        MainPageObject.waitForElementAndClick(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                "Cannot find 'Search Wikipedia' input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementAndSendKeys(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                word_for_empty_search,
    //                "Cannot find search input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementPresent(
    //                By.xpath(empty_search_result_locator),
    //                "Cannot find empty result text by the request " + word_for_empty_search,
    //                15
    //        );
    //
    //        MainPageObject.assertElementNotPresent(
    //                By.xpath(search_result_locator),
    //                "We've found some results by the request " + word_for_empty_search
    //        );
    //    }
    //
    //    @Test
    //    public void testChangesScreenOrientationOnSearchResults(){
    //
    //        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
    //        element_to_skip.click();
    //
    //        MainPageObject.waitForElementAndClick(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                "Cannot find 'Search Wikipedia' input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementAndSendKeys(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                first_word_for_search,
    //                "Cannot find search input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementAndClick(
    //                By.xpath("//*[contains(@text,'" + first_word_for_search_description + "')]"),
    //                "Cannot find " + first_word_for_search_description + " topic searching by " + first_word_for_search+ "",
    //                5
    //        );
    //
    //        String title_before_rotation = MainPageObject.waitForElementsAndGetAttribute(
    //                By.id("pcs-edit-section-title-description"),
    //                "text",
    //                "Cannot find artiles' title",
    //                15
    //        );
    //
    //        driver.rotate(ScreenOrientation.LANDSCAPE);
    //
    //        String title_after_rotation = MainPageObject.waitForElementsAndGetAttribute(
    //                By.id("pcs-edit-section-title-description"),
    //                "text",
    //                "Cannot find artile title",
    //                15
    //        );
    //
    //        Assert.assertEquals(
    //                "Article title has been changed after screen rotation",
    //                title_before_rotation,
    //                title_after_rotation
    //        );
    //
    //        driver.rotate(ScreenOrientation.PORTRAIT);
    //
    //        String title_after_second_rotation = MainPageObject.waitForElementsAndGetAttribute(
    //                By.id("pcs-edit-section-title-description"),
    //                "text",
    //                "Cannot find artile title",
    //                15
    //        );
    //
    //        Assert.assertEquals(
    //                "Article title has been changed after screen rotation",
    //                title_before_rotation,
    //                title_after_second_rotation
    //        );
    //    }
    //
    //    @Test
    //    public void testCheckSearchArticleInBackground() {
    //
    //        WebElement element_to_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
    //        element_to_skip.click();
    //
    //        MainPageObject.waitForElementAndClick(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                "Cannot find 'Search Wikipedia' input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementAndSendKeys(
    //                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    //                first_word_for_search,
    //                "Cannot find search input",
    //                5
    //        );
    //
    //        MainPageObject.waitForElementPresent(
    //                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='" + first_word_for_search_description + "']"),
    //                "Cannot find find 'Search Wikipedia' input",
    //                5
    //        );
    //
    //        driver.runAppInBackground(10);
    //
    //        MainPageObject.waitForElementPresent(
    //                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='" + first_word_for_search_description + "']"),
    //                "Cannot find the article after returning from background",
    //                5
    //        );
    //    }
}
