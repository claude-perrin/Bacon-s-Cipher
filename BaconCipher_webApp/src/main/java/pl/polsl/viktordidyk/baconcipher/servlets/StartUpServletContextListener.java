/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pl.polsl.viktordidyk.baconcipher.dao.HistoryDAO;

@WebListener
public class StartUpServletContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            HistoryDAO historyDao = HistoryDAO.getInstance();
            historyDao.setUpDbSchema();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.print(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                System.out.print(String.format("Error deregistering driver %s", e));
            }
        }
    }
}