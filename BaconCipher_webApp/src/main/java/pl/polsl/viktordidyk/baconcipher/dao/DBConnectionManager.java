/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author viktor
 */
public class DBConnectionManager implements AutoCloseable {
    
    private final static String DBURL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
    private final static String DBUSER = "root";
    private final static String DBPASS = "password";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection connection;


    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DBDRIVER);
        this.connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        return connection;
    }
    
    public void closeConnection() throws SQLException{
        this.connection.close();
    }
    
    @Override
    public void close() throws Exception {
        this.closeConnection();
    }
}
