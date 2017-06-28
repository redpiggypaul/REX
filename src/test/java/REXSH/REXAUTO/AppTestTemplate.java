package REXSH.REXAUTO;

import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.readProperity;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;

//import static com.sun.tools.doclint.Entity.para;


/**
 * Unit test for simple App.
 */
public class AppTestTemplate {
    private AppiumDriver driver;

    String autoOSType = readProperity.getProValue("autoRunningOSType");

    String mobileOS = readProperity.getProValue("targetMobileOS");
    String deviceORsimulator = readProperity.getProValue("deviceORsimulator");
    String andOSVersion = readProperity.getProValue("androidOSVersion");
    String andPlatVersion = readProperity.getProValue("androidPlatformVersion");

    String iosDeviceType = readProperity.getProValue("IOSdeviceType");
    String iosDeviceName = readProperity.getProValue("IOSdeviceName");
    String iosPlaformVersion = readProperity.getProValue("IOSplatformVersion");
    String iosPlatformName = readProperity.getProValue("IOSplatformName");
    String iosBund = readProperity.getProValue("IOSbundleID");
    String iosUDID = readProperity.getProValue("IOSudid");
    String iosShowMode = readProperity.getProValue("IOSorientationMode");

    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = format.format(date);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    StringBuilder cPath = new StringBuilder().append(System.getProperty("user.dir")).append(File.separator).append("logs").append(File.separator).append(mobileOS).append(File.separator).append(this.getClass().getSimpleName()).append("_").append(time.replaceAll(":", "_"));
    File pfile = new File(cPath.toString());
    Workbook XLSXwb = null;
    Sheet XLSXsheet = null;
    String testMethod4XLSX = null;
    Row headerRow = null;
    Row row4TCsingle;
    Cell cell4TCsingle;
    ArrayList<String> list4result = new ArrayList<String>();
    FileOutputStream theFileOutStream = null;
    int globalRowId = 0;
    int rownum = 1;
    public String port = "4723";


    public AppTestTemplate() throws FileNotFoundException {
    }


