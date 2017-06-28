package REXSH.REXAUTO;

import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.Lassert.Assertion;
import REXSH.REXAUTO.LocalException.REXException;
//import com.sun.deploy.model.ResourceProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import REXSH.REXAUTO.APPunderTest.AppPage.AND.*;
import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Page.Element.operationItem;
import REXSH.REXAUTO.LocalException.REXException;
import REXSH.REXAUTO.LocalException.XMLException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import io.appium.java_client.android.*;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import REXSH.REXAUTO.TC.TestCaseTemplate;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;
//import static com.sun.tools.doclint.Entity.para;

import REXSH.REXAUTO.Lassert.Assertion;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Unit test for simple App.
 */
public class AppTestChrome {
    private WebDriver driver;
    private String URL = "https://www.mailinator.com/signup.jsp";

    @BeforeTest
    public void launchapp() throws IOException {
        driver = new SafariDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }


    @DataProvider
    public static Object[][] profileChooseGroup() {
        return new Object[][]{
                {"samfirst", "samlast", "rexshanghaitest002@mailinator.com::Pwcwelcome1::Pwcwelcome1", "regular", "", ""}, // last para can be regular / "Error;NO RESULT" "err" "part1;part2 split the except err info"

        };
    }

    @Test(dataProvider = "profileChooseGroup")
    public void signupNEWMailBox(String p1, String p2, String useful, String p4, String p5, String p6) throws InterruptedException {
        String [] extPara = useful.split("::");
        Thread.sleep(2000);
        WebElement addr = driver.findElement(By.id("signupEmail"));
        addr.sendKeys(extPara[0]);
        Thread.sleep(2000);
        WebElement psw = driver.findElement(By.id("signupPassword"));
        psw.sendKeys(extPara[1]);
        WebElement conpwd = driver.findElement(By.id("signupReenter"));
        Thread.sleep(2000);
        conpwd.sendKeys(extPara[2]);
        WebElement signinBtn = driver.findElement(By.xpath("/html/body/section[1]/div/div[2]/div/div/div/div[2]/div/button"));
        signinBtn.click();
        Thread.sleep(6000);

    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}


