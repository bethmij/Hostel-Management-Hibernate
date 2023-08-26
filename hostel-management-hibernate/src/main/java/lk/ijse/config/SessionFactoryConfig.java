package lk.ijse.config;

import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() throws IOException {

        Properties p = new Properties();
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));

        sessionFactory = new Configuration().setProperties(p)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() throws IOException {
        return (null == factoryConfig)
                ? factoryConfig = new SessionFactoryConfig()
                : factoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
