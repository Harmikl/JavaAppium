package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase  {//
    protected RemoteWebDriver driver;
    protected Platform  Platform;

@Before
@Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }
@After
@Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }
    @Step("Rotate screen to portrait mode")
    public void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Rotate screen to landscape mode")
    public void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
        driver.rotate(ScreenOrientation.LANDSCAPE);
        }else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Send app to background (this method does nothing for mobile web)")
    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
        driver.runAppInBackground(Duration.ofSeconds(seconds)); //отправляем аппку в бэк, через кол секунд в скобках аппка достанестя из бэка
        }else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Open wiki page for mobile web(this method does nothing for android and ios)")
    protected void openWikiWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMw()){
            driver.get("https://en.m.wikipedia.org");
        }else {
            System.out.println("Method openWikiWebPageForMobileWeb do nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Skip welcome page for ios")
    private void skipWelcomePageForIOSApp()
    {
        if (lib.Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }

    }

    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allure.results.directory");//получаем путь до allure
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path+"/environment.properties");// пишем в файл environment.properties
            props.setProperty("Environment",Platform.getInstance().getPlatformVar());//получаем значение переменной окружения
            props.store(fos,"See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

}
