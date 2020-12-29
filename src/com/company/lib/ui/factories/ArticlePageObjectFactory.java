package com.company.lib.ui.factories;

import com.company.lib.Platform;
import com.company.lib.ui.ArticlePageObject;
import com.company.lib.ui.android.AndroidArticlePageObject;
import com.company.lib.ui.ios.iOSArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
