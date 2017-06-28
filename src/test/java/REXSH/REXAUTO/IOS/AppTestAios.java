package REXSH.REXAUTO.IOS;

import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.readProperity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;
import static org.openqa.selenium.Keys.ENTER;


/**
 * Unit test for simple App.
 */
public class AppTestAios {
    private IOSDriver driver;

    String autoOSType = readProperity.getProValue("autoRunningOSType");

    String extResumeAccount = readProperity.getProValue("resumeAccount");
    String extResumeAccountPSW = readProperity.getProValue("resumeAccountPSW");
    String mobileOS = readProperity.getProValue("targetMobileOS");
    String TEAccount = readProperity.getProValue("TEAccount");
    String TEAccountPSW = readProperity.getProValue("TEAccountPSW");

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

    String[] testStepListMil = new String[]{"login", "switch2Profile", "clearProfile", "addMilitaryService_CheckAll_Focus_Rank", "military_profileSaveback2Profile", "addMilitaryService_CheckAll_Focus_Rank_Check"};

    String[] testStepList_OT = new String[]{"login", "switch2Profile", "editPreferencecOnly_Overtime", "preference_profileSaveback2Profile", "editPreferencecOnly_Overtime_Check"};
    String[] testStepList_language = new String[]{"login", "switch2Profile", "clearProfile", "addLanguageOnly", "language_profileSaveback2Profile", "addLanguageOnly_Check"};
    String[] testStepList_educatio = new String[]{"login", "switch2Profile", "clearProfile", "addEducation_CheckAll_Focus_School", "education_profileSaveback2Profile", "addEducation_CheckAll_Focus_School_Check"};
    String[] TestStepList_rolefilte = new String[]{"login", "switch2SearchRole", "Role_Filte_test_skill"};
    String[] TestStepList_2TE = new String[]{"login", "switch2TE", "switch2TE_check"};
    String[] TestStepList_2Expense = new String[]{"login", "switch2Expense", "addExpense", "addExpenseEntry", "addExpenseEntry_Check"};
    String[] TestStepList_addMoreExpense = new String[]{"login", "addMoreExpense", "addExpense", "addExpenseEntry", "addExpenseEntry_Check"};

    String[] TestStepList_RoleFilter_test = new String[]{"login", "switch2SearchRole", "Role_Filte_test_travel", "Role_Filte_test_travel_check"};
    String[] testStepList_education = new String[]{"login", "switch2Profile", "clearProfile", "addEducation_CheckAll_noDate_r", "education_profileSaveback2Profile", "addEducation_CheckAll_noDate_Check_r"};

    String[] testStepList_education_nn = new String[]{"login", "switch2Profile", "addEducation_CheckAll_noDate_Check_r"};
    String[] TestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw", "AccountSettingLogout"};
    String[] TestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount"};
    String[] actionSeq_Sample = new String[]{"login", "switch2Profile", "clearProfile", "addLanguageOnly", "language_profileSaveback2Profile", "addLanguageOnly_Check"};
    String[] actionSeq_All_FocusIssuer = new String[]{"login", "switch2Profile", "clearProfile", "addAward_CheckAll_Focus_Issuer", "award_profileSaveback2Profile", "addAward_CheckAll_Focus_Issuer_Check"};
    String[] actionSeq_All_WORKEXP = new String[]{"login", "switch2Profile", "clearProfile", "addWorkExp_CheckAll_Focus_Company", "work_profileSaveback2Profile", "addWorkExp_CheckAll_Focus_Company_Check"};

