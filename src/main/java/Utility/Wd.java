package Utility;


import REXSH.REXAUTO.Component.AppAction.BaseAction;
import REXSH.REXAUTO.LocalException.REXException;
import Utility.readProperity;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static REXSH.REXAUTO.Component.Driver.ScreenShot.ScreenShot;

//import static com.sun.tools.doclint.Entity.para;

/**
 * Created by appledev131 on 9/4/16.
 */
public class Wd {

  /*
    //配置 webdriver 并启动 webview 应用。
    try{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("device", "iPhone Simulator");
        desiredCapabilities.setCapability("app", "http://appium.s3.amazonaws.com/WebViewApp6.0.app.zip");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);

        // 切换到最新的web视图
        for (String winHandle : remoteWebDriver.getWindowHandles()) {
            remoteWebDriver.switchTo().window(winHandle);
        }

        //在 guinea-pig 页面用 id 和 元素交互。
        WebElement div = remoteWebDriver.findElement(By.id("i_am_an_id"));
        Assert.assertEquals("I am a div", div.getText()); //验证得到的文本是否正确。
        remoteWebDriver.findElement(By.id("comments")).sendKeys("My comment"); //填写评论。

        //离开 webview，回到原生应用。
        remoteWebDriver.executeScript("mobile: leaveWebView");
        //关闭应用。
        remoteWebDriver.quit();
    }catch(Exception e)
    {

    }
    */
}
