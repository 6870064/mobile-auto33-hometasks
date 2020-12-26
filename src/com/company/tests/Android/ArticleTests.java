package com.company.tests.Android;

import com.company.lib.CoreTestCase;
import com.company.lib.ui.SearchPageObject;
import com.company.lib.ui.ArticlePageObject;
import com.company.lib.ui.factories.ArticlePageObjectFactory;
import com.company.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    String articles_list_title = "1-st articles list for reading";
    String articles_list_description = "1-st articles list for reading description";
    String first_word_for_search = "Java";
    String first_word_for_search_description = "Object-oriented programming language";
    String second_word_for_search = "Rammstein";
    String second_word_for_search_description = "German industrial metal band";
    String word_for_empty_search = "zedqazss pewwqsd frtd zzz";

    @Test
    public void testCompareArticle(){
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
    public void testSwipeArticle(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.typeSkipElement();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_word_for_search);
        SearchPageObject.clickByArticleWithSubstring(first_word_for_search_description);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
}
