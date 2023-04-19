/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package MainForms;

<<<<<<< HEAD
import DatabaseControl.PatientServiceImpl;
import Entity.PatientsRecord;
import Globals.FormatDate;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
=======
import java.awt.CardLayout;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
>>>>>>> origin/master

/**
 *
 * @author Eman
 */
public class AddingPatient extends javax.swing.JDialog {

    /**
     * Creates new form SuppliesAdding
     */
<<<<<<< HEAD
    SessionFactory factory = null;
    public static int pageCount = 0;
    public static int idHolder = 0;
    public static int idHolderForStatus = 0;
    CardLayout cardLayout;
    int counter = 0;
    PatientsRecord patientData = null;

    public AddingPatient(java.awt.Frame parent, boolean modal, int updateId, int addingId) {
        super(parent, modal);

        initComponents();

        if (addingId >= 1) {
            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(PatientsRecord.class)
                    .buildSessionFactory();
            Session session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PatientsRecord> query = builder.createQuery(PatientsRecord.class);
            Root<PatientsRecord> root = query.from(PatientsRecord.class);

            Predicate predicate = builder.equal(root.get("count"), addingId);
            query.where(predicate);

            List<PatientsRecord> results = session.createQuery(query).getResultList();

            for (PatientsRecord patients : results) {
                counter = patients.getCount();
                idHolder = patients.getCount();
                idHolderForStatus = idHolder;
                nameText.setText(patients.getName());

                studIDText.setText(patients.getId());
                String dateValue = patients.getBirth();

                DateFormat format = new SimpleDateFormat("EEE, MMMM dd, yyyy");
                Date birthDate;
                try {
                    birthDate = format.parse(dateValue);
                    bdayChooser.setDate(birthDate);

                    // Calculate age based on birthdate
                    Calendar dob = Calendar.getInstance();
                    dob.setTime(birthDate);
                    Calendar now = Calendar.getInstance();
                    int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                    if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                        age--;
                    }
                    String ageString = Integer.toString(age);

                    // Update ageText with current age
                    ageText.setText(ageString);
                } catch (ParseException ex) {
                    Logger.getLogger(AddingPatient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            session.close();
        }

        if (updateId >= 1) {
            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(PatientsRecord.class)
                    .buildSessionFactory();
            Session session = factory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PatientsRecord> query = builder.createQuery(PatientsRecord.class);
            Root<PatientsRecord> root = query.from(PatientsRecord.class);

            Predicate predicate = builder.equal(root.get("count"), updateId);
            query.where(predicate);

            List<PatientsRecord> results = session.createQuery(query).getResultList();

            for (PatientsRecord patients : results) {
                counter = patients.getCount();
                idHolder = patients.getCount();

                studIDText.setText(patients.getId());
                String dateValue = patients.getBirth();
                DateFormat format = new SimpleDateFormat("EEE, MMMM dd, yyyy");
                Date date;
                try {
                    date = format.parse(dateValue);
                    bdayChooser.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(AddingPatient.class.getName()).log(Level.SEVERE, null, ex);
                }

                ageText.setText(patients.getAge());
                nameText.setText(patients.getName());
                sectionText.setText(patients.getSection());
                yearLevelText.setSelectedItem(patients.getYear());

                //
                String vitalSigns = patients.getVitalSigns();
                String[] values = vitalSigns.split(", ");

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("[^\\d]", "");
                }
                bpText.setText(values[0]);
                tempText.setText(values[1]);
                rrText.setText(values[2]);
                hrText.setText(values[3]);

                chiefCompText.setText(patients.getChiefComplaints());
                allergiesText.setText(patients.getAlergies());
                contactTracingText.setText(patients.getContactTracing());
                painScale.setSelectedItem(patients.getPainScale());

                // 
                objectiveText.setText(patients.getObjective());
                subjectiveText.setText(patients.getSubjective());

                assestmentText.setText(patients.getAssesment());
                recomText.setText(patients.getPlans_And_Reccomendation());

            }
            confirm.setText("Update");

            session.close();
        }

        bdayChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                // Get the selected birthday from the calendar component
                Calendar birthday = bdayChooser.getCalendar();

                if (birthday != null) {
                    // Get the current date
                    Calendar now = Calendar.getInstance();
                    // Calculate the age based on the difference between the birth year and the current year
                    int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

                    // If the birth month is after the current month, subtract one from the age
                    if (birthday.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
                        age--;
                    } // If the birth month is the same as the current month and the birth day is after the current day, subtract one from the age
                    else if (birthday.get(Calendar.MONTH) == now.get(Calendar.MONTH) && birthday.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)) {
                        age--;
                    }

                    // Display the age and time between in a dialog
                    ageText.setText(String.valueOf(age));
                }

            }
        });

        this.setLocationRelativeTo(null);
        this.setResizable(false);