    String[] actionSeq_All_License = new String[]{"login", "switch2Profile", "clearProfile", "addLicense_CheckAll_Focus_Number", "license_profileSaveback2Profile", "addLicense_CheckAll_Focus_Number_Check"};
    String[] actionSeq_All_Patent = new String[]{"login", "switch2Profile", "clearProfile", "addPatentFocus_Number", "patent_profileSaveback2Profile", "addPatentFocus_Number_Check"};
    String[] actionSeq_All_FocusPosition = new String[]{"login", "switch2Profile", "clearProfile", "addProAffiliation_CheckAll_Focus_Position", "proAffiliation_profileSaveback2Profile", "addProAffiliation_CheckAll_Focus_Position_Check"};
    String[] actionSeq_Military = new String[]{"login", "switch2Profile", "clearProfile", "addMilitaryService_CheckAll_Focus_Rank", "military_profileSaveback2Profile", "addMilitaryService_CheckAll_Focus_Rank_Check"};
    String[] TestStepList_RoleFilter_travel = new String[]{"login", "switch2SearchRole", "Role_Filte_test_travel", "Role_Filte_test_travel_check"};
    String[] TestStepList_RoleFilter_travelonly = new String[]{"login", "switch2SearchRole", "Role_Filte_onlytravel", "Role_Filte_test_travel_check"};
    String[] TestStepList_RoleFilter_LocationName = new String[]{"login", "switch2SearchRole", "Role_Filte_LocationName", "Role_Filte_LocationName_check"};
    String[] TestStepList_RoleFilter_sortDate = new String[]{"login", "switch2SearchRole", "Role_Filte_test_sortBy", "Role_Filte_test_sortDate_check"};

    String[] TestStepList_RoleFilter_sortDuration = new String[]{"login", "switch2SearchRole", "Role_Filte_test_sortBy", "Role_Filte_test_sortDuration_check"};
    String[] TestStepList_RoleFilter_Bookmarkno = new String[]{"login", "switch2SearchRole", "Role_Filte_Bookmark_EmptyResult"};
    String[] TestStepList_RoleFilter_Sharedno = new String[]{"login", "switch2SearchRole", "Role_Filte_Share_EmptyResult"};
    String[] TestStepList_RoleFilter_Interestedno = new String[]{"login", "switch2SearchRole", "Role_Filte_Interest_EmptyResult"};

    @DataProvider
    public Object[][] emptyResult() {
        return new Object[][]{
                {this.TEAccount, this.TEAccountPSW, "", "no result found", ""},
        };
    }

    @DataProvider
    public static Object[][] accountPSW() {
        return new Object[][]{
                {"REXexceed01@mailinator.com", "Pwcwelcome1", "Pwcwelcome1::Pwcwelcome2::Pwcwelcome2", "regular", ""},
                {"REXexceed01@mailinator.com", "Pwcwelcome2", "Pwcwelcome2::Pwcwelcome1::Pwcwelcome1", "regular", ""},

        };
    }

    @DataProvider
    public static Object[][] accountDEL() {
        return new Object[][]{
                {"paulzhu002@grr.la", "Pwcwelcome1", "", "regular", "Your request to remove your account from Talent Exchange cannot occur because"},
        };
    }

    @DataProvider
    public static Object[][] profileRegist() {
        return new Object[][]{
                {"paulzhu001@grr.la", "Pwcwelcome1", "Tax", "ignore", ""},
        };
    }

    @DataProvider
    public static Object[][] profileEditM() {
        return new Object[][]{
                {"paulzhu002@grr.la", "Pwcwelcome1", "eng::20_80::oracle", "regular", ""},
                {"paulzhu001@grr.la", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"paulzhu001@grr.la", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"paulzhu001@grr.la", "Pwcwelcome1", "20_80::oracle", "no result found", ""},
                {"paulzhu001@grr.la", "Pwcwelcome1", "no", "I am not willing to work more than a standard 40 hour work week.", ""},
        };
    }

    @DataProvider
    public static Object[][] profileEditMu() {
        return new Object[][]{
                {"paulzhu001@grr.la", "Pwcwelcome1", "20_80::oracle", "regular", ""},

                {"paulzhu001@grr.la", "Pwcwelcome1", "00883::purpose123asd::task342043::TAXI::description::341", "regular"},
                //  {"648412480@qq.com", "Pwcwelcome1", "JAPANESE::asd123", "regular"},
                {"paulzhu001@grr.la", "Pwcwelcome1", "00883::205::SUN::23.5::SUN", "regular"},
                //    {"iris001@grr.la",   "Pwcwelcome1", "no", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "abc::sampled::sample::masters::C.M.A", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "space::##Dac 43%", "regular"},
                {"648412480@qq.com", "Pwcwelcome1", "java::<r a=\"asd\" />saugf43", "regular"},
        };
    }


