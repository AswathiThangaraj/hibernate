package com.example.student;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Create Configuration instance
            Configuration configuration = new Configuration();

            // Set Database Connection Properties
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/students");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "Achu2307@mysql");

            // Set Hibernate Properties
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update"); // Use "validate" in production

            // Add Annotated Class (Entity)
            configuration.addAnnotatedClass(Student.class);

            // Build SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Hibernate Configuration Failed!");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}