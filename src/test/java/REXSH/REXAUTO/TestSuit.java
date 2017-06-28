package REXSH.REXAUTO;

import REXSH.REXAUTO.TC.TestCaseTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;
//import com.uitest.Utils.PropUtils;
//import com.uitest.Utils.Utils;

/**
 * Unit test for simple App.
 */
public class TestSuit extends TestCaseTemplate {
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

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

    }


    //@Listeners({AssertionListener.class})
    // public class Test1 {
    @Listeners({REXSH.REXAUTO.Lassert.AssertionListener.class})
    public class TestCase2 {
        @BeforeTest
        public void setUp() {
            System.out.println("*******before********");
        }

        @BeforeMethod
        public void beforeMethod() {
            System.out.println("*******beforeMethod********");
            String nameOFmethod = Thread.currentThread().getStackTrace()[1].getMethodName();
            XLSXwb = new XSSFWorkbook();
            XLSXsheet = XLSXwb.createSheet(nameOFmethod);
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
        }

        @AfterMethod
        public void afterMethod() {
            System.out.println("*******aftermethod********");
            try {
                XLSXwb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test(dataProvider = "UserNameGroup")
        public void t1(String theUserPara, String thePSW, String paraGroup) throws IOException {
            try {
                list4result.add("1#2#3#4#5#6");
            } catch (Exception e) {
                list4result.add("e1#e2#e3#e4#e5#e6");
            } finally {
                String[] templist4r = null;
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
                list4result.clear();
            }
        }

        @Test(dataProvider = "UserNameGroup")
        public void t2(String theUserPara, String thePSW, String paraGroup) {
            System.out.println("*********t2**********");
            System.out.println("theUserPara");
        }

        @BeforeClass
        public void beforeClass() {
            System.out.println("*****beforeClass*****");
        }

        @AfterClass
        public void afterClass() {
            System.out.println("*****afterClass*****");
        }

        @AfterTest
        public void finish() {
            System.out.println("*******finish********");
        }

    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("*******beforeMethod 222 ********");
    }


    @DataProvider
    public static Object[][] UserNameGroup() {
        return new Object[][]{
                {"648412480@qq.com", "Pwcwelcome1", "test"},
                {"sylar.wan@hotmail.com", "Pwcwelcome1", "test"},
        };
    }


    @Test(dataProvider = "UserNameGroup")
    public void sampleLogin(String theUserPara, String thePSW, String tRoleName) throws Exception {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        try {

        } catch (Exception e) {
            System.out.println("++++++++++++++++" + e.getCause() + "++++++++++++++++");
            e.printStackTrace();
            assert false;
        } finally {

        }


    }
    //  }
}
/*
    theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationNameOnly", "publication_profileSaveback2Profile", "addPublicationNameOnlyCheck"}, para4Aaction);
    theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationURLOnly", "publication_profileSaveback2Profile", "addPublicationURLOnlyCheck"}, para4Aaction);
    theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationDescOnly", "publication_profileSaveback2Profile", "addPublicationDescOnlyCheck"}, para4Aaction);
    theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationFocusTitle", "publication_profileSaveback2Profile", "addPublicationFocusTitleCheck"}, para4Aaction);
        theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationFocusName", "publication_profileSaveback2Profile", "addPublicationFocusNameCheck"}, para4Aaction)

            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationFocusURL", "publication_profileSaveback2Profile", "addPublicationFocusURLCheck"}, para4Aaction);

            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublicationFocusDesc", "publication_profileSaveback2Profile", "addPublicationFocusDescOnly"}, para4Aaction);
            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublication_CheckAll_Focus_Title", "publication_profileSaveback2Profile", "addPublication_CheckAll_Focus_Title_Check"}, para4Aaction);
            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublication_CheckAll_Focus_Desc", "publication_profileSaveback2Profile", "addPublication_CheckAll_Focus_Desc_Check"}, para4Aaction);
            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublication_CheckAll_Focus_Name", "publication_profileSaveback2Profile", "addPublication_CheckAll_Focus_Name_Check"}, para4Aaction);
            theAction.comOperationInAND(driver, new String[]{"login", "switch2Profile", "clearProfile", "addPublication_CheckAll_Focus_URL", "publication_profileSaveback2Profile", "addPublication_CheckAll_Focus_URL_Check"}, para4Aaction);
 */



