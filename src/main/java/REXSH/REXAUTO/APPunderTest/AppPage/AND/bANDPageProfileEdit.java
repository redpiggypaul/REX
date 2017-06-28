package REXSH.REXAUTO.APPunderTest.AppPage.AND;

import REXSH.REXAUTO.Component.Page.ANDPageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.PageLoadWait.ANDDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.ANDExpectedCondition;
import REXSH.REXAUTO.LocalException.REXException;
import REXSH.REXAUTO.LocalException.XMLException;
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
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by appledev131 on 4/11/16.
 */
public class bANDPageProfileEdit extends bANDPageTemplate {
    protected String fileName = "bProfileEdit.json";
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    private String contentFilePath = "\\PageXML\\And\\Resume\\";
    public String path4Log = System.getProperty("user.dir");
    protected String targetPagename = "";
    protected String comFileName = null;
    protected ANDPageContent content = null;
    protected HashMap<String, ElementMapping> eleContentMap = null;
    public HashMap<String, By> byMap = null;
    protected AndroidDriver dr = null;
    protected int osType = 1;
    protected int index4XPATH = 0;
    private String errNoticeWind = "";
    // private String confirmNoticeWind = "ProfileEditClearWin";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";
    private String storeValue4compare = "";
    private static String logSpace4thisPage = " --- bANDPageProfileEdit --";


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



