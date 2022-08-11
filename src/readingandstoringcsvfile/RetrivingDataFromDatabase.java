/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingandstoringcsvfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GSwetha
 */
public class RetrivingDataFromDatabase {
 static final String QUERY = "SELECT CONCAT(Customer.FirstName,' ',Customer.LastName)AS 'CUSTOMER NAME',Product.Name AS 'PRODUCT NAME',SalesOrderDetail.OrderQty,SalesOrderDetail.LineTotal FROM SalesLT.SalesOrderHeader as soh INNER JOIN SalesLT.Customer as Customer on soh.CustomerID=Customer.CustomerID\n" +
"INNER JOIN SalesLT.SalesOrderDetail as SalesOrderDetail on soh.SalesOrderID=SalesOrderDetail.SalesOrderID INNER JOIN SalesLT.Product as Product on SalesOrderDetail.ProductID=Product.ProductID;";
    public static void main(String[] args) {
        String dbURL = "jdbc:sqlserver://localhost;databaseName=AdventureWorksLT2019";
        String user = "sa";
        String pass = "@swetha12";
        
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(dbURL, user, pass);
             Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
         
   		 System.out.println("CUSTOMER NAME                   PRODUCT NAME                                        ORDER QUANTITY                          TOTAL COST");      
         while(rs.next()){
            //Display values
            System.out.println(rs.getString(1)+"                   "+rs.getString(2)+"                                   "+rs.getInt(3)+"                             "+rs.getDouble(4));
           
         }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RetrivingDataFromDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RetrivingDataFromDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
