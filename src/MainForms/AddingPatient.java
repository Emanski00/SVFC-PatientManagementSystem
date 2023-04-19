/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package MainForms;

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

/**
 *
 * @author Eman
 */
public class AddingPatient extends javax.swing.JDialog {

    /**
     * Creates new form SuppliesAdding
     */
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
        this.cardLayout = (CardLayout) mainCard.getLayout();

        confirm.setVisible(false);

        firstPageError(false, false, false, false, false, false, "");
        secondPageError(false, false, false, false, false, false, false, false, "");
        thirdPageError(false, false, false, false, "");

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
        sectError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        seconPage = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bpText = new javax.swing.JTextField();
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
        objectiveError = new javax.swing.JLabel();
        subjectiveError = new javax.swing.JLabel();
        assestmentError = new javax.swing.JLabel();
        recError = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nextPage = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        mainCard.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Student Id");

        studIDText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        studError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        studError.setForeground(new java.awt.Color(255, 0, 0));
        studError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        studError.setText("error");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Name");

        nameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        nameError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameError.setForeground(new java.awt.Color(255, 0, 0));
        nameError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameError.setText("error");

        bdayChooser.setDoubleBuffered(false);

        birthError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        birthError.setForeground(new java.awt.Color(255, 0, 0));
        birthError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        birthError.setText("error");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Birth / Age");

        ageText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ageText.setEnabled(false);

        ageError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ageError.setForeground(new java.awt.Color(255, 0, 0));
        ageError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ageError.setText("error");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Year/Level");

        yearLevelText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yearLevelText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "Kinder", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "------------------------", "1st Year  Junior High", "2nd Year Junior High", "3rd Year Junior High", "4th Year Junior High", "------------------------", "1st Year  Senior High", "2nd Year Senior High", "------------------------", "1st Year College", "2nd Year College", "3rd Year College", "4th Year College", " " }));
        yearLevelText.setToolTipText("");

        yearError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        yearError.setForeground(new java.awt.Color(255, 0, 0));
        yearError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearError.setText("error");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Section");

        sectionText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                .addGap(93, 93, 93))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
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
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectionText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
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
        jLabel9.setText("HR - HEARTH RATE");

        hrText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        hrError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hrError.setForeground(new java.awt.Color(255, 0, 0));
        hrError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hrError.setText("error");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("CHIEF COMPLAINT");

        chiefCompText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

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
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageActionPerformed

        if (pageCount == 0) {

            if (studIDText.getText().isEmpty()) {
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
                cardLayout.show(mainCard, "secondPage");
                pageCount++;
                previous.setEnabled(true);
                confirm.setVisible(false);
                confirm.setEnabled(false);
            }

        } else if (pageCount == 1) {

            if (bpText.getText().isEmpty()) {
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
            title.setText("Vital Signs / Other Information");
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMMM dd, yyyy");
            String birthDay = "";
            String age = "";
            String studentId = studIDText.getText();
            String name = nameText.getText();
            if (bdayChooser.getCalendar() != null) {
                birthDay = bdayChooser.getCalendar().getTime().toString();
            }
            if (!ageText.getText().isEmpty()) {
                age = ageText.getText();
            }

            String yearLvl = yearLevelText.getSelectedItem().toString();
            String section = sectionText.getText();
            String timeIn = currentDateTime.format(formatter);

            // Vital Signs
            String bp = bpText.getText();
            String hr = hrText.getText();
            String rr = rrText.getText();
            String temp = tempText.getText();
            String allergies = allergiesText.getText();
            String painSc = painScale.getSelectedItem().toString();
            String contactTracing = contactTracingText.getText();
            String chiefCom = chiefCompText.getText();

            // Diagnosis
            String objective = objectiveText.getText();
            String subjective = subjectiveText.getText();
            String asses = assestmentText.getText();
            String recom = recomText.getText();

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

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_confirmActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pageCount = 0;
    }//GEN-LAST:event_formWindowOpened

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void rrErrorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rrErrorFocusGained
        JOptionPane.showMessageDialog(null, "YES");
    }//GEN-LAST:event_rrErrorFocusGained

    private void rrErrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rrErrorMouseClicked
        JOptionPane.showMessageDialog(null, "YES");
    }//GEN-LAST:event_rrErrorMouseClicked

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

    public void firstPageError(boolean studStatus, boolean nameStatus, boolean ageStatu, boolean birthStatus, boolean yearStatus, boolean sectStatus, String errorMessage) {
        studError.setVisible(studStatus);
        nameError.setVisible(nameStatus);
        ageError.setVisible(ageStatu);
        birthError.setVisible(birthStatus);
        yearError.setVisible(yearStatus);
        sectError.setVisible(sectStatus);

        if (studStatus) {
            studError.setText(errorMessage);
        } else {
            studError.setText("");
            studError.setVisible(false);
        }
        if (nameStatus) {
            nameError.setText(errorMessage);
        } else {
            nameError.setText("");
            nameError.setVisible(false);
        }
        if (ageStatu) {
            ageError.setText(errorMessage);
        } else {
            ageError.setText("");
            ageError.setVisible(false);
        }
        if (birthStatus) {
            birthError.setText(errorMessage);
        } else {
            birthError.setText("");
            birthError.setVisible(false);
        }
        if (yearStatus) {
            yearError.setText(errorMessage);
        } else {
            yearError.setText("");
            yearError.setVisible(false);
        }
        if (sectStatus) {
            sectError.setText(errorMessage);
        } else {
            sectError.setText("");
            sectError.setVisible(false);
        }

    }

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
    private javax.swing.JTextField hrText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel objectiveError;
    private javax.swing.JTextField objectiveText;
    private javax.swing.JLabel painError;
    private javax.swing.JComboBox<String> painScale;
    private javax.swing.JButton previous;
    private javax.swing.JLabel recError;
    private javax.swing.JTextArea recomText;
    private javax.swing.JLabel rrError;
    private javax.swing.JTextField rrText;
    private javax.swing.JPanel seconPage;
    private javax.swing.JLabel sectError;
    private javax.swing.JTextField sectionText;
    private javax.swing.JLabel studError;
    private javax.swing.JTextField studIDText;
    private javax.swing.JLabel subjectiveError;
    private javax.swing.JTextField subjectiveText;
    private javax.swing.JLabel tempError;
    private javax.swing.JTextField tempText;
    private javax.swing.JPanel thirdPage;
    private javax.swing.JLabel title;
    private javax.swing.JLabel yearError;
    private javax.swing.JComboBox<String> yearLevelText;
    // End of variables declaration//GEN-END:variables
}
