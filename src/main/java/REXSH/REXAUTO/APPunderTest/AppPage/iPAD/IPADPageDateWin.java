package REXSH.REXAUTO.APPunderTest.AppPage.iPAD;

import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.Page.IOSPageContent;
import REXSH.REXAUTO.Component.PageLoadWait.IOSDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.IOSExpectedCondition;
import REXSH.REXAUTO.LocalException.REXException;
import REXSH.REXAUTO.LocalException.XMLException;
import Utility.Log;
import Utility.readProperity;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;


/**
 * Created by appledev131 on 4/11/16.
 */
public class IPADPageDateWin extends IPADPageTemplate {
    protected String fileName = "DateWin.json";
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    private String contentFilePath = "\\PageXML\\iPAD\\";
    public String path4Log = System.getProperty("user.dir");
    protected String targetPagename = "";
    protected String comFileName = null;
    protected IOSPageContent content = null;
    protected HashMap<String, ElementMapping> eleContentMap = null;
    protected HashMap<String, IOSElement> eleMap = null;
    public HashMap<String, By> byMap = null;
    protected IOSDriver dr = null;
    protected int osType = 2;
    protected HashMap<String, String> nextPageList = null;
    private String errNoticeWind = "";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";
    private String storeValue4compare = "";
    private static String logSpace4thisPage = " --- IPADPageDateWin --";
    private boolean pageMoveableCheckDisable = true;        // flag for check the page is moveable or not during test
    private boolean moveable = false;                        // flag for the page is moveable or not
    protected int roundScope = 5;

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

    public HashMap<String, String> getEleWinMap() {
        return this.content.eleWindowMap;
    }

    public boolean checkFlag4Win() {
        return this.content.flag4EleWindow;
    }

    public IPADPageDateWin(IOSDriver theD) throws Exception {
        super(theD);
        this.dr = theD;
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        String tempName = contentFilePath + fileName;

        content = new IOSPageContent(theD, tempName, osType);
        eleContentMap = content.getContentMap(content.getContentMap4Base(tempName, osType));
        Thread.sleep(3000);
        byMap = getByMap(theD);
    }


    public IPADPageDateWin(IOSDriver theD, String p4L) throws Exception {
        super(theD);
        this.dr = theD;
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        String tempName = contentFilePath + fileName;

        content = new IOSPageContent(theD, tempName, osType);
        eleContentMap = content.getContentMap4Base(tempName, osType);
        byMap = content.getByMap(theD,this.eleContentMap);
        loadingJudgement(theD, this.eleContentMap);
        this.path4Log = p4L;
    }


    public IPADPageDateWin(String theFile, IOSDriver theD, Log theLogger) throws Exception {
        super(theFile, theD, theLogger);
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        this.comFileName = contentFilePath + fileName;
        this.dr = theD;
    }


