package com.company.tests.iOS;

import com.company.lib.IOSTestCase;
import com.company.lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends IOSTestCase {

    @Test
    public void testPassThroughWelcome() {

    WelcomePageObject WelcomePage = new WelcomePageObject(driver);
    WelcomePage.waitForLearnMoreLink();
    WelcomePage.clickNextButton();
    WelcomePage.waitForNewWayToExploreText();
    WelcomePage.clickNextButton();
    WelcomePage.waitForAddOrEditPreferredLangText();
    WelcomePage.clickNextButton();
    WelcomePage.waitForLearnMoreAboutDataCollectedLink();
    WelcomePage.clickGetStartedButton();
    }
}
