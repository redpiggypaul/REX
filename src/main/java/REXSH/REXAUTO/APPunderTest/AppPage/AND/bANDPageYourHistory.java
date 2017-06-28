package REXSH.REXAUTO.APPunderTest.AppPage.AND;

import REXSH.REXAUTO.Component.Page.ANDPageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.Component.PageLoadWait.ANDDriverWait;
import REXSH.REXAUTO.Component.PageLoadWait.ANDExpectedCondition;
import REXSH.REXAUTO.LocalException.PageTitleException;
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
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;


/**
 * Created by appledev131 on 4/11/16.
 */
public class bANDPageYourHistory extends bANDPageTemplate {
    protected String fileName = "bYourHistory.json";
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    private String contentFilePath = "\\PageXML\\And\\History\\";
    public String path4Log = System.getProperty("user.dir");
    protected String targetPagename = "";
    protected String comFileName = null;
    protected ANDPageContent content = null;
    protected HashMap<String, ElementMapping> eleContentMap = null;
    protected HashMap<String, AndroidElement> eleMap = null;
    public HashMap<String, By> byMap = null;
    protected AndroidDriver dr = null;
    protected int osType = 1;
    private static String logSpace4thisPage = " --- bANDPageYourHistory --";
    protected long timeOutInSeconds = Long.parseLong(readProperity.getProValue("pageLoadTimeout"));
    private String subBoxClass = "RoleDetail";
    private String errNoticeWind = "";
    private String confirmNoticeWind = "";
    private String completeNoticeWind = "";
    private String confirmNoticeWindWOT = "";
    private String storeValue4compare = "";

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


    public bANDPageYourHistory(AndroidDriver theD, String p4L) throws Exception {
        super(theD);
        this.dr = theD;
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        String tempName = contentFilePath + fileName;

        content = new ANDPageContent(theD, tempName, osType);
        eleContentMap = content.getContentMap4Base(tempName, osType);
        byMap = content.getByMap(theD, this.eleContentMap);
        loadingJudgement(theD, this.eleContentMap);
        this.path4Log = p4L;
    }

    public bANDPageYourHistory(String theFile, AndroidDriver theD, Log theLogger) throws Exception {
        super(theFile, theD, theLogger);
        if (!autoOSType.toLowerCase().contains("windows")) {
            contentFilePath = new String(contentFilePath.replaceAll("\\\\", File.separator));
            path4Log = new String(path4Log.replaceAll("\\\\", File.separator));
        }
        this.comFileName = contentFilePath + fileName;
        this.dr = theD;
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

    public String value4compare() {
        return this.storeValue4compare;
    }

    public HashMap<String, String> getEleWinMap() {
        return this.content.eleWindowMap;
    }

    public boolean checkFlag4Win() {
        return this.content.flag4EleWindow;
    }

    public HashMap<String, AndroidElement> getAndroidElemenet(AndroidDriver driver) {
        HashMap<String, AndroidElement> theAndEle = null;
        HashMap<String, By> theByMap = getByMap(dr);


        return theAndEle;
    }

    public HashMap<String, ElementMapping> getEleConMap() {
        return this.eleContentMap;
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


    public String textOperationWithSaveInput(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperationWithSaveInput in bANDPageYourHistory has been called ~~~ +++");
        String tempDefaultValue = null;
        WebElement theResult = null;
        String result = null;
        final String localPath = this.path4Log;
        try {
            if (eleName.equals("searchContent")) {
                for (Iterator it = this.byMap.entrySet().iterator(); it.hasNext() && theResult == null; ) {
                    System.out.println("iterator : " + it == null);
                    System.out.println("inMap size : " + this.byMap.size());
                    final Map.Entry tempContentEntry = (Map.Entry) it.next();
                    if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                        ANDDriverWait xWait = new ANDDriverWait(driver, timeOutInSeconds, localPath);
                        theResult = xWait.until(new ANDExpectedCondition<WebElement>() {
                            public WebElement apply(AndroidDriver driver) {
                                WebElement temp = null;
                                try {
                                    temp = findElementWithSearch(driver, (By) tempContentEntry.getValue(), Integer.parseInt(String.valueOf(timeOutInSeconds * 1000)), localPath);
                                } catch (Exception e) {
                                    throw new TimeoutException(e.getMessage());
                                }
                                return temp;
                            }
                        });
                        theResult.click();
                    }
                }
                for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                    ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                    if ("searchContentEdit".equals(tempEle.getElementName())) {
                        tempDefaultValue = tempEle.getDefaultValue();
                        if (tempDefaultValue != null) {
                            result = super.textOperationWithSaveInput(driver, "searchContentEdit", para1, this.byMap, tempDefaultValue);
                        } else {
                            throw new XMLException("some element need to add with the default value");
                        }
                        break;
                    }
                }
                //   result = super.textOperationWithSaveInput(driver, "searchContentEdit", para1, this.byMap, tempDefaultValue);
            } else {
                result = super.textOperationWithSaveInput(driver, eleName, para1, this.byMap, tempDefaultValue);
            }

        } catch (
                Exception e
                ) {
            System.out.println(logSpace4thisPage + "In bANDPageYourHistory . textOperationWithSaveInput , Other Error appear : " + e.getCause());
            e.printStackTrace();
        } finally

        {
            return result;
        }

    }


