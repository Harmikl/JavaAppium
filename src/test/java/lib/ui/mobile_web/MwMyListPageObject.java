package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwMyListPageObject extends MyListsPageObject {
    static
    {
        FOLDER_BY_NAME_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        ARTICLE_IS_NOT_DELETED = "id:org.wikipedia:id/page_list_item_title";
        REMOVE_FROM_SAVE_BUTTON="xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
    }
    public MwMyListPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
