package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
@Epic("Tests for search")

public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Wait for search result by substring")
    @Description("We open search field and make sure the substring after entering Java is expected")
    @Step("Starting test testSearch")
    @Severity(value=SeverityLevel.BLOCKER)

    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Wait for search cancel button is disappear")
    @Description("We open search field enter Java and tap cancel")
    @Step("Starting test testCancelSearchEx3")
    @Severity(value=SeverityLevel.MINOR)
    public void testCancelSearchEx3() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Not empty search line after entering Linkin Park Diskography")
    @Description("We open search field enter Linkin Park Diskography and make sure search results is not empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value=SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch() throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found few results",
                amount_of_search_result > 0
        );
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Empty search line after cxczxx")
    @Description("We open search field enter cxczxx and make sure search results is  empty")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value=SeverityLevel.CRITICAL)
    public void testAmountOfEmptySearch() throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "cxczxx";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Making sure '{search_line}' word in search results")
    @Description("We open search field enter '{search_line}' and make sure search results is  empty")
    @Step("Starting test testCheckJavaWordInSearchResults")
    @Severity(value=SeverityLevel.CRITICAL)
    public void testCheckJavaWordInSearchResults() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "JAVA";
        SearchPageObject.typeSearchLine(search_line);
        String id_for_item_container = "id:org.wikipedia:id/page_list_item_container";
        SearchPageObject.waitForElementPresent(id_for_item_container, " Cannot find item containers", 5);
        List<WebElement> listOfElementsWithItemContainers = driver.findElements(By.id("org.wikipedia:id/page_list_item_container")); //находим количество контейнеров

        String xpath_for_java_contains = "xpath://*[contains(@text,'Java')]";
        SearchPageObject.waitForElementPresent(xpath_for_java_contains, "Cannot find java in containers", 10);
        List<WebElement> listOfElementsWithJava = driver.findElements(By.xpath("//*[contains(@text,'Java')]")); //находим количество xpath с текстом 'Java'

        Assert.assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );
    }
        @Test
        @Features(value = {@Feature(value="Search")})
        @DisplayName("Making sure 3 and more titles with Java word in search results")
        @Description("We open search field enter Java and make sure search results is  more than 3")
        @Step("Starting test testFindMoreThan3TitleAndDescription")
    public void testFindMoreThan3TitleAndDescription ()throws InterruptedException
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            String search_line ="Java";
            SearchPageObject.typeSearchLine(search_line);
            int amount_of_titles_and_descriptions = SearchPageObject.getResultByTitleAndDescription();
            Assert.assertTrue(
                    "no one title and description by  request "+search_line,
                    amount_of_titles_and_descriptions>=3
            );
        }
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Finding title and description by Java word in search ")
    @Description("We open search field enter Java and find article with title and substring")
    @Step("Starting test testFindMTitleAndDescriptionByJavaRequest")
    public void testFindMTitleAndDescriptionByJavaRequest ()throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java","Object-oriented programming language");
    }
    }

