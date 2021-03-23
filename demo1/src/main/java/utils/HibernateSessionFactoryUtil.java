package utils;

import models.Country;
import models.Cities;
import models.Peoples;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil() {

    }
        public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Country.class);
                configuration.addAnnotatedClass(Cities.class);
                configuration.addAnnotatedClass(Peoples.class);

//                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
//                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//                configuration.setProperty("hibernate.connection.pool_size", "10");
//                configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/Peoples");
//                configuration.setProperty("hibernate.connection.username", "postgres");
//                configuration.setProperty("hibernate.connection.password", "3311534197");
//                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e){
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
