/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author didik
 */
public class DBHelper {
    private static String USERNAME="root";
    private static String PASSWORD="";
    private static String MyCONN="jdbc:mysql://localhost/pbo";  //There are exist pbo in mysql database
    private static String SQCONN="jdbc:sqlite:dbBank.sqlite";
    
    public Connection getConnection() throws SQLException{
        Connection conn=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQCONN);
        } catch (ClassNotFoundException ex){
            ex.getStackTrace();
        }
        return conn;
    }
    
}
