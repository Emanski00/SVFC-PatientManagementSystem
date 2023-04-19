package DatabaseControl;

import DatabaseService.PatientService;
import Entity.PatientsRecord;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PatientServiceImpl implements PatientService {

    private SessionFactory factory;

    @Override
    public long patientCountsByStatus(String status) {
        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        long count = 0;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM PatientsRecord WHERE status = :status");
            query.setParameter("status", status);
            count = (Long) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return count;

    }

    @Override
    public boolean insertPatient(PatientsRecord patientInfos) {

        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.save(patientInfos);

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
    public boolean updatePatientData(int id, PatientsRecord patient) {

        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.update(patient);

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
    public boolean updatePatientStatus(PatientsRecord patient) {
        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.update(patient);

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
    public boolean deletePatient(int id) {

        PatientsRecord patients = new PatientsRecord();
        patients.setCount(id);
        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.delete(patients);

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
    public boolean updatePatientAdmission(PatientsRecord patientRecord) {
        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // begin a transaction
            session.beginTransaction();

            // save the UserAccount object to the database
            session.update(patientRecord);

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
