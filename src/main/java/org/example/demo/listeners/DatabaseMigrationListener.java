package org.example.demo.listeners;



import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseMigrationListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Starting Database Migration ===");
        try {

            System.out.println("=== Database Migration Completed Successfully ===");
        } catch (Exception e) {
            System.err.println("=== Database Migration Failed ===");
            e.printStackTrace();
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=== Application Shutdown ===");
    }
}
