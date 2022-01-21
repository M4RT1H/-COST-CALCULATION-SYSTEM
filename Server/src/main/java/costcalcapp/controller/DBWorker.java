/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.controller;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.*;

/**
 *
 * @author M4RT1H
 */
public class DBWorker {

    private static final String URL = "jdbc:mysql://localhost:3306/costcalcapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    private Connection connection;

    public DBWorker() {
//        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
//        } catch (SQLException ex) {
//            System.err.println("Не удалось загрузить драйвер!");
//        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
}
