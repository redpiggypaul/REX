package REXSH.REXAUTO.APPunderTest.AppPage;

import REXSH.REXAUTO.App;
import REXSH.REXAUTO.Component.Operation.AndroidBaseOperation;
import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Operation.IOSBaseOperation;
import REXSH.REXAUTO.Component.Page.ANDBasePageContent;
import REXSH.REXAUTO.Component.Page.AppBasePageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.LocalException.PageException;
import Utility.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static REXSH.REXAUTO.Component.Operation.AppBaseOperation.clearAndTypeString;
//import static REXSH.REXAUTO.Component.Page.BasePageContent.getElement;

/**
 * Created by appledev131 on 4/11/16.
 */
public abstract class AbsHomePage extends AbsPage {
    protected String fileName;
    static String path = null;
    AppiumDriver driver = null;
    public Log theLogger = new Log(AbsHomePage.class.getName());
    protected int osType = 0;
    protected AppBaseOperation theBaseOperation = null;
    private String[][] operationMap = {{"NaviBarBtn", "search_view_layout"}};

    public AbsHomePage(String name) throws Exception {

        super(name, 1);
        System.out.println("AbsHomePage(String name)  call super(name,default)");
        this.path = path + "AND/";
        this.theBaseOperation = new AppBaseOperation(driver);
    }

    public AbsHomePage(AppiumDriver driver) throws Exception {
        System.out.println("AbsHomePage(String name)  call super(name,default)");
    }

    public AbsHomePage(String name, int os) throws Exception {
        super(name, 1);
        System.out.println("AbsHomePage(String name, int os)  call super(name,1)");

        this.theBaseOperation = new AppBaseOperation(driver);
        fileName = name;
        if (1 == os) {
            this.path = path + "AND/";
        }
        else if (2 == os) {
            this.path = path + "IOS/";
        }
        else{

        }
    }

    protected AbsHomePage() {
    }


    public String[] getOpertionSeq() {
        theLogger.info("The getOpertionSeq of " + this.getClass().getSimpleName() + " has been called");
        System.out.println("The getOpertionSeq of " + this.getClass().getSimpleName() + " has been called");
        String[] theSeq = null;
        for (int ind = 0; ind < operationMap.length; ) {
            System.out.println("map : " + operationMap[ind][1]);
            ind++;
        }
        return (new String[]{"NaviBarBtn"});
    }


    public String getTPageName() {
        return "loginPage";
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

    public void switch2HomePage(AppiumDriver driver) throws Exception {
        Thread.sleep(1000);
    }

    public void switch2NaviBar(AppiumDriver driver) throws PageException
    {
    }

    public void typePasswordG(Map<String, ElementMapping> eleMap, String value) throws Exception {
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

    public void typeUserIdG(Map<String, ElementMapping> eleMap, String value) throws Exception {
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

    public void submitLogin(Map<String, ElementMapping> eleMap) throws Exception {
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


    public void switch2NaviBar(Map<String, ElementMapping> eleMap, AppiumDriver theD) throws Exception {
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

    public void toNaviBar(HashMap<String, WebElement>  eleMap, AppiumDriver theD) throws Exception {
        theLogger.info("The toNaviBar of " + this.getClass().getSimpleName() + " has been called");
        try {
            for (Iterator it = eleMap.entrySet().iterator(); it.hasNext(); ) {
                System.out.println("Start loop the Map in toNaviBarG");
                Map.Entry eleEntry = (Map.Entry) it.next();
                     System.out.println(eleEntry.getKey() + ":" + eleEntry.getValue());
                if ((eleEntry.getKey().toString().equalsIgnoreCase("NaviBarBtn")) ||
                        (eleEntry.getKey().toString().toLowerCase().contains("NaviBarBtn"))) {
                             System.out.println("Try the theO click : " + (WebElement) eleEntry.getValue());
                             System.out.println("Try the theO click : " + theD);
                    try {
                        System.out.println("theBaseOperation is empty ? " + theBaseOperation);
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


}


