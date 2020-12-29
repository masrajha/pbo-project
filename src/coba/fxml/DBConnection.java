/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba.fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author didik
 */
public class DBConnection {
   public static Connection conn = null;
   boolean driver=false;
   public DBConnection(){
       try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           this.driver=true;
           System.out.println("Driver loaded");
    } catch (Exception e){
        e.printStackTrace();
    }
   }
   public static void getConnection(){
       try {
           conn =DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=root&password=");
           System.out.println("Koneksi sukses");
       } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
       }
   }
}
