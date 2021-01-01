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
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String DB="pbo";
    private static final String MYCONN="jdbc:mysql://localhost/"+DB;
    private static final String SQCONN="jdbc:sqlite:C:\\Users\\didik\\OneDrive\\Documents\\Kuliah\\2020-2021 Ganjil\\PBO\\Program\\pbo-project\\src\\dbBank.sqlite";
    
    /**
     * @return java.sql.Connection object, null if not connected to db
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        Connection conn=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQCONN);
        } catch (ClassNotFoundException ex){
            ex.getStackTrace();
        }
        return conn;
    }
    
    /**
     * 
     * @param driver: MYSQL or SQLITE
     * @return java.sql.Connection object, null if not connected to db
     * @throws SQLException 
     */
    public static Connection getConnection(String driver) throws SQLException{
        Connection conn=null;
        switch (driver) {
            case "MYSQL":{
               try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(MYCONN,USERNAME,PASSWORD);
                } catch (ClassNotFoundException ex){
                 ex.getStackTrace();
                }
                break;
            }
            case "SQLITE":{
                conn = getConnection();
                break;
            }              
        }  
        return conn;
    }
}
