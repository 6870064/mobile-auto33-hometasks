package com.company.lib.ui.ios;

import com.company.lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        DELETE_FIRST_ARTICLE_TITLE = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
        SECOND_ARTICLE_TITLE =  "id:Rammstein";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
        SAVED_BUTTON = "id:Saved";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
