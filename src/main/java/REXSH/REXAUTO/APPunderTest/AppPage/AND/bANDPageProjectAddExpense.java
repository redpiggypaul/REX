package REXSH.REXAUTO.APPunderTest.AppPage.AND;

import REXSH.REXAUTO.Component.Page.ANDPageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.PageLoadWait.ANDDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.ANDExpectedCondition;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.Log;
import Utility.readProperity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;


/**
 * Created by appledev131 on 5/25/16.
 */


/**
 * Created by appledev131 on 4/11/16.
 */
public class bANDPageProjectAddExpense extends bANDPageTemplate {
    protected String fileName = "bAddExpense.json";
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    private String contentFilePath = "\\PageXML\\And\\ProjectTimeAndExpense\\";
    public String path4Log = System.getProperty("user.dir");
    protected String targetPagename = "";
    protected String comFileName = null;
    protected ANDPageContent content = null;
    protected HashMap<String, ElementMapping> eleContentMap = null;
    protected HashMap<String, AndroidElement> eleMap = null;
    public HashMap<String, By> byMap = null;
    protected AndroidDriver dr = null;
    protected int osType = 1;
    private String errNoticeWind = "";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";
    private String dateWin = "DateWin";
    private String storeValue4compare = "";
    private static String logSpace4thisPage = " --- bANDPageProjectAddExpense --";


    public String getConfWindName() {
        return this.confirmNoticeWind;
    }

    public String getErrWindName() {
        return this.errNoticeWind;
    }

    public String getCompWindName() {
        return this.completeNoticeWind;
    }

    public String getConfWindWOTName() {
        return this.confirmNoticeWindWOT;
    }

    public String value4compare() {
        return this.storeValue4compare;
    }

    public boolean checkFlag4Win() {
        return this.content.flag4EleWindow;
    }

    public HashMap<String, String> getEleWinMap() {
        return this.content.eleWindowMap;
    }

    public boolean getFlag4TitleCheck() {
        return this.content.getFlag4TitleCheck();
    }

    public void setFlag4TitleCheckTrue() {
        this.content.setFlag4TitleCheck(true);
    }

    public void setFlag4TitleCheckFalse() {
        this.content.setFlag4TitleCheck(false);
    }

    public boolean titleCheck(AndroidDriver driver) throws Exception {
        System.out.println("    +++ ~~~ The titleCheck in " + logSpace4thisPage + " has been called ~~~ +++");
        return super.titleCheckTe(driver, "PageTitle", this.byMap, this.eleContentMap, this.content.eleTextContentMap, this.path4Log);
    }

    public bANDPageProjectAddExpense(AndroidDriver theD) throws Exception {
        super(theD);
        this.dr = theD;
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        String tempName = contentFilePath + fileName;

        content = new ANDPageContent(theD, tempName, osType);
        eleContentMap = content.getContentMap(content.getContentMap4Base(tempName, osType));
        Thread.sleep(3000);
        byMap = getByMap(theD);
    }


    public bANDPageProjectAddExpense(AndroidDriver theD, String p4L) throws Exception {
        super(theD);
        this.dr = theD;
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        String tempName = contentFilePath + fileName;

        content = new ANDPageContent(theD, tempName, osType);
        eleContentMap = content.getContentMap4Base(tempName, osType);
        byMap = content.getByMap(theD,this.eleContentMap);
        loadingJudgement(theD, this.eleContentMap);
        this.path4Log = p4L;
    }


    public bANDPageProjectAddExpense(String theFile, AndroidDriver theD, Log theLogger) throws Exception {
        super(theFile, theD, theLogger);
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        this.comFileName = contentFilePath + fileName;
        this.dr = theD;
    }


    @Override
    public String getContentInBtn(AndroidDriver driver, String eleName) throws Exception {
        String theResult = null;
        try {
            for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " ::");
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds, this.path4Log);

