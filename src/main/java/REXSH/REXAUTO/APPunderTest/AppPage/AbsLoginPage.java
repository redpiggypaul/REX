package REXSH.REXAUTO.APPunderTest.AppPage;

import REXSH.REXAUTO.App;
import REXSH.REXAUTO.Component.Operation.AndroidBaseOperation;
import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Operation.IOSBaseOperation;
import REXSH.REXAUTO.LocalException.PageException;
import REXSH.REXAUTO.TC.TestCaseTemplate;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import Utility.Log;
//import sun.jvm.hotspot.debugger.Page;

import static REXSH.REXAUTO.Component.Operation.AppBaseOperation.*;
//import static REXSH.REXAUTO.Component.Page.BasePageContent.getElement;

/**
 * Created by pzhu023 on 4/11/16.
 */
public abstract class AbsLoginPage extends AbsPage {
    protected String fileName;
    //protected String path;
//    static String path = "/PageXML/";   //  file path 4 xml, "/PageXML/AND/" for And, "/PageXML/IOS/"; for IOS
    AppiumDriver driver = null;
    public Log theLogger = new Log(AbsLoginPage.class.getName());
    protected int osType = 0;
    //protected AppBaseOperation theBaseOperation;

    static String path = "/PageXML/";   //  file path 4 xml, "/PageXML/AND/" for And, "/PageXML/IOS/"; for IOS
    protected int defaultTimer = 5000;

    private String[][] operationMap = {{"inputUserID", "uidField"}, {"inputPSW", "pwdField"}, {"clickLoginBTN", "loginBtn"}};

    public AbsLoginPage(String name) throws Exception {
        super(name, 1);
        this.path = path + "AND/";
        this.theBaseOperation = new AppBaseOperation(driver);
    }

    public AbsLoginPage() {
        super();
        path = null;
        theBaseOperation = null;
    }

    public String[] getOpertionSeq() {
        theLogger.info("The getOpertionSeq of " + this.getClass().getSimpleName() + " has been called");
        String[] theSeq = null;
        for (int ind = 0; ind < operationMap.length; ) {
            System.out.println("map : " + operationMap[ind][1]);
            ind++;
        }
        return (new String[]{"uidField", "pwdField", "loginBtn"});
    }


    public String getTPageName() {
        return "loginPage";
    }

