/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingandstoringcsvfile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author GSwetha
 */
public class ReadingTableDataFromXML {

    public static void main(String argv[]) {
        try {
            String filepath = "C:\\Users\\GSwetha\\Downloads\\Version2_3_5_3.xml";
            File file = new File(filepath);
          
            if (file.exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("table");
//                HashMap<String, String> sourceMap = new LinkedHashMap<>();
                HashMap<String,List< String>> targetMap = new LinkedHashMap<>();
                System.out.println("nodeList.getLength()" + nodeList.getLength());

                for (int temp = 0; temp < nodeList.getLength(); temp++) {
//
//                    String[] stringArray = new String[4];
                    String tableName = ((Element) nodeList.item(temp)).getAttribute("name") + "\t";
//                    System.out.println("tableName" + tableName);
                    Node nOzet = nodeList.item(temp);
//                    System.out.println("\nNode Name :" + nOzet.getAttributes());
                    Element eElement = (Element) nOzet;
  List<String> columnList=new ArrayList();
                    NodeList tableColumnList = eElement.getElementsByTagName("column");
                    System.out.println("tableColumnList.getLength()" + tableColumnList.getLength());
                    for (int temp2 = 0; temp2 < tableColumnList.getLength(); temp2++) {

                        String coulmn = ((Element) tableColumnList.item(temp2)).getAttribute("name") + "\t";
//                        System.out.println("coulmnName" + coulmn);

                          columnList.add(coulmn);
                      
                       targetMap.put(tableName, columnList);
                    }
                      
                     
                         for (Map.Entry<String, List<String>> entry : targetMap.entrySet()) {
    System.out.println(entry.getKey() + "/" + entry.getValue());
    
    
     
                         }
//                    targetMap.forEach((k, v) -> {
//                        System.out.println("Target " + "key:" + k + " value:" + v);
//                        stringArray[2] = k;
//                        stringArray[3] = v;
//                    });

//                    targetMap.clear();
//columnList.clear();
                }
                System.out.println("Data entered");
            } else {
                System.out.println("file not found please enter valid file path");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
