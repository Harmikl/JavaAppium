package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        TITLE_1 = "id:Linkin Park discography";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        ARTICLE_OF_READING_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']";
        CANCEL_BUTTON_ON_SEARCH_PAGE = "id:Cancel";
    }
    public IOSArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
