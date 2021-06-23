package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearchEx3() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found few results",
                amount_of_search_result > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "cxczxx";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
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

        assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );
    }
        @Test
    public void testFindMoreThan3TitleAndDescription ()throws InterruptedException
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            String search_line ="Java";
            SearchPageObject.typeSearchLine(search_line);
            int amount_of_titles_and_descriptions = SearchPageObject.getResultByTitleAndDescription();
            assertTrue(
                    "no one title and description by  request "+search_line,
                    amount_of_titles_and_descriptions>=3
            );
        }
    @Test
    public void testFindMTitleAndDescriptionByJavaRequest ()throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java","Object-oriented programming language");
//        double ширина=3.5;
//        double длина = 6;
//        double ширина_доски =0.15;
//        double толщина_доски =0.04;
//        double длина_доски =4;
//        double кубатура_для_пола =ширина*длина*толщина_доски;
//        double количество_досок=длина/ширина_доски;
//        double кубатура_по_количеству_досок = количество_досок*длина_доски*ширина_доски*толщина_доски;
//        double длина_бруса =4;
//        double ширина_бруса =0.10;
//        double толщина_бруса = 0.05;
//        double количество_брусов =26;
//        double кубатура_брусов_5_на_10 =количество_брусов*длина_бруса*ширина_бруса*толщина_бруса;
//        System.out.println("кубатура_для_пола "+кубатура_для_пола+ " м3");
//        System.out.println("кубатура_по_количеству_досок(проверка) "+кубатура_по_количеству_досок+ " м3");
//        System.out.println("количество досок "+количество_досок);
//        System.out.println("кубатура_брусов_5_на_10 "+кубатура_брусов_5_на_10+" м3");
    }
    }

