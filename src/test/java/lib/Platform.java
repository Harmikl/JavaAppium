package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB="mobile_web"; 
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance()
    {
        if (instance==null)
        {
            instance=new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL,getAndroidDesiredCapabilities());
        }else if (this.isIOS()){
            return new IOSDriver(URL,getIOSDesiredCapabilities());
        }else {
            throw new Exception("Cannot detect type of driver. Platform value "+this.getPlatformVar());
        }
    }
    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
      //  capabilities.setCapability("udid", "1c88f784220d7ece");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities ()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE My");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }
    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return platform.equals(my_platform);
    }
    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
