package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
     protected static  String
     MY_LIST_LINK ,
     CLOSE_SYNC_YOUR_SAVED_ARTICLES,
    OPEN_NAVIGATION;

    public NavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Wait and click navigation button")
    public void openNavigation()
    {
        if (Platform.getInstance().isMw()){
            this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click open navigation button",5);
        }else {
            System.out.println("Method OpenNavigation() do nothing for Platform  "+Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Wait and click My lists")
    public void clickMyLists() throws InterruptedException {
        Thread.sleep(1000);
        screenshot(this.takeScreenshot("navigation"));

        if (Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find 'My lists' icon",
                5);
        }else if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find 'My lists' icon",
                    5);
            this.waitForElementAndClick(
                    CLOSE_SYNC_YOUR_SAVED_ARTICLES,
                    "Cannot find close icon on sync your saved articles",
                    5
            );
        }else {
            this.tryClickElementWithFewAttempts(

                    MY_LIST_LINK,
                    "Cannot find 'My lists' icon",
                    5
            );
        }
    }
}