=======
    public static int pageCount = 0;

    CardLayout cardLayout;

    public AddingPatient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
>>>>>>> origin/master
        this.cardLayout = (CardLayout) mainCard.getLayout();

        confirm.setVisible(false);

<<<<<<< HEAD
        firstPageError(false, false, false, false, false, false, "");
        secondPageError(false, false, false, false, false, false, false, false, "");
        thirdPageError(false, false, false, false, "");

=======
        errorStatus(false, false, false, false, false, false);
>>>>>>> origin/master
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainCard = new javax.swing.JPanel();
        firstPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        studIDText = new javax.swing.JTextField();
<<<<<<< HEAD
        studError = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        nameError = new javax.swing.JLabel();
        bdayChooser = new com.toedter.calendar.JDateChooser();
        birthError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ageText = new javax.swing.JTextField();
        ageError = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        yearLevelText = new javax.swing.JComboBox<>();
        yearError = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        sectionText = new javax.swing.JTextField();
=======
        jLabel4 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ageText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bdayChooser = new com.toedter.calendar.JDateChooser();
        yearLevelText = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        sectionText = new javax.swing.JTextField();
        studError = new javax.swing.JLabel();
        nameError = new javax.swing.JLabel();
        birthError = new javax.swing.JLabel();
        ageError = new javax.swing.JLabel();
        yearError = new javax.swing.JLabel();
>>>>>>> origin/master
        sectError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        seconPage = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bpText = new javax.swing.JTextField();
<<<<<<< HEAD
        bpError = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tempText = new javax.swing.JTextField();
        tempError = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rrText = new javax.swing.JTextField();
        rrError = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        hrText = new javax.swing.JTextField();
        hrError = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        chiefCompText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        allergiesText = new javax.swing.JTextField();
        allError = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ctError = new javax.swing.JLabel();
        contactTracingText = new javax.swing.JTextField();
        ccError = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        painError = new javax.swing.JLabel();
        painScale = new javax.swing.JComboBox<>();
=======
        jLabel9 = new javax.swing.JLabel();
        hrText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rrText = new javax.swing.JTextField();
        tempText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        allergiesText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        contactTracingText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        chiefCompText = new javax.swing.JTextField();
>>>>>>> origin/master
        jLabel13 = new javax.swing.JLabel();
        thirdPage = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        objectiveText = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        subjectiveText = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        assestmentText = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recomText = new javax.swing.JTextArea();
<<<<<<< HEAD
        objectiveError = new javax.swing.JLabel();
        subjectiveError = new javax.swing.JLabel();
        assestmentError = new javax.swing.JLabel();
        recError = new javax.swing.JLabel();
=======
>>>>>>> origin/master
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nextPage = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
<<<<<<< HEAD
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
=======

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
>>>>>>> origin/master
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        mainCard.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
<<<<<<< HEAD
        jLabel3.setText("Student Id");

        studIDText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

=======
        jLabel3.setText("STUDENT ID");

        studIDText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("NAME");

        nameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("BIRTHDAY");

        ageText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("AGE");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Year/Level");

        yearLevelText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yearLevelText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Year", "2nd Year", "3rd Year", "4th Year" }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Section");

        sectionText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

>>>>>>> origin/master
        studError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        studError.setForeground(new java.awt.Color(255, 0, 0));
        studError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        studError.setText("error");

<<<<<<< HEAD
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Name");

        nameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

=======
>>>>>>> origin/master
        nameError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameError.setForeground(new java.awt.Color(255, 0, 0));
        nameError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameError.setText("error");

<<<<<<< HEAD
        bdayChooser.setDoubleBuffered(false);

=======
>>>>>>> origin/master
        birthError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        birthError.setForeground(new java.awt.Color(255, 0, 0));
        birthError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        birthError.setText("error");

<<<<<<< HEAD
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Birth / Age");

        ageText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ageText.setEnabled(false);

=======
>>>>>>> origin/master
        ageError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ageError.setForeground(new java.awt.Color(255, 0, 0));
        ageError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ageError.setText("error");

<<<<<<< HEAD
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Year/Level");

        yearLevelText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yearLevelText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "Kinder", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "------------------------", "1st Year  Junior High", "2nd Year Junior High", "3rd Year Junior High", "4th Year Junior High", "------------------------", "1st Year  Senior High", "2nd Year Senior High", "------------------------", "1st Year College", "2nd Year College", "3rd Year College", "4th Year College", " " }));
        yearLevelText.setToolTipText("");

