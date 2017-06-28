package REXSH.REXAUTO.APPunderTest.AppPage.AND;

import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Page.ANDPageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.PageLoadWait.ANDDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.ANDExpectedCondition;
import REXSH.REXAUTO.LocalException.PageException;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.Log;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;

import Utility.readProperity;

/**
 * Created by appledev131 on 4/11/16.
 */
public class bANDPageNaviBar extends bANDPageTemplate {
    protected String fileName = "bNaviBar.json";
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    private String contentFilePath = "\\PageXML\\And\\";
    public String path4Log = System.getProperty("user.dir");
    protected String targetPagename = "";
    protected String comFileName = null;
    protected ANDPageContent content = null;
    protected HashMap<String, ElementMapping> eleContentMap = null;
    protected HashMap<String, AndroidElement> eleMap = null;
    public HashMap<String, By> byMap = null;
    protected AndroidDriver dr = null;
    protected int osType = 1;
    protected long timeOutInSeconds = Long.parseLong(readProperity.getProValue("pageLoadTimeout"));
    private static String logSpace4thisPage = " --- bANDPageNaviBar --";
    private String storeValue4compare = "";
    private String errNoticeWind = "";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";


    public String value4compare() {
        return this.storeValue4compare;
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
        boolean result = false;
        WebElement theResult = null;
        final String localPath = this.path4Log;
        try {
            System.out.println("    +++ ~~~ In the ANDPageTemplate :: titleCheck in " + this.getClass().getSimpleName() + " has been called ~~~ +++");
            for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext() && theResult == null; ) {
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase("AccountSetBtn")) {
                    System.out.println("AccountSetBtn" + " :?:" + tempContentEntry.getKey().toString());
                    ANDDriverWait xWait = new ANDDriverWait(driver, 2, localPath);
                    theResult = xWait.until(new ANDExpectedCondition<WebElement>() {
                        public WebElement apply(AndroidDriver driver) {
                            return driver.findElement((By) tempContentEntry.getValue());
                        }
                    });
                    System.out.println("AND , titleCheck : " + theResult.getText());
                    String textInxml = this.content.eleTextContentMap.get("AccountSetBtn").toString();
                    if (!textInxml.equals("") && textInxml.equals(theResult.getText()) || theResult.getText().equals("")) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    public bANDPageNaviBar(AndroidDriver theD, String p4L) throws Exception {
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


    public bANDPageNaviBar(String theFile, AndroidDriver theD, Log theLogger) throws Exception {
        super(theFile, theD, theLogger);
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        this.comFileName = contentFilePath + fileName;
        this.dr = theD;
    }

    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
    }




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

    public boolean checkFlag4Win() {
        return this.content.flag4EleWindow;
    }

    public HashMap<String, String> getEleWinMap() {
        return this.content.eleWindowMap;
    }


    public WebElement findElementWithSearch(AndroidDriver driver, final By theKey, int timeValue) throws Exception {
        WebElement result = null;
        int localTime = timeValue;
        int step = 2;
        if (timeValue > 6000) {
            localTime = 5500;
        }
        for (; result == null; ) {
            try {
                ANDDriverWait wait4find = new ANDDriverWait(driver, localTime / ((step + 1) * 2) / 1000);
                result = wait4find.until(new ANDExpectedCondition<WebElement>() {
                    public WebElement apply(AndroidDriver driver) {
                        return driver.findElement(theKey);
                    }
                });
                if (result != null) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("In the bANDPageNaviBar::findElementWithSearch, saerching ");
                moveUPwithSyncTime(driver, localTime / ((step + 1) * 2) * step);
                Thread.sleep(localTime / ((step + 1) * 2));
            }
        }
        return result;
    }


    public void btnOperation(AndroidDriver driver, String eleName) throws Exception {
        try {
            System.out.println("    +++ ~~~ In the bANDPageTemplate ~~~ +++");
            System.out.println("    +++ ~~~ The btnOperation in " + this.getClass().getSimpleName() + " has been called ~~~ +++");
            for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " ::");
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds, this.path4Log);
                    WebElement theResult = xWait.until(new ANDExpectedCondition<WebElement>() {
                        public WebElement apply(AndroidDriver driver) {
                            WebElement temp = null;
                            try {
                                temp = findElementWithSearch(driver, (By) tempContentEntry.getValue(), Integer.parseInt(String.valueOf(timeOutInSeconds * 1000)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return temp;
                        }
                    });
                    theResult.click();
                }
            }
        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageNaviBar . btnOperation 2, Other Error appear : " + e.getCause());
            e.printStackTrace();
        }
    }
    public String btnOperationRoute(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageNaviBar has been called ~~~ +++");
        try {
            return super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
        } catch (Exception e) {
            throw e;
        }
    }



    public String checkTheNextPage(String btnName, HashMap<String, ElementMapping> eleMap) {
        String result = null;
        for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry tempContentEntry = (Map.Entry) it.next();
            System.out.println("    +++ ~~~ tempContentEntry : " + tempContentEntry);
            System.out.println("    +++ ~~~ tempContentEntry.getvalue : " + tempContentEntry.getValue());
            String tempRoute = ((ElementMapping) tempContentEntry.getValue()).getNextPage();
            System.out.println("    +++ ~~~ Find the temp Route : " + tempRoute);
            if (((ElementMapping) tempContentEntry.getValue()).getElementName().toLowerCase().contains((btnName).toLowerCase())) {
                if ((tempRoute != null) && (tempRoute != "")) {
                    result = tempRoute;
                }
            }
        }
        return result;
    }

