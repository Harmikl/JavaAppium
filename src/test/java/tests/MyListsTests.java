package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
private static final String
        name_of_folder = "Learning programming",
        login="Harmikl57",
        password="23581321mhl";

    @Test
    public void testSaveFirstArticleToMyList()throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth= new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            //ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()){
            ArticlePageObject.cancelOnSearchField();
        }

        NavigationUI NavigationUI =  NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject =  MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveTwoArticleToMyListEx5() throws InterruptedException{
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else ArticlePageObject.addArticleToMySaved();

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.cancelOnSearchField();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Linkin Park Diskography");
        SearchPageObject.clickByArticleWithSubstring("Linkin Park discography");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
        } else{
            ArticlePageObject.waitForTitleElement1();
    }


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListSecondTime(name_of_folder);
        }else ArticlePageObject.addArticleToMySaved();

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()){
            ArticlePageObject.cancelOnSearchField();
        }

        NavigationUI NavigationUI =  NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject =  MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.articleIsNotDeleted();
  }
}
