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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GSwetha
 */
public class ReadingAndStoringCSVFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String jdbcURL = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String username = "system";
        String password = "oracledb";

        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("connection Established");

            String sql = "Insert into CSVDATA values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\GSwetha\\Downloads\\10000 Records.csv"));

            prepareStatement = connection.prepareStatement(sql);
            String line = "";
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                if (line != null) {
                    String[] array = line.split(",");

                    prepareStatement.setString(1, array[0]);
                    prepareStatement.setString(2, array[1]);
                    prepareStatement.setString(3, array[2]);
                    prepareStatement.setString(4, array[3]);
                    prepareStatement.setString(5, array[4]);
                    prepareStatement.setString(6, array[5]);
                    prepareStatement.setString(7, array[6]);
                    prepareStatement.setString(8, array[7]);
                    prepareStatement.setString(9, array[8]);
                    prepareStatement.setString(10, array[9]);
                    prepareStatement.setString(11, array[10]);
                    prepareStatement.setString(12, array[11]);
                    prepareStatement.setFloat(13, Float.parseFloat(array[12]));
                    prepareStatement.setDouble(14, Double.parseDouble(array[13]));
                    prepareStatement.setString(15, array[14]);
                    prepareStatement.setString(16, array[15]);
                    prepareStatement.setString(17, array[16]);
                    prepareStatement.setString(18, array[17]);
                    prepareStatement.setString(19, array[18]);
                    prepareStatement.setString(20, array[19]);
                    prepareStatement.setString(21, array[20]);
                    prepareStatement.setString(22, array[21]);
                    prepareStatement.setString(23, array[22]);
                    prepareStatement.setString(24, array[23]);
                    prepareStatement.setDouble(25, Double.parseDouble(array[24]));
                    prepareStatement.setLong(26, Long.parseLong(array[25]));
                    prepareStatement.setString(27, array[26]);
                    prepareStatement.setString(28, array[27]);
                    prepareStatement.setString(29, array[28]);
                    prepareStatement.setString(30, array[29]);
                    prepareStatement.setString(31, array[30]);
                    prepareStatement.setString(32, array[31]);
                    prepareStatement.setString(33, array[32]);
                    prepareStatement.setString(34, array[33]);
                    prepareStatement.setString(35, array[34]);
                    prepareStatement.setString(36, array[35]);
                    prepareStatement.setString(37, array[36]);
                    prepareStatement.executeUpdate();

                }
                if (bReader == null) {
                    bReader.close();
                }
            }
            System.out.println("Records Inserted");
        } catch (FileNotFoundException ex) {
             System.out.println("File not found Please check");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadingAndStoringCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadingAndStoringCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadingAndStoringCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReadingAndStoringCSVFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReadingAndStoringCSVFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