    public void AbsLoginPage(String theFile, AppiumDriver theD) {
        theLogger.info("The constructor of " + this.getClass().getSimpleName() + " has been called");
        this.fileName = theFile;
        path = (System.getProperty("user.dir") + fileName).replace("/", File.separator);
        this.driver = theD;
        try {
            theBaseOperation = new AndroidBaseOperation(this.driver, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void AbsLoginPage(String theFile, AppiumDriver theD, int os) {
        theLogger.info("The constructor of " + this.getClass().getSimpleName() + " has been called");
        this.fileName = theFile;
        path = (System.getProperty("user.dir") + fileName).replace("/", File.separator);
        this.driver = theD;
        osType = os;
        try {
            if (osType == 1) {
                theBaseOperation = new AndroidBaseOperation(this.driver, path);
            } else if (osType == 2) {
                theBaseOperation = new IOSBaseOperation(this.driver, path);
            } else {
                throw new PageException("OS Type error in AbsLoginPage !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AbsLoginPage(String theFile, AppiumDriver theD, String pName) {
        theLogger.info("The AbsLoginPage(String theFile, AppiumDriver theD, String pName)  of " + this.getClass().getSimpleName() + " has been called");
        this.fileName = theFile;
        path = (System.getProperty("user.dir") + fileName).replace("/", File.separator);
        //   System.out.println(path);
        this.driver = theD;
    }


    public void typePasswordG(Map<String, AppiumDriver> eleMap, String value) throws Exception {
        theLogger.info("The typePasswordG of " + this.getClass().getSimpleName() + " has been called");
        try {

            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry eleEntry = (Map.Entry) it.next();
                if ((eleEntry.getKey().toString().equalsIgnoreCase("pwdField")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("pwdfield"))) {
                    //               System.out.println(eleEntry.getKey() + ":" + eleEntry.getValue());
                    //              System.out.println("Try the typePasswordG : " + (WebElement) eleEntry.getValue());
                    clearAndTypeString((WebElement) eleEntry.getValue(), value);
                }
            }
        } catch (Exception e) {
            throw new PageException(e.getCause().toString());
        }
    }

    public void typeUserIdG(Map<String, AppiumDriver> eleMap, String value) throws Exception {
        theLogger.info("The typeUserIdG of " + this.getClass().getSimpleName() + " has been called");
        try {
            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry eleEntry = (Map.Entry) it.next();
                if ((eleEntry.getKey().toString().equalsIgnoreCase("uidField")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("uidfield"))) {
                    //           System.out.println("Try the typeUserIdG : " + (WebElement) eleEntry.getValue());
                    clearAndTypeString((WebElement) eleEntry.getValue(), value);
                }
            }
        } catch (Exception e) {
            throw new PageException(e.getCause().toString());
        }
    }

    public void switch2NaviBar(Map<String, AppiumDriver> eleMap, String value) throws Exception {
        theLogger.info("The typeUserIdG of " + this.getClass().getSimpleName() + " has been called");
        try {
            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry eleEntry = (Map.Entry) it.next();
                if ((eleEntry.getKey().toString().equalsIgnoreCase("uidField")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("uidfield"))) {
                    //           System.out.println("Try the typeUserIdG : " + (WebElement) eleEntry.getValue());
                    clearAndTypeString((WebElement) eleEntry.getValue(), value);
                }
            }
        } catch (Exception e) {
            throw new PageException(e.getCause().toString());
        }
    }

    public void submitLogin(Map<String, AppiumDriver> eleMap) throws Exception {
        theLogger.info("The submitLogin of " + this.getClass().getSimpleName() + " has been called");
        try {

            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                System.out.println("Start loop the Map in submitLoginG");
                Map.Entry eleEntry = (Map.Entry) it.next();
                //        System.out.println(eleEntry.getKey() + ":" + eleEntry.getValue());
                if ((eleEntry.getKey().toString().equalsIgnoreCase("loginBtn")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("loginbtn"))) {
                    //            System.out.println("Try the theO click : " + (WebElement) eleEntry.getValue());
                    //           System.out.println("submitLogin has been disabled");
                    //theO.click((WebElement)eleEntry.getValue());
                }
            }
        } catch (Exception e) {
            throw new PageException(e.getCause().toString());
        }
    }

    public void submitLoginG(Map<String, AppiumDriver> eleMap, AppiumDriver theD) throws Exception {
        theLogger.info("The submitLoginG of " + this.getClass().getSimpleName() + " has been called");
        try {
            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                System.out.println("Start loop the Map in submitLoginG");
                Map.Entry eleEntry = (Map.Entry) it.next();
                //     System.out.println(eleEntry.getKey() + ":" + eleEntry.getValue());
                if ((eleEntry.getKey().toString().equalsIgnoreCase("loginBtn")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("loginbtn"))) {
                    //         System.out.println("Try the theO click : " + (WebElement) eleEntry.getValue());
                    //         System.out.println("Try the theO click : " + theD);
                    try {
                        theBaseOperation.click((WebElement) eleEntry.getValue(), theD);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            throw new PageException(e.getCause().toString());
        }
    }



    @Override
    public void switch2NaviBar(AppiumDriver driver) throws PageException
    {
        System.out.println(this.getClass().getName() + "Login Page is isolated from Navigation Bar !");
        throw new PageException(this.getClass().getName() + "Login Page is isolated from Navigation Bar !");
    }

    @Override
    public void switch2HomePage(AppiumDriver driver) throws Exception
    {
        super.switch2HomePage(driver);
    }
}


