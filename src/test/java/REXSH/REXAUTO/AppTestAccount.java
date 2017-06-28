package REXSH.REXAUTO;

import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.LocalException.PageTitleException;
import Utility.readProperity;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.reflections.vfs.Vfs;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;


/**
 * Unit test for simple App.
 */
public class AppTestAccount extends AppTestTemplate {
    private AppiumDriver driver;
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    String extAccount = readProperity.getProValue("TEAccount");
    String extAccountPSW = readProperity.getProValue("TEAccountPSW");

    String accontFromXls = readProperity.getProValue(this.getClass().getSimpleName() + "_account");
    String accontPSWFromXls = readProperity.getProValue(this.getClass().getSimpleName() + "_accountPSW");

    String mobileOS = readProperity.getProValue("targetMobileOS");
    String deviceORsimulator = readProperity.getProValue("deviceORsimulator");
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = format.format(date);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    StringBuilder cPath = new StringBuilder().append(System.getProperty("user.dir")).append(File.separator).append("logs").append(File.separator).append(mobileOS).append(File.separator).append(this.getClass().getSimpleName()).append("_").append(time.replaceAll(":","_"));
    File pfile = new File(cPath.toString());
  //  private String cPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + mobileOS + File.separator + this.getClass().getSimpleName() + "_" + time.replaceAll(":", "_");
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


    String[] TestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw"};
    String[] TestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount"};
    String[] iosTestStepList_Account_PSW = new String[]{"login", "switch2AccountSetting", "changePsw", "AccountSettingLogout"};
    String[] iosTestStepList_Account_del = new String[]{"login", "switch2AccountSetting", "delAccount", "AccountSettingLogout"};

    String[] TestStepList_change_FirstName = new String[]{"login", "switch2AccountSetting", "changeFirstName", "changeFirstName_Check"};
    String[] TestStepList_change_LastName = new String[]{"login", "switch2AccountSetting", "changeLastName", "changeLastName_Check"};
    String[] TestStepList_change_Email = new String[]{"login", "switch2AccountSetting", "changeEmail", "changeEmail_Check"};

    public AppTestAccount() throws FileNotFoundException {
    }


    @DataProvider
    public Object[][] accountPSW() {
        return new Object[][]{
                {this.accontFromXls, this.accontPSWFromXls, this.accontPSWFromXls + "::Pwcwelcome2::Pwcwelcome2", "regular", ""},
                {this.accontFromXls, "Pwcwelcome2", "Pwcwelcome2::" + this.accontPSWFromXls + "::" + this.accontPSWFromXls, "regular", ""},
        };
    }

    @DataProvider
    public Object[][] accountDEL() {
        return new Object[][]{
                {this.accontFromXls, this.accontPSWFromXls, "", "", "Your request to remove your account from Talent Exchange cannot occur because"},
        };
    }

    @DataProvider
    public Object[][] accountFirstName() {
        return new Object[][]{
                {this.accontFromXls, this.accontPSWFromXls, "try", "regular", ""},
        };
    }

    @DataProvider
    public Object[][] accountLastName() {
        return new Object[][]{
                {this.accontFromXls, this.accontPSWFromXls, "try", "regular", ""},
                {this.accontFromXls, this.accontPSWFromXls, "REXexceed01", "regular", ""},
        };
    }

    @DataProvider
    public Object[][] accountEmail() {
        return new Object[][]{
                {this.accontFromXls, this.accontPSWFromXls, "try@163.com", "regular", "emptyContent"},
        };
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        File app = null;
        if (mobileOS.equalsIgnoreCase("Android")) {
            if (!autoOSType.toLowerCase().contains("windows")) {
                app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
            } else {
                app = new File("C:\\Paul", "app-debug.apk");
            }
        } else if (mobileOS.toLowerCase().startsWith("ios")) {
            app = new File(("\\\\Users\\\\appledev131\\\\Downloads\\\\").replaceAll("\\\\", File.separator), "REX.app");
            System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        }
        if (mobileOS.equalsIgnoreCase("Android")) {
            driver = super.setupAndroid(app, this.port);
        } else if (mobileOS.toLowerCase().equals("ios")) {
            if (deviceORsimulator.equalsIgnoreCase("device")) {
                driver = super.setupIOSdevice(app, this.port);
            } else if (deviceORsimulator.equalsIgnoreCase("simulator")) {
                driver = super.setupIOSsimulator(app, this.port);
            }
        } else if (mobileOS.toLowerCase().contains("ipad")) {
            if (deviceORsimulator.equalsIgnoreCase("device")) {
                driver = super.setupIPADdevice(app, this.port);
            } else if (deviceORsimulator.equalsIgnoreCase("simulator")) {
                driver = super.setupIPADsimulator(app, this.port);
            }
        }
        pfile.mkdir();
    }