    public void textOperation(AndroidDriver driver, String eleName, String para1) throws Exception {
        System.out.println("    +++ ~~~ The textOperation in bANDPageYourHistory has been called ~~~ +++");
        String tempDefaultValue = null;
        String result = null;
        try {
            for (Iterator it = this.content.getContentMap().entrySet().iterator(); it.hasNext(); ) {
                Map.Entry tempContentEntry = (Map.Entry) it.next();     //  get the next string , ele map instance
                ElementMapping tempEle = (ElementMapping) tempContentEntry.getValue();  // get the ele instance
                if (eleName.equals(tempEle.getElementName())) {
                    tempDefaultValue = tempEle.getDefaultValue();
                    if (tempDefaultValue != null) {
                        result = super.textOperationWithSaveInput(driver, eleName, para1, this.byMap, tempDefaultValue);
                    } else {
                        throw new XMLException("some element need to add with the default value");
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void btnOperation(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperation in bANDPageYourHistory has been called ~~~ +++");
        try {
            if (eleName.startsWith("role")) {
                super.btnOperationListMatch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
            } else {
                super.btnOperation(driver, eleName, this.byMap);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String btnOperationRoute(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The btnOperationRoute in bANDPageYourHistory has been called ~~~ +++");
        try {
            if (eleName.startsWith("role")) {
                return super.btnOperationListMatch(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
            } else {
                return super.btnOperation(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String getElementContent(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The getElementContent in bANDPageYourHistory has been called ~~~ +++");
        try {
            if (eleName.startsWith("role")) {
                String result = "";

                List<String> tempList = super.getElementContentList(driver, eleName, this.byMap, this.eleContentMap);
                if (tempList.size() != 0) {
                    for (int ind = 0; ind < tempList.size(); ind++) {
                        if (ind == 0) {
                            result = new String(tempList.get(ind));
                        } else {
                            result = new String(result + "_REX_" + tempList.get(ind));
                        }
                    }

                }
                return result;
            } else {
                return super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String sortElementContent(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The getElementContent in bANDPageYourHistory has been called ~~~ +++");
        try {
            if (eleName.startsWith("role")) {
                String result = "";
                List<String> tempList = super.getElementContentListMove(driver, eleName, this.byMap, this.eleContentMap, this.path4Log);
                if (tempList.size() != 0) {
                    for (int ind = 0; ind < tempList.size(); ind++) {
                        if (ind == 0) {
                            result = new String(tempList.get(ind));
                        } else {
                            result = new String(result + "_:REX:_" + tempList.get(ind));
                        }
                    }
                }
                return result;
            } else {
                return super.getElementContent(driver, eleName, this.byMap, this.eleContentMap);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String checkAbnormalElement(AndroidDriver driver, String eleName) throws Exception {
        System.out.println("    +++ ~~~ The checkAbnormalElement in bANDPageYourHistory has been called ~~~ +++");
        try {
            return super.checkAbnormalElement(driver, eleName, this.byMap, this.eleContentMap);
        } catch (Exception e) {
            throw e;
        }
    }


    public String boxSearchTitleContentOperation(AndroidDriver driver, String eleName) throws Exception {
        String result = null;
        System.out.println("    +++ ~~~ The boxSearchTitleContentOperation in bANDPageYourHistory has been called ~~~ +++");
        String eleType = null;
        String eleLocStr = null;
        int targetLength = 0;
        int moveStep = 1;
        try {
            targetLength = super.getFirstNumIntFromStr(getElementContent(driver, "resultCount"));
            moveStep = 1 + targetLength / 5;

            ElementMapping tempCell = this.eleContentMap.get(eleName);
            if (tempCell != null) {
                eleType = tempCell.getType();
                eleLocStr = tempCell.getLocatorStr();
            } else {
                for (Iterator it = this.eleContentMap.entrySet().iterator(); it.hasNext() && eleType == null; ) {
                    final Map.Entry tempContentEntry = (Map.Entry) it.next();
                    if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                        eleType = ((ElementMapping) tempContentEntry.getValue()).getType();
                        eleLocStr = ((ElementMapping) tempContentEntry.getValue()).getLocatorStr();
                    }
                }
            }
            if (eleType.equalsIgnoreCase("id")) {
                result = getSingleElementContentWithBYid(driver, eleLocStr, moveStep, targetLength, "", this.path4Log);
            } else if ((eleType.equalsIgnoreCase("xpath") && eleLocStr.contains(":REX:"))) {
                result = getSingleElementContentWithBYxpath(driver, eleLocStr, moveStep, targetLength, "", this.path4Log);
            } else {
                throw new XMLException(eleName + " in bANDPageYourHistory is incorrect ! ");
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }


    public String boxSearchTitleContentListOperation(AndroidDriver driver, String eleName) throws Exception {
        StringBuilder result = new StringBuilder();
        result.delete(0, result.length());
        ArrayList<String> resultList = new ArrayList<String>();
        System.out.println("    +++ ~~~ The boxSearchTitleContentListOperation in bANDPageYourHistory has been called ~~~ +++");
        String eleType = null;
        String eleLocStr = null;
        final String p4l = this.path4Log;
        int targetLength = 0;
        int moveStep = 1;
        try {
            targetLength = super.getFirstNumIntFromStr(getElementContent(driver, "resultCount"));
            moveStep = 1 + targetLength / 5;

            ElementMapping tempCell = this.eleContentMap.get(eleName);
            if (tempCell != null) {
                eleType = tempCell.getType();
                eleLocStr = tempCell.getLocatorStr();
            } else {
                for (Iterator it = this.eleContentMap.entrySet().iterator(); it.hasNext() && eleType == null; ) {
                    final Map.Entry tempContentEntry = (Map.Entry) it.next();
                    if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                        eleType = ((ElementMapping) tempContentEntry.getValue()).getType();
                        eleLocStr = ((ElementMapping) tempContentEntry.getValue()).getLocatorStr();
                    }
                }
            }
            if (eleType.equalsIgnoreCase("id")) {
                resultList = getListElementContentWithBYid(driver, eleLocStr, moveStep, targetLength, "", this.path4Log);

            } else if ((eleType.equalsIgnoreCase("xpath") && eleLocStr.contains(":REX:"))) {
                resultList = getListElementContentWithBYxpath(driver, eleLocStr, moveStep, targetLength, "", this.path4Log);
            } else {
                throw new XMLException(eleName + " in bANDPageYourHistory is incorrect ! ");
            }
            for (String curr : resultList) {
                if (result.length() != 0) {
                    result.append("_:REX:_").append(curr);
                } else {
                    result.append(curr);
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public String boxSearchTitleContentListOperation(AndroidDriver driver, String eleName, String sample) throws Exception {
        StringBuilder result = new StringBuilder();
        result.delete(0, result.length());
        ArrayList<String> resultList = new ArrayList<String>();
        System.out.println("    +++ ~~~ The boxSearchTitleContentListOperation in bANDPageYourHistory has been called ~~~ +++");
        String eleType = null;
        String eleLocStr = null;
        final String p4l = this.path4Log;
        int targetLength = 0;
        int moveStep = 1;
        try {
            targetLength = super.getFirstNumIntFromStr(getElementContent(driver, "resultCount"));
            moveStep = 1 + targetLength / 5;

            ElementMapping tempCell = this.eleContentMap.get(eleName);
            if (tempCell != null) {
                eleType = tempCell.getType();
                eleLocStr = tempCell.getLocatorStr();
            } else {
                for (Iterator it = this.eleContentMap.entrySet().iterator(); it.hasNext() && eleType == null; ) {
                    final Map.Entry tempContentEntry = (Map.Entry) it.next();
                    if (tempContentEntry.getKey().toString().equalsIgnoreCase(eleName)) {
                        eleType = ((ElementMapping) tempContentEntry.getValue()).getType();
                        eleLocStr = ((ElementMapping) tempContentEntry.getValue()).getLocatorStr();
                    }
                }
            }
            if (eleType.equalsIgnoreCase("id")) {
                resultList = getListElementContentWithBYid(driver, eleLocStr, moveStep, targetLength, sample, this.path4Log);

            } else if ((eleType.equalsIgnoreCase("xpath") && eleLocStr.contains(":REX:"))) {
                resultList = getListElementContentWithBYxpath(driver, eleLocStr, moveStep, targetLength, sample, this.path4Log);
            } else {
                throw new XMLException(eleName + " in bANDPageYourHistory is incorrect ! ");
            }
            for (String curr : resultList) {
                if (result.length() != 0) {
                    result.append("_:REX:_").append(curr);
                } else {
                    result.append(curr);
                }
            }
            return result.toString();
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
                        String result4ab = checkAbnormalElement(theD, "alertTitle");
                        if (result4ab.equals("ready")) {
                            flag = false;
                        } else if (result4ab.equals("someerror")) {
                            flag = true;
                        } else {
                            flag = false;
                        }
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

            bANDPageYourHistory x = new bANDPageYourHistory("/PageXML/AND/loginPage.xml", driver, null);
            System.out.println("before defaulf operation");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}


