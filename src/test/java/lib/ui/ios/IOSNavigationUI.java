package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        CLOSE_SYNC_YOUR_SAVED_ARTICLES = "id:Close";
    }
    public IOSNavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }
}