    public bANDPageProfileEdit(AndroidDriver theD, String p4L) throws Exception {
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


    public bANDPageProfileEdit(String theFile, AndroidDriver theD, Log theLogger) throws Exception {
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

    public String btnOperationRoute(AndroidDriver driver, String eleName) throws Exception { // elename may contains common 4 title, record 4 record under title
        System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageProfileEdit has been called ~~~ +++");
        String tempLocatorStr = null;
        String tempType = null;
        String result = null;
        String commonType = null;
        String commonLocatorStr = null;
        String recordType = null;
        String recordLocatorStr = null;
        try {
            for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                if (eleName.equals(tempEle.getElementName())) {
                    tempLocatorStr = tempEle.getLocatorStr();
                    tempType = tempEle.getType();
                }
                if (tempEle.getElementName().toLowerCase().contains("commonby")) {
                    commonLocatorStr = tempEle.getLocatorStr();
                    commonType = tempEle.getType();
                }
                if (tempEle.getElementName().toLowerCase().contains("recordby")) {
                    recordLocatorStr = tempEle.getLocatorStr();
                    recordType = tempEle.getType();
                }
            }
            loadingJudgement(driver, this.eleContentMap);
            if (!eleName.toLowerCase().contains("_record")) { // for title
                if (commonLocatorStr != null) {  //
                    if ((commonType.equals("xpath")) && (tempType.equals("xpath") && tempLocatorStr.contains(":REXindex:"))) {
                        result = super.btnOperationSearch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                        if (result.split("::").length > 1) {
                            index4XPATH = Integer.parseInt(result.split("::")[1]);
                            result = new String(result.split("::")[0]);
                        } else if (result.endsWith("::")) {
                            result = new String(result.split("::")[0]);
                        } else {
                            //
                        }
                    } else if ((commonType.equals("id")) && (tempType.equals("xpath") && tempLocatorStr.contains(":REXindex:"))) {
                        result = super.btnOperationSearch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                        if (result.split("::").length > 1) {
                            index4XPATH = Integer.parseInt(result.split("::")[1]);
                            result = new String(result.split("::")[0]);
                        } else if (result.endsWith("::")) {
                            result = new String(result.split("::")[0]);
                        } else {
                            //
                        }
                    } else if ((tempType.equals("xpath") && tempLocatorStr.contains(":REXindex:") && !tempLocatorStr.contains(":REXrecord:"))) {
                        result = super.btnOperationAutoIndex(driver, eleName, this.byMap, this.eleContentMap, this.path4Log); // TODO
                    } else {
                        result = super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
                    }
                } else {
                    throw new XMLException("the locator str don't match");
                }
            } else if (eleName.toLowerCase().contains("_record")) {  // for record
                System.out.println("stop 2.1  ~~");
                String name4title = eleName.split("_")[0];
                String titleTempLocatorStr = null;
                String titleTempType = null;
                // find the index of title directly
                for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                    ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                    if (name4title.equals(tempEle.getElementName())) {
                        titleTempLocatorStr = tempEle.getLocatorStr();
                        titleTempType = tempEle.getType();
                        break;
                    }
                }
                if (commonLocatorStr != null && titleTempLocatorStr != null & titleTempType != null) {  //
                    System.out.println("stop 2.2  ~~");
                    if ((commonType.equals("xpath")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                        System.out.println("stop 2.3  ~~");
                        index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                    } else if ((commonType.equals("id")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                        System.out.println("stop 2.4  ~~");
                        index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                    }
                }
                // find the index of title directly
                if (recordLocatorStr != null && recordType != null && tempLocatorStr != null && tempType != null) {
                    System.out.println("stop 3.1  ~~");
                    if ((recordType.equals("xpath")) && ((recordLocatorStr.contains(":REXindex:")) || (recordLocatorStr.contains(":REXrecord:")))) {
                        result = super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
                    } else if ((recordType.equals("id")) && ((tempType.contains(":REXindex:")) || (tempLocatorStr.contains(":REXrecord:")))) {
                        System.out.println("stop 3.4  ~~");
                        result = super.recordSearch(driver, eleName, this.eleContentMap, this.path4Log, index4XPATH, tempType, tempLocatorStr, recordType, recordLocatorStr);
                    } else {
                        System.out.println("stop 3.6  ~~");
                        throw new XMLException("element name _record need XML content support");
                    }
                } else {
                    System.out.println("stop 3.7  ~~");
                    throw new XMLException("the locator str don't match");
                }
            } else {
                result = super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public String getElementContent(AndroidDriver driver, String eleName) throws Exception { // elename may contains common 4 title, record 4 record under title
        try {
            if (!eleName.toLowerCase().contains("language") && !eleName.toLowerCase().contains("skill") && !eleName.toLowerCase().contains("link")) {
                System.out.println("    +++ ~~~ The getElementContent in bANDPageProfileEdit has been called ~~~ +++");
                return super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
            } else {
                System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageProfileEdit has been called ~~~ +++");
                String tempLocatorStr = null;
                String tempType = null;
                String result = null;
                String commonType = null;
                String commonLocatorStr = null;
                String recordType = null;
                String recordLocatorStr = null;
                try {

                    for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                        ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                        if (eleName.equals(tempEle.getElementName())) {
                            tempLocatorStr = tempEle.getLocatorStr();
                            tempType = tempEle.getType();
                        }
                        if (tempEle.getElementName().toLowerCase().contains("commonby")) {
                            commonLocatorStr = tempEle.getLocatorStr();
                            commonType = tempEle.getType();
                        }
                        if (tempEle.getElementName().toLowerCase().contains("recordby")) {
                            recordLocatorStr = tempEle.getLocatorStr();
                            recordType = tempEle.getType();
                        }
                    }
                    loadingJudgement(driver, this.eleContentMap);
                    if (!eleName.toLowerCase().contains("_record")) { // for title
                        if (commonLocatorStr != null) {  //
                            if ((commonType.equals("xpath")) && (tempType.equals("xpath") && tempLocatorStr.contains(":REXindex:"))) {
                                result = super.btnOperationSearch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                                if (result.split("::").length > 1) {
                                    index4XPATH = Integer.parseInt(result.split("::")[1]);
                                    result = new String(result.split("::")[0]);
                                } else if (result.endsWith("::")) {
                                    result = new String(result.split("::")[0]);
                                } else {
                                    //
                                }
                            } else if ((commonType.equals("id")) && (tempType.equals("xpath") && tempLocatorStr.contains(":REXindex:"))) {
                                result = super.btnOperationSearch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                                if (result.split("::").length > 1) {
                                    index4XPATH = Integer.parseInt(result.split("::")[1]);
                                    result = new String(result.split("::")[0]);
                                } else if (result.endsWith("::")) {
                                    result = new String(result.split("::")[0]);
                                } else {
                                    //
                                }
                            } else {
                                result = super.getElementContent(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, 0);
                            }
                        } else {
                            throw new XMLException("the locator str don't match");
                        }
                    } else if (eleName.toLowerCase().contains("_recordcontent")) {// for record content
                        String name4title = eleName.split("_")[0];
                        String titleTempLocatorStr = null;
                        String titleTempType = null;
                        for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                            ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                            if (name4title.equals(tempEle.getElementName())) {
                                titleTempLocatorStr = tempEle.getLocatorStr();
                                titleTempType = tempEle.getType();
                                break;
                            }
                        }
                        if (commonLocatorStr != null) {  //
                            if ((commonType.equals("xpath")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                                index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                            } else if ((commonType.equals("id")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                                index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                            }
                        }
                        String tLocatorStr = null;
                        String tType = null;
                        for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                            ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                            if (eleName.equals(tempEle.getElementName())) {
                                tLocatorStr = tempEle.getLocatorStr();
                                tType = tempEle.getType();
                                break;
                            }
                        }
                        if (recordLocatorStr != null) {
                            if ((tType.equals("xpath")) && ((tLocatorStr.contains(":REXindex:")) || (tLocatorStr.contains(":REXrecord:")))) {
                                if (index4XPATH == 0) {
                                    throw new REXException("failed to find the target title index");
                                } else {
                                    result = super.getStaticElementContent(driver, tLocatorStr, this.byMap, this.eleContentMap, this.path4Log, index4XPATH);
                                }
                            } else {
                                throw new XMLException("element name _recordContent need XML content support");
                            }
                        } else {
                            throw new XMLException("the locator str don't match");
                        }
                    } else if (eleName.toLowerCase().contains("_record")) {  // for record
                        String name4title = eleName.split("_")[0];
                        String titleTempLocatorStr = null;
                        String titleTempType = null;
                        for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                            ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                            if (name4title.equals(tempEle.getElementName())) {
                                titleTempLocatorStr = tempEle.getLocatorStr();
                                titleTempType = tempEle.getType();
                                break;
                            }
                        }
                        if (commonLocatorStr != null && titleTempLocatorStr != null & titleTempType != null) {  //
                            if ((commonType.equals("xpath")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                                index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                            } else if ((commonType.equals("id")) && (titleTempType.equals("xpath") && titleTempLocatorStr.contains(":REXindex:"))) {
                                index4XPATH = getIndex4TitleIndex(driver, name4title, this.byMap, this.eleContentMap, this.path4Log, this.index4XPATH);
                            }
                        }
                        // find the index of title directly
                        if (recordLocatorStr != null && recordType != null && tempLocatorStr != null && tempType != null) {
                            if ((recordType.equals("xpath")) && ((recordLocatorStr.contains(":REXindex:")) || (recordLocatorStr.contains(":REXrecord:")))) {
                                result = super.getElementContent(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, 0);
                            } else if ((recordType.equals("id")) && ((tempType.contains(":REXindex:")) || (tempLocatorStr.contains(":REXrecord:")))) {
                                result = super.recordSearch(driver, eleName, this.eleContentMap, this.path4Log, index4XPATH, tempType, tempLocatorStr, recordType, recordLocatorStr);
                            } else {
                                throw new XMLException("element name _record need XML content support");
                            }
                        } else {
                            throw new XMLException("the locator str don't match");
                        }
                    } else {
                        result = super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
                return result;
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
    }




    public void btnOperation(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperation in bANDPageProfileEdit has been called ~~~ +++");
        try {
            super.btnOperation(driver, eleName, this.byMap);
        } catch (Exception e) {
            throw e;
        }
    }

    public void textOperation(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperation in bANDPageProfileEdit has been called ~~~ +++");
        try {
            super.textOperation(driver, eleName, para1, this.byMap, this.content.eleDefaultValueMap);
        } catch (Exception e) {
            throw e;
        }
    }


    public String textOperationWithSaveInput(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperationWithSaveInput in bANDPageProfileEdit has been called ~~~ +++");
        try {
            return super.textOperationWithSaveInput(driver, eleName, para1, this.byMap, this.content.eleDefaultValueMap);
        } catch (Exception e) {
            throw e;
        }
    }


    public String dropDownListOperation(AndroidDriver driver, String eleName) throws Exception {  //TODO
        String defaultText = null;
        String dropItemValue = null;
        System.out.println("    +++ ~~~ The dropDownListOperation in bANDPageProfileEdit has been called ~~~ +++");
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
        System.out.println("    +++ ~~~ The dropDownListOperation in bANDPageProfileEdit has been called ~~~ +++");
        System.out.println(" this time we want to choose the " + extPara + " item ");
        try {
            defaultText = super.dropDownListShow(driver, eleName, this.byMap, this.eleContentMap, this.path4Log, this.content.eleDefaultValueMap);
            dropItemValue = super.dropDownItemChooseWithPara(driver, defaultText, this.byMap, this.eleContentMap, this.path4Log, extPara);
            return dropItemValue;
        } catch (Exception e) {
            throw e;
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

            bANDPageProfileEdit x = new bANDPageProfileEdit("/PageXML/AND/bPageProfileEdit.xml", driver, null);
            System.out.println("before defaulf operation");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}


