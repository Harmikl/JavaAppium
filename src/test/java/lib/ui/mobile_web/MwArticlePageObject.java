package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_1 = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch";//"css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON="css:#page-actions li#ca-watch.mw-ui-icon-mf-watched button";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        ARTICLE_OF_READING_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']";
        CANCEL_BUTTON_ON_SEARCH_PAGE = "id:Cancel";
    }
    public MwArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
