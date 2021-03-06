package com.company.tests.Android;

import com.company.lib.CoreTestCase;
import com.company.lib.Platform;
import com.company.lib.ui.ArticlePageObject;
import com.company.lib.ui.MyListsPageObject;
import com.company.lib.ui.NavigationUI;
import com.company.lib.ui.SearchPageObject;
import com.company.lib.ui.factories.ArticlePageObjectFactory;
import com.company.lib.ui.factories.MyListsPageObjectFactory;
import com.company.lib.ui.factories.NavigationUIFactory;
import com.company.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class HomeTaskTests extends CoreTestCase {

    String articles_list_title = "1-st articles list for reading";
    String articles_list_description = "1-st articles list for reading description";
    String first_word_for_search = "Java";
    String first_word_for_search_description = "Object-oriented programming language";
    String second_word_for_search = "Rammstein";
    String second_word_for_search_description = "German industrial metal band";
    String word_for_empty_search = "zedqazss pewwqsd frtd zzz";

    @Test
    public void testCompareOneArticleTitle(){ //Ex.3
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSaveArticlesToMyList(){ //Ex5.Тест: сохранение двух статей

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(articles_list_title, articles_list_description);
        } else {
            ArticlePageObject.addArticleToSaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.backNavigation();

        SearchPageObject.initSearchInput();
        SearchPageObject.SearchFieldClear();
        SearchPageObject.typeSearchLine(second_word_for_search);
        SearchPageObject.clickByArticleWithSubstringSecondArticle(second_word_for_search_description);
        ArticlePageObject.waitForTitleElementSecondArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList(articles_list_title, articles_list_description);
        } else {
            ArticlePageObject.addSecondArticleToSaved();
            NavigationUI.backNavigation();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.myListOpening(articles_list_title);
        } else {
            MyListsPageObject.mySavedArticlesFolderOpening();
        }

        MyListsPageObject.swipeArticleToDelete(first_word_for_search_description);
        MyListsPageObject.articleAvailabilityCheck(second_word_for_search);
    }

    @Test
    public void testAssertTitleTest(){  // Ex6: Тест: assert title

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.assertSearchResult(first_word_for_search);
    }

    @Test
    public void testChangesScreenOrientationFirst(){ // Ex7*: Поворот экрана Тест. Первый тест, который падает после смены ориентации

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);

        this.rotateScreenLandscape();
        SearchPageObject.clickByArticleWithSubstring(word_for_empty_search); //место падения первого теста
    }

    @Test
    public void testChangesScreenOrientationSecond() { // Ex7*: Поворот экрана Тест. Второй тест, идущий в Portrait orientation вслед за "упавшим" первым

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(second_word_for_search);
        this.rotateScreenLandscape();
    }
}
