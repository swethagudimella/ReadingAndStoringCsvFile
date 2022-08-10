/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingandstoringcsvfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GSwetha
 */
public class ConcatinatingData {

    public static void main(String[] args) {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\GSwetha\\Downloads\\normalize.txt"));
            String line = "";
            bReader.readLine();
            List<String> list1 = new ArrayList<String>();
            HashMap<String, String> map = new LinkedHashMap<>();
            String tempArr[] = null;
            while ((line = bReader.readLine()) != null) {
                if (line != null) {
                    String[] array = line.split(",");
                    if (tempArr != null) {
                        if ((Arrays.asList(tempArr).contains(array[0])) && (Arrays.asList(tempArr).contains(array[1]))) {
//                            System.out.println("inside while");
                            tempArr[2] += "," + array[2];

//                            System.out.println(tempArr[2] + "temp");

                            map.put(array[1], Arrays.toString(tempArr));

                        } else {

                            tempArr = line.split(",");

                        }

                    } else {

                        tempArr = line.split(",");

                    }
                }
            }

            map.forEach((k, v) -> {
                System.out.println("key" + k + " value:" + v);
                list1.add(v);
            });
            System.out.println("readingandstoringcsvfile.ConcatinatingData.main()" + list1);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConcatinatingData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConcatinatingData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
