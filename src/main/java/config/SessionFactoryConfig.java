package config;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }
    private SessionFactoryConfig(){
        Properties properties = new Properties();

        try{
            properties.load(SessionFactoryConfig.class.getResourceAsStream("/hostal.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionFactory = new Configuration().setProperties(properties)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
