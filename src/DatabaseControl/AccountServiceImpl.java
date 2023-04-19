package DatabaseControl;

import DatabaseService.AccountService;
import Entity.UserAccount;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Eman
 */
public class AccountServiceImpl implements AccountService {

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    public boolean insertAccount(String name, String username, String password, File selectedFile) {
        byte[] imageBytes = null;
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, getFileExtension(selectedFile.getName()), baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        UserAccount userAccount = new UserAccount(name, username, password, imageBytes);

        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.save(userAccount);

            // commit the transaction
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            // if an error occurs, rollback the transaction
            session.getTransaction().rollback();

        } finally {
            // close the Session and SessionFactory
            session.close();
            factory.close();
        }
        return false;
    }

    @Override
    public String updateAccount(int id, String name, String username, String password, File selectedFile, byte[] byteImg) {
        UserAccount userAccount = null;
        if (selectedFile != null) {
            byte[] imageBytes = null;
            try {

                BufferedImage image = ImageIO.read(selectedFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, getFileExtension(selectedFile.getName()), baos);
                baos.flush();
                imageBytes = baos.toByteArray();
                baos.close();
                userAccount = new UserAccount(id, name, username, password, imageBytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            userAccount = new UserAccount(id, name, username, password, byteImg);
        }

        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Begin a transaction
            session.beginTransaction();

            // Check if username already exists in the database
            UserAccount existingUser = (UserAccount) session.createQuery("FROM UserAccount WHERE username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            if (existingUser != null && existingUser.getCount() != id) {
                return "Username is already taken!";
            }

            // Update the UserAccount object in the database
            session.merge(userAccount);

            // Commit the transaction
            session.getTransaction().commit();
            return "UserAccount updated successfully!";
        } catch (HibernateException e) {
            // If an error occurs, rollback the transaction
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Close the Session and SessionFactory
            session.close();
            factory.close();
        }
        return null;
    }

    public boolean deleteAccount(int id) {

        UserAccount userAccount = new UserAccount();
        userAccount.setCount(id);

        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.delete(userAccount);

            // commit the transaction
            session.getTransaction().commit();

            return true;
        } catch (HibernateException e) {
            // if an error occurs, rollback the transaction
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // close the Session and SessionFactory
            session.close();
            factory.close();
        }
        return false;
    }

}
