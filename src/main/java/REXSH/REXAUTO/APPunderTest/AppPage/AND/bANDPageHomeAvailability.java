package REXSH.REXAUTO.APPunderTest.AppPage.AND;

import REXSH.REXAUTO.Component.Page.ANDPageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.PageLoadWait.ANDDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.ANDExpectedCondition;
import REXSH.REXAUTO.LocalException.REXException;
import REXSH.REXAUTO.LocalException.XMLException;
import Utility.Log;
import Utility.dateHandle;
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
 * Created by appledev131 on 4/11/16.
 */
public class bANDPageHomeAvailability extends bANDPageTemplate {
    protected String fileName = "bHomeAvailability.json";
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
    String tomorrow = readProperity.getProValue("tomorrow");
    protected int osType = 1;
    private String errNoticeWind = "";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";
    private String dateWin = "DateWin";
    private String storeValue4compare = "";
    private static String logSpace4thisPage = " --- bANDPageHomeAvailability --";


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

    public bANDPageHomeAvailability(AndroidDriver theD) throws Exception {
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


    public bANDPageHomeAvailability(AndroidDriver theD, String p4L) throws Exception {
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


    public bANDPageHomeAvailability(String theFile, AndroidDriver theD, Log theLogger) throws Exception {
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
        System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            return super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
        } catch (Exception e) {
            throw e;
        }
    }

    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
    }



    public String calendarOperation(AndroidDriver driver, String eleName) throws Exception {
        String result = null;
        String calendarValue = null;
        System.out.println("    +++ ~~~ The calendarOperation in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            result = super.calendarOperationSuper(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, dateWin);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public String calendarOperationDate(AndroidDriver driver, String eleName, String tDate) throws Exception {
        String result = null;
        System.out.println("    +++ ~~~ The calendarOperationDate in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            result = super.calendarOperationSuper(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, tDate, dateWin);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public String dropDownListOperation(AndroidDriver driver, String eleName) throws Exception {  //TODO
        String defaultText = null;
        String dropItemValue = null;
        System.out.println("    +++ ~~~ The dropDownListOperation in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            defaultText = super.dropDownListShow(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.content.eleDefaultValueMap);
            dropItemValue = super.dropDownItemChoose(driver, defaultText, this.byMap, this.eleContentMap, this.path4Log);
            return dropItemValue;
        } catch (Exception e) {
            throw e;
        }
    }

    public String dropDownListOperation(AndroidDriver driver, String eleName, String extPara) throws Exception {  //TODO
        String defaultText = null;
        String dropItemValue = null;
        System.out.println("    +++ ~~~ The dropDownListOperation in bANDPageHomeAvailability has been called ~~~ +++");
        System.out.println(" this time we want to choose the " + extPara + " item ");
        try {
            defaultText = super.dropDownListShow(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.content.eleDefaultValueMap);
            dropItemValue = super.dropDownItemChooseWithPara(driver, defaultText, this.byMap, this.eleContentMap, this.path4Log, extPara);
            return dropItemValue;
        } catch (Exception e) {
            throw e;
        }
    }


    public void btnOperation(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperation in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            super.btnOperation(driver, eleName, this.byMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public void textOperation(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperation in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            super.textOperation(driver, eleName, para1, this.byMap, this.content.eleDefaultValueMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public String getElementContent(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The getElementContent in bANDPageHomeAvailability has been called ~~~ +++");
        try {
            return super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public String textOperationWithSaveInput(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperationWithSaveInput in bANDPageHomeAvailability has been called ~~~ +++");
        String tempDefaultValue = null;
        String result = null;
        try {
            for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                if (eleName.equals(tempEle.getElementName())) {
                    tempDefaultValue = tempEle.getDefaultValue();
                    break;
                }
            }
            if (tempDefaultValue != null) {
                result = super.textOperationWithSaveInput(driver, eleName, para1, this.byMap, tempDefaultValue);
            } else {
                throw new XMLException("some element need to add with the default value");
            }

        } catch (Exception e) {
            System.out.println(logSpace4thisPage + "In bANDPageHomeAvailability . textOperationWithSaveInput , Other Error appear : " + e.getCause());
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


}