    public void recover() throws Exception {
        driver.closeApp();
        driver.quit();
        Thread.sleep(5000);
        File app = null;
        if (mobileOS.equalsIgnoreCase("Android")) {
            if (!autoOSType.toLowerCase().contains("windows")) {
                app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
            } else {
                app = new File("C:\\Paul", "app-debug.apk");
            }
        } else if (mobileOS.toLowerCase().startsWith("ios")) {
            app = new File(("\\\\Users\\\\appledev131\\\\Downloads\\\\").replaceAll("\\\\", File.separator), "REX.app");
            System.out.println("System.getPre + user.dir : " + System.getProperty("user.dir"));
        }
        if (mobileOS.equalsIgnoreCase("Android")) {
            driver = super.recoverAndroid(app, this.port);
        } else if (mobileOS.toLowerCase().equals("ios")) {
            if (deviceORsimulator.equalsIgnoreCase("device")) {
                driver = super.recoverIOSdevice(app, this.port);
            } else if (deviceORsimulator.equalsIgnoreCase("simulator")) {
                driver = super.recoverIOSsimulator(app, this.port);
            }
        } else if (mobileOS.toLowerCase().contains("ipad")) {
            if (deviceORsimulator.equalsIgnoreCase("device")) {
                driver = super.setupIPADdevice(app, this.port);
            } else if (deviceORsimulator.equalsIgnoreCase("simulator")) {
                driver = super.setupIPADsimulator(app, this.port);
            }
        }
    }


    @BeforeMethod
    public void beforeMethod() throws FileNotFoundException {
        System.out.println("*******beforeMethod********");
        String nameOfMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        XLSXwb = new XSSFWorkbook();
        XLSXsheet = XLSXwb.createSheet("Test Result");
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
        String XLSXfile = cPath + File.separator + "Test_Result.xlsx";
        theFileOutStream = new FileOutputStream(XLSXfile);
        testMethod4XLSX = Thread.currentThread().getStackTrace()[1].getMethodName();
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
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_"); // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            //  BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            //  theAction.comOperationInAND(driver, actionSeq_OnlyTitle, para4Action);
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            if (mobileOS.equalsIgnoreCase("Android")) {
                theAction.comOperationInAND((AndroidDriver) driver, this.TestStepList_Account_PSW, para4Action);   // 2nd para is the action sequence
            } else if (mobileOS.startsWith("ios")) {
                theAction.comOperationInIOS((IOSDriver) driver, this.iosTestStepList_Account_PSW, para4Action, mobileOS);    // 2nd para is the action sequence
            }
            Thread.sleep(2000);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = super.formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {

            // list4result.clear();
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }


    @Test(dataProvider = "accountDEL")
    public void accountDEL(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_"); // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            //  BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            //  theAction.comOperationInAND(driver, actionSeq_OnlyTitle, para4Action);
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            if (mobileOS.equalsIgnoreCase("Android")) {
                theAction.comOperationInAND((AndroidDriver) driver, this.TestStepList_Account_del, para4Action);   // 2nd para is the action sequence
            } else if (mobileOS.startsWith("ios")) {
                theAction.comOperationInIOS((IOSDriver) driver, this.iosTestStepList_Account_del, para4Action, mobileOS);     // 2nd para is the action sequence
            }
            Thread.sleep(2000);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = super.formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {

            // list4result.clear();
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }


    @Test(dataProvider = "accountFirstName")
    public void change_FirstName(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_"); // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            //  BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            //  theAction.comOperationInAND(driver, actionSeq_OnlyTitle, para4Action);
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            if (mobileOS.equalsIgnoreCase("Android")) {
                theAction.comOperationInAND((AndroidDriver) driver, this.TestStepList_change_FirstName, para4Action);   // 2nd para is the action sequence
            } else if (mobileOS.startsWith("ios")) {
                theAction.comOperationInIOS((IOSDriver) driver, this.TestStepList_change_FirstName, para4Action, mobileOS);     // 2nd para is the action sequence
            }
            Thread.sleep(2000);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = super.formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {

            // list4result.clear();
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }


    @Test(dataProvider = "accountLastName")
    public void change_LastName(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_"); // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            //  BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            //  theAction.comOperationInAND(driver, actionSeq_OnlyTitle, para4Action);
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            if (mobileOS.equalsIgnoreCase("Android")) {
                theAction.comOperationInAND((AndroidDriver) driver, this.TestStepList_change_LastName, para4Action);   // 2nd para is the action sequence
            } else if (mobileOS.startsWith("ios")) {
                theAction.comOperationInIOS((IOSDriver) driver, this.TestStepList_change_LastName, para4Action, mobileOS);     // 2nd para is the action sequence
            }
            Thread.sleep(2000);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = super.formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {

            // list4result.clear();
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }


    @Test(dataProvider = "accountEmail")
    public void change_Email(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
        String cMethodPath = this.cPath + File.separator + nameOfMethod + "_" + preRoleName + "_" + time.replaceAll(":", "_"); // for screen
        list4result.add(nameOfMethod + "#" + time + "#" + preRoleName + "#" + targetResult + "#" + "JustStart" + "#" + "waiting4result");
        try {
            //  BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            //  theAction.comOperationInAND(driver, actionSeq_OnlyTitle, para4Action);
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);  // 2nd para is for os type, last para is the excepted result str
            if (mobileOS.equalsIgnoreCase("Android")) {
                theAction.comOperationInAND((AndroidDriver) driver, this.TestStepList_change_Email, para4Action);   // 2nd para is the action sequence
            } else if (mobileOS.startsWith("ios")) {
                theAction.comOperationInIOS((IOSDriver) driver, this.TestStepList_change_Email, para4Action, mobileOS);     // 2nd para is the action sequence
            }
            Thread.sleep(2000);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = super.formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
            list4result.add(newRow4Cell);
            e.printStackTrace();
            assert false;
            recover();
        } finally {

            // list4result.clear();
            driver.closeApp();
            Thread.sleep(1000);
            driver.launchApp();
        }
    }

}



