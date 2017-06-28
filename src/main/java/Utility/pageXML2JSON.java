package Utility;

import REXSH.REXAUTO.Component.Page.Element.ElementMapping;
import REXSH.REXAUTO.LocalException.XMLException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by appledev131 on 8/29/16.
 */
public class pageXML2JSON {
    static int countFiles = 0;// 声明统计文件个数的变量
    static int countFolders = 0;// 声明统计文件夹的变量

    public static void main(String[] args) throws Exception {

        File folder = new File(new String(System.getProperty("user.dir") + "/PageXML/and/"));// 默认目录
        String keyword = ".xml";
        if (!folder.exists()) {// 如果文件夹不存在
            System.out.println("目录不存在：" + folder.getAbsolutePath());
            return;
        }
        long gstartTime = System.currentTimeMillis();
        File[] result = TextSearchFile.searchFile(folder, keyword);// 调用方法获得文件数组
        System.out.println("^^^^^^    get all files：" + (System.currentTimeMillis() - gstartTime) + " ms    ^^^^^^");
        System.out.println("在 " + folder + " 以及所有子文件时查找对象" + keyword);
        System.out.println("查找了" + countFiles + " 个文件，" + countFolders + " 个文件夹，共找到 " + result.length + " 个符合条件的文件：");
        LinkedHashMap<String, Path> file2path = new LinkedHashMap<String, Path>();
        for (int i = 0; i < result.length; i++) {// 循环显示文件
            File file = result[i];
            file2path.put(file.getName(), Paths.get(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/") + 1)));
       //     System.out.println("~~~~~~ " + (file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/") + 1)) + " ~~~~~~~");
        }

        for (Iterator it = file2path.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry eleEntry = (Map.Entry) it.next();
            String fileName = eleEntry.getKey().toString();
            String contentFilePath = eleEntry.getValue().toString();
            StringBuilder fileNameXML = new StringBuilder(contentFilePath + "/" + fileName);
            System.out.println("~~~~~~ " + fileNameXML + " ~~~~~~~");
            TreeMap<String, ElementMapping> elementsMap = new TreeMap<String, ElementMapping>();
            elementsMap.clear();
            elementsMap = getElementMap(fileNameXML);
            MyJsonReader.createJSON_WebElement(elementsMap, fileName, contentFilePath);
        }
    }


    public  static  TreeMap<String, ElementMapping> getElementMap(StringBuilder fileNameXML) throws Exception
    {
        TreeMap<String, ElementMapping> result = new TreeMap<String, ElementMapping>();
        HashMap<String, ElementMapping> tempMap = new HashMap<String, ElementMapping>();
        result.clear();
        int lineN = 1;
        try {
            File file = new File(fileNameXML.toString());
            if (!file.exists()) {

                throw new IOException("Can't find elements mapping file: " + fileNameXML.toString());

            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();

            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
                        lineN++;
                        try {
                            Element elementObj = (Element) iter.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            String tWindowName = elementObj.attributeValue("triggerWindow");
                            ElementMapping eLocator;
                            if (waitTime != null) {
                                eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text, tWindowName);
                            } else {
                                eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text, tWindowName);
                            }
                            tempMap.put(name, eLocator);
                        } catch (Exception e) {
                            throw new XMLException("The content in Element Line " + lineN + "is incorrect : " + e.getCause());
                        }
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            } else {
                throw new XMLException("The NAME att is missing in the pageName 4 Android");
            }
        } catch (XMLException e) {
            throw new XMLException("Utility.XmlUtils.readANDXMLDocument4Base : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.putAll(tempMap);
        return result;
    }
}