=======
>>>>>>> origin/master
        yearError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        yearError.setForeground(new java.awt.Color(255, 0, 0));
        yearError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearError.setText("error");

<<<<<<< HEAD
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Section");

        sectionText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

=======
>>>>>>> origin/master
        sectError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sectError.setForeground(new java.awt.Color(255, 0, 0));
        sectError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sectError.setText("error");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
<<<<<<< HEAD
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(yearLevelText, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studIDText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sectionText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addComponent(sectError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bdayChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(birthError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ageText, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(ageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
=======
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(birthError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studIDText, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(nameText, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bdayChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(studError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yearLevelText, 0, 339, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sectionText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(ageText)
                    .addComponent(ageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sectError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
>>>>>>> origin/master
                .addGap(93, 93, 93))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
<<<<<<< HEAD
                        .addGap(0, 0, 0)
                        .addComponent(studIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearLevelText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ageText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ageError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
=======
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(studIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(studError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearLevelText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ageText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ageError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(birthError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
>>>>>>> origin/master
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectionText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
<<<<<<< HEAD
                .addContainerGap(82, Short.MAX_VALUE))
=======
                .addContainerGap(69, Short.MAX_VALUE))
>>>>>>> origin/master
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BASIC INFO");

        javax.swing.GroupLayout firstPanelLayout = new javax.swing.GroupLayout(firstPanel);
        firstPanel.setLayout(firstPanelLayout);
        firstPanelLayout.setHorizontalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        firstPanelLayout.setVerticalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainCard.add(firstPanel, "firstPage");

<<<<<<< HEAD
        seconPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("BLOOD PREASURE");

        bpText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        bpError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bpError.setForeground(new java.awt.Color(255, 0, 0));
        bpError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bpError.setText("error");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("TEMPERATURE");

        tempText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tempError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tempError.setForeground(new java.awt.Color(255, 0, 0));
        tempError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tempError.setText("error");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("RESPIRATORY RATE");

        rrText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        rrError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rrError.setForeground(new java.awt.Color(255, 0, 0));
        rrError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rrError.setText("error");
        rrError.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rrErrorFocusGained(evt);
            }
        });
        rrError.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rrErrorMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
=======
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("BP - BLOOD PREASURE");

        bpText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
>>>>>>> origin/master
        jLabel9.setText("HR - HEARTH RATE");

        hrText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

<<<<<<< HEAD
        hrError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hrError.setForeground(new java.awt.Color(255, 0, 0));
        hrError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hrError.setText("error");
=======
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("RR - RESPIRATORY RATE");

        rrText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tempText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("TEMP");

        allergiesText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("ALLERGIES");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("CONTACT TRACING");

        contactTracingText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
>>>>>>> origin/master

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("CHIEF COMPLAINT");

        chiefCompText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

<<<<<<< HEAD
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("ALLERGIES");

        allergiesText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        allError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        allError.setForeground(new java.awt.Color(255, 0, 0));
        allError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        allError.setText("error");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("CONTACT TRACING");

        ctError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ctError.setForeground(new java.awt.Color(255, 0, 0));
        ctError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ctError.setText("error");

        contactTracingText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ccError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ccError.setForeground(new java.awt.Color(255, 0, 0));
        ccError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ccError.setText("error");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("PAIN SCALE");

        painError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        painError.setForeground(new java.awt.Color(255, 0, 0));
        painError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        painError.setText("error");

        painScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT SCALE(1-10)", "LEVEL 1", "LEVEL 2", "LEVEL 3", "LEVEL 4", "LEVEL 5", "LEVEL 6", "LEVEL 7", "LEVEL 8", "LEVEL 9", "LEVEL 10" }));

=======
>>>>>>> origin/master
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bpText)
                            .addComponent(bpError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tempError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tempText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ccError, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addComponent(chiefCompText, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ctError, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contactTracingText)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painScale, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painError, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allError, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(rrError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rrText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hrError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hrText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addComponent(allergiesText, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(86, 86, 86))
=======
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bpText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(hrText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rrText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allergiesText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(contactTracingText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(93, 93, 93))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chiefCompText, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
>>>>>>> origin/master
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
<<<<<<< HEAD
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(bpText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bpError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rrError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hrError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rrText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tempText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hrText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chiefCompText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ccError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactTracingText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctError))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergiesText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(allError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painScale, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        rrText.getAccessibleContext().setAccessibleName("");
        jLabel9.getAccessibleContext().setAccessibleName("");
        hrText.getAccessibleContext().setAccessibleName("");
        hrError.getAccessibleContext().setAccessibleName("");

        seconPage.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 34, 1098, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("VITAL SIGNS");
        seconPage.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1098, -1));
=======
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bpText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hrText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergiesText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rrText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactTracingText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chiefCompText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("VITAL SIGNS");

        javax.swing.GroupLayout seconPageLayout = new javax.swing.GroupLayout(seconPage);
        seconPage.setLayout(seconPageLayout);
        seconPageLayout.setHorizontalGroup(
            seconPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seconPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seconPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        seconPageLayout.setVerticalGroup(
            seconPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seconPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
>>>>>>> origin/master

        mainCard.add(seconPage, "secondPage");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Subjective :");

        objectiveText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Objective :");

        subjectiveText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Assestment :");

        assestmentText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("RECOMENDATION");

        recomText.setColumns(20);
        recomText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        recomText.setRows(5);
        jScrollPane1.setViewportView(recomText);

<<<<<<< HEAD
        objectiveError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        objectiveError.setForeground(new java.awt.Color(255, 0, 0));
        objectiveError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        objectiveError.setText("error");

        subjectiveError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        subjectiveError.setForeground(new java.awt.Color(255, 0, 0));
        subjectiveError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subjectiveError.setText("error");

        assestmentError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        assestmentError.setForeground(new java.awt.Color(255, 0, 0));
        assestmentError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        assestmentError.setText("error");

        recError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        recError.setForeground(new java.awt.Color(255, 0, 0));
        recError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        recError.setText("error");

=======
>>>>>>> origin/master
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
<<<<<<< HEAD
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assestmentError, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(objectiveError, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(subjectiveText)
                    .addComponent(assestmentText, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(subjectiveError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(objectiveText, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(65, 65, 65))
=======
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(objectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                                    .addComponent(assestmentText)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(652, Short.MAX_VALUE)))
>>>>>>> origin/master
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
<<<<<<< HEAD
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(objectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectiveError)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subjectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subjectiveError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(assestmentText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(assestmentError)
                        .addGap(206, 206, 206))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recError)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
=======
                .addComponent(objectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assestmentText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
>>>>>>> origin/master
        );

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Diagnosis");

        javax.swing.GroupLayout thirdPageLayout = new javax.swing.GroupLayout(thirdPage);
        thirdPage.setLayout(thirdPageLayout);
        thirdPageLayout.setHorizontalGroup(
            thirdPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thirdPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thirdPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
>>>>>>> origin/master
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        thirdPageLayout.setVerticalGroup(
            thirdPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thirdPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        mainCard.add(thirdPage, "thirdPage");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainCard, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adding Patient");

        title.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Basic Information");

        previous.setText("Previous");
        previous.setEnabled(false);
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextPage.setText("Next");
        nextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageActionPerformed(evt);
            }
        });
        jPanel5.add(nextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 193, 53));

        confirm.setText("CONFIRM");
        confirm.setEnabled(false);
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        jPanel5.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 193, 53));

<<<<<<< HEAD
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

=======
>>>>>>> origin/master
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
=======
>>>>>>> origin/master
                .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previous, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
>>>>>>> origin/master
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageActionPerformed

        if (pageCount == 0) {

            if (studIDText.getText().isEmpty()) {
<<<<<<< HEAD
                firstPageError(true, false, false, false, false, false, "Please Fill Student Id.");

            } else if (nameText.getText().isEmpty()) {
                firstPageError(false, true, false, false, false, false, "Please fill student name.");
            } else if (!nameText.getText().matches("[a-zA-Z ]+")) {
                firstPageError(false, true, false, false, false, false, "Please use valid student name.(letters only)");
            } else if (ageText.getText().isEmpty()) {
                firstPageError(false, false, true, false, false, false, "Please fill age.");
            } else if (Integer.parseInt(ageText.getText()) <= 0) {
                firstPageError(false, false, true, false, false, false, "Please use valid age.");
            } else if (bdayChooser.getCalendar() == null) {
                firstPageError(false, false, false, true, false, false, "Please choose birthday.");
            } else if (yearLevelText.getSelectedIndex() == 0 || yearLevelText.getSelectedItem().toString().contains("-")) {
                firstPageError(false, false, false, false, true, false, "Please choose year level.");
            } else if (sectionText.getText().isEmpty()) {
                firstPageError(false, false, false, false, false, true, "Please fill section.");
            } else {
                firstPageError(false, false, false, false, false, false, "");
                title.setText("Vitals Signs / Other Information");
=======
                errorStatus(true, false, false, false, false, false);

            } else if (nameText.getText().isEmpty()) {
                errorStatus(false, true, false, false, false, false);
            } else if (ageText.getText().isEmpty()) {
                errorStatus(false, false, true, false, false, false);
            } else if (bdayChooser.getCalendar() == null) {
                errorStatus(false, false, false, true, false, false);
            } else if (yearLevelText.getSelectedIndex() == 0) {
                errorStatus(false, false, false, false, true, false);
            } else if (sectionText.getText().isEmpty()) {
                errorStatus(false, false, false, false, false, true);
            } else {
                errorStatus(false, false, false, false, false, false);
                title.setText("Vital Signs");
>>>>>>> origin/master
                cardLayout.show(mainCard, "secondPage");
                pageCount++;
                previous.setEnabled(true);
                confirm.setVisible(false);
                confirm.setEnabled(false);
            }

        } else if (pageCount == 1) {

            if (bpText.getText().isEmpty()) {
<<<<<<< HEAD
                secondPageError(true, false, false, false, false, false, false, false, "Please fill blodd preasure.");
            } else if (tempText.getText().isEmpty()) {
                secondPageError(false, true, false, false, false, false, false, false, "Please fill Temperature information.");
            } else if (rrText.getText().isEmpty()) {
                secondPageError(false, false, false, false, false, false, true, false, "Please fill respiratory.");
            } else if (hrText.getText().isEmpty()) {
                secondPageError(false, false, false, true, false, false, false, false, "Please fill hearth rate.");
            } else if (chiefCompText.getText().isEmpty()) {
                secondPageError(false, false, false, false, false, false, false, true, "Please fill chief complaint.");

            } else if (contactTracingText.getText().isEmpty()) {
                secondPageError(false, false, true, false, false, false, false, false, "Please fill contact tracing.");
            } else if (allergiesText.getText().isEmpty()) {
                secondPageError(false, false, false, false, true, false, false, false, "Please fill allergies or (none).");
            } else if (painScale.getSelectedIndex() == 0) {
                secondPageError(false, false, false, false, false, true, false, false, "Please fill select pain scale.");
            } else if (painScale.getSelectedIndex() == 0) {

            } else if (!bpText.getText().matches("[0-9]+")) {

                secondPageError(true, false, false, false, false, false, false, false, "Please enter a valid number for blood preasure.");
            } else if (!tempText.getText().matches("[0-9]+")) {
                secondPageError(false, true, false, false, false, false, false, false, "Please enter a valid number for temperature.");
            } else if (!rrText.getText().matches("[0-9]+")) {
                secondPageError(false, false, false, false, false, false, true, false, "Please enter a valid number for respiratory rate.");
            } else if (!hrText.getText().matches("[0-9]+")) {
                secondPageError(false, false, false, true, false, false, false, false, "Please enter a valid number for hearth rate.");

            } else {
                secondPageError(false, false, false, false, false, false, false, false, "");
=======
                JOptionPane.showMessageDialog(null, "Please Fill BP Text", "Empty!!", JOptionPane.INFORMATION_MESSAGE);
            } else if (hrText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill Heart Rate", "Empty!!", JOptionPane.INFORMATION_MESSAGE);
            } else {
>>>>>>> origin/master
                title.setText("Diagnosis");
                cardLayout.show(mainCard, "thirdPage");
                pageCount++;

                nextPage.setEnabled(true);

                nextPage.setVisible(false);
                nextPage.setEnabled(false);

                confirm.setVisible(true);
                confirm.setEnabled(true);
            }

        }

    }//GEN-LAST:event_nextPageActionPerformed

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        nextPage.setEnabled(true);
        if (pageCount == 1) {
            title.setText("Basic Information");
            cardLayout.show(mainCard, "firstPage");
            pageCount = 0;
            previous.setEnabled(false);

            nextPage.setVisible(true);
            nextPage.setEnabled(true);
            confirm.setVisible(false);
            confirm.setEnabled(false);
        } else if (pageCount == 2) {
<<<<<<< HEAD
            title.setText("Vital Signs / Other Information");
=======
            title.setText("Vital Signs");
>>>>>>> origin/master
            cardLayout.show(mainCard, "secondPage");
            pageCount = 1;
            nextPage.setEnabled(true);

            nextPage.setVisible(true);
            nextPage.setEnabled(true);

            confirm.setVisible(false);
            confirm.setEnabled(false);

        }

    }//GEN-LAST:event_previousActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        try {

            LocalDateTime currentDateTime = LocalDateTime.now();
<<<<<<< HEAD
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMMM dd, yyyy");
            String birthDay = "";
            String age = "";
=======
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-HH:mm:ss-yyyy-MM-dd");
            String birthDay = "";
            int age = 0;
>>>>>>> origin/master
            String studentId = studIDText.getText();
            String name = nameText.getText();
            if (bdayChooser.getCalendar() != null) {
                birthDay = bdayChooser.getCalendar().getTime().toString();
            }
            if (!ageText.getText().isEmpty()) {
<<<<<<< HEAD
                age = ageText.getText();
            }

            String yearLvl = yearLevelText.getSelectedItem().toString();
            String section = sectionText.getText();
=======
                age = Integer.parseInt(ageText.getText());
            }

            String yearLvl = yearLevelText.getSelectedItem().toString();
>>>>>>> origin/master
            String timeIn = currentDateTime.format(formatter);

            // Vital Signs
            String bp = bpText.getText();
            String hr = hrText.getText();
            String rr = rrText.getText();
            String temp = tempText.getText();
            String allergies = allergiesText.getText();
<<<<<<< HEAD
            String painSc = painScale.getSelectedItem().toString();
=======
>>>>>>> origin/master
            String contactTracing = contactTracingText.getText();
            String chiefCom = chiefCompText.getText();

            // Diagnosis
            String objective = objectiveText.getText();
            String subjective = subjectiveText.getText();
            String asses = assestmentText.getText();
            String recom = recomText.getText();

<<<<<<< HEAD
            if (objective.isEmpty()) {
                thirdPageError(true, false, false, false, "Please fill objective.");
            } else if (subjective.isEmpty()) {
                thirdPageError(false, true, false, false, "please fill subjective.");
            } else if (asses.isEmpty()) {
                thirdPageError(false, false, true, false, "Please fill assesment.");
            } else if (recom.isEmpty()) {
                thirdPageError(false, false, false, true, "Please fill recomendation.");
            } else {
                thirdPageError(false, false, false, false, "");

                PatientsRecord patient = new PatientsRecord();
                FormatDate formatedDate = new FormatDate();
                patient.setId(studentId);
                patient.setName(name);
                DateFormat dateFormat = new SimpleDateFormat("EEE, MMMM dd, yyyy", Locale.ENGLISH);

                Date date = bdayChooser.getDate();
                String formattedDate = dateFormat.format(date);
                patient.setBirth(formattedDate);
                patient.setAge(age);
                patient.setYear(yearLvl);
                patient.setSection(section);
                patient.setDate(formatedDate.formattedDate());
                patient.setAdviser("");
                patient.setChiefComplaints(chiefCom);
                patient.setAlergies(allergies);
                patient.setVitalSigns("BP:" + bp + ", HR:" + hr + ", RR:" + rr + ", TEMP:" + temp);
                patient.setPainScale(painSc);
                patient.setLastDay_InCampus("");
                patient.setContactTracing(contactTracing);
                patient.setSubjective(subjective);
                patient.setObjective(objective);
                patient.setAssesment(asses);
                patient.setPlans_And_Reccomendation(recom);
                patient.setStatus("new");
                patient.setDateReleased("");

                PatientServiceImpl patientService = new PatientServiceImpl();

                System.out.println(counter);
                if (confirm.getText().toLowerCase().equals("update")) {
                    patient.setCount(counter);
                    if (patientService.updatePatientData(counter, patient)) {
                        JOptionPane.showMessageDialog(null, "Patient Data Updated");
                        this.dispose();
                    }
                } else {
                    if (idHolderForStatus != 0) {

                        try ( SessionFactory factory = new Configuration()
                                .configure("/DatabaseSettings/hibernate.cfg.xml")
                                .addAnnotatedClass(PatientsRecord.class)
                                .buildSessionFactory();  Session session = factory.openSession()) {

                            CriteriaBuilder builder = session.getCriteriaBuilder();
                            CriteriaQuery<PatientsRecord> query = builder.createQuery(PatientsRecord.class);
                            Root<PatientsRecord> root = query.from(PatientsRecord.class);

                            Predicate predicate = builder.equal(root.get("count"), idHolderForStatus);
                            query.where(predicate);

                            List<PatientsRecord> results = session.createQuery(query).getResultList();
                            if (results != null && !results.isEmpty()) {
                                PatientsRecord patientRecord = results.get(0);
                              
                                patientRecord.setDateReleased(formatedDate.formattedDate());
                                patientRecord.setStatus("old");
                                if (patientService.insertPatient(patient) && patientService.updatePatientStatus(patientRecord)) {
                                    JOptionPane.showMessageDialog(null, "Patient Data Inserted");
                                    this.dispose();
                                }
                            }

                        } catch (Exception e) {
                            // Handle exceptiowns
                            e.printStackTrace();
                        }

                    } else {
                        if (patientService.insertPatient(patient)) {
                            JOptionPane.showMessageDialog(null, "Patient Data Inserted");
                            this.dispose();
                        }
                    }

                }

=======
            String message = "--- Student Information ---\n"
                    + "Student ID: " + studentId + "\n"
                    + "Name: " + name + "\n"
                    + "Birthday: " + birthDay + "\n"
                    + "Age: " + age + "\n"
                    + "Year Level: " + yearLvl + "\n"
                    + "Time In: " + timeIn + "\n"
                    + "--- Vital Signs ---\n"
                    + "Blood Pressure: " + bp + "\n"
                    + "Heart Rate: " + hr + "\n"
                    + "Respiratory Rate: " + rr + "\n"
                    + "Temperature: " + temp + "\n"
                    + "Allergies: " + allergies + "\n"
                    + "Contact Tracing: " + contactTracing + "\n"
                    + "Chief Complaint: " + chiefCom + "\n"
                    + "--- Diagnosis ---\n"
                    + "Objective: " + objective + "\n"
                    + "Subjective: " + subjective + "\n"
                    + "Assessment: " + asses + "\n"
                    + "Recommendation: " + recom + "\n";

            if (objective.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill up Objective!!", "Empty", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, message, "Student Information", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> origin/master
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_confirmActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pageCount = 0;
    }//GEN-LAST:event_formWindowOpened

<<<<<<< HEAD
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void rrErrorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rrErrorFocusGained
        JOptionPane.showMessageDialog(null, "YES");
    }//GEN-LAST:event_rrErrorFocusGained

    private void rrErrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rrErrorMouseClicked
        JOptionPane.showMessageDialog(null, "YES");
    }//GEN-LAST:event_rrErrorMouseClicked

=======
>>>>>>> origin/master
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddingPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddingPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddingPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddingPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

<<<<<<< HEAD
    public void firstPageError(boolean studStatus, boolean nameStatus, boolean ageStatu, boolean birthStatus, boolean yearStatus, boolean sectStatus, String errorMessage) {
=======
    public void errorStatus(boolean studStatus, boolean nameStatus, boolean ageStatu, boolean birthStatus, boolean yearStatus, boolean sectStatus) {
>>>>>>> origin/master
        studError.setVisible(studStatus);
        nameError.setVisible(nameStatus);
        ageError.setVisible(ageStatu);
        birthError.setVisible(birthStatus);
        yearError.setVisible(yearStatus);
        sectError.setVisible(sectStatus);

        if (studStatus) {
<<<<<<< HEAD
            studError.setText(errorMessage);
=======
            studError.setText("Please fill student id");
>>>>>>> origin/master
        } else {
            studError.setText("");
            studError.setVisible(false);
        }
        if (nameStatus) {
<<<<<<< HEAD
            nameError.setText(errorMessage);
=======
            nameError.setText("Please fill student name.");
>>>>>>> origin/master
        } else {
            nameError.setText("");
            nameError.setVisible(false);
        }
        if (ageStatu) {
<<<<<<< HEAD
            ageError.setText(errorMessage);
=======
            ageError.setText("Please fill age.");
>>>>>>> origin/master
        } else {
            ageError.setText("");
            ageError.setVisible(false);
        }
        if (birthStatus) {
<<<<<<< HEAD
            birthError.setText(errorMessage);
=======
            birthError.setText("Please fill birthday.");
>>>>>>> origin/master
        } else {
            birthError.setText("");
            birthError.setVisible(false);
        }
        if (yearStatus) {
<<<<<<< HEAD
            yearError.setText(errorMessage);
=======
            yearError.setText("Please fill year level.");
>>>>>>> origin/master
        } else {
            yearError.setText("");
            yearError.setVisible(false);
        }
        if (sectStatus) {
<<<<<<< HEAD
            sectError.setText(errorMessage);
=======
            sectError.setText("Please fill section.");
>>>>>>> origin/master
        } else {
            sectError.setText("");
            sectError.setVisible(false);
        }

    }

<<<<<<< HEAD
    public void secondPageError(boolean bpStatus, boolean tempStatus, boolean ctStatus, boolean hearthStatus, boolean allStatus, boolean painStatus, boolean rrStatus, boolean ccStatus, String errorMessage) {
        bpError.setVisible(bpStatus);
        tempError.setVisible(tempStatus);
        ctError.setVisible(ctStatus);
        hrError.setVisible(hearthStatus);
        allError.setVisible(allStatus);
        painError.setVisible(painStatus);
        rrError.setVisible(rrStatus);
        ccError.setVisible(ccStatus);

        if (bpStatus) {
            bpError.setText(errorMessage);
        } else {
            bpError.setText("");
            bpError.setVisible(false);
        }
        if (tempStatus) {
            tempError.setText(errorMessage);
        } else {
            tempError.setText("");
            tempError.setVisible(false);
        }
        if (ctStatus) {
            ctError.setText(errorMessage);
        } else {
            ctError.setText("");
            ctError.setVisible(false);
        }
        if (hearthStatus) {
            hrError.setText(errorMessage);
        } else {
            hrError.setText("");
            hrError.setVisible(false);
        }
        if (allStatus) {
            allError.setText(errorMessage);
        } else {
            allError.setText("");
            allError.setVisible(false);
        }
        if (painStatus) {
            painError.setText(errorMessage);
        } else {
            painError.setText("");
            painError.setVisible(false);
        }
        if (rrStatus) {
            rrError.setText(errorMessage);
        } else {
            rrError.setText("");
            rrError.setVisible(false);
        }
        if (ccStatus) {
            ccError.setText(errorMessage);
        } else {
            ccError.setText("");
            ccError.setVisible(false);
        }

    }

    public void thirdPageError(boolean obStatus, boolean subStatus, boolean asseStatus, boolean recStatus, String errorMessage) {
        objectiveError.setVisible(obStatus);
        subjectiveError.setVisible(subStatus);
        assestmentError.setVisible(asseStatus);
        recError.setVisible(recStatus);

        if (obStatus) {
            objectiveError.setText(errorMessage);
        } else {
            objectiveError.setText("");
            objectiveError.setVisible(false);
        }
        if (subStatus) {
            subjectiveError.setText(errorMessage);
        } else {
            subjectiveError.setText("");
            subjectiveError.setVisible(false);
        }
        if (asseStatus) {
            assestmentError.setText(errorMessage);
        } else {
            assestmentError.setText("");
            assestmentError.setVisible(false);
        }
        if (recStatus) {
            recError.setText(errorMessage);
        } else {
            recError.setText("");
            recError.setVisible(false);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageError;
    private javax.swing.JTextField ageText;
    private javax.swing.JLabel allError;
    private javax.swing.JTextField allergiesText;
    private javax.swing.JLabel assestmentError;
    private javax.swing.JTextField assestmentText;
    private com.toedter.calendar.JDateChooser bdayChooser;
    private javax.swing.JLabel birthError;
    private javax.swing.JLabel bpError;
    private javax.swing.JTextField bpText;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel ccError;
    private javax.swing.JTextField chiefCompText;
    private javax.swing.JButton confirm;
    private javax.swing.JTextField contactTracingText;
    private javax.swing.JLabel ctError;
    private javax.swing.JPanel firstPanel;
    private javax.swing.JLabel hrError;
=======

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageError;
    private javax.swing.JTextField ageText;
    private javax.swing.JTextField allergiesText;
    private javax.swing.JTextField assestmentText;
    private com.toedter.calendar.JDateChooser bdayChooser;
    private javax.swing.JLabel birthError;
    private javax.swing.JTextField bpText;
    private javax.swing.JTextField chiefCompText;
    private javax.swing.JButton confirm;
    private javax.swing.JTextField contactTracingText;
    private javax.swing.JPanel firstPanel;
>>>>>>> origin/master
    private javax.swing.JTextField hrText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel16;
=======
>>>>>>> origin/master
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
<<<<<<< HEAD
=======
    private javax.swing.JLabel jLabel5;
>>>>>>> origin/master
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainCard;
    private javax.swing.JLabel nameError;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton nextPage;
<<<<<<< HEAD
    private javax.swing.JLabel objectiveError;
    private javax.swing.JTextField objectiveText;
    private javax.swing.JLabel painError;
    private javax.swing.JComboBox<String> painScale;
    private javax.swing.JButton previous;
    private javax.swing.JLabel recError;
    private javax.swing.JTextArea recomText;
    private javax.swing.JLabel rrError;
=======
    private javax.swing.JTextField objectiveText;
    private javax.swing.JButton previous;
    private javax.swing.JTextArea recomText;
>>>>>>> origin/master
    private javax.swing.JTextField rrText;
    private javax.swing.JPanel seconPage;
    private javax.swing.JLabel sectError;
    private javax.swing.JTextField sectionText;
    private javax.swing.JLabel studError;
    private javax.swing.JTextField studIDText;
<<<<<<< HEAD
    private javax.swing.JLabel subjectiveError;
    private javax.swing.JTextField subjectiveText;
    private javax.swing.JLabel tempError;
=======
    private javax.swing.JTextField subjectiveText;
>>>>>>> origin/master
    private javax.swing.JTextField tempText;
    private javax.swing.JPanel thirdPage;
    private javax.swing.JLabel title;
    private javax.swing.JLabel yearError;
    private javax.swing.JComboBox<String> yearLevelText;
    // End of variables declaration//GEN-END:variables
}