    @DataProvider
    public Object[][] profileEditPatent() {
        return new Object[][]{
                //      {"paul001@grr.la", "Pwcwelcome1", "Japanese", "regular", ""},
                //  {this.TEAccount, this.TEAccountPSW, "Duration", "regular", ""},
                //  {this.TEAccount, this.TEAccountPSW, "Start Date", "regular", ""},
                //  {this.TEAccount, this.TEAccountPSW, "new york,ny", "regular", ""},
                {this.TEAccount, this.TEAccountPSW, "20_80", "regular", ""},
                //   {this.TEAccount, this.TEAccountPSW, "20_80::oracle", "regular", ""},
                //  {this.TEAccount, this.TEAccountPSW, "006::205::SUN::3.5::SUN", "regular", ""},
                //   {this.TEAccount, this.TEAccountPSW, "s1::air force", "regular", ""},
                //   {"paul001@grr.la", "Pwcwelcome1", "s1::s2::s3::Other::Other", "regular", ""},
                //   {"648412480@qq.com", "Pwcwelcome1", "JAPANESE::asd123", "regular", ""},
                //   {"648412480@qq.com", "Pwcwelcome1", "space::##Dac 43%", "regular", ""},
                //    {"648412480@qq.com", "Pwcwelcome1", "java::<r a=\"asd\" />saugf43", "regular", ""},
        };
    }


    public AppTestAios() throws FileNotFoundException {
    }


    @BeforeClass
    public void setUp() throws Exception {
        File app = null;
        if (!autoOSType.toLowerCase().contains("windows")) {
            app = new File(("\\\\Users\\\\appledev131\\\\Downloads\\\\").replaceAll("\\\\", File.separator), "REX.app");
            //   /Users/appledev131/Library/Developer/Xcode/DerivedData/REX-bbwgxqprkuseurehlqknegagqtmh/Build/Products/Debug-iphonesimulator/REX.app
        } else {
            app = new File("C:\\Paul", "REX.app");
        }
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        //   capabilities.setCapability("deviceName", "Rex iphone6p");
        // capabilities.setCapability("udid", "60eef3f286cf3962ffb68e661cfbc21ebe67bf10");
        capabilities.setCapability("BundleID", "com.pwc.REX");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //   capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        //   capabilities.setCapability("app-package", "com.pwc.talentexchange");
        //   capabilities.setCapability("app-activity", ".activity.BaseActivity");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // DesiredCapabilities capabilities = new DesiredCapabilities();
        //   capabilities.setCapability("deviceName", ("iPhone 6" + " "));
        //  capabilities.setCapability("deviceName", ("iPad 2"));
        capabilities.setCapability("deviceName", ("iPhone 6"));
        capabilities.setCapability("platformVersion", "8.4");
        capabilities.setCapability("platformName", "iOS");
     //   capabilities.setCapability("orientation", "LANDSCAPE");
        //   capabilities.setCapability("sendKeyStrategy", "oneByOne");
        //   capabilities.setCapability("app", "REX.app");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

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
        } catch (Exception e) {
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
            app = new File(("\\\\Users\\\\appledev131\\\\Downloads\\\\").replaceAll("\\\\", File.separator), "REX.app");
        } else {
            app = new File("C:\\Paul", "REX.app");
        }
        System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        //   capabilities.setCapability("udid", "60eef3f286cf3962ffb68e661cfbc21ebe67bf10");
        capabilities.setCapability("BundleID", "com.pwc.REX");
        // capabilities.setCapability("deviceName", ("iPad 2"));
        capabilities.setCapability("deviceName", ("iPhone 6"));
        capabilities.setCapability("platformVersion", "8.4");
        capabilities.setCapability("platformName", "iOS");
    //    capabilities.setCapability("orientation", "LANDSCAPE");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //    capabilities.setCapability("app", "REX.app");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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

    // @Test(dataProvider = "profileEditPatent")
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
            theAction.comOperationInIOS(driver, this.TestStepList_RoleFilter_travelonly, para4Action, mobileOS);   // 2nd para is the action sequence
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

    @Test(dataProvider = "accountPSW")
    public void accountPSW(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            theAction.comOperationInIOS(driver, this.TestStepList_Account_PSW, para4Action, mobileOS);      // 2nd para is the action sequence
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


}



