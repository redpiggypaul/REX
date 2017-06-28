package REXSH.REXAUTO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
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
public class WebSignupFreeMailBOX {
    private AndroidDriver driver;
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = format.format(date);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    private String cPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + this.getClass().getSimpleName() + "_" + time;
    File pfile = new File(cPath);
    private WebDriver SafariDriver;
   // private String URL = "https://www.mailinator.com/login.jsp";
    private String URL = "https://www.mailinator.com/signup.jsp";
    @DataProvider
    public static Object[][] userdata1() {
        return new Object[][]{
                {"rexshanghaitest001@mailinator.com", "5TGBNHY6&*"}, // last para can be regular / "Error;NO RESULT" "err" "part1;part2 split the except err info"

        };
    }

    @BeforeTest
    public void launchapp() throws IOException {
/*
        if (browser.equalsIgnoreCase("firefox"))
        {
            System.out.println(" Executing on FireFox");
            driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else */
        // ChromeOptions options = new ChromeOptions();
        // options.setBinary("/Applications/GoogleChrome.app/Contents/MacOS/chromedriver");

        // 设置 chrome 的路径
   /*     System.setProperty(
                "webdriver.chrome.driver",
                "/Applications/GoogleChrome.app/Contents/MacOS/Google\\ Chrome");
        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        @SuppressWarnings("deprecation")
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File("/Applications/GoogleChrome.app/Contents/MacOS/chromedriver")).usingAnyFreePort().build();
            //    .usingChromeDriverExecutable(
              //          new File(
               //                 "/Applications/GoogleChrome.app/Contents/MacOS/chromedriver"))
               // .usingAnyFreePort().build();
        service.start();
        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());

        // 让浏览器访问 Baidu
        driver.get(URL);
*/



        File app = new File("/Users/appledev131/Downloads", "app-debug.apk");
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));

        capabilities.setCapability("deviceName", "Android Emulator");

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        pfile.mkdir();

        SafariDriver = new SafariDriver();
    }

    public static String[] preapareActionParameter(String[] a, String[] b) {
        String[] target = new String[a.length + b.length];
        System.arraycopy(a, 0, target, 0, a.length);
        System.arraycopy(b, 0, target, a.length, b.length);
        for (int ind = 0; ind < target.length; ind++) {
            System.out.println("###" + target[ind]);
        }
        return target;
    }

    public static boolean assertTestResult(String testResult, String exceptedResult) throws REXException {
        boolean result = true;
        testResult = testResult.toLowerCase();
        String sampleResult = testResult;
        exceptedResult = filter4space(exceptedResult).toLowerCase();
        if (!exceptedResult.equalsIgnoreCase("ingnore")) {
            if (sampleResult.equals("teststart")) {
                result = false;
            } else if (!exceptedResult.equalsIgnoreCase("regular")) {
                String[] excResult = exceptedResult.split(";");
                for (int ind = 0; (ind < excResult.length) && result == true; ind++) {
                    sampleResult = testResult;
                    String[] singlePartResult = excResult[ind].split(" ");
                    for (int dind = 0; (dind < singlePartResult.length) && result == true; dind++) {
                        if (!sampleResult.contains(singlePartResult[dind])) {
                            result = false;
                            break;
                        } else {
                            System.out.println("start : " + sampleResult.indexOf(singlePartResult[dind]));
                            System.out.println("end : " + sampleResult.length());
                            sampleResult = sampleResult.substring(sampleResult.indexOf(singlePartResult[dind]), sampleResult.length() - 1);
                            System.out.println(sampleResult);
                        }
                    }
                }
            } else if (exceptedResult.equalsIgnoreCase("regular")) {
                if (testResult.contains("err")) {
                    result = false;
                } else {
                    System.out.println("");
                }
            } else {
                result = false;
                throw new REXException("test result mismatch with current judgement rule");
            }
            return result;
        } else {
            return true;
        }
    }


    public void recover() throws Exception {
        driver.closeApp();
        driver.quit();
        Thread.sleep(5000);
        File app = new File("/Users/appledev131/Downloads", "app-debug.apk");
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public static String filter4space(String s) {
        int i = s.length();// 字符串最后一个字符的位置
        int j = 0;// 字符串第一个字符
        int k = 0;// 中间变量
        char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
            ++j;// 确定字符串前面的空格数
        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
            --i;// 确定字符串后面的空格数
        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
    }


    @DataProvider
    public static Object[][] profileChooseGroup() {
        return new Object[][]{
                {"REXexceed01", "REXexceed01", "REXexceed01@mailinator.com::Pwcwelcome1::Pwcwelcome1", "ingnore", "REXexceed01@mailinator.com", "Pwcwelcome1"},
        };
    }

  //  @Test(dataProvider = "profileChooseGroup")
    public void signupNEWMailBox(String p1, String p2, String useful, String p4, String p5, String p6) throws InterruptedException {
        String [] extPara = useful.split("::");
        SafariDriver.get(URL);
        SafariDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SafariDriver.manage().window().maximize();
        Thread.sleep(6000);
        WebElement addr = SafariDriver.findElement(By.id("signupEmail"));
        addr.sendKeys(extPara[0]);
        Thread.sleep(2000);
        WebElement psw = SafariDriver.findElement(By.id("signupPassword"));
        psw.sendKeys(extPara[1]);
        WebElement conpwd = SafariDriver.findElement(By.id("signupReenter"));
        Thread.sleep(2000);
        conpwd.sendKeys(extPara[2]);
        WebElement signinBtn = SafariDriver.findElement(By.xpath("/html/body/section[1]/div/div[2]/div/div/div/div[2]/div/button"));
        signinBtn.click();
        Thread.sleep(6000);
        URL = "https://www.mailinator.com/login.jsp";

    }


   // @Test(dataProvider = "profileChooseGroup",dependsOnMethods = {"signupNEWMailBox"})
    @Test(dataProvider = "profileChooseGroup")
    public void demotest(String theUserPara, String thePSW, String paraGroup, String targetResult, String p1, String p2) throws Exception {
        date = new Date();
        time = format.format(date);
        String nameOFmethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        String[] extParaGroup = new String[]{theUserPara, thePSW, paraGroup};
        String preRoleName = null;
        String[] extPara = paraGroup.split("::");
        if (extPara.length != 0) {
            if (extPara[extPara.length - 1].length() > 10) {//如果长度大于10则截取
                preRoleName = new String(extPara[extPara.length - 1].substring(0, 9));  // for screen
                preRoleName = filter4space(preRoleName);
            }
        }
        String[] para4Aaction = preapareActionParameter(new String[]{theUserPara, thePSW}, extPara);  // for action
        String cMethodPath = this.cPath + File.separator + nameOFmethod + "_" + preRoleName + "_" + time; // for screen


        try {
            BaseAction theAction = new BaseAction(driver, null, 1, para4Aaction, cMethodPath, "");
            theAction.comOperationInAND(driver, new String[]{"regist"}, para4Aaction);
            Thread.sleep(6000);
            System.out.println("Here is the result for this case : " + theAction.getExeResult());

            if (assertTestResult(theAction.getExeResult().toString(), targetResult) == false)  // judge the result
            {
                assert false;
            }

        } catch (TimeoutException e) {
            date = new Date();
            time = format.format(date);
            e.printStackTrace();
            Assertion.verifyEquals(e.getClass(), TimeoutException.class, "Find other exception : ");
            ScreenShot(driver, nameOFmethod + time + "TimeoutException.png", cMethodPath);
            assert false;
            recover();
        } catch (REXException e) {
            date = new Date();
            time = format.format(date);
            e.printStackTrace();
            Assertion.verifyEquals(e.getClass(), REXException.class, "Find other exception : ");
            ScreenShot(driver, nameOFmethod + time + "REXException.png", cMethodPath);
            assert false;
            recover();
        } catch (XMLException e) {
            date = new Date();
            time = format.format(date);
            e.printStackTrace();
            Assertion.verifyEquals(e.getClass(), XMLException.class, "Find other exception : ");
            ScreenShot(driver, nameOFmethod + time + "XMLException.png", cMethodPath);
            assert false;
            recover();
        } catch (Exception e) {
            date = new Date();
            time = format.format(date);
            e.printStackTrace();
            ScreenShot(driver, nameOFmethod + time + "OtherException.png", cMethodPath);
            assert false;
            recover();
        } finally {
            driver.closeApp();
       //     Thread.sleep(1000);
     //       driver.launchApp();
            Thread.sleep(30000);
        }
    }




    @Test(dataProvider = "profileChooseGroup" ,dependsOnMethods = {"demotest"})
    public void acceptmail(String p1, String p2, String useful, String p4, String p5, String p6)throws InterruptedException {
     //   URL = "https://www.mailinator.com/login.jsp";
        SafariDriver.get(URL);
        SafariDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SafariDriver.manage().window().maximize();
        String [] extPara = useful.split("::");
        Thread.sleep(2000);
        WebElement addr = SafariDriver.findElement(By.id("loginEmail"));
        addr.sendKeys(extPara[0]);
        Thread.sleep(2000);
        WebElement psw = SafariDriver.findElement(By.id("loginPassword"));
        psw.sendKeys(extPara[1]);
        WebElement loginBtn = SafariDriver.findElement(By.xpath("/html/body/section[1]/div/div[2]/div/div/div/div[2]/div/button"));
        Thread.sleep(2000);
        loginBtn.click();
        Thread.sleep(30000);
        WebElement mailNUM = SafariDriver.findElement(By.id("public_count"));
        int Num4Mail = Integer.parseInt(mailNUM.getText());
        System.out.println("get the mail num : " + Num4Mail);
        Thread.sleep(2000);
        for (int ind = 1; ind <= Num4Mail; ind++) {
            WebElement mailTitle = SafariDriver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[" + ind + "]/div[2]/div[5]/div"));
            System.out.println("get the mail Title : " + mailTitle.getText());
            if (mailTitle.getText().contains("Welcome to the Talent Exchange!")) ;
            mailTitle.click();
        }
        Thread.sleep(2000);
        WebElement acceptBtn = SafariDriver.findElement(By.cssSelector("a"));
        if (acceptBtn.getText().contains("Validate email address")) {
            acceptBtn.click();
        }
        Thread.sleep(2000);

    }

    @Test(dependsOnMethods = {"acceptmail"}, dataProvider = "profileChooseGroup")
    public void deleteTheMail(String p1, String p2, String p3, String p4, String username, String password) throws InterruptedException {
        SafariDriver.get(URL);
        SafariDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SafariDriver.manage().window().maximize();
        Thread.sleep(30000);
        //   WebElement addr = driver.findElement(By.className("placeholder has-focus"));
        // WebElement addr = driver.findElement(By.id("u"));
        WebElement addr = SafariDriver.findElement(By.id("inboxfield"));
        addr.sendKeys(username);
        Thread.sleep(2000);
        addr.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
     //   WebElement psw = driver.findElement(By.id("loginPassword"));
     //   psw.sendKeys(password);
     //   WebElement loginBtn = driver.findElement(By.xpath("/html/body/section[1]/div/div[2]/div/div/div/div[2]/div/button"));
     //   Thread.sleep(2000);
     //   loginBtn.click();
     //   Thread.sleep(6000);
        WebElement mailNUM = SafariDriver.findElement(By.id("public_count"));
        int Num4Mail = Integer.parseInt(mailNUM.getText());
        System.out.println("get the mail num : " + Num4Mail);
        Thread.sleep(2000);
        for (int ind = 1; ind <= Num4Mail; ind++) {
                                                            //  /html/body/div/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[2]/div[5]/div
            WebElement mailTitle = SafariDriver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[" + ind + "]/div[2]/div[5]/div"));
            System.out.println("get the mail Title : " + mailTitle.getText());
            if (mailTitle.getText().contains("Welcome to the Talent Exchange!")) ;
            mailTitle.click();
        }
        Thread.sleep(2000);
        WebElement deleteBtn = SafariDriver.findElement(By.id("public_delete_button"));
        deleteBtn.click();
        Thread.sleep(2000);
    }


    @AfterTest
    public void closeBrowser() {
        SafariDriver.close();
    }
}


