package com.redis.hibernate.util;

import java.util.logging.Level;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
        	try {
        		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	}
        	catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                ex.printStackTrace();
                throw new ExceptionInInitializerError(ex);
            }
        }
         
        return sessionFactory;
    }
	
}
