package REXSH.REXAUTO.APPunderTest.AppPage;

import REXSH.REXAUTO.Component.Operation.AppBaseOperation;
import REXSH.REXAUTO.Component.Page.ANDBasePageContent;
import REXSH.REXAUTO.Component.Page.AppBasePageContent;
import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.LocalException.PageException;
import Utility.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import static Utility.OSMatcher.matchOS;

/**
 * Created by appledev131 on 4/14/16.
 */
public abstract class AbsPage {
    // ~~~~~~~~ Start 4 ENV
    protected String fileName = "";     // file name 4 xml  页面文件名称,不带.XML
  //  protected String path = "/PageXML/";   //  file path 4 xml, "/PageXML/AND/" for And, "/PageXML/IOS/"; for IOS 页面文件路径
    AppiumDriver driver = null;         //  driver   驱动器
    public Log theLogger = new Log(AbsLoginPage.class.getName());   // log4j instance   日志对象
    protected int osType;       //  OS Type flag , 04A , 14I    OS类型符号
    protected int defaultTimer = 3000;  // 默认页面装载等待时间
    // ~~~~~~~~ End   4 ENV
    // ~~~~~~~~ Start 4 content
    public AppBasePageContent pageContent = null;  // get content in xml file  XML 内容
    HashMap<String, ElementMapping> contentMap = null;      //  map 4 name 2 ele att map   页面元素信息集合, 不是元素集合  名称,所有信息
    protected HashMap<String, String> nextPageList = null;    //   map 4 btn 2 other page    页面路由信息集合   (按键)名称,下一页页面名称, 也是下一页文件名称,不带.XML
    // ~~~~~~~~ End   4 content
    // ~~~~~~~~ Start 4 element

    protected HashMap<String, WebElement> eleList = null;     //  map 4 name 2 element  页面元素集合
    // ~~~~~~~~ End   4 element a
    // ~~~~~~~~ Start 4 operation
    protected AppBaseOperation theBaseOperation = null; // operation instance   操作对象, 分and和ios
    protected String[][] operationMap = null; //  map 4 name 2 operation method  操作名称和元素的数组,用于确定执行顺序


    public AbsPage(String name, int OStype) throws Exception {       //  构造函数
        this.fileName = name;       //
        System.out.println("Start 2 get the page Content with XMLDecoder" + fileName);
       // this.osType = setOSType(OStype);
        this.osType = matchOS(OStype);
        System.out.println(osType);
      //  String tempName = fileName;
        System.out.println(fileName);
        pageContent = new AppBasePageContent();

        contentMap = pageContent.XMLdecoder(fileName);  // read the xml file
        System.out.println("start 2 get the page content with getPageEleList with " + fileName );
        eleList = pageContent.getPageEleList(osType, fileName);

        nextPageList = ((AppBasePageContent) pageContent).getNextPageList(fileName + ".xml", this.osType, fileName);
        if (driver != null) {
            theBaseOperation = new AppBaseOperation(driver);
        } else {
            throw new PageException("NULL AppiumDriver driver in AbsPage (String name, String OStype)");
        }
        //  this.theBaseOperation.click(driver, (WebElement)eleList.entrySet());

    }

    private int setOSType(String osType) throws Exception {     //  设置os,  似乎没有意义, 因为已经设计了  Utility下的OSMatcher, 里面的 matchOS() 返回int型指示
        int result = 0;
        try {
            if (osType.equalsIgnoreCase("And") || osType.toLowerCase().contains("and")) {
                result = 1;
            } else if (osType.equalsIgnoreCase("IOS") || osType.toLowerCase().contains("ios")) {
                result = 2;
            } else {
                theLogger.error("set OS Type failed at : " + this.getClass().getSimpleName() + " !!! ");
                throw new PageException("set OS Type failed at : " + this.getClass().getSimpleName() + " !!! ");
            }
            //return result;
        } catch (Exception e) {
            theLogger.error(e.toString());
        }
        return result;
    }

    public AbsPage() {      // 默认构造函数, 其实也无意义
        System.out.println("Meaningless Constructor in AbsPage");
    }

    public void switch2NaviBar(AppiumDriver driver) throws Exception {
        System.out.println(this.getClass().getName() + "Abs Page Obj method should not be called !");
        throw new PageException(this.getClass().getName() + "Abs Page Obj method should not be called !");
    }

    public void switch2HomePage(AppiumDriver driver) throws Exception {
        System.out.println(this.getClass().getName() + "Abs Page Obj method should not be called !");
        throw new PageException(this.getClass().getName() + "Abs Page Obj method should not be called !");
    }

}
