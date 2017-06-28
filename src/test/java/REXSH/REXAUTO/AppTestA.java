package REXSH.REXAUTO;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.URL;
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
import REXSH.REXAUTO.LocalException.PageTitleException;
import REXSH.REXAUTO.LocalException.REXException;
import REXSH.REXAUTO.LocalException.XMLException;
import Utility.readProperity;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
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

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sun.jna.platform.win32.Guid;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Unit test for simple App.
 */
public class AppTestA {
    private AndroidDriver driver;

    String autoOSType = readProperity.getProValue("autoRunningOSType");
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = format.format(date);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    private String cPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + this.getClass().getSimpleName() + "_" + time.replaceAll(":", "_");

    File pfile = new File(cPath);
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

    String[] testStepList_regist = new String[]{"registtest"};

    String[] testStepListMil = new String[]{"login","switch2Profile", "clearProfile", "addMilitaryService_CheckAll_Focus_Rank", "military_profileSaveback2Profile", "addMilitaryService_CheckAll_Focus_Rank_Check"};

    String[] testStepList_OT = new String[]{"login", "switch2Profile", "editPreferencecOnly_Overtime", "preference_profileSaveback2Profile", "editPreferencecOnly_Overtime_Check"};
    String[] testStepList_language = new String[]{"login", "switch2Profile", "clearProfile", "addLanguageOnly", "language_profileSaveback2Profile", "addLanguageOnly_Check"};
    String[] testStepList_education = new String[]{"login", "switch2Profile", "clearProfile", "addEducation_CheckAll_Focus_School", "education_profileSaveback2Profile", "addEducation_CheckAll_Focus_School_Check"};
    String[] TestStepList_rolefilte = new String[]{"login", "switch2SearchRole", "Role_Filte_test_skill"};
    String[] TestStepList_2TE = new String[]{"login", "switch2TE", "switch2TE_check"};
    String[] TestStepList_2Expense = new String[]{"login", "switch2Expense", "addExpense", "addExpenseEntry","addExpenseEntry_Check"};
    String[] TestStepList_addMoreExpense = new String[]{"login", "addMoreExpense", "addExpense", "addExpenseEntry","addExpenseEntry_Check"};

    String[] TestStepList_RoleFilter_test = new String[]{"login", "switch2SearchRole", "Role_Filte_test_travel","Role_Filte_test_travel_check"};

    String[] TestStepList_RoleFilter_testno = new String[]{"login", "switch2SearchRole", "Role_Filte_test"};

    String[] TestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw"};
    String[] TestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount"};

    String[] testStepListEdu_Wrongdate_present = new String[]{"login","switch2Profile", "clearProfile","addEducation_FillAll_WrongDate_Present","education_profileSaveback2Profile", "addEducation_FillAll_WrongDate_Present_Check"};

