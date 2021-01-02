/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author didik
 */
public class DBHelper {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB = "test";
    private static final String MYCONN = "jdbc:mysql://localhost/" + DB;
//    private static final String SQCONN = "jdbc:sqlite:C:\\Users\\didik\\OneDrive\\Documents\\Kuliah\\2020-2021 Ganjil\\PBO\\Program\\pbo-project\\src\\dbBank.sqlite";
    private static final String SQCONN = "jdbc:sqlite:dbBank.sqlite";

    /**
     * @return java.sql.Connection object, null if not connected to db
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQCONN);
            createTable(conn, "SQLITE");
        } catch (ClassNotFoundException ex) {
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
    public static Connection getConnection(String driver) throws SQLException {
        Connection conn = null;
        switch (driver) {
            case "MYSQL": {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(MYCONN, USERNAME, PASSWORD);
                    createTable(conn, driver);
                } catch (ClassNotFoundException ex) {
                    ex.getStackTrace();
                }
                break;
            }
            case "SQLITE": {
                conn = getConnection();
                createTable(conn, driver);
                break;
            }
        }
        return conn;
    }

    private static void createTable(Connection conn, String driver) throws SQLException {
        switch (driver) {
            case "MYSQL": {
                String sqlCreate = "CREATE TABLE IF NOT EXISTS `account_holder` ("
                        + "  `holder_id` int(10) NOT NULL,"
                        + "  `address` varchar(255) NOT NULL,"
                        + "  `name` varchar(100) NOT NULL,"
                        + "  PRIMARY KEY (`holder_id`)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `corporate_holder` ("
                        + "  `holder_id` int(10) NOT NULL,"
                        + "  `contact` varchar(200) DEFAULT NULL,"
                        + "  PRIMARY KEY (`holder_id`),"
                        + "  FOREIGN KEY (`holder_id`) REFERENCES `account_holder` (`holder_id`)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `individual_holder` ("
                        + "  `holder_id` int(10) NOT NULL,"
                        + "  `SSN` varchar(20) DEFAULT NULL,"
                        + "  `birthdate` date NOT NULL,"
                        + "  PRIMARY KEY (`holder_id`),"
                        + "  FOREIGN KEY (`holder_id`) REFERENCES `account_holder` (`holder_id`)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `account` ("
                        + "  `acc_number` int(10) NOT NULL,"
                        + "  `balance` double(20,2) DEFAULT NULL,"
                        + "  `holder_id` int(10) DEFAULT NULL,"
                        + "  PRIMARY KEY (`acc_number`),"
                        + "  FOREIGN KEY (`holder_id`) REFERENCES `account_holder` (`holder_id`),"
                        + "  KEY `holder_id` (`holder_id`)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "COMMIT;";
                String sqls[] = sqlCreate.split(";");
                int i = 1;
                for (String sql : sqls) {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.execute();
                    System.out.println(i++);
                }
                break;
            }
            case "SQLITE": {
                String sqlCreate = "CREATE TABLE IF NOT EXISTS  account ("
                        + "    holder_id  INT (10)       REFERENCES account_holder (holder_id) ON DELETE RESTRICT"
                        + "                                                                    ON UPDATE CASCADE,"
                        + "    acc_number INT (10)       PRIMARY KEY,"
                        + "    balance    DOUBLE (20, 2) "
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS  account_holder ("
                        + "    holder_id INT (10)      NOT NULL"
                        + "                            PRIMARY KEY,"
                        + "    name      VARCHAR (100) NOT NULL,"
                        + "    address   VARCHAR (255) NOT NULL"
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS  corporate_holder ("
                        + "    holder_id INT (10)      PRIMARY KEY"
                        + "                            REFERENCES account_holder (holder_id) ON DELETE RESTRICT"
                        + "                                                                  ON UPDATE CASCADE,"
                        + "    contact   VARCHAR (200) "
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS  individual_holder ("
                        + "    holder_id INT (10)     NOT NULL"
                        + "                           PRIMARY KEY"
                        + "                           REFERENCES account_holder (holder_id) ON DELETE RESTRICT"
                        + "                                                                 ON UPDATE CASCADE,"
                        + "    SSN       VARCHAR (20) DEFAULT NULL,"
                        + "    birthdate DATE         NOT NULL"
                        + ");";
                String sqls[] = sqlCreate.split(";");
                for (String sql : sqls) {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.execute();
                }
                break;
            }
        }
    }
}
