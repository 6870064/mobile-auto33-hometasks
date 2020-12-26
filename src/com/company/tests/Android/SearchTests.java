package com.company.tests.Android;

import com.company.lib.CoreTestCase;
import com.company.lib.ui.SearchPageObject;
import com.company.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    String articles_list_title = "1-st articles list for reading";
    String articles_list_description = "1-st articles list for reading description";
    String first_word_for_search = "Java";
    String first_word_for_search_description = "Object-oriented programming language";
    String second_word_for_search = "Rammstein";
    String second_word_for_search_description = "German industrial metal band";
    String word_for_empty_search = "zedqazss pewwqsd frtd zzz";

    @Test
    public void testSearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.waitForSearchResult(first_word_for_search_description);
    }

    @Test
    public void  testCancelSearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We've found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(word_for_empty_search);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