    @Override
    public String getContentInBtn(IOSDriver driver, String eleName) throws Exception {
        String theResult = null;
        for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                System.out.println(eleName + " ::");
                IOSDriverWait xWait = new IOSDriverWait(driver, timeOutInSeconds, this.path4Log);

                theResult = xWait.until(
                        new IOSExpectedCondition<String>() {
                            public String apply(IOSDriver driver) {
                                WebElement theX = driver.findElement((By) tempContentEntry.getValue());
                                return theX.getText();
                            }
                        });
            }
        }
        return theResult;
    }

    public String btnOperationRoute(IOSDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperationRoute in IPADPageDateWin has been called ~~~ +++");
        return super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
    }

    public String calendarOperation(IOSDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperationRoute in IPADPageDateWin has been called ~~~ +++");
        return super.calendarOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
    }

    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
    }


    public HashMap<String, By> getByMap(IOSDriver driver) {
        HashMap result = new HashMap<String, By>();
        result.clear();
        By tempElement = null;
        System.out.println(logSpace4thisPage + "xxxxxx ------ Check the driver in IPADPageDateWin . getByMap (IOSDriver driver)  : " + driver.toString());

        for (Iterator it = this.eleContentMap.entrySet().iterator(); it.hasNext(); ) {
            try {
                Thread.sleep(50);

                Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (((ElementMapping) tempContentEntry.getValue()).getType().equalsIgnoreCase("xpath")) {
                    tempElement = By.xpath(((ElementMapping) tempContentEntry.getValue()).getLocatorStr());
                } else if (((ElementMapping) tempContentEntry.getValue()).getType().equalsIgnoreCase("classname")) {
                    tempElement = By.className(((ElementMapping) tempContentEntry.getValue()).getLocatorStr());
                } else {
                    System.out.println(logSpace4thisPage + "currently we just support xpath & classname");
                }
                result.put(tempContentEntry.getKey().toString(), tempElement);
                System.out.println("!!!!!! " + result.toString() + " !!!!!!");
                //return resultList;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Iterator it = result.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry eleEntry = (Map.Entry) it.next();
            System.out.println("@@@@@@------ getElementMap check result value ------@@@@@@  " + eleEntry.getKey());
            System.out.println("@@@@@@------ getElementMap check result value ------@@@@@@  " + eleEntry.getValue());
        }
        return result;
    }

    public HashMap<String, By> getByMap(IOSDriver driver, HashMap<String, ElementMapping> eleMap) {
        HashMap result = new HashMap<String, By>();
        result.clear();
        By tempElement = null;
        System.out.println(logSpace4thisPage + "xxxxxx ------ Check the driver in IPADPageDateWin . getByMap (IOSDriver driver, HashMap<String, ElementMapping> eleMap)  : " + driver.toString());

        for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
            try {
                Thread.sleep(50);

                Map.Entry tempContentEntry = (Map.Entry) it.next();
                if (((ElementMapping) tempContentEntry.getValue()).getType().equalsIgnoreCase("xpath")) {
                    tempElement = By.xpath(((ElementMapping) tempContentEntry.getValue()).getLocatorStr());
                } else if (((ElementMapping) tempContentEntry.getValue()).getType().equalsIgnoreCase("id")) {
                    tempElement = By.id(((ElementMapping) tempContentEntry.getValue()).getLocatorStr());
                } else {
                    System.out.println(logSpace4thisPage + "currently we just support xpath & id");
                }
                result.put(tempContentEntry.getKey().toString(), tempElement);
                System.out.println("!!!!!! " + result.toString() + " !!!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Iterator it = result.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry eleEntry = (Map.Entry) it.next();
            System.out.println("@@@@@@------ getElementMap check result value ------@@@@@@  " + eleEntry.getKey());
            System.out.println("@@@@@@------ getElementMap check result value ------@@@@@@  " + eleEntry.getValue());
        }
        return result;
    }

    public void btnOperation(IOSDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperation in IPADPageDateWin has been called ~~~ +++");
        super.btnOperation(driver, eleName, this.byMap);
    }


    public String getElementContent(IOSDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The getElementContent in IPADPageDateWin has been called ~~~ +++");
        //  (IOSDriver driver, String locatorStr, String locatorType, HashMap<String, By> inMap, HashMap<String, ElementMapping> eleMap, String p4L, int TITLEindex)
        String eleType = null;
        String eleText = null;
        String eleStr = null;
        for (Iterator it = this.eleContentMap.entrySet().iterator(); it.hasNext() && eleType == null; ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                eleType = ((ElementMapping) tempContentEntry.getValue()).getType();
                eleStr = ((ElementMapping) tempContentEntry.getValue()).getLocatorStr();
            }
        }
        return super.getElementContentStatic(driver, eleStr, eleType, this.byMap, this.eleContentMap, this.path4Log, 1);
    }

    public boolean chooseDateItem(IOSDriver theD, String theDay) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String targetDay = theDay;
        boolean flag = false;
        List<WebElement> theItems = null;
        for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase("DateItem")) {
                System.out.println("DateItem" + " ::");
                IOSDriverWait xWait = new IOSDriverWait(theD, timeOutInSeconds, this.path4Log);
                theItems = theD.findElements((By) tempContentEntry.getValue());
                break;
            }
        }
        if ((theItems == null) || (theItems.size() == 0)) {
            throw new REXException("failed to find the DateItem");
        } else {
            for (int ind = 0; ind < theItems.size(); ind++) {
                if (theItems.get(ind).getText().equals(targetDay)) {
                    WebElement temp = theItems.get(ind);
                    temp.click();
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean rollDateItem(IOSDriver theD, String theDay) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String targetDay = theDay;
        int roundSco = this.roundScope; // 5 in the windows
        boolean flag = false;
        WebElement theItem = null;
        boolean rollUP = false;
        String result = "";
        for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase("dayRoll")) {
                System.out.println("dayRoll" + " ::");
                IOSDriverWait xWait = new IOSDriverWait(theD, timeOutInSeconds, this.path4Log);
                theItem = theD.findElement((By) tempContentEntry.getValue());
                break;
            }
        }
        if (theItem == null) {
            throw new REXException("failed to find the dayRoll");
        } else {
            String currentDayStr = theItem.getText();
            int currentDayInt = Integer.parseInt(theItem.getText());
            //     int step = currentDayInt - Integer.parseInt(theDay);
            if (currentDayStr.equals(targetDay)) {
                flag = true;
            } else {
                rollUP = true;
                result = super.rollTheRound(theD, theItem, currentDayInt, roundSco, rollUP);
            }
            System.out.println("Current value in roller is : " + result);
        }

        if (!result.equals("error") && !result.equals("emptyContent")) { //TODO
            flag = true;
        }
        return flag;
    }

    public boolean pressDateItem(IOSDriver theD, String theDay) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        boolean flag = false;
        flag = super.pressDateItem(theD, theDay, this.byMap);
        return flag;
    }

    public boolean pressItemString(IOSDriver theD, String tValue) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String targetValue = tValue;
        int roundSco = this.roundScope; // 5 in the windows
        boolean flag = false;
        WebElement theItem = null;
        boolean rollUP = false;
        String result = "";
        for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase("dayRoll")) {
                System.out.println("dayRoll" + " ::");
                IOSDriverWait xWait = new IOSDriverWait(theD, timeOutInSeconds, this.path4Log);
                theItem = theD.findElement((By) tempContentEntry.getValue());
                break;
            }
        }
        if (theItem == null) {
            throw new REXException("failed to find the dayRoll");
        } else {
            String currentDayStr = theItem.getText();
            int currentDayInt = Integer.parseInt(theItem.getText());
            if (currentDayStr.equals(tValue)) {
                flag = true;
            } else {
                rollUP = true;
                result = super.pressStrTheRound(theD, (IOSElement) theItem, targetValue, roundSco, rollUP);      // true for up
            }
            System.out.println("Current value in roller is : " + result);
        }

        if (!result.equals("error") && !result.equals("emptyContent")) { //TODO
            flag = true;
        }
        return flag;
    }

    public boolean pressMonthItem(IOSDriver theD, String theMonth) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        boolean flag = false;
        flag = super.pressMonthItem(theD, theMonth, this.byMap);
        return flag;
    }

    public String saveDate(IOSDriver theD, String theDay, String thePath) throws Exception {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String targetDay = theDay;
        WebElement theBtn = null;
        String theRoute = null;
        for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext(); ) {
            final Map.Entry tempContentEntry = (Map.Entry) it.next();
            if (tempContentEntry.getKey().toString().equalsIgnoreCase("OKBtn")) {
                System.out.println("OKBtn" + " :?:" + tempContentEntry.getKey().toString());
                IOSDriverWait xWait = new IOSDriverWait(theD, timeOutInSeconds, thePath);
                theBtn = xWait.until(
                        new IOSExpectedCondition<WebElement>() {
                            public WebElement apply(IOSDriver driver) {
                                return driver.findElement((By) tempContentEntry.getValue());
                            }
                        });
                theRoute = super.checkTheNextPage(tempContentEntry.getKey().toString(), this.eleContentMap);
                System.out.println("    +++ ~~~ Check the route result : ~~~ +++" + theBtn);
                Thread.sleep(2000);
                theBtn.click();
                if (!theRoute.equals("noRoute") && (theRoute != "") && (theRoute != null)) {
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(date);
                    ScreenShot(theD, "During_Switch_" + time + ".png", thePath);
                }
            }
        }
        return theRoute;
    }

    public String comPageCheck(IOSDriver theD, String type4check, String para4check) {  // type4check : ready, loading, error, temp , etc.  , para4check: target element name, all element, title element
        String result = null;
        System.out.println("comPageCheck in IOSPageProfileEditPublicationDateWin");
        result = super.comPageCheckWithInstanceInfo(theD, type4check, para4check, this.content, this.eleContentMap, this.byMap);
        return result;
    }

}


