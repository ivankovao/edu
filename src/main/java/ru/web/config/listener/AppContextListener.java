package ru.web.config.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    public AppContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("************** Starting up! **************");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("************** Shutting down! **************");
        System.out.println("Destroying Context...");
        System.out.println("Calling MySQL AbandonedConnectionCleanupThread checkedShutdown");
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration drivers = DriverManager.getDrivers();

        while(drivers.hasMoreElements()) {
            Driver driver = (Driver)drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    System.out.println("Deregistering JDBC driver " + driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException var6) {
                    System.out.println("Error deregistering JDBC driver " + driver);
                    var6.printStackTrace();
                }
            } else {
                System.out.println("Not deregistering JDBC driver " + driver + " as it does not belong to this webapp's ClassLoader");
            }
        }

    }
}
