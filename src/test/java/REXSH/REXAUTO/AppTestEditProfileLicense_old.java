package REXSH.REXAUTO;

import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.readProperity;
import io.appium.java_client.android.AndroidDriver;
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


/**
 * Unit test for simple App.
 */
public class AppTestEditProfileLicense_old {
    private AndroidDriver driver;
    String autoOSType = readProperity.getProValue("autoRunningOSType");
    String extResumeAccount = readProperity.getProValue("resumeAccount");
    String extResumeAccountPSW = readProperity.getProValue("resumeAccountPSW");
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
    String[] actionSeq_OnlyName = new String[]{"login", "switch2Profile", "clearProfile", "addLicenseOnly_Name", "license_profileSaveback2Profile", "addLicenseOnly_Name_Check"};
    String[] actionSeq_OnlyNumber = new String[]{"login", "switch2Profile", "clearProfile", "addLicenseOnly_Number", "license_profileSaveback2Profile", "addLicenseOnly_Number_Check"};
    String[] actionSeq_OnlyDate = new String[]{"login", "switch2Profile", "clearProfile", "addLicenseOnly_Date", "license_profileSaveback2Profile", "addLicenseOnly_Date_Check"};
    String[] actionSeq_FocusName = new String[]{"login", "switch2Profile", "clearProfile", "addLicenseFocus_Name", "license_profileSaveback2Profile", "addLicenseFocus_Name_Check"};
    String[] actionSeq_FocusNumber = new String[]{"login", "switch2Profile", "clearProfile", "addLicenseFocus_Number", "license_profileSaveback2Profile", "addLicenseFocus_Number_Check"};
    String[] actionSeq_All_FocusName = new String[]{"login", "switch2Profile", "clearProfile", "addLicense_CheckAll_Focus_Name", "license_profileSaveback2Profile", "addLicense_CheckAll_Focus_Name_Check"};
    String[] actionSeq_All_FocusNumber = new String[]{"login", "switch2Profile", "clearProfile", "addLicense_CheckAll_Focus_Number", "license_profileSaveback2Profile", "addLicense_CheckAll_Focus_Number_Check"};

    @DataProvider
    public  Object[][] profileEditLicense() {
        return new Object[][]{
                {this.extResumeAccount, this.extResumeAccountPSW,  "abc", "regular", ""},
                {this.extResumeAccount, this.extResumeAccountPSW,  "12313131312", "regular", ""},

        };
    }

    @DataProvider
    public  Object[][] profileEditLicenseAll() {
        return new Object[][]{
                {this.extResumeAccount, this.extResumeAccountPSW, "abc::sample1", "regular", ""},
                {this.extResumeAccount, this.extResumeAccountPSW,  "12313131312::nnnn", "regular", ""},

        };
    }

    public AppTestEditProfileLicense_old() throws FileNotFoundException {
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        File app = null;
        if (!autoOSType.toLowerCase().contains("windows")) {
            app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
        } else {
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
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("platform", "22");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        pfile.mkdir();

    }

    public String formatResult4XLSX(String nameOfMethod, String preRoleName, String targetResult, String nameExp, String intime) throws Exception {
        String result = null;
        String shortM = null;
        String time4xlsx = intime;
        result = (nameOfMethod + "#" + time4xlsx + "#" + preRoleName + "#" + targetResult + "#" + "End with " + "#" + "Exception");
        return result;
    }

    public void recover() throws Exception {
        driver.closeApp();
        driver.quit();
        Thread.sleep(5000);
        File app = null;
        if (!autoOSType.toLowerCase().contains("windows")) {
            app = new File(("\\Users\\appledev131\\Downloads").replaceAll("\\\\", File.separator), "app-debug.apk");
        } else {
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
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("platform", "22");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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

    public static String filter4space(String s) {
        int i = s.length();
        int j = 0;
        int k = 0;
        char[] arrayOfChar = s.toCharArray();
        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
            ++j;
        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
            --i;
        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);
    }


    public static String[] preapareActionParameter(String[] a, String[] b) {
        String[] target = new String[a.length + b.length];
        System.arraycopy(a, 0, target, 0, a.length);
        System.arraycopy(b, 0, target, a.length, b.length);
        for (int ind = 0; ind < target.length; ind++) {
            System.out.println("###" + target[ind]);
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
                if (!sampleResult.equals("teststart")) {
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
                                sampleResult = sampleResult.substring(sampleResult.indexOf(singlePartResult[dind]), sampleResult.length() - 1);
                                System.out.println(sampleResult);
                            }
                        }
                    }
                }
            } else if (exceptedResult.equalsIgnoreCase("regular")) {
                if (sampleResult.equals("teststart")) {
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

    @Test(dataProvider = "profileEditLicense")
    public void addLicenseOnlySchool(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_OnlyName, para4Action);
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
        } catch (Exception e) {
            date = new Date();
            time = format.format(date);
            String expName = e.getClass().getSimpleName();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ " + expName + " ~~~~~~~~~~~~~~~~~~~~~~~~~~");
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
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

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        theFileOutStream.close();
        driver.closeApp();
        driver.quit();
    }


    @Test(dataProvider = "profileEditLicense")
    public void addLicenseOnlyNumber(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_OnlyNumber, para4Action);
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
            e.printStackTrace();
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

    @Test(dataProvider = "profileEditLicense")
    public void addLicenseOnlyDate(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_OnlyDate, para4Action);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
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


    @Test(dataProvider = "profileEditEducationAll")
    public void addLicenseFocusSchool(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_FocusName, para4Action);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
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


    @Test(dataProvider = "profileEditLicenseAll")
    public void addLicenseFocusNumber(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_FocusNumber, para4Action);
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
            e.printStackTrace();
            ScreenShot(driver, nameOfMethod + time + expName + ".png", cMethodPath);
            String newRow4Cell = formatResult4XLSX(nameOfMethod, preRoleName, targetResult, expName, time);
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

    @Test(dataProvider = "profileEditLicenseAll")
    public void addLicenseAllFocusName(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_All_FocusName, para4Action);
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
            e.printStackTrace();
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

    @Test(dataProvider = "profileEditLicenseAll")
    public void addLicenseAllFocusNumber(String theUserPara, String thePSW, String paraGroup, String targetResult, String expectResult) throws Exception {
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
            BaseAction theAction = new BaseAction(driver, null, 1, para4Action, cMethodPath, expectResult);
            theAction.comOperationInAND(driver, actionSeq_All_FocusNumber, para4Action);
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
            e.printStackTrace();
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



