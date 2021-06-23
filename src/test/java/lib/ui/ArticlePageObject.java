package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            TITLE,
            TITLE_1,
    FOOTER_ELEMENT ,
    OPTIONS_BUTTON ,
    OPTIONS_ADD_TO_MY_LIST_BUTTON ,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT ,
    MY_LIST_OK_BUTTON ,
    CLOSE_ARTICLE_BUTTON ,
    ARTICLE_OF_READING_LIST ,
    CANCEL_BUTTON_ON_SEARCH_PAGE;
    public ArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
            return this.waitForElementPresent(TITLE, "cannot find article title on page", 15);
    }

    @Step("Waiting for title on the article page with title 1")
    public WebElement waitForTitleElement1()
    {
        return this.waitForElementPresent(TITLE_1,"cannot find article 1 title on page",15);
    }

    @Step("Get article title")
    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        }else {
            return title_element.getText();
        }
    }

    @Step("Get article title1")
    public String getArticleTitle1 ()
    {
        WebElement title_element = waitForTitleElement1();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }
    }

    @Step("Swipe to footer on article page")
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "cannot find the end of article",
                    40);
        }else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article in ios",
                    40);
        }else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

    @Step("Adding the article to my list")
    public void addArticleToMyList (String name_of_folder)
    {
        this.waitTillElementBeClickable(
                OPTIONS_BUTTON,
                "Element more options is not visible"
        );
        this.waitForElementAndClick(
                OPTIONS_BUTTON,//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        this.waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list'",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it'",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input field to set name of article",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find put 'Learning programming' in input field",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button ",
                5
        );
    }

    @Step("Adding article to my list second time")
    public void addArticleToMyListSecondTime (String name_of_folder)
    {
        this.waitTillElementBeClickable(
                OPTIONS_BUTTON,
                "Element more options is not visible"
        );
        this.waitForElementAndClick(
                OPTIONS_BUTTON,//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        this.waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list'",
                10
        );
        this.waitForElementAndClick(
                ARTICLE_OF_READING_LIST,
                "Cannot find " +name_of_folder,
                5
        );

    }

    @Step("Adding the article to my saved articles")
    public void addArticleToMySaved() throws InterruptedException {
        if (Platform.getInstance().isMw()){
            Thread.sleep(2000);

            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10);

    }

    @Step("Removing the article from saved if it has been saved")
    public void removeArticleFromSavedIfItAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1);
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find to add an article to saved list after removing it from this list before"
            );
        }
    }

    @Step("Closing the article")
    public void closeArticle()
    {
        if (Platform.getInstance().isIOS()||Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot tap 'X' icon",
                5);
        }else {
            System.out.println("Method  closeArticle() do nothing for platform "+Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Tap cancel on search field")
    public void cancelOnSearchField()
    {
        this.waitForElementAndClick(
                CANCEL_BUTTON_ON_SEARCH_PAGE,
                "Cannot find cancel button on serch page",
                10);
    }
}