    public static String filter4space(String s) {
        int i = s.length();//
        int j = 0;//
        int k = 0;//
        char[] arrayOfChar = s.toCharArray();//
        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
            ++j;//
        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
            --i;//
        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);//
    }

    public void setScreenInfoAndroid(int theWide, int theLength) throws Exception {
        readProperity.setProValue("screenWide", String.valueOf(theWide));
        readProperity.setProValue("screenLength", String.valueOf(theLength));
        int down = theLength / 100 * 100 - 100;
        int up = down - theLength / 100 * 100 * 3 / 10;
        System.out.println("~~~~~~ Android wide is :" + theWide);
        System.out.println("~~~~~~ Android length is :" + theLength);
        System.out.println("~~~~~~ Android up is :" + up);
        System.out.println("~~~~~~ Android down is :" + down);
        readProperity.setProValue("upOFscreen", String.valueOf(up));
        readProperity.setProValue("downOFscreen", String.valueOf(down));
//        prop.setProperty("upOFscreen", theENVConfigInfo.getupOFscreen());
//        prop.setProperty("downOFscreen", theENVConfigInfo.getdownOFscreen());
        // 将Properties集合保存到流中
    }

    public void setScreenInfoIOS(int theWide, int theLength) throws Exception {
        readProperity.setProValue("IOSscreenWide", String.valueOf(theWide));
        readProperity.setProValue("IOSscreenLength", String.valueOf(theLength));
        int down = theLength / 100 * 100;
        int up = down - theLength / 100 * 100 * 3 / 10;
        System.out.println("~~~~~~ IOS wide is :" + theWide);
        System.out.println("~~~~~~ IOS length is :" + theLength);
        System.out.println("~~~~~~ IOS up is :" + up);
        System.out.println("~~~~~~ IOS down is :" + down);
        readProperity.setProValue("IOSupOFscreen", String.valueOf(up));
        readProperity.setProValue("IOSdownOFscreen", String.valueOf(down));
//        prop.setProperty("IOSupOFscreen", theENVConfigInfo.getupOFscreen());
//        prop.setProperty("IOSdownOFscreen", theENVConfigInfo.getdownOFscreen());
        // 将Properties集合保存到流中
    }

    public AndroidDriver setupAndroid(File extapp, String extport) throws Exception {
        AndroidDriver driver = null;
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, andOSVersion);
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("platform", andPlatVersion);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
            int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
            setScreenInfoAndroid(Screen_X, Screen_Y);
        } catch (UnreachableBrowserException e) {
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
            int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
            setScreenInfoAndroid(Screen_X, Screen_Y);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return driver;
        }
    }

    public AndroidDriver recoverAndroid(File extapp, String extport) throws Exception {
        AndroidDriver driver = null;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, andOSVersion);
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("platform", andPlatVersion);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
            int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
            setScreenInfoAndroid(Screen_X, Screen_Y);
        } catch (UnreachableBrowserException e) {
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
            int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
            setScreenInfoAndroid(Screen_X, Screen_Y);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return driver;
        }
    }

    public IOSDriver setupIOSsimulator(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        //   capabilities.setCapability("deviceName", ("iPad 2"));
        capabilities.setCapability("deviceName", (iosDeviceName));
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("platformName", iosPlatformName);
        //   capabilities.setCapability("orientation","LANDSCAPE");

        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
        int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
        setScreenInfoIOS(Screen_X, Screen_Y);
        return driver;
    }


    public IOSDriver recoverIOSsimulator(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("BundleID", iosBund);
        //   capabilities.setCapability("deviceName", ("iPad 2"));
        capabilities.setCapability("deviceName", (iosDeviceName));
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("platformName", iosPlatformName);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //    capabilities.setCapability("orientation","LANDSCAPE");
        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }

    public IOSDriver setupIPADsimulator(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("deviceName", (iosDeviceName));
        // capabilities.setCapability("deviceName", ("iPhone 6"));
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("platformName", iosPlatformName);
        capabilities.setCapability("orientation", iosShowMode);

        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
        int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
        setScreenInfoIOS(Screen_X, Screen_Y);
        return driver;
    }


    public IOSDriver recoverIPADsimulator(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability("deviceName", (iosDeviceName));
        //  capabilities.setCapability("deviceName", ("iPhone 6"));
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("platformName", iosPlatformName);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("orientation", iosShowMode);

        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }


    public IOSDriver setupIOSdevice(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));

        capabilities.setCapability("deviceName", iosDeviceName);
        capabilities.setCapability("udid", iosUDID);
        capabilities.setCapability("platformVersion", iosPlaformVersion);

        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("platformName", iosPlatformName);

        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
        int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
        setScreenInfoIOS(Screen_X, Screen_Y);
        return driver;
    }

    public IOSDriver recoverIOSdevice(File extapp, String extport) throws Exception {
        IOSDriver driver;
        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("udid", iosUDID);
        capabilities.setCapability("deviceName", (iosDeviceName));
        capabilities.setCapability("platformVersion", iosPlaformVersion);

        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability("platformName", iosPlatformName);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }

    public IOSDriver setupIPADdevice(File extapp, String extport) throws Exception {
        IOSDriver driver;
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("deviceName", iosDeviceName);
        capabilities.setCapability("udid", iosUDID);
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("orientation", iosShowMode);
        capabilities.setCapability("platformName", iosPlatformName);
        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int Screen_X = driver.manage().window().getSize().getWidth();//获取手机屏幕宽度
        int Screen_Y = driver.manage().window().getSize().getHeight();//获取手机屏幕长度
        setScreenInfoIOS(Screen_X, Screen_Y);
        return driver;
    }

    public IOSDriver recoverIPADdevice(File extapp, String extport) throws Exception {
        IOSDriver driver;
        capabilities.setCapability("app", extapp.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("deviceName", iosDeviceName);
        capabilities.setCapability("udid", iosUDID);
        capabilities.setCapability("platformVersion", iosPlaformVersion);
        capabilities.setCapability("BundleID", iosBund);
        capabilities.setCapability("platformName", iosPlatformName);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("orientation", iosShowMode);
        driver = new IOSDriver(new URL("http://127.0.0.1:" + extport + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }

    public static String[] preapareActionParameter(String[] a, String[] b) {
        String[] target = new String[a.length + b.length];
        System.arraycopy(a, 0, target, 0, a.length);
        System.arraycopy(b, 0, target, a.length, b.length);
        for (int ind = 0; ind < target.length; ind++) {
            System.out.println("%%%%%%%%%%%%%%%%%%%" + target[ind]);
        }
        return target;
    }

    public static String[] preapareActionParameter(String[] a) {
        String[] target = new String[a.length];
        System.arraycopy(a, 0, target, 0, a.length);
        for (int ind = 0; ind < target.length; ind++) {
            System.out.println("%%%%%%%%%%%%%%%%%%%" + target[ind]);
        }
        return target;
    }


    public static boolean assertTestResult(String testResult, String exceptedResultMode) throws REXException {
        System.out.println("~~~~~~ Start to judge the test result! : ");
        boolean result = true;
        testResult = new String(testResult.toLowerCase());
        String sampleResult = new String(testResult);
        exceptedResultMode = filter4space(exceptedResultMode).toLowerCase();
        if (!exceptedResultMode.equalsIgnoreCase("ignore")) {
            if (!exceptedResultMode.equalsIgnoreCase("regular")) {  // for error check
                if (!sampleResult.startsWith("teststart")) {  //error cause present
                    String[] excResult = exceptedResultMode.split(";");
                    for (int ind = 0; (ind < excResult.length) && result == true; ind++) {
                        sampleResult = new String(testResult);
                        String[] singlePartResult = excResult[ind].split(" ");
                        for (int dind = 0; (dind < singlePartResult.length) && result == true; dind++) {
                            if (!sampleResult.contains(singlePartResult[dind])) {
                                result = false;
                                break;
                            } else {
                                System.out.println("start : " + sampleResult.indexOf(singlePartResult[dind]));
                                System.out.println("end : " + sampleResult.length());
                                sampleResult = sampleResult.substring(sampleResult.indexOf(singlePartResult[dind]), sampleResult.length());  //TODO cut too much, should be length()
                                System.out.println(sampleResult);
                            }
                        }
                    }
                } else  // error check but the error cause is empty OR the case which need error end with no error
                {
                    result = false;
                }
            } else if (exceptedResultMode.equalsIgnoreCase("regular")) {
                if (sampleResult.startsWith("teststart")) {
                    result = true;
                } else if (testResult.toLowerCase().contains("err") || testResult.toLowerCase().contains("fail")) {
                    result = false;
                } else {
                    System.out.println("");
                    result = false;
                }
            }
        } else {
            result = true;
        }
        System.out.println("~~~~~~ End of judge the test result! : " + result);
        return result;
    }


    public static boolean assertTestResultPageTitle(String testResult, String exceptedResult) throws REXException {
        System.out.println("~~~~~~ Start to judge the test result! : assertTestResultPageTitle");
        boolean result = true;
        testResult = testResult.toLowerCase();
        String sampleResult = testResult;
        exceptedResult = filter4space(exceptedResult).toLowerCase();
        if (!exceptedResult.equalsIgnoreCase("ignore")) {
            if (!exceptedResult.equalsIgnoreCase("regular")) {
                if (!sampleResult.startsWith("teststart")) {
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
                                sampleResult = sampleResult.substring(sampleResult.indexOf(singlePartResult[dind]), sampleResult.length());
                                System.out.println(sampleResult);
                            }
                        }
                    }
                }
            } else if (exceptedResult.equalsIgnoreCase("regular")) {
                if (sampleResult.startsWith("teststart")) {
                    result = true;
                } else if (testResult.toLowerCase().contains("err") || testResult.toLowerCase().contains("fail")) {
                    result = false;
                } else {
                    System.out.println("");
                    result = false;
                }
            }
        } else {
            result = true;
        }
        System.out.println("~~~~~~ End of judge the test result! : " + result);
        return result;
    }

    public String formatResult4XLSX(String nameOfMethod, String preRoleName, String targetResult, String nameExp, String intime) throws Exception {
        String result = null;
        String shortM = null;
        String time4xlsx = intime;
        result = (nameOfMethod + "#" + time4xlsx + "#" + preRoleName + "#" + targetResult + "#" + "End with " + "#" + nameExp);
        return result;
    }


}



