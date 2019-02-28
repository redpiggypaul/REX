/**
 *
 */
package utility;

import Component.Page.Element.ElementMapping;
import Component.Page.Element.operationItem;
import LocalException.XMLException;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.io.File;
import java.util.*;

import static utility.OSMatcher.matchOS;

/**
 * @author lyou009  , Modified by pzhu023
 */
public class XmlUtils {
    private static Logger theLogger;
    private static String logSpace4thisPage = "             ";

    public XmlUtils(Logger l) {
        theLogger = l;
    }

    public static HashMap<String, ElementMapping> readXMLDocumentAll
            (String fileNameXML, Object osType) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        try {
            //System.out.println("in XmlUtils readXMLDoucmentAll(String fileNameXML, Object osType ) *** ");
            //System.out.println("Chcek the file " + fileNameXML);
            File file = new File(System.getProperty("user.dir") + fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            if (1 == matchOS(osType)) {
                elementsMap = readANDXMLDocumentN(fileNameXML);
            } else if (2 == matchOS(osType)) {
                elementsMap = readIOSXMLDocumentN(fileNameXML);
            } else {
                throw new XMLException("Wrong OS id for : " + osType + "NO match XML Decoder Method !!!");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readXMLDocumentAll : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<StringBuilder, operationItem> readActionXMLDocument
            (StringBuilder fileNameXML) throws Exception {
        return readActionXMLDocumentAll(fileNameXML, 0);
    }

    public static HashMap<StringBuilder, operationItem> readActionXMLDocumentAll
            (StringBuilder fileNameXML, Object osType) throws Exception {
        HashMap<StringBuilder, operationItem> opeMap = new HashMap<StringBuilder, operationItem>();
        opeMap.clear();
        int lineN = 1;
        try {
            File file = new File(System.getProperty("user.dir") + fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            //System.out.println("Check the file name in XmlUtils.readxxxAll : " + file);
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        Element elementObj = (Element) it.next();
                        StringBuilder oName = new StringBuilder(elementObj.attributeValue("OperationName"));
                        StringBuilder pName = new StringBuilder(elementObj.attributeValue("PageName"));
                        StringBuilder oType = new StringBuilder(elementObj.attributeValue("OperationType"));
                        StringBuilder eleName = new StringBuilder(elementObj.attributeValue("elementName"));
                        //     String eleType = elementObj.attributeValue("elementType");
                        StringBuilder elePara = new StringBuilder(elementObj.attributeValue("elementParameter"));
                        Integer s = Integer.parseInt(elementObj.attributeValue("Step"));

                        //System.out.println(oName);
                        //System.out.println(s.toString());
                        operationItem eLocator;
                        eLocator = new operationItem(oName, pName, oType, eleName, elePara, s);
                        opeMap.put(new StringBuilder(oName + ":" + s), eLocator);
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readActionXMLDocumentAll : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(opeMap.size());
        return opeMap;
    }

    public static void readActionXMLDoc4Set
            (String fileNameXML, Object osType) throws Exception {
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        int rowNum = getRows(file);
        //System.out.println("LLLLLL Line  : " + rowNum);
        SortedSet<operationItem> parts = new TreeSet<operationItem>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        //System.out.println(logSpace4thisPage + "in try");
        //System.out.println(logSpace4thisPage + "file " + file.toString());
        //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
        if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
            //System.out.println("Pass the name check");
            if (root.getName().equalsIgnoreCase("page")) {
                //System.out.println("Pass the 1st line check");
                for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                    //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                    lineN++;
                    Element elementObj = (Element) it.next();
                    String oName = elementObj.attributeValue("OperationName");
                    String pName = elementObj.attributeValue("PageName");
                    String oType = elementObj.attributeValue("OperationType");
                    String eleName = elementObj.attributeValue("elementName");
                    String eleType = elementObj.attributeValue("elementType");
                    String elePara = elementObj.attributeValue("elementParameter");
                    Integer s = Integer.parseInt(elementObj.attributeValue("Step"));
                    //System.out.println(oName);
                    //System.out.println(s.toString());
                    operationItem eLocator = null;
                    // parts.add(new operationItem("oName3","pName3", "oType3", "eleName", "eleType","elePara3", 3));
                    //System.out.println("Check the eL : " + eLocator.toString() + "::" + eLocator.getElementPara());
                    parts.add(eLocator);
                }
                //System.out.println("size : " + parts.size());
                for (Iterator it = parts.iterator(); it.hasNext(); ) {
                    //System.out.println("check para" + ((operationItem) it.next()).getElementPara());
                }
            } else {
                throw new XMLException("The Root Line is not PAGE!!!");
            }
            //System.out.println("size " + parts.size());
            //System.out.println("first " + parts.first());
            //System.out.println("last " + parts.last());
        }
    }


    public static HashMap<String, ElementMapping> readXMLDocumentNeg
            (String fileNameXML, Object osType) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        HashMap<String, ElementMapping> tMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        tMap.clear();
        try {
            File file = new File(System.getProperty("user.dir") + fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            tMap = readXMLDocumentAll(fileNameXML, osType);
            for (Iterator it = tMap.entrySet().iterator(); it.hasNext(); ) {
                //System.out.println(logSpace4thisPage + "Start loop the Map in readXMLDocumentNeg");
                Map.Entry eleEntry = (Map.Entry) it.next();
                ElementMapping newTobj = (ElementMapping) eleEntry.getValue();
                String modeValue = newTobj.getMode().toString();
                //System.out.println(modeValue);
                if (!(modeValue.equalsIgnoreCase("ready") || modeValue.toLowerCase().contains("ready"))) {
                    //System.out.println(logSpace4thisPage + "put!!!");
                    elementsMap.put((String) eleEntry.getKey(), (ElementMapping) eleEntry.getValue());
                }
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readXMLDocumentNeg : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static int getRows(File file) {
        LineNumberReader lnr = null;
        int num = 0;
        try {
            lnr = new LineNumberReader(new FileReader(file));
            lnr.skip(Long.MAX_VALUE);
            num = lnr.getLineNumber();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ++num;
    }


    public static HashMap<String, ElementMapping> readXMLDocument
            (String fileNameXML, Object osType) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        HashMap<String, ElementMapping> tMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        tMap.clear();
        //System.out.println(logSpace4thisPage + "S XmlU java");
        try {
            File file = new File(System.getProperty("user.dir") + fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            tMap = readXMLDocumentAll(fileNameXML, osType);
            for (Iterator it = tMap.entrySet().iterator(); it.hasNext(); ) {
                //System.out.println(logSpace4thisPage + "Start loop the Map in readXMLDocument");
                Map.Entry eleEntry = (Map.Entry) it.next();
                ElementMapping newTobj = (ElementMapping) eleEntry.getValue();
                String modeValue = newTobj.getMode();
                //System.out.println(logSpace4thisPage + modeValue);
                if ((modeValue.equalsIgnoreCase("ready") || modeValue.toLowerCase().contains("ready"))) {
                    //System.out.println("put!!!");
                    elementsMap.put((String) eleEntry.getKey(), (ElementMapping) eleEntry.getValue());
                }
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readXMLDocument : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<StringBuilder, operationItem> readXMLDocument4Ope
            (StringBuilder fileNameXML, Object osType) throws Exception {
        HashMap<StringBuilder, operationItem> elementsMap = new HashMap<StringBuilder, operationItem>();
        HashMap<StringBuilder, operationItem> tMap = new HashMap<StringBuilder, operationItem>();
        elementsMap.clear();
        tMap.clear();
        try {
            File file = new File(System.getProperty("user.dir") + fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            tMap = readActionXMLDocumentAll(fileNameXML, osType);
            for (Iterator it = tMap.entrySet().iterator(); it.hasNext(); ) {
                //System.out.println(logSpace4thisPage + "Start loop the Map in readXMLDocumentNeg");
                Map.Entry eleEntry = (Map.Entry) it.next();
                ElementMapping newTobj = (ElementMapping) eleEntry.getValue();
                String modeValue = newTobj.getMode().toString();
                //System.out.println(logSpace4thisPage + modeValue);
                if ((modeValue.equalsIgnoreCase("ready") || modeValue.toLowerCase().contains("ready"))) {
                    //System.out.println(logSpace4thisPage + "put!!!");
                    elementsMap.put((StringBuilder) eleEntry.getKey(), (operationItem) eleEntry.getValue());
                }
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readXMLDocument4Ope : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findRouteList(String fileNameXML, Object osType) throws Exception {
        //    HashMap<String, String> elementsMap = new HashMap<String, String>();
        HashMap<String, String> tMap = new HashMap<String, String>();
        tMap.clear();
        //   tMap.clear();

        try {
            File file = new File(fileNameXML);
            if (!file.exists()) {
                throw new IOException("Can't find elements mapping file: " + fileNameXML);
            }
            if (1 == matchOS(osType)) {
                tMap = findANDRouteList(fileNameXML);
            } else if (2 == matchOS(osType)) {
                tMap = findIOSRouteList(fileNameXML);
            } else {
                throw new XMLException("Wrong OS type for findRouteList : ");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.findRouteList : " + e.getMessage());
        }
        return tMap;
    }


    /*

    public static HashMap<String, ElementMapping> readANDXMLDocument
            (String path, String pageName, String para1, String tPageName) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + path);
        //System.out.println(logSpace4thisPage + "The file" + file);
        //System.out.println(logSpace4thisPage + "sss 383 : " + file.exists());
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + path);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();

        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "1st : " + pageName);
            //System.out.println(logSpace4thisPage + "tPageName : " + tPageName);
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + value);

                            ElementMapping eLocator;
                            if (waitTime != null) {
                                eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type);
                            } else {
                                eLocator = new ElementMapping(name, value, type);
                            }
                            elementsMap.put(name, eLocator);
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
            throw new XMLException("utility.XmlUtils.readANDXMLDocument : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }
*/
    public static HashMap<String, ElementMapping> readIOSXMLDocument4X
    (String fileNameXML) throws Exception {
        //System.out.println(logSpace4thisPage + "readIOSXMLDocument4X NOT ready !!!");
        return null;
    }

    public static HashMap<String, ElementMapping> readIOSXMLDocument4Base
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");

                            ElementMapping eLocator;
                            if (waitTime != null) {
                                eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                            } else {
                                eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                            }
                            elementsMap.put(name, eLocator);
                        } catch (Exception e) {
                            throw new XMLException("The content in Element Line " + lineN + "is incorrect : " + e.getCause());
                        }
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            } else {
                throw new XMLException("The NAME att is missing in the pageName 4 IOS");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.readIOSXMLDocument4Base : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, ElementMapping> readANDXMLDocument4X
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        //System.out.println(logSpace4thisPage + "Check file in readANDXMLDocumentN " + fileNameXML);
        //System.out.println(logSpace4thisPage + "Check the userdir in readANDXMLDocN " + System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        //System.out.println(logSpace4thisPage + "The file" + file + " exists? : " + file.exists());
        if (!file.exists()) {
            //System.out.println(logSpace4thisPage + "Can't find elements mapping file: " + fileNameXML);
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + type);
                            //System.out.println(logSpace4thisPage + "### " + value);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            //System.out.println(logSpace4thisPage + "### " + showMode);
                            //System.out.println(logSpace4thisPage + "### " + dValue);
                            //System.out.println(logSpace4thisPage + "### " + text);

                            ElementMapping eLocator;
                            if ((showMode.equalsIgnoreCase("ready") || showMode.toLowerCase().contains("ready"))) {
                                if (waitTime != null) {
                                    eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                                } else {
                                    eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                                }
                                elementsMap.put(name, eLocator);
                            }
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
            throw new XMLException("utility.XmlUtils.readANDXMLDocument4X : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(elementsMap.size());
        return elementsMap;
    }

    public static HashMap<String, ElementMapping> readANDXMLDocument4Base
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            ElementMapping eLocator;
                            if (waitTime != null) {
                                eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                            } else {
                                eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                            }
                            elementsMap.put(name, eLocator);
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
            throw new XMLException("utility.XmlUtils.readANDXMLDocument4Base : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<String, ElementMapping> readANDXMLDoc4loading
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        //System.out.println(logSpace4thisPage + "Check file in readANDXMLDocumentN " + fileNameXML);
        //System.out.println(logSpace4thisPage + "Check the userdir in readANDXMLDocN " + System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        //System.out.println(logSpace4thisPage + "The file" + file + " exists? : " + file.exists());
        if (!file.exists()) {
            //System.out.println(logSpace4thisPage + "Can't find elements mapping file: " + fileNameXML);
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + type);
                            //System.out.println(logSpace4thisPage + "### " + value);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            //System.out.println(logSpace4thisPage + "### " + showMode);
                            //System.out.println(logSpace4thisPage + "### " + dValue);
                            //System.out.println(logSpace4thisPage + "### " + text);

                            ElementMapping eLocator;
                            if ((showMode.equalsIgnoreCase("load"))) {
                                if (waitTime != null) {
                                    eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                                } else {
                                    eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                                }
                                elementsMap.put(name, eLocator);
                            }
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
            throw new XMLException("utility.XmlUtils.readANDXMLDoc4loading : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<String, ElementMapping> readANDXMLDoc4Mode
            (String fileNameXML, String mode) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        //System.out.println(logSpace4thisPage + "Check file in readANDXMLDocumentN " + fileNameXML);
        //System.out.println(logSpace4thisPage + "Check the userdir in readANDXMLDocN " + System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        //System.out.println(logSpace4thisPage + "The file" + file + " exists? : " + file.exists());
        if (!file.exists()) {
            //System.out.println(logSpace4thisPage + "Can't find elements mapping file: " + fileNameXML);
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + type);
                            //System.out.println(logSpace4thisPage + "### " + value);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            //System.out.println(logSpace4thisPage + "### " + showMode);
                            //System.out.println(logSpace4thisPage + "### " + dValue);
                            //System.out.println(logSpace4thisPage + "### " + text);

                            ElementMapping eLocator;
                            if ((showMode.equalsIgnoreCase(mode))) {
                                if (waitTime != null) {
                                    eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                                } else {
                                    eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                                }
                                elementsMap.put(name, eLocator);
                            }
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
            throw new XMLException("utility.XmlUtils.readANDXMLDocument4Mode : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, ElementMapping> readANDXMLDocumentN
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        //System.out.println(logSpace4thisPage + "Check file in readANDXMLDocumentN " + fileNameXML);
        //System.out.println(logSpace4thisPage + "Check the userdir in readANDXMLDocN " + System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        //System.out.println(logSpace4thisPage + "The file" + file + " exists? : " + file.exists());
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + type);
                            //System.out.println(logSpace4thisPage + "### " + value);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            //System.out.println(logSpace4thisPage + "### " + showMode);
                            //System.out.println(logSpace4thisPage + "### " + dValue);
                            //System.out.println(logSpace4thisPage + "### " + text);

                            ElementMapping eLocator;
                            if ((showMode.equalsIgnoreCase("ready") || showMode.toLowerCase().contains("ready"))) {
                                if (waitTime != null) {
                                    eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                                } else {
                                    eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                                }
                                elementsMap.put(name, eLocator);
                            }
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
            throw new XMLException("utility.XmlUtils.readANDXMLDocumentN : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<String, ElementMapping> readIOSXMLDocumentN
            (String fileNameXML) throws Exception {
        HashMap<String, ElementMapping> elementsMap = new HashMap<String, ElementMapping>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileNameXML);
        //System.out.println(logSpace4thisPage + "The file" + file);
        //System.out.println(logSpace4thisPage + "sss 537: " + file.exists());
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileNameXML);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            String type = elementObj.attributeValue("type");
                            String value = elementObj.attributeValue("value");
                            //    String attEditORDispaly = elementObj.attributeValue("EditORDisplay");
                            //    String attComparable = elementObj.attributeValue("Comaprable");
                            //    String attUnique = elementObj.attributeValue("Unique");
                            //    String attRoute = elementObj.attributeValue("Route");
                            String nextPage = elementObj.attributeValue("NextPage");
                            String showMode = elementObj.attributeValue("showMode");
                            String dValue = elementObj.attributeValue("defaultValue"); // for textfiled default e.g value content
                            String text = elementObj.attributeValue("textContent");    // for text content of btn/line/title
                            String parEleName = elementObj.attributeValue("parentElementName");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + type);
                            //System.out.println(logSpace4thisPage + "### " + value);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            //System.out.println(logSpace4thisPage + "### " + showMode);
                            //System.out.println(logSpace4thisPage + "### " + dValue);
                            //System.out.println(logSpace4thisPage + "### " + text);

                            ElementMapping eLocator;
                            if ((showMode.equalsIgnoreCase("ready") || showMode.toLowerCase().contains("ready"))) {
                                if (waitTime != null) {
                                    eLocator = new ElementMapping(name, value, Integer.parseInt(waitTime), type, nextPage, showMode, dValue, text);
                                } else {
                                    eLocator = new ElementMapping(name, value, type, nextPage, showMode, dValue, text);
                                }
                                elementsMap.put(name, eLocator);
                            }
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
            throw new XMLException("utility.XmlUtils.readIOSXMLDocumentN : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findANDRouteList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();

        //  elementsMap = readANDXMLDocumentN(path);
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        // File file = new File(path);
        //System.out.println(logSpace4thisPage + "The file" + file);
        //System.out.println(logSpace4thisPage + "sss 614: " + file.exists());
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String nextPage = elementObj.attributeValue("NextPage");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            elementsMap.put(name, nextPage);
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
            throw new XMLException("utility.XmlUtils.findANDXMLRouteList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<String, String> findIOSRouteList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        // File file = new File(path);
        //System.out.println(logSpace4thisPage + "The file" + file);
        //System.out.println(logSpace4thisPage + "sss 673: " + file.exists());
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            // //System.out.println(logSpace4thisPage + "1st : " + fileName);
            // //System.out.println(logSpace4thisPage + "tPageName : " + tPageName);
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String nextPage = elementObj.attributeValue("NextPage");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + nextPage);
                            elementsMap.put(name, nextPage);
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
            throw new XMLException("utility.XmlUtils.findIOSRouteList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findIOSDefaultValueList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String defaultValue = elementObj.attributeValue("defaultValue");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + defaultValue);
                            elementsMap.put(name, defaultValue);
                        } catch (Exception e) {
                            throw new XMLException("The content in Element Line " + lineN + "is incorrect : " + e.getCause());
                        }
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            } else {
                throw new XMLException("The NAME att is missing in the pageName 4 IOS");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.findIOSDefaultValueList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, Integer> findIOSWaitStepList
            (String fileName) throws Exception {
        HashMap<String, Integer> elementsMap = new HashMap<String, Integer>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String waitTime = elementObj.attributeValue("waitTime");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + waitTime);
                            elementsMap.put(name, Integer.parseInt(waitTime));
                        } catch (Exception e) {
                            throw new XMLException("The content in Element Line " + lineN + "is incorrect : " + e.getCause());
                        }
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            } else {
                throw new XMLException("The NAME att is missing in the pageName 4 IOS");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.findIOSWaitStepList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findIOSWindowList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            //System.out.println(logSpace4thisPage + "in try");
            //System.out.println(logSpace4thisPage + "root " + root.attributeValue("name"));
            //System.out.println(logSpace4thisPage + "att name " + root.attributeValue("name"));
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String windowValue = elementObj.attributeValue("triggerWindow");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + waitTime);
                            if (windowValue != "") {
                                elementsMap.put(name, windowValue);
                            }
                        } catch (Exception e) {
                            throw new XMLException("The content in Element Line " + lineN + "is incorrect : " + e.getCause());
                        }
                    }
                } else {
                    throw new XMLException("The Root Line is not PAGE!!!");
                }
            } else {
                throw new XMLException("The NAME att is missing in the pageName 4 IOS");
            }
        } catch (XMLException e) {
            throw new XMLException("utility.XmlUtils.findIOSWindowList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }

    public static HashMap<String, String> findANDTextContentList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String textContent = elementObj.attributeValue("textContent");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + textContent);
                            elementsMap.put(name, textContent);
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
            throw new XMLException("utility.XmlUtils.findANDTextContentList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findIOSTextContentList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String defaultValue = elementObj.attributeValue("textContent");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + defaultValue);
                            elementsMap.put(name, defaultValue);
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
            throw new XMLException("utility.XmlUtils.findIOSTextContentList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findANDDefaultValueList
            (String fileName) throws Exception {
        //System.out.println(logSpace4thisPage + "### findANDDefaultValueList in XmlUtils, Line ~1122" );
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String defaultValue = elementObj.attributeValue("defaultValue");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + defaultValue);
                            elementsMap.put(name, defaultValue);
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
            throw new XMLException("utility.XmlUtils.findANDDefaultValueList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, Integer> findANDWaitStepList
            (String fileName) throws Exception {
        HashMap<String, Integer> elementsMap = new HashMap<String, Integer>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String defaultValue = elementObj.attributeValue("waitTime");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + defaultValue);
                            elementsMap.put(name, Integer.parseInt(defaultValue));
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
            throw new XMLException("utility.XmlUtils.findANDWaitStepList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static HashMap<String, String> findANDWindowList
            (String fileName) throws Exception {
        HashMap<String, String> elementsMap = new HashMap<String, String>();
        elementsMap.clear();
        int lineN = 1;
        File file = new File(System.getProperty("user.dir") + fileName);
        if (!file.exists()) {
            throw new IOException("Can't find elements mapping file: " + fileName);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        try {
            if (file.toString().toLowerCase().contains(root.attributeValue("name").toLowerCase())) {
                if (root.getName().equalsIgnoreCase("page")) {
                    for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
                        //System.out.println(logSpace4thisPage + "Line NUM : " + lineN);
                        lineN++;
                        try {
                            Element elementObj = (Element) it.next();
                            String name = elementObj.attributeValue("name");
                            String windowValue = elementObj.attributeValue("triggerWindow");
                            //System.out.println(logSpace4thisPage + "### " + name);
                            //System.out.println(logSpace4thisPage + "### " + defaultValue);
                            if (windowValue != "") {
                                elementsMap.put(name, windowValue);
                            }
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
            throw new XMLException("utility.XmlUtils.findANDWindowList : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsMap;
    }


    public static void main(String args[]) {
        try {
            SortedSet<operationItem> parts = new TreeSet<operationItem>();
            operationItem aa = (new operationItem(new StringBuilder("xxx"), new StringBuilder(""), new StringBuilder(""), new StringBuilder(""), new StringBuilder(""), 1));
            //  operationItem[] ada  = new operationItem[]{aa, bb, cc};
            parts.add(aa);

            long gstartTime = System.currentTimeMillis();
            readANDXMLDocument4Base(new String("/src/main/java/utility/" + "test.xml"));

            System.out.println(System.currentTimeMillis() - gstartTime + " ms");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