    public String btnOperation(AndroidDriver driver, String eleName, HashMap<String, By> inMap, HashMap<String, ElementMapping> eleMap) throws REXException {
        String result = null;
        try {
            System.out.println("    +++ ~~~ In the bANDPageNaviBar ~~~ +++");
            System.out.println("    +++ ~~~ The btnOperation in " + this.getClass().getSimpleName() + " has been called ~~~ +++");
            for (Iterator it = inMap.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " :?:" + tempContentEntry.getKey().toString());
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds);
                    WebElement theResult = xWait.until(
                            new ANDExpectedCondition<WebElement>() {
                                public WebElement apply(AndroidDriver driver) {
                                    return driver.findElement((By) tempContentEntry.getValue());
                                }
                            });
                    System.out.println("Check the eleContMpa : " + eleMap.size());
                    result = checkTheNextPage(tempContentEntry.getKey().toString(), eleMap);
                    System.out.println("    +++ ~~~ Check the route result : ~~~ +++" + result);
                    theResult.click();
                }
            }
        } catch (REXException e) {
            throw e;
        } catch (TimeoutException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageNaviBar . btnOperationRoute, Other Error appear : " + e.getCause());
            e.printStackTrace();
        }
        return result;
    }




    public boolean getSingleEle(AndroidDriver driver, String eleName, HashMap<String, By> inMap) throws REXException {
        boolean result = false;
        try {
            System.out.println("    +++ ~~~ In the bANDPageNaviBar ~~~ +++");
            System.out.println("    +++ ~~~ The getSingleEle in " + this.getClass().getSimpleName() + " has been called ~~~ +++");
            for (Iterator it = inMap.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                    System.out.println(eleName + " ::");
                    ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds);
                    WebElement theResult = xWait.until(
                            new ANDExpectedCondition<WebElement>() {
                                public WebElement apply(AndroidDriver driver) {
                                    return driver.findElement((By) tempContentEntry.getValue());
                                }
                            });
                    if (theResult != null) {
                        result = true;
                    }
                }
            }
        } catch (REXException e) {
            throw e;
        } catch (TimeoutException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageNaviBar . getSingleEle , Other Error appear : " + e.getCause());
            e.printStackTrace();
        }
        return result;
    }

    public String comPageCheck(AndroidDriver theD, String type4check, String para4check) {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String result = null;
        boolean flag = false;
        try {
            if (type4check.equals("ready")) {       // ready mode
                if (para4check.equals("all")) {     // check all element for ready mode
                    String[] temp = this.content.getContentNameList4type(this.eleContentMap, "ready");
                    for (int ind = 0; ind < temp.length; ind++) {
                        flag = getSingleEle(theD, para4check, this.byMap);
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

            bANDPageNaviBar x = new bANDPageNaviBar("/PageXML/AND/loginPage.xml", driver, null);
            System.out.println("before defaulf operation");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}


