package com.company.lib.ui.factories;

import com.company.lib.Platform;
import com.company.lib.ui.ArticlePageObject;
import com.company.lib.ui.android.AndroidArticlePageObject;
import com.company.lib.ui.ios.iOSArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
