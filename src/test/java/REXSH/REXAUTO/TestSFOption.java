package REXSH.REXAUTO;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.uitest.Utils.Helper;

/**
 * @author appledev071
 */
public class TestSFOption {
    WebDriver driver;
    long stepSleepTime = 3000;
    long pageLoadTime = 5000;
    String deviceName;

    @BeforeClass
 //   @Parameters({"28dd158bbee699438c41bc45144cd79e3daa40c1", "iPad.061"})
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // CLY's iPhone
        this.deviceName = deviceName;
        capabilities.setCapability("deviceName", "iPad.061");
        capabilities.setCapability("platformName", "iOS");

        // 9e9687bcf119b3e759e4439383e886cbd2be10d9
        // 60eef3f286cf3962ffb68e661cfbc21ebe67bf10
        // 7f236b90eb80091de9f9681883f197fb5cfbddbc
        // com.pwcsdc.lilly.safari
        capabilities.setCapability("udid", "28dd158bbee699438c41bc45144cd79e3daa40c1");
        // capabilities.setCapability("udid", "197D9E87-AF14-419C-B82D-CD3F3FCE5448");
        capabilities.setCapability("BundleID", "com.pwc.REX");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("browserName", "safari");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(2000);

        driver.get("http://login.salesforce.com");
        Thread.sleep(2000);
    }

    @Test
    public void operation() {
        try {
            // ************ login page *************
            WebElement usrname = driver.findElement(By.xpath("//*[@id='username']"));
            WebElement passwd = driver.findElement(By.xpath("//*[@id='password']"));
            WebElement submitBtn = driver.findElement(By.xpath("//*[@id='Login']"));
            usrname.clear();
            Thread.sleep(2000);
            // lanny.zhu@pwcsdctest.com
            usrname.sendKeys("ningxiang.yuan@pwcsdctest.com");
            Thread.sleep(2000);
            // ABCD1234
            passwd.clear();
            passwd.sendKeys("Pwcwelcome1");
            submitBtn.click();
            Thread.sleep(2000);

            // *********** navigate to create case ***************
            clickSideBar();
            Thread.sleep(2000);

            WebElement caseItem = driver
                    .findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div/div/ul[3]/li[2]/a/div/div[2]/div"));
            caseItem.click();
            Thread.sleep(2000);

            // ************ create case *****************
            // for ipad
            WebElement crtCaseBtn;
            if (deviceName.contains("ipad")) {
                crtCaseBtn = driver.findElement(By.xpath("//*[@id='actionBarItem_NewCase']/button/div/img"));
            } else {
                crtCaseBtn = driver.findElement(By.xpath(
                        "html/body/div[5]/div[1]/section/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/header/div[2]/ul/li/button"));
            }
            crtCaseBtn.click();
            Thread.sleep(2000);

            WebElement contactNameField = driver.findElement(By.xpath(
                    "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[3]/div/div/div/input"));
            contactNameField.click();
            Thread.sleep(2000);
            WebElement contactNameItem = driver
                    .findElement(By.xpath("/html/body/div[5]/div[2]/div[2]/div/div[2]/div/div[2]/ul/li/a"));
            contactNameItem.click();
            Thread.sleep(2000);

            WebElement accountNameField = driver.findElement(By.xpath(
                    "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[4]/div/div/div/input"));
            accountNameField.click();
            Thread.sleep(2000);
            WebElement accountNameItem = driver
                    .findElement(By.xpath("/html/body/div[5]/div[2]/div[2]/div/div[2]/div/div[2]/ul/li[1]/a"));
            accountNameItem.click();
            Thread.sleep(2000);
            //
            // for ipad://*[@id="33:1262;a"]
            // /html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[7]/div/div/select
            WebElement status;
            WebElement caseOrigin;
            if (deviceName.contains("ipad")) {
                status = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[1]/div/div/select"));
                Select statusValue = new Select(status);
                statusValue.selectByValue("New");

                caseOrigin = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[3]/div[2]/div/select"));
                Select caseOriginValue = new Select(caseOrigin);
                caseOriginValue.selectByValue("Phone");
            } else {
                status = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[7]/div/div/select"));
                Select statusValue = new Select(status);
                statusValue.selectByValue("New");

                caseOrigin = driver.findElement(By.xpath(
                        "html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[9]/div/div/select"));
                Select caseOriginValue = new Select(caseOrigin);
                caseOriginValue.selectByValue("Phone");
            }
            clickSaveButton();
            Thread.sleep(2000);

            // ************ navigate to main page **************
            clickBackArrow();
            Thread.sleep(2000);

            // ************ update case **************

            WebElement firstCaseName;
            if (deviceName.contains("ipad")) {
                firstCaseName = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[1]/section/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a"));
            } else {
                WebElement myCaseLink = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[1]/section/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/ol/li/a/div"));
                myCaseLink.click();
                Thread.sleep(2000);

                firstCaseName = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[1]/section/div[1]/div[1]/div[2]/div/div/div[2]/div[3]/ul/li[1]/div[1]/a/h3/span"));
            }

            firstCaseName.click();
            Thread.sleep(2000);

            WebElement editBtn = driver.findElement(By.xpath("//*[@id='actionBarItem_Edit']/button/div/img"));
            editBtn.click();
            Thread.sleep(2000);


            WebElement caseOriginEdit;
            if (deviceName.contains("ipad")) {

                caseOriginEdit = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[3]/div[2]/div/select"));
                Select caseOriginEditValue = new Select(caseOriginEdit);
                caseOriginEditValue.selectByValue("Email");
            } else {

                caseOriginEdit = driver.findElement(By.xpath(
                        "html/body/div[5]/div[2]/div/div/div[2]/form/section/div/div/section[1]/div[9]/div/div/select"));
                Select caseOriginEditValue = new Select(caseOriginEdit);
                caseOriginEditValue.selectByValue("Email");
            }

            Thread.sleep(2000);

            clickSaveButton();
            Thread.sleep(2000);

            // ************ navigate to main page **************
            clickBackArrow();
            Thread.sleep(2000);

            // ************* delete case ***************
            WebElement secondCaseName;
            if (deviceName.contains("ipad")) {
                secondCaseName = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[1]/section/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[1]/a"));
            } else {
                secondCaseName = driver.findElement(By.xpath(
                        "/html/body/div[5]/div[1]/section/div[1]/div[1]/div[2]/div/div/div[2]/div[3]/ul/li[1]/div[1]/a/h3/span"));
            }
            secondCaseName.click();
            Thread.sleep(2000);

            WebElement delBtn = driver.findElement(By.xpath("//*[@id='actionBarItem_Delete']/button"));
            delBtn.click();
            Thread.sleep(2000);
            WebElement delConirmBtn = driver
                    .findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[2]/div/button[1]"));
            delConirmBtn.click();
            Thread.sleep(2000);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    public void clearAndTypeString(WebElement e, String text) {
        e.clear();
        e.sendKeys(text);
    }

    public void clickSaveButton() {
        WebElement saveBtn = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div[1]/button[1]"));
        saveBtn.click();
    }

    public void clickBackArrow() {
        WebElement backIcon = driver.findElement(By.xpath("//*[@id='oneHeader']/div/div[1]/a"));
        backIcon.click();
    }

    public void clickSideBar() {
        WebElement sideBarIcon = driver.findElement(By.xpath("//*[@id='oneHeader']/div/div[1]/div/a"));
        sideBarIcon.click();
    }

    public void scrollTobottom() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollTo(0,document.body.scrollTop=10000)");
    }
}
