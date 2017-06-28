package REXSH.REXAUTO;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import REXSH.REXAUTO.APPunderTest.AppPage.AND.*;
import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Page.Element.operationItem;
import REXSH.REXAUTO.LocalException.REXException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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
/**
 * Unit test for simple App.
 */
public class WebMailApprove {
    private ChromeDriver driver = new ChromeDriver();;
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = format.format(date);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    ChromeOptions cOptions = new ChromeOptions();
    private String cPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + this.getClass().getSimpleName() + "_" + time.replaceAll(":","_");
    File pfile = new File(cPath);

    public class TestNGClass
    {
        private WebDriver driver;
        private String URL = "https://login.live.com/";

        @Parameters("browser")
        @BeforeTest
        public void launchapp(String browser)
        {

            if (browser.equalsIgnoreCase("firefox"))
            {
                System.out.println(" Executing on FireFox");
                driver = new FirefoxDriver();
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("chrome"))
            {
                System.out.println(" Executing on CHROME");
                System.out.println("Executing on IE");
                System.setProperty("webdriver.chrome.driver", "/Applications/Google\\ Chrome.app/Contents/MacOS/chromedriver");
                driver = new ChromeDriver();
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
            else
            {
                throw new IllegalArgumentException("The Browser Type is Undefined");
            }
        }


        @Test
        public void calculatepercent()
        {
          /*
            driver.findElement(By.xpath(".//*[@id='menu']/div[3]/a")).click();     	// Click on Math Calculators
            driver.findElement(By.xpath(".//*[@id='menu']/div[4]/div[3]/a")).click();     // Click on Percent Calculators
            driver.findElement(By.id("cpar1")).sendKeys("10"); 		// Enter value 10 in the first number of the percent Calculator
            driver.findElement(By.id("cpar2")).sendKeys("50");		// Enter value 50 in the second number of the percent Calculator
            driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr/td[2]/input")).click();	    // Click Calculate Button
            String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/span/font/b")).getText();		    // Get the Result Text based on its xpath
            System.out.println(" The Result is " + result);					//Print a Log In message to the screen

            if(result.equals("5"))
            {
                System.out.println(" The Result is Pass");
            }
            else
            {
                System.out.println(" The Result is Fail");
            }
            */
        }

        @AfterTest
        public void closeBrowser()
        {
            driver.close();
        }
    }
}
