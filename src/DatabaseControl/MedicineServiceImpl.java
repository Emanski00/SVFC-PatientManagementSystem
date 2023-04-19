/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseControl;

import DatabaseService.MedicineService;
import Entity.Medicines;
import Entity.PatientsRecord;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eman
 */
public class MedicineServiceImpl implements MedicineService {

    @Override
    public Boolean insertMedicine(Medicines medicine) {
        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(Medicines.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.save(medicine);

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

    @Override
    public boolean updateMedicineData(long id, Medicines medicine) {
        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(Medicines.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.update(medicine);

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

    @Override
    public boolean deleteMedicineData(long id) {
        Medicines medicine = new Medicines();
        medicine.setCount(id);

        SessionFactory factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(Medicines.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.delete(medicine);

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