    @DataProvider
    public static Object[][] accountPSW() {
        return new Object[][]{
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "Pwcwelcome1::Pwcwelcome2::Pwcwelcome2", "regular", ""},
                {"REXcandy01@mailinator.com", "Pwcwelcome2", "Pwcwelcome2::Pwcwelcome1::Pwcwelcome1", "regular", ""},

        };
    }

    @DataProvider
    public static Object[][] accountDEL() {
        return new Object[][]{
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "", "regular", "Your request to remove your account from Talent Exchange cannot occur because"},
        };
    }

    @DataProvider
    public static Object[][] profileRegist() {
        return new Object[][]{
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "Tax", "ignore", ""},
        };
    }
    @DataProvider
    public static Object[][] profileEditM() {
        return new Object[][]{
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "shco::21::16", "regular", ""},
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "no", "I am not willing to work more than a standard 40 hour work week.", ""},
        };
    }

    @DataProvider
    public static Object[][] profileEditMu() {
        return new Object[][]{
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "20_80::oracle", "regular", ""},

                {"REXcandy01@mailinator.com", "Pwcwelcome1", "00883::purpose123asd::task342043::TAXI::description::341", "regular"},
                //  {"648412480@qq.com", "Pwcwelcome1", "JAPANESE::asd123", "regular"},
                {"REXcandy01@mailinator.com", "Pwcwelcome1", "00883::205::SUN::23.5::SUN", "regular"},
                //    {"iris001@grr.la",   "Pwcwelcome1", "no", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "abc::sampled::sample::masters::C.M.A", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "space::##Dac 43%", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "java::<r a=\"asd\" />saugf43", "regular"},
        };
    }


    @DataProvider
    public static Object[][] profileEditPatent() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "Confidential", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "JAPANESE::asd123", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "space::##Dac 43%", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "java::<r a=\"asd\" />saugf43", "regular"},
        };
    }


    public AppTestA() throws FileNotFoundException {
    }


    @BeforeClass
    public void setUp() throws Exception {
        File app = null;
        if (!autoOSType.toLowerCase().contains("windows")) {
            app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
        }
        else
        {
            app = new File("C:\\Paul", "app-debug.apk");
        }
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));

        capabilities.setCapability("deviceName", "Android Emulator");

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");         capabilities.setCapability("platform","22");

        driver = new AndroidDriver(new URL("http://127.0.0.1:" + this.port + "/wd/hub"), capabilities);
        pfile.mkdir();

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        try {
            System.out.println("*******beforeMethod********");
            String nameOfMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
            XLSXwb = new XSSFWorkbook();
            XLSXsheet = XLSXwb.createSheet("Test Record");
            testMethod4XLSX = null;
            final String[] titles = {
                    "TestCase Name", "Time", "input Para", "Except Result Mode", "Reality Result", "Status"};
            headerRow = XLSXsheet.createRow(0);
            Row row4TCsingle;
            Cell cell4TCsingle;
            ArrayList<String> list4result = new ArrayList<String>();
            FileOutputStream out = null;
            globalRowId = 0;
            rownum = 1;

            // set head row
            headerRow.setHeightInPoints(12.75f);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(titles[i]);
            }
            globalRowId = 1;
            String XLSXfile = cPath + File.separator + "demotest" + "_Test Result.xlsx";
            theFileOutStream = new FileOutputStream(XLSXfile);
            testMethod4XLSX = Thread.currentThread().getStackTrace()[1].getMethodName();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("*******aftermethod********");
        String[] templist4r = null;
        try {
            for (int ind = 0; ind < list4result.size(); ind++) {
                String resultL = list4result.get(ind).toString();
                templist4r = resultL.split("#");
                Row row;
                Cell cell;
                row = XLSXsheet.createRow(rownum + globalRowId);
                for (int index = 0; index < templist4r.length; index++) {
                    cell = row.createCell(index);
                    cell.setCellValue(templist4r[index]);
                }
                rownum++;
            }
            globalRowId = globalRowId + list4result.size();
            XLSXwb.write(theFileOutStream);

            XLSXwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recover() throws Exception {
        driver.closeApp();
        driver.quit();
        Thread.sleep(5000);
        File app = null;
        if (!autoOSType.toLowerCase().contains("windows")) {
            app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
        }
        else
        {
            app = new File("C:\\Paul", "app-debug.apk");
        }
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.pwc.talentexchange");
        capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");         capabilities.setCapability("platform","22");
        driver = new AndroidDriver(new URL("http://127.0.0.1:" + this.port + "/wd/hub"), capabilities);
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


    @DataProvider
    public static Object[][] UserNameGroup() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "addEducation"} //   ,
                //     {"648412480@qq.com", "Pwcwelcome1", "eng", "res"}
                // wanchen2mp@163.com
        };
    }


    @DataProvider
    public static Object[][] profileChooseGroup() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "name::switch2Profile::addSkills", "regular"}, // last para can be regular / "Error;NO RESULT" "err" "part1;part2 split the except err info"
                // rextest3749@sina.com
                // 648412480@qq.com
        };
    }

    @DataProvider
    public static Object[][] profileEditRefereneceCompany() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "abc::sample::sample::sample", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "12313131312::nnnn::uuurl/asdf#$!FSD#$%::ashfk38q89sv ~!@#$%^&*()_+{}:?><,./;'][=-0987654321", "regular"},

        };
    }

    @DataProvider
    public static Object[][] profileEditAward1value() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "abc", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "12313131312d", "regular"},

        };
    }

    @DataProvider
    public static Object[][] profileEditAward() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "abc::sampled::sample::masters::C.M.A", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "12313131312::nnnn::llll::Doctorate::D.A.S", "regular"},
        };
    }

    @DataProvider
    public static Object[][] profileEditLanguage() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "chinese", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "JAPANESE", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "english", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "german", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "korean", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "java", "regular"},
        };
    }

    @DataProvider
    public static Object[][] profileEditSkill() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "c++", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "JAPANESE", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "oracle", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "space", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "data", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "java", "regular"},
        };
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


    public static boolean assertTestResult(String testResult, String exceptedResult) throws REXException {
        System.out.println("~~~~~~ Start to judge the test result! : ");
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
                                sampleResult = sampleResult.substring(sampleResult.indexOf(singlePartResult[dind]), sampleResult.length());  //TODO cut too much, should be length()
                                System.out.println(sampleResult);
                            }
                        }
                    }
                }
            } else if (exceptedResult.equalsIgnoreCase("regular")) {
                if (sampleResult.startsWith("teststart")) {
                    result = true;
                } else if (testResult.contains("err")) {
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

    @Test(dataProvider = "profileEditM")
    public void ndledemotest(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
        String nameOfMethod = Thread.currentThread().getStackTrace()[1].getMethodName();

        globalRowId++;
        String result4XLSX = "Failed";
        date = new Date();
        time = format.format(date);
        String preRoleName = null;
        String[] extPara = paraGroup.split("::");
        if (extPara.length != 0) {
            if (extPara[extPara.length - 1].length() > 10) {//如果长度大于10则截取
                preRoleName = new String(extPara[extPara.length - 1].substring(0, 9));  // for screen
                preRoleName = filter4space(preRoleName);
            } else {
                preRoleName = extPara[extPara.length - 1];
            }
        }
        String[] para4Action = preapareActionParameter(new String[]{theUserPara, thePSW}, extPara);  // for action
      //  String[] para4Action = preapareActionParameter( extPara);
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_");
        // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            theAction.comOperationInAND(driver, this.testStepListEdu_Wrongdate_present, para4Action);   // 2nd para is the action sequence
            Thread.sleep(1000);
            System.out.println("Here is the result for this case : " + theAction.getExeResult());
            date = new Date();
            time = format.format(date);
            boolean boolean4assert = assertTestResult(theAction.getExeResult().toString(), targetResult); // for judge
            if (boolean4assert == true) {
                result4XLSX = "PASS";
            }
            list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + theAction.getExeResult().toString() + "#" + result4XLSX);  // for xlsx
            if (boolean4assert == false)  // judge the result
            {
                assert false;
            }
        } catch (PageTitleException e) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ Single result judgement for PageTitleException ~~~~~~~~~~~~~~~~~~~~~~~~~~");

            boolean boolean4assert = assertTestResult(e.getMessage(), targetResult); // for judge
            if (boolean4assert == true) {
                result4XLSX = "PASS";
            }
            list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + e.getMessage() + "#" + result4XLSX);  // for xlsx
            if (boolean4assert == false)  // judge the result
            {
                assert false;
            }
        } catch (Exception e) {
            date = new Date();
            time = format.format(date);
            String expName = e.getClass().getSimpleName();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ " + expName + " ~~~~~~~~~~~~~~~~~~~~~~~~~~");
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        theFileOutStream.close();
        driver.closeApp();
        driver.quit();
    }

}

/*

    String[] TestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw"};
    String[] TestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount"};
    String[] iosTestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw", "AccountSettingLogout"};
    String[] iosTestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount", "AccountSettingLogout"};

    String[] TestStepList_change_FirstName = new String[]{"login", "changeFirstName", "changeFirstName_Check"};
    String[] TestStepList_change_LastName = new String[]{"login", "changeLastName",  "changeLastName_Check"};
    String[] TestStepList_change_Email = new String[]{"login", "changeEmail",  "changeEmail_Check"};


    @DataProvider
    public Object[][] accountPSW() {
        return new Object[][]{
                {this.extSetAccount, this.extSetAccountPSW, "Pwcwelcome1::Pwcwelcome2::Pwcwelcome2", "regular", ""},
                {this.extSetAccount, this.extSetAccountPSW, "Pwcwelcome2::Pwcwelcome1::Pwcwelcome1", "regular", ""},

        };
    }

    @DataProvider
    public Object[][] accountDEL() {
        return new Object[][]{
                {this.extSetAccount, this.extSetAccountPSW, "", "regular", "Your request to remove your account from Talent Exchange cannot occur because"},
        };
    }

 */

