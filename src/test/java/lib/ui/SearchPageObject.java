package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject  extends MainPageObject{
     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT ,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION ,
            SEARCH_RESULT_BY_EXACT_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            TEST_ASSERT_TITLE_EX6;
    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /*template methods*/

    @Step("Getting result search element and getting substring")
    private static String getResultSearchElement (String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Getting result search element by exact title and description")
    private static String getResultByExactTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_EXACT_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    @Step("Getting result by title and description")
    public int  getResultByTitleAndDescription()
    {
        this.waitForElementPresent(SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION, "Cannot find title and description",10);
        return this.getAmountOfElement(SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION);
    }
    /*template methods*/

    @Step("Initializing the search field")
    public void initSearchInput () throws InterruptedException { //при заруске находит поиск, тапает по нужному элементу, и проверяет что инпут есть

        this.waitTillElementBeClickable(SEARCH_INIT_ELEMENT,"search is not clickable");
        //clickSearchButton();
        this.waitForElementAndClick (SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 15);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    @Step("Waiting for button to cancel search")
    public void waitForCancelButtonToAppear()
    {
        this.waitTillElementBeClickable(SEARCH_CANCEL_BUTTON,"cannot find search cancel button");
    }

    @Step("Waiting for cancel button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present", 5);
    }

    @Step("Click button to cancel search result")
    public void clickCancelSearch()
    {
        this.waitTillElementBeClickable(SEARCH_CANCEL_BUTTON,"Cancel button is not clickable");
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line, "Cannot fine and type in to search input", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"cannot find search result with substring " + substring);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementAndClick(search_result_xpath,"cannot find and click search result with substring " + substring, 10);
    }

    @Step("Waiting for search result with title and description")
    public void waitForElementByTitleAndDescription (String title, String description)
    {
        String title_of_article = getResultByExactTitleAndDescription(title,description);
        this.waitForElementPresent(title_of_article,"cannot find title and description"+title ,10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles ()
    {
        String search_line = "Linkin Park Diskography";


        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request " ,
                15
        );
        return this.getAmountOfElement(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element",15);

    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
    }
    public void clickSearchButton() throws InterruptedException {
        Thread.sleep(9000);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find search field", 10);
    }
}
