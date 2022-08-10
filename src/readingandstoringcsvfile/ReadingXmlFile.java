/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingandstoringcsvfile;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author GSwetha
 */
public class ReadingXmlFile {

    public static void main(String argv[]) {
        try {

            File file = new File("C:\\Users\\GSwetha\\Downloads\\data-lineage-result.xml");
            if (file.exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("relation");
                HashMap<String, String> sourceMap = new LinkedHashMap<>();
                HashMap<String, String> targetMap = new LinkedHashMap<>();
                CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
                String[] stringArrayHeader = {"SourceColumnName", "SourceTableName", "TargetColumnName", "TargetTableName"};
                writer.writeNext(stringArrayHeader);

                for (int temp = 0; temp < nodeList.getLength(); temp++) {

                    String[] stringArray = new String[4];
                    Node nOzet = nodeList.item(temp);
                    System.out.println("\nNode Name :" + nOzet.getNodeName() + temp);
                    Element eElement = (Element) nOzet;
                    NodeList targetList = eElement.getElementsByTagName("target");

                    for (int temp2 = 0; temp2 < targetList.getLength(); temp2++) {
                        String tableName = ((Element) targetList.item(temp2)).getAttribute("parent_name") + "\t";
//                    System.out.println("tableName" + tableName);
                        String coulmn = ((Element) targetList.item(temp2)).getAttribute("column") + "\t";
//                    System.out.println("coulmnName" + coulmn);
                        targetMap.put(coulmn, tableName);
                    }
                    targetMap.forEach((k, v) -> {
                        System.out.println("Target " + "key:" + k + " value:" + v);
                        stringArray[2] = k;
                        stringArray[3] = v;
                    });

                    NodeList sourceList = eElement.getElementsByTagName("source");

                    String sourceTableName = "";
                    String sourceCoulmn = "";
                    for (int temp2 = 0; temp2 < sourceList.getLength(); temp2++) {
                        sourceTableName = ((Element) sourceList.item(temp2)).getAttribute("parent_name") + "\t";
//                    System.out.println("tableName" + sourceTableName);

                        sourceCoulmn = ((Element) sourceList.item(temp2)).getAttribute("column") + "\t";
//                    System.out.println("coulmnName" + sourceCoulmn);
                        sourceMap.put(sourceCoulmn, sourceTableName);

                    }
                    sourceMap.forEach((k, v) -> {
                        System.out.println("Source  key:" + k + " value:" + v);
                        stringArray[0] = k;
                        stringArray[1] = v;
//                    System.out.println("LIST:" + " stringArray[0]:" + stringArray[0] + " stringArray[1]:" + stringArray[1]);
                        writer.writeNext(stringArray);

                    });

                    sourceMap.clear();
                    targetMap.clear();

                    writer.flush();

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