                    theResult = xWait.until(
                            new ANDExpectedCondition<String>() {
                                public String apply(AndroidDriver driver) {
                                    WebElement theX = driver.findElement((By) tempContentEntry.getValue());
                                    return theX.getText();
                                }
                            });
                }
            }
            return theResult;
        } catch (Exception e) {
            throw e;
        }
    }

    public String btnOperationRoute(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageProjectAddExpense has been called ~~~ +++");
        try {
            return super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
        } catch (Exception e) {
            throw e;
        }
    }

    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
    }


    public void btnOperation(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperation in bANDPageProjectAddExpense has been called ~~~ +++");
        try {
            super.btnOperation(driver, eleName, this.byMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public void textOperation(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperation in bANDPageProjectAddExpense has been called ~~~ +++");
        String dValue = null;
        try {
            System.out.println("Some on called me with : " + para1);
            System.out.println("inMap size : " + this.byMap.size());
            for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
                System.out.println("iterator : " + it == null);
                System.out.println("inMap size : " + this.byMap.size());
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " ::");
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds, this.path4Log);
                    WebElement theResult = xWait.until(
                            new ANDExpectedCondition<WebElement>() {
                                public WebElement apply(AndroidDriver driver) {
                                    return driver.findElement((By) tempContentEntry.getValue());
                                }
                            });
                    for (Iterator iter = dValueMap.entrySet().iterator(); iter.hasNext(); ) {
                        final Map.Entry tempValueEntry = (Map.Entry) iter.next();
                        if (tempValueEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                            dValue = (String) tempValueEntry.getValue();
                        }
                    }
                    theResult.click();
                    Thread.sleep(200);
                    if ((dValue != "") && (dValue != null)) {
                        if (!theResult.getText().equals(dValue)) {
                            for (String x = theResult.getText(); !x.equals(dValue); ) {
                                System.out.println("### Default value of current textfield : " + x);
                                int tempTextLength = theResult.getText().length();
                                driver.sendKeyEvent(123);
                                for (int i = 0; i < tempTextLength; i++) {
                                    driver.sendKeyEvent(67);
                                }
                                x = theResult.getText();
                            }
                        } else {
                            System.out.println("### Current textfield match " + dValue + " :: " + theResult.getText());
                        }
                    } else if (dValue.equals("")) {
                        for (String x = theResult.getText(); x.length() != 0; ) {
                            int tempTextLength = x.length();
                            System.out.println("### Default value of current textfield : " + x);
                            System.out.println("### Default value of current textfield : " + tempTextLength);
                            driver.sendKeyEvent(123);
                            for (int i = 0; i < tempTextLength; i++) {
                                driver.sendKeyEvent(67);
                            }
                            x = theResult.getText();
                        }
                    }
                    System.out.println("Ready to send the key !!! ");
                    theResult.sendKeys(para1);
                    Thread.sleep(200);
                    driver.sendKeyEvent(66);
                }
            }
            Thread.sleep(500);
            try {
                driver.hideKeyboard();
            } catch (Exception e) {
                System.out.println(" ~~~~~~ Some thing wrong during hide keyboard ~~~~~~");
            }

        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageProjectAddExpense . textOperation 4, Other Error appear : " + e.getCause());
            e.printStackTrace();
        }

    }


    public String calendarOperation(AndroidDriver driver, String eleName) throws Exception {
        String result = null;
        String calendarValue = null;
        System.out.println("    +++ ~~~ The calendarOperation in bANDPageProjectAddExpense has been called ~~~ +++");
        try {
            result = super.calendarOperationSuper(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, dateWin);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public String calendarOperationDate(AndroidDriver driver, String eleName, String tDate) throws Exception {
        String result = null;
        System.out.println("    +++ ~~~ The calendarOperationDate in bANDPageProjectAddExpense has been called ~~~ +++");
        try {
            result = super.calendarOperationSuper(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, tDate, dateWin);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }


    public String getElementContent(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The getElementContent in bANDPageProjectAddExpense has been called ~~~ +++");
        try {
            return super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public String textOperationWithSaveInput(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperationWithSaveInput in bANDPageProjectAddExpense has been called ~~~ +++");
        String result = null;
        String dValue = null;
        try {
            System.out.println("Some on called me with : " + para1);
            System.out.println("inMap size : " + this.byMap.size());
            for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
                System.out.println("iterator : " + it == null);
                System.out.println("inMap size : " + this.byMap.size());
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " ::");
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds, this.path4Log);
                    WebElement theResult = xWait.until(
                            new ANDExpectedCondition<WebElement>() {
                                public WebElement apply(AndroidDriver driver) {
                                    return driver.findElement((By) tempContentEntry.getValue());
                                }
                            });
                    for (Iterator iter = this.content.getDefaultValueMap().entrySet().iterator(); iter.hasNext(); ) {
                        final Map.Entry tempValueEntry = (Map.Entry) iter.next();
                        if (tempValueEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                            dValue = (String) tempValueEntry.getValue();
                        }
                    }
                    theResult.click();
                    Thread.sleep(200);
                    if ((dValue != "") && (dValue != null)) {
                        if (!theResult.getText().equals(dValue)) {
                            for (String x = theResult.getText(); !x.equals(dValue); ) {
                                System.out.println("### Default value of current textfield : " + x);
                                int tempTextLength = theResult.getText().length();
                                driver.sendKeyEvent(123);
                                for (int i = 0; i < tempTextLength; i++) {
                                    driver.sendKeyEvent(67);
                                }
                                x = theResult.getText();
                            }
                        } else {
                            System.out.println("### Current textfield match " + dValue + " :: " + theResult.getText());
                        }
                    } else if (dValue.equals("")) {
                        for (String x = theResult.getText(); x.length() != 0; ) {
                            int tempTextLength = x.length();
                            System.out.println("### Default value of current textfield : " + x);
                            System.out.println("### Default value of current textfield : " + tempTextLength);
                            driver.sendKeyEvent(123);
                            for (int i = 0; i < tempTextLength; i++) {
                                driver.sendKeyEvent(67);
                            }
                            x = theResult.getText();
                        }
                    }
                    System.out.println("Ready to send the key !!! ");
                    theResult.sendKeys(para1);
                    Thread.sleep(200);
                    driver.sendKeyEvent(66);
                    String tempText = theResult.getText();
                    if ((tempText != "") && (tempText != null)) {
                        if (tempText.equals(para1)) {
                            result = tempText;
                        } else {
                            throw new REXException("textOperationWithSaveInput : the real input para is different from excepted ");
                        }
                    } else {
                        result = "failedToGetText";
                    }
                }
            }
            Thread.sleep(500);
            try {
                driver.hideKeyboard();
            } catch (Exception e) {
                System.out.println(" ~~~~~~ Some thing wrong during hide keyboard ~~~~~~");
            }

        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageProjectAddExpense . textOperation 4, Other Error appear : " + e.getCause());
            e.printStackTrace();
        } finally {
            return result;
        }

    }


    public String comPageCheck(AndroidDriver theD, String type4check, String para4check) {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String result = null;
        boolean flag = false;
        try {
            if (type4check.equals("ready")) {       // ready mode
                if (para4check.equals("all")) {     // check all element for ready mode
                    String[] temp = this.content.getContentNameList4type(this.eleContentMap, "ready");
                    for (int ind = 0; ind < temp.length; ind++) {
                        flag = super.getSingleEle(theD, para4check, this.byMap);
                    }
                } else if (para4check.equals("title")) // check title for ready mode
                {
                    String[] temp = this.content.getContentNameList4name(this.eleContentMap, "PageTitle");
                    if (temp.length != 1) {
                        flag = false;
                        System.out.println("More than 1 title element found in : " + this.getClass().getSimpleName());
                        System.out.println("Please check the XML file for : " + this.getClass().getSimpleName());
                    } else {
                        //TODO  compare the ((ElementMapping) temp[0]).getTextContent() and compare with the element current content to confirm the result
                        flag = true;
                        System.out.println("TODO  compare the ((ElementMapping) temp[0]).getTextContent() and compare with the element current content to confirm the result");
                    }
                } else if (para4check.equals("alertTitle")) {
                    String[] temp = this.content.getContentNameList4name(this.eleContentMap, "alertTitle");
                    if (temp.length != 1) {
                        flag = false;
                        System.out.println("More than 1 alertTitle element found in : " + this.getClass().getSimpleName());
                        System.out.println("Please check the XML file for : " + this.getClass().getSimpleName());
                    } else {
                        //TODO  compare the ((ElementMapping) temp[0]).getTextContent() and compare with the element current content to confirm the result
                        flag = true;

                    }
                } else {
                }
            } else {
                //TODO
                System.out.println("Other type of check are not ready");
            }
            if (flag == true) {
                result = "pass";
            } else {
                result = "fail";
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            flag = false;
            result = "fail";
        } catch (REXException e) {
            e.printStackTrace();
            flag = false;
            result = "fail";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    public static void main(String args[]) {
        AndroidDriver driver = null;

        try {
            File app = new File("/Users/appledev131/Downloads", "app-debug.apk");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability(CapabilityType.VERSION, "5.1");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("app-package", "com.pwc.talentexchange");
            capabilities.setCapability("app-activity", ".activity.BaseActivity");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            Thread.sleep(6000);

            bANDPageProjectAddExpense x = new bANDPageProjectAddExpense("/PageXML/AND/bProfileEditSkills.xml", driver, null);
            System.out.println("before default operation");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}



