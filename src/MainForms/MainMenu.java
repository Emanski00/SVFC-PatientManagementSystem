<<<<<<< HEAD
package MainForms;

import SecondaryForms.ViewMedicine;
import DatabaseControl.AccountServiceImpl;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MainForms;

import ButtonColumn.TableActionCellEditor;
import ButtonColumn.TableActionCellRender;
import ButtonColumn.TableActionEvent;
>>>>>>> origin/master
import DatabaseSettings.DB_Connection;
import Entity.Medicines;
import Entity.PatientsRecord;
import Entity.UserAccount;
import java.awt.CardLayout;
import java.awt.Color;
<<<<<<< HEAD
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
=======
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
>>>>>>> origin/master
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
<<<<<<< HEAD
import org.hibernate.query.Query;

import DatabaseControl.MedicineServiceImpl;
import DatabaseControl.PatientServiceImpl;
import Globals.ExportPatientData;
import Globals.FormatDate;
import MiscForms.Loading;
import SecondaryForms.ViewPatient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
=======
>>>>>>> origin/master

/**
 *
 * @author Eman
 */
public class MainMenu extends javax.swing.JFrame {

    CardLayout cardLayout;
    DB_Connection database = null;
<<<<<<< HEAD
    SessionFactory factory = null;

    public static int idHolder = 0;

    public boolean patientSearchMode = false;
    public boolean medSearchMode = false;
    public boolean accSearchMode = false;

    // table pagination
    private int maxResults = 50; // maximum number of records to fetch
    private int maxSearchResults = 1;

    private int currentSearchPatientPage = 1; // current page number
    private int totalSearchPatientRecords = 0; // total number of records in the database
    private int totalSearchPatientPage = 0; // total number of pages

    private int currentPatientPage = 1; // current page number
    private int totalPatientRecords = 0; // total number of records in the database
    private int totalPatientPage = 0; // total number of pages

    private int currentMedPage = 1; // current page number
    private int totalMedRecords = 0; // total number of records in the database
    private int totalMedPage = 0; // total number of pages

    private int currentAccPage = 1; // current page number
    private int totalAccRecords = 0; // total number of records in the database
    private int totalAccPage = 0; // total number of pages
=======
>>>>>>> origin/master

    public MainMenu() {

        // start
        initComponents();

        // customized
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        panelsTitles.setText("Dashboard");

        // create card layout
        this.cardLayout = (CardLayout) mainCardPanel.getLayout();

        // image icon
        ImageIcon currPatient = new ImageIcon(getClass().getResource("/images/patient.png"));
        ImageIcon disPatient = new ImageIcon(getClass().getResource("/images/happy-p.png"));
        ImageIcon medIcon = new ImageIcon(getClass().getResource("/images/med.png"));
        ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/images/empty.png"));

        icon_CurrentPati.setIcon(currPatient);
        icon_Discharged.setIcon(disPatient);
        icon_TotalMed.setIcon(medIcon);
        icon_outOfMed.setIcon(emptyIcon);
        cardLayout.show(mainCardPanel, "dashboardCard");

<<<<<<< HEAD
        refreshPatientTable(currentPatientPage);

        refreshAccountTable(currentAccPage);

        refreshMedicineTable(currentMedPage);

        factory = new Configuration()
=======
        TableActionEvent patientActions = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println(row);
            }

            @Override
            public void onDelete(int row) {
                System.out.println(row);
            }

            @Override
            public void onView(int row) {
                System.out.println(row);
            }
        };
        
         TableActionEvent accountActions = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println(row);
            }

            @Override
            public void onDelete(int row) {
                System.out.println(row);
            }

            @Override
            public void onView(int row) {
                System.out.println(row);
            }
        };
        patientTable.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        patientTable.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(patientActions));

        accountsTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        accountsTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(accountActions));

        //Manage Database Connetion
//        database = new DB_Connection();
        SessionFactory factory = new Configuration()
>>>>>>> origin/master
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .addAnnotatedClass(Medicines.class)
                .addAnnotatedClass(UserAccount.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            System.out.println("Table created successfully");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
<<<<<<< HEAD
        loadPatientsCount();

        hideColumnCount(patientTable);
        hideColumnCount(medTable);
        hideColumnCount(accountsTable);
        // hideCount(logsTable);
        updateButtonStates();
        updateMedButtonStates();
        updateAccButtonStates();
    }

    public void hideColumnCount(JTable table) {
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
    }

    public void loadPatientsCount() {
        PatientServiceImpl patientService = new PatientServiceImpl();
        long currentPatients = patientService.patientCountsByStatus("new");
        long dischargedPatients = patientService.patientCountsByStatus("old");

        currentPatientsLabelCount.setText(String.valueOf(currentPatients));
        dischargedPatientLabelCount.setText(String.valueOf(dischargedPatients));
=======
>>>>>>> origin/master
    }

    /**
     * Creates new form MainMenu
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dashBtn = new javax.swing.JButton();
        suppliesBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        manageRequests = new javax.swing.JButton();
        logBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        manageAcc = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
<<<<<<< HEAD
        jLabel19 = new javax.swing.JLabel();
        manageMed = new javax.swing.JButton();
        mainCardPanel = new javax.swing.JPanel();
        patientsPanel = new javax.swing.JPanel();
=======
        mainCardPanel = new javax.swing.JPanel();
        patientsPanel = new javax.swing.JPanel();
        filterByRequest = new javax.swing.JComboBox<>();
        updateFilter = new javax.swing.JButton();
        addRecord = new javax.swing.JButton();
>>>>>>> origin/master
        refreshPatientTbl = new javax.swing.JButton();
        searchBoxPatient = new javax.swing.JTextField();
        searchPatientBtn = new javax.swing.JButton();
        patientTablePane = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
<<<<<<< HEAD
        addPatient = new javax.swing.JButton();
        editBtnPatient = new javax.swing.JButton();
        delBtnPatient = new javax.swing.JButton();
        viewPatient = new javax.swing.JButton();
        changePatientStatusBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        firstPatientTable = new javax.swing.JButton();
        previousPatientTable = new javax.swing.JButton();
        pagePatientInfo = new javax.swing.JLabel();
        nextPatientTable = new javax.swing.JButton();
        lastPatientTable = new javax.swing.JButton();
        exportAllPatientDataBtn = new javax.swing.JButton();
=======
        addPatientBtn1 = new javax.swing.JButton();
>>>>>>> origin/master
        dashboardPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        suppliesPanelCard = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        imageIcon = new javax.swing.JLabel();
<<<<<<< HEAD
        currentPatientsLabelCount = new javax.swing.JLabel();
=======
        jLabel8 = new javax.swing.JLabel();
>>>>>>> origin/master
        icon_CurrentPati = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        destribCardPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
<<<<<<< HEAD
        dischargedPatientLabelCount = new javax.swing.JLabel();
=======
        jLabel11 = new javax.swing.JLabel();
>>>>>>> origin/master
        icon_Discharged = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        destribCardPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        icon_TotalMed = new javax.swing.JLabel();
        destribCardPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        icon_outOfMed = new javax.swing.JLabel();
        destribCardPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        accountsPanel = new javax.swing.JPanel();
<<<<<<< HEAD
        addAccBtn = new javax.swing.JButton();
        refreshAccTbl = new javax.swing.JButton();
        searchAccBtn = new javax.swing.JButton();
        searchBoxAcc = new javax.swing.JTextField();
        accountsTablePane = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        editBtnAcc = new javax.swing.JButton();
        delBtnAcc = new javax.swing.JButton();
        viewAcc = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        firstAccTable = new javax.swing.JButton();
        previousAccTable = new javax.swing.JButton();
        pageAccInfo = new javax.swing.JLabel();
        nextAccTable = new javax.swing.JButton();
        lastAccTable = new javax.swing.JButton();
        logsPanel = new javax.swing.JPanel();
        refreshAccTbl1 = new javax.swing.JButton();
        searchAcc1 = new javax.swing.JButton();
        searchBoxAcc2 = new javax.swing.JTextField();
        logsTablePane = new javax.swing.JScrollPane();
        logsTable = new javax.swing.JTable();
        viewLog = new javax.swing.JButton();
        medPanel = new javax.swing.JPanel();
        filterMedBy = new javax.swing.JComboBox<>();
        addMedBtn = new javax.swing.JButton();
        refreshMedTble = new javax.swing.JButton();
        searchMedBtn = new javax.swing.JButton();
        medSearchBox = new javax.swing.JTextField();
        medTablePane = new javax.swing.JScrollPane();
        medTable = new javax.swing.JTable();
        viewMed = new javax.swing.JButton();
        delBtnMed = new javax.swing.JButton();
        editBtnMed = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        firstMedTable = new javax.swing.JButton();
        previousMedTable = new javax.swing.JButton();
        pageMedInfo = new javax.swing.JLabel();
        nextMedTable = new javax.swing.JButton();
        lastMedTable = new javax.swing.JButton();
=======
        filterByRequest1 = new javax.swing.JComboBox<>();
        accountFilterBox = new javax.swing.JButton();
        addAccBtn = new javax.swing.JButton();
        refreshAccTbl = new javax.swing.JButton();
        searchAcc = new javax.swing.JButton();
        searchBoxAcc1 = new javax.swing.JTextField();
        accountsTablePane = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        logsPanel = new javax.swing.JPanel();
        filterByRequest2 = new javax.swing.JComboBox<>();
        accountFilterBox1 = new javax.swing.JButton();
        addAccBtn1 = new javax.swing.JButton();
        refreshAccTbl1 = new javax.swing.JButton();
        searchAcc1 = new javax.swing.JButton();
        searchBoxAcc2 = new javax.swing.JTextField();
>>>>>>> origin/master
        jPanel5 = new javax.swing.JPanel();
        panelsTitles2 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        panelsTitles = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SVFC - Patient Management System");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        dashBtn.setBackground(new java.awt.Color(204, 204, 204));
<<<<<<< HEAD
=======
        dashBtn.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        dashBtn.setText("Dashboard");
        dashBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dashBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBtnActionPerformed(evt);
            }
        });

        suppliesBtn.setBackground(new java.awt.Color(204, 204, 204));
<<<<<<< HEAD
=======
        suppliesBtn.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        suppliesBtn.setText("Patients");
        suppliesBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        suppliesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
<<<<<<< HEAD
=======
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Patient");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
<<<<<<< HEAD
=======
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Logs");

        manageRequests.setBackground(new java.awt.Color(204, 204, 204));
<<<<<<< HEAD
=======
        manageRequests.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        manageRequests.setText("Users Logs");
        manageRequests.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        manageRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageRequestsActionPerformed(evt);
            }
        });

        logBtn.setText("Logout");
        logBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logBtnActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Image");

        jLabel4.setText("Name:");

        jLabel5.setText("Login As");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        manageAcc.setBackground(new java.awt.Color(204, 204, 204));
<<<<<<< HEAD
=======
        manageAcc.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        manageAcc.setText("Accounts");
        manageAcc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        manageAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAccActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
<<<<<<< HEAD
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Medicines");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Manage Accounts");

        manageMed.setBackground(new java.awt.Color(204, 204, 204));
        manageMed.setText("Medicines");
        manageMed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        manageMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageMedActionPerformed(evt);
            }
        });
=======
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Manage Accounts");
>>>>>>> origin/master

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageRequests, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suppliesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suppliesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageRequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
>>>>>>> origin/master
                    .addComponent(logBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
<<<<<<< HEAD
                        .addComponent(dashBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(manageMed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
                        .addComponent(dashBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
>>>>>>> origin/master
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dashBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suppliesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMed, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
>>>>>>> origin/master
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        mainCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainCardPanel.setLayout(new java.awt.CardLayout());

<<<<<<< HEAD
        refreshPatientTbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        refreshPatientTbl.setText("Refresh");
        refreshPatientTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPatientTblActionPerformed(evt);
            }
        });

        searchBoxPatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchBoxPatient.setForeground(new java.awt.Color(204, 204, 204));
        searchBoxPatient.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        searchBoxPatient.setText("Search Here");
        searchBoxPatient.setToolTipText("");
        searchBoxPatient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxPatientFocusLost(evt);
            }
        });
        searchBoxPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxPatientActionPerformed(evt);
            }
        });
        searchBoxPatient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchBoxPatientKeyPressed(evt);
            }
        });

        searchPatientBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N
        searchPatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientBtnActionPerformed(evt);
            }
        });

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P_ID", "Student ID / ID No.", "Name", "Date Admitted", "Date Released", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
=======
        filterByRequest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FILTER BY", "OLD", "NEW", "ALL" }));
        filterByRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByRequestActionPerformed(evt);
            }
        });

        updateFilter.setText("Update");

        addRecord.setText("New Record");
        addRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecordActionPerformed(evt);
            }
        });

        refreshPatientTbl.setText("Refresh");

        searchBoxPatient.setToolTipText("");

        searchPatientBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "P_ID", "Name", "Age", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
>>>>>>> origin/master
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientTable.setRowHeight(45);
<<<<<<< HEAD
        patientTable.getTableHeader().setReorderingAllowed(false);
        patientTablePane.setViewportView(patientTable);

        addPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admitpatient.png"))); // NOI18N
        addPatient.setText("Add Patient");
        addPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientActionPerformed(evt);
            }
        });

        editBtnPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        editBtnPatient.setText("Edit");
        editBtnPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnPatientActionPerformed(evt);
            }
        });

        delBtnPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/remove.png"))); // NOI18N
        delBtnPatient.setText("Delete");
        delBtnPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnPatientActionPerformed(evt);
            }
        });

        viewPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/detail.png"))); // NOI18N
        viewPatient.setText("View Details");
        viewPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPatientActionPerformed(evt);
            }
        });

        changePatientStatusBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/discharged.png"))); // NOI18N
        changePatientStatusBtn.setText("Discharged");
        changePatientStatusBtn.setToolTipText("");
        changePatientStatusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePatientStatusBtnActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstPatientTable.setText("First");
        firstPatientTable.setEnabled(false);
        firstPatientTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstPatientTableActionPerformed(evt);
            }
        });
        jPanel6.add(firstPatientTable);

        previousPatientTable.setText("Previous");
        previousPatientTable.setEnabled(false);
        previousPatientTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPatientTableActionPerformed(evt);
            }
        });
        jPanel6.add(previousPatientTable);

        pagePatientInfo.setText("jLabel7");
        jPanel6.add(pagePatientInfo);

        nextPatientTable.setText("Next");
        nextPatientTable.setEnabled(false);
        nextPatientTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPatientTableActionPerformed(evt);
            }
        });
        jPanel6.add(nextPatientTable);

        lastPatientTable.setText("Last");
        lastPatientTable.setEnabled(false);
        lastPatientTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPatientTableActionPerformed(evt);
            }
        });
        jPanel6.add(lastPatientTable);

        exportAllPatientDataBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export-all.png"))); // NOI18N
        exportAllPatientDataBtn.setText("Export All");
        exportAllPatientDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportAllPatientDataBtnActionPerformed(evt);
=======
        patientTablePane.setViewportView(patientTable);

        addPatientBtn1.setText("Add Patient");
        addPatientBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientBtn1ActionPerformed(evt);
>>>>>>> origin/master
            }
        });

        javax.swing.GroupLayout patientsPanelLayout = new javax.swing.GroupLayout(patientsPanel);
        patientsPanel.setLayout(patientsPanelLayout);
        patientsPanelLayout.setHorizontalGroup(
            patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientTablePane)
<<<<<<< HEAD
                    .addGroup(patientsPanelLayout.createSequentialGroup()
                        .addComponent(editBtnPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delBtnPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(viewPatient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changePatientStatusBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportAllPatientDataBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addGap(180, 180, 180)
                        .addComponent(addPatient)
                        .addGap(7, 7, 7)
                        .addComponent(refreshPatientTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBoxPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
=======
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsPanelLayout.createSequentialGroup()
                        .addComponent(filterByRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(searchBoxPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPatientBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshPatientTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
>>>>>>> origin/master
                .addContainerGap())
        );
        patientsPanelLayout.setVerticalGroup(
            patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchPatientBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                    .addComponent(searchBoxPatient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(editBtnPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(delBtnPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(refreshPatientTbl, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(viewPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addGroup(patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(changePatientStatusBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(exportAllPatientDataBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
=======
                    .addComponent(searchBoxPatient, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterByRequest)
                    .addComponent(updateFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(patientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPatientBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshPatientTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(patientTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
>>>>>>> origin/master
        );

        mainCardPanel.add(patientsPanel, "patientCard");

        dashboardPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        suppliesPanelCard.setBackground(new java.awt.Color(137, 207, 240));
        suppliesPanelCard.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        suppliesPanelCard.setPreferredSize(new java.awt.Dimension(194, 180));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Current Patients");

        imageIcon.setForeground(new java.awt.Color(255, 255, 255));
        imageIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

<<<<<<< HEAD
        currentPatientsLabelCount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        currentPatientsLabelCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentPatientsLabelCount.setText("0");
=======
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");
>>>>>>> origin/master

        javax.swing.GroupLayout suppliesPanelCardLayout = new javax.swing.GroupLayout(suppliesPanelCard);
        suppliesPanelCard.setLayout(suppliesPanelCardLayout);
        suppliesPanelCardLayout.setHorizontalGroup(
            suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliesPanelCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suppliesPanelCardLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(36, 36, 36)
                        .addComponent(icon_CurrentPati, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageIcon))
                    .addGroup(suppliesPanelCardLayout.createSequentialGroup()
                        .addGroup(suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .addComponent(currentPatientsLabelCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
>>>>>>> origin/master
                        .addContainerGap())))
        );
        suppliesPanelCardLayout.setVerticalGroup(
            suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliesPanelCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                .addComponent(currentPatientsLabelCount)
=======
                .addComponent(jLabel8)
>>>>>>> origin/master
                .addGroup(suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suppliesPanelCardLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageIcon)
                        .addGap(31, 31, 31))
                    .addGroup(suppliesPanelCardLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(suppliesPanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(icon_CurrentPati, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        destribCardPanel.setBackground(new java.awt.Color(137, 194, 255));
        destribCardPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        destribCardPanel.setPreferredSize(new java.awt.Dimension(194, 180));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Discharged Patients");
        jLabel10.setToolTipText("");

<<<<<<< HEAD
        dischargedPatientLabelCount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dischargedPatientLabelCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dischargedPatientLabelCount.setText("0");
=======
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("0");
>>>>>>> origin/master

        javax.swing.GroupLayout destribCardPanelLayout = new javax.swing.GroupLayout(destribCardPanel);
        destribCardPanel.setLayout(destribCardPanelLayout);
        destribCardPanelLayout.setHorizontalGroup(
            destribCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(destribCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                    .addComponent(dischargedPatientLabelCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
>>>>>>> origin/master
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, destribCardPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(icon_Discharged, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        destribCardPanelLayout.setVerticalGroup(
            destribCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                .addComponent(dischargedPatientLabelCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon_Discharged, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
=======
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon_Discharged, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
>>>>>>> origin/master
                .addContainerGap())
        );

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
<<<<<<< HEAD
=======
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel23.setText("Dashboard");

        destribCardPanel1.setBackground(new java.awt.Color(102, 204, 0));
        destribCardPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        destribCardPanel1.setPreferredSize(new java.awt.Dimension(194, 180));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total Medicines");
        jLabel14.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("0");

        javax.swing.GroupLayout destribCardPanel1Layout = new javax.swing.GroupLayout(destribCardPanel1);
        destribCardPanel1.setLayout(destribCardPanel1Layout);
        destribCardPanel1Layout.setHorizontalGroup(
            destribCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(destribCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, destribCardPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(icon_TotalMed, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        destribCardPanel1Layout.setVerticalGroup(
            destribCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                .addComponent(icon_TotalMed, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
=======
                .addComponent(icon_TotalMed, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
>>>>>>> origin/master
                .addContainerGap())
        );

        destribCardPanel2.setBackground(new java.awt.Color(102, 255, 102));
        destribCardPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        destribCardPanel2.setPreferredSize(new java.awt.Dimension(194, 180));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Out of Stocks - Medicine");
        jLabel16.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("0");

        javax.swing.GroupLayout destribCardPanel2Layout = new javax.swing.GroupLayout(destribCardPanel2);
        destribCardPanel2.setLayout(destribCardPanel2Layout);
        destribCardPanel2Layout.setHorizontalGroup(
            destribCardPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(destribCardPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, destribCardPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(icon_outOfMed, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        destribCardPanel2Layout.setVerticalGroup(
            destribCardPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                .addComponent(icon_outOfMed, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
=======
                .addComponent(icon_outOfMed, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
>>>>>>> origin/master
                .addContainerGap())
        );

        destribCardPanel3.setBackground(new java.awt.Color(255, 255, 255));
        destribCardPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        destribCardPanel3.setPreferredSize(new java.awt.Dimension(194, 180));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
<<<<<<< HEAD
=======
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Top Cases");
        jLabel18.setToolTipText("");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Headache" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout destribCardPanel3Layout = new javax.swing.GroupLayout(destribCardPanel3);
        destribCardPanel3.setLayout(destribCardPanel3Layout);
        destribCardPanel3Layout.setHorizontalGroup(
            destribCardPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, destribCardPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(destribCardPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
<<<<<<< HEAD
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
=======
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
>>>>>>> origin/master
                .addContainerGap())
        );
        destribCardPanel3Layout.setVerticalGroup(
            destribCardPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destribCardPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
<<<<<<< HEAD
                            .addComponent(destribCardPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                            .addComponent(suppliesPanelCard, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destribCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(destribCardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
=======
                            .addComponent(destribCardPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(suppliesPanelCard, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destribCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(destribCardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
>>>>>>> origin/master
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(destribCardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destribCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suppliesPanelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destribCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destribCardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 188, Short.MAX_VALUE))
                    .addComponent(destribCardPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainCardPanel.add(dashboardPanel, "dashboardCard");

<<<<<<< HEAD
        addAccBtn.setLabel("Add Account");
        addAccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccBtnActionPerformed(evt);
            }
        });

        refreshAccTbl.setText("Refresh");
        refreshAccTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshAccTblActionPerformed(evt);
            }
        });

        searchAccBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N
        searchAccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAccBtnActionPerformed(evt);
            }
        });

        searchBoxAcc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchBoxAcc.setForeground(new java.awt.Color(204, 204, 204));
        searchBoxAcc.setText("Search Here");
        searchBoxAcc.setToolTipText("");
        searchBoxAcc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxAccFocusLost(evt);
            }
        });
        searchBoxAcc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchBoxAccKeyPressed(evt);
            }
        });

        accountsTablePane.setViewportView(null);

        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Id", "Name", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
=======
        filterByRequest1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        accountFilterBox.setText("Update");

        addAccBtn.setLabel("Add Account");

        refreshAccTbl.setText("Refresh");

        searchAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N

        searchBoxAcc1.setToolTipText("");

        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Count", "User Id", "Name", "Password", "Username", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
>>>>>>> origin/master
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accountsTable.setRowHeight(45);
<<<<<<< HEAD
        accountsTable.getTableHeader().setReorderingAllowed(false);
        accountsTablePane.setViewportView(accountsTable);

        editBtnAcc.setText("Edit");
        editBtnAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnAccActionPerformed(evt);
            }
        });

        delBtnAcc.setText("Delete");
        delBtnAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnAccActionPerformed(evt);
            }
        });

        viewAcc.setText("View Details");
        viewAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAccActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstAccTable.setText("First");
        firstAccTable.setEnabled(false);
        firstAccTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstAccTableActionPerformed(evt);
            }
        });
        jPanel7.add(firstAccTable);

        previousAccTable.setText("Previous");
        previousAccTable.setEnabled(false);
        previousAccTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousAccTableActionPerformed(evt);
            }
        });
        jPanel7.add(previousAccTable);

        pageAccInfo.setText("jLabel7");
        jPanel7.add(pageAccInfo);

        nextAccTable.setText("Next");
        nextAccTable.setEnabled(false);
        nextAccTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextAccTableActionPerformed(evt);
            }
        });
        jPanel7.add(nextAccTable);

        lastAccTable.setText("Last");
        lastAccTable.setEnabled(false);
        lastAccTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastAccTableActionPerformed(evt);
            }
        });
        jPanel7.add(lastAccTable);

=======
        accountsTablePane.setViewportView(accountsTable);

>>>>>>> origin/master
        javax.swing.GroupLayout accountsPanelLayout = new javax.swing.GroupLayout(accountsPanel);
        accountsPanel.setLayout(accountsPanelLayout);
        accountsPanelLayout.setHorizontalGroup(
            accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accountsPanelLayout.createSequentialGroup()
<<<<<<< HEAD
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBoxAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAccBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountsPanelLayout.createSequentialGroup()
                        .addComponent(editBtnAcc, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delBtnAcc, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewAcc, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addGap(147, 147, 147)
                        .addComponent(addAccBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshAccTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(accountsTablePane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
=======
                        .addComponent(filterByRequest1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountFilterBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(searchBoxAcc1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addAccBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshAccTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(accountsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(accountsTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                    .addContainerGap()))
>>>>>>> origin/master
        );
        accountsPanelLayout.setVerticalGroup(
            accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
<<<<<<< HEAD
                    .addComponent(searchAccBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBoxAcc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(editBtnAcc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(delBtnAcc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(viewAcc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(refreshAccTbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(addAccBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountsTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
=======
                    .addComponent(searchAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBoxAcc1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterByRequest1)
                    .addComponent(accountFilterBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAccBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshAccTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(424, Short.MAX_VALUE))
            .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountsPanelLayout.createSequentialGroup()
                    .addContainerGap(88, Short.MAX_VALUE)
                    .addComponent(accountsTablePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
>>>>>>> origin/master
        );

        mainCardPanel.add(accountsPanel, "accountsCard");

<<<<<<< HEAD
=======
        filterByRequest2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        accountFilterBox1.setText("Update");

        addAccBtn1.setLabel("Add Account");

>>>>>>> origin/master
        refreshAccTbl1.setText("Refresh");

        searchAcc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N

        searchBoxAcc2.setToolTipText("");

<<<<<<< HEAD
        logsTablePane.setViewportView(null);

        logsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Log Message", "Threat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        logsTable.setRowHeight(45);
        logsTable.getTableHeader().setReorderingAllowed(false);
        logsTablePane.setViewportView(logsTable);

        viewLog.setText("View Details");
        viewLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLogActionPerformed(evt);
            }
        });

=======
>>>>>>> origin/master
        javax.swing.GroupLayout logsPanelLayout = new javax.swing.GroupLayout(logsPanel);
        logsPanel.setLayout(logsPanelLayout);
        logsPanelLayout.setHorizontalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logsPanelLayout.createSequentialGroup()
<<<<<<< HEAD
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logsPanelLayout.createSequentialGroup()
                        .addGap(10, 809, Short.MAX_VALUE)
                        .addComponent(searchBoxAcc2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAcc1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(logsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(logsPanelLayout.createSequentialGroup()
                                .addComponent(viewLog, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshAccTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(logsTablePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE))))
                .addContainerGap())
=======
                .addContainerGap()
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logsPanelLayout.createSequentialGroup()
                        .addComponent(filterByRequest2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountFilterBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(searchBoxAcc2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAcc1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addAccBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshAccTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
>>>>>>> origin/master
        );
        logsPanelLayout.setVerticalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchAcc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                    .addComponent(searchBoxAcc2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshAccTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewLog, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logsTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
=======
                    .addComponent(searchBoxAcc2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterByRequest2)
                    .addComponent(accountFilterBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAccBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshAccTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(424, Short.MAX_VALUE))
>>>>>>> origin/master
        );

        mainCardPanel.add(logsPanel, "logsCard");
        logsPanel.getAccessibleContext().setAccessibleName("");

<<<<<<< HEAD
        medPanel.setName(""); // NOI18N

        filterMedBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Name", "Brand", " " }));

        addMedBtn.setText("Add Medicine");
        addMedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMedBtnActionPerformed(evt);
            }
        });

        refreshMedTble.setText("Refresh");
        refreshMedTble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMedTbleActionPerformed(evt);
            }
        });

        searchMedBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_1.png"))); // NOI18N
        searchMedBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchMedBtnFocusLost(evt);
            }
        });
        searchMedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMedBtnActionPerformed(evt);
            }
        });
        searchMedBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchMedBtnKeyPressed(evt);
            }
        });

        medSearchBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        medSearchBox.setForeground(new java.awt.Color(204, 204, 204));
        medSearchBox.setText("Search Here");
        medSearchBox.setToolTipText("");
        medSearchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                medSearchBoxFocusLost(evt);
            }
        });
        medSearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                medSearchBoxKeyPressed(evt);
            }
        });

        medTablePane.setViewportView(null);

        medTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Count", "ID", "Name", "Brand", "ExpDate", "Quantity", "Status", "Guide"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        medTable.setRowHeight(45);
        medTable.getTableHeader().setReorderingAllowed(false);
        medTablePane.setViewportView(medTable);

        viewMed.setText("View Details");
        viewMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMedActionPerformed(evt);
            }
        });

        delBtnMed.setText("Delete");
        delBtnMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnMedActionPerformed(evt);
            }
        });

        editBtnMed.setText("Edit");
        editBtnMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnMedActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstMedTable.setText("First");
        firstMedTable.setEnabled(false);
        firstMedTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstMedTableActionPerformed(evt);
            }
        });
        jPanel8.add(firstMedTable);

        previousMedTable.setText("Previous");
        previousMedTable.setEnabled(false);
        previousMedTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousMedTableActionPerformed(evt);
            }
        });
        jPanel8.add(previousMedTable);

        pageMedInfo.setText("jLabel7");
        jPanel8.add(pageMedInfo);

        nextMedTable.setText("Next");
        nextMedTable.setEnabled(false);
        nextMedTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextMedTableActionPerformed(evt);
            }
        });
        jPanel8.add(nextMedTable);

        lastMedTable.setText("Last");
        lastMedTable.setEnabled(false);
        lastMedTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastMedTableActionPerformed(evt);
            }
        });
        jPanel8.add(lastMedTable);

        javax.swing.GroupLayout medPanelLayout = new javax.swing.GroupLayout(medPanel);
        medPanel.setLayout(medPanelLayout);
        medPanelLayout.setHorizontalGroup(
            medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medPanelLayout.createSequentialGroup()
                        .addComponent(editBtnMed, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delBtnMed, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewMed, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshMedTble, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(medPanelLayout.createSequentialGroup()
                        .addComponent(filterMedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(medSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchMedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(medTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        medPanelLayout.setVerticalGroup(
            medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchMedBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medSearchBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterMedBy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(medPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addMedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshMedTble, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(viewMed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delBtnMed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editBtnMed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainCardPanel.add(medPanel, "medCard");

=======
>>>>>>> origin/master
        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelsTitles2.setBackground(new java.awt.Color(153, 153, 153));
        panelsTitles2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
<<<<<<< HEAD
=======
        panelsTitles2.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        panelsTitles2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelsTitles2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/svfclog.png"))); // NOI18N
        panelsTitles2.setText("St. Vincent de Ferrer College of Camarin Inc.");

        titlePanel.setBackground(new java.awt.Color(255, 204, 204));

        panelsTitles.setBackground(new java.awt.Color(153, 153, 153));
        panelsTitles.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
<<<<<<< HEAD
=======
        panelsTitles.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> origin/master
        panelsTitles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelsTitles.setText("Title");
        panelsTitles.setToolTipText("");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelsTitles, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsTitles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(panelsTitles2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(panelsTitles2)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                    .addComponent(mainCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
                    .addComponent(mainCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
>>>>>>> origin/master
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
=======
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
>>>>>>> origin/master
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logBtnActionPerformed

    }//GEN-LAST:event_logBtnActionPerformed

    private void manageRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageRequestsActionPerformed
        panelsTitles.setText("User Logs");
        titlePanel.setBackground(new Color(255, 204, 204));
<<<<<<< HEAD
        cardLayout.show(mainCardPanel, "logsCard");
=======
        cardLayout.show(mainCardPanel, "userlogsCard");
>>>>>>> origin/master
    }//GEN-LAST:event_manageRequestsActionPerformed

    private void dashBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBtnActionPerformed
        panelsTitles.setText("Dashboard");
        titlePanel.setBackground(new Color(255, 204, 204));
        cardLayout.show(mainCardPanel, "dashboardCard");
<<<<<<< HEAD

        loadPatientsCount();
=======
>>>>>>> origin/master
    }//GEN-LAST:event_dashBtnActionPerformed

    private void manageAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageAccActionPerformed
        panelsTitles.setText("Accounts");
        titlePanel.setBackground(new Color(255, 204, 204));
        cardLayout.show(mainCardPanel, "accountsCard");
    }//GEN-LAST:event_manageAccActionPerformed

    private void patientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientBtnActionPerformed
        panelsTitles.setText("Patients");
        titlePanel.setBackground(new Color(255, 204, 204));
        cardLayout.show(mainCardPanel, "patientCard");
    }//GEN-LAST:event_patientBtnActionPerformed

<<<<<<< HEAD
=======
    private void filterByRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterByRequestActionPerformed

    private void addRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addRecordActionPerformed

>>>>>>> origin/master
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

<<<<<<< HEAD
    private void addPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientActionPerformed

        AddingPatient addingPatient = new AddingPatient(null, true, 0, 0);
        addingPatient.setVisible(true);
        refreshPatientTable(currentPatientPage);


    }//GEN-LAST:event_addPatientActionPerformed

    private void addAccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAccBtnActionPerformed
        AddingAccount addingAccount = new AddingAccount(null, true, 0);
        addingAccount.setVisible(true);
        refreshAccountTable(currentAccPage);
    }//GEN-LAST:event_addAccBtnActionPerformed

    private void refreshAccTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshAccTblActionPerformed

        refreshAccountTable(currentAccPage);
    }//GEN-LAST:event_refreshAccTblActionPerformed

    private void refreshPatientTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPatientTblActionPerformed
        refreshPatientTable(currentPatientPage);
    }//GEN-LAST:event_refreshPatientTblActionPerformed

    private void manageMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageMedActionPerformed
        panelsTitles.setText("Medicines");
        titlePanel.setBackground(new Color(255, 204, 204));
        cardLayout.show(mainCardPanel, "medCard");
    }//GEN-LAST:event_manageMedActionPerformed

    private void addMedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMedBtnActionPerformed
        AddingMedicine addingMedicine = new AddingMedicine(null, true, 0);
        addingMedicine.setVisible(true);
    }//GEN-LAST:event_addMedBtnActionPerformed

    private void refreshMedTbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshMedTbleActionPerformed
        refreshMedicineTable(currentMedPage);
    }//GEN-LAST:event_refreshMedTbleActionPerformed

    private void editBtnPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnPatientActionPerformed
        int selectedViewRow = patientTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int selectedCellValue = getSelectedPatientCellValue(selectedViewRow, 0);
            AddingPatient addingPatient;

            addingPatient = new AddingPatient(null, true, selectedCellValue, 0);
            addingPatient.setVisible(true);
            refreshPatientTable(currentPatientPage);
        }


    }//GEN-LAST:event_editBtnPatientActionPerformed

    private void delBtnPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnPatientActionPerformed
        int selectedViewRow = patientTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedCellValue = getSelectedPatientCellValue(selectedViewRow, 0);
            PatientServiceImpl patientService = new PatientServiceImpl();

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure to delete this data?", "Deletion Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (patientService.deletePatient(selectedCellValue)) {
                    showMessage("Patient Data has been deleted!");
                    refreshPatientTable(currentPatientPage);
                } else {
                    showMessage("Patient Data Failed to delete, Please try again.");
                }

            }
        }
    }//GEN-LAST:event_delBtnPatientActionPerformed

    private void viewPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPatientActionPerformed
        int selectedViewRow = patientTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int selectedCellValue = getSelectedPatientCellValue(selectedViewRow, 0);
            ViewPatient viewPatient = new ViewPatient(null, true, selectedCellValue);
            viewPatient.setVisible(true);
            refreshPatientTable(currentPatientPage);
        }
    }//GEN-LAST:event_viewPatientActionPerformed

    private void editBtnAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnAccActionPerformed
        int selectedViewRow = accountsTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int selectedCellValue = getSelectedAccountCellValue(selectedViewRow, 0);
            AddingAccount addingAccount = new AddingAccount(null, true, selectedCellValue);
            addingAccount.setVisible(true);
            refreshAccountTable(currentAccPage);
        }
    }//GEN-LAST:event_editBtnAccActionPerformed

    private void delBtnAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnAccActionPerformed
        int selectedViewRow = accountsTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int selectedCellValue = getSelectedAccountCellValue(selectedViewRow, 0);
            AccountServiceImpl accountService = new AccountServiceImpl();

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure to delete this data?", "Deletion Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (accountService.deleteAccount(selectedCellValue)) {
                    showMessage("User Account has been deleted!");
                    refreshAccountTable(currentAccPage);

                } else {
                    showMessage("User Account Failed to delete, Please try again.");
                }

            }
        }
    }//GEN-LAST:event_delBtnAccActionPerformed

    private void viewAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewAccActionPerformed

    private void viewLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewLogActionPerformed

    private void editBtnMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnMedActionPerformed
        int selectedViewRow = medTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedCellValue = getSelectedMedicineCellValue(selectedViewRow, 0);
            AddingMedicine addingMedicine;

            addingMedicine = new AddingMedicine(null, true, selectedCellValue);
            addingMedicine.setVisible(true);
            refreshMedicineTable(currentMedPage);
        }


    }//GEN-LAST:event_editBtnMedActionPerformed

    private void delBtnMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnMedActionPerformed
        int selectedViewRow = medTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedCellValue = getSelectedMedicineCellValue(selectedViewRow, 0);
            MedicineServiceImpl medicineService = new MedicineServiceImpl();

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure to delete this data?", "Deletion Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (medicineService.deleteMedicineData(selectedCellValue)) {
                    showMessage("Medicine data has been deleted!");

                    // Remove the row from the table model
                    DefaultTableModel model = (DefaultTableModel) medTable.getModel();
                    model.removeRow(selectedViewRow);

                } else {
                    showMessage("Medicine data failed to delete, Please try again.");
                }
            }
            refreshMedicineTable(currentMedPage);
        }


    }//GEN-LAST:event_delBtnMedActionPerformed

    private void viewMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMedActionPerformed
        int selectedViewRow = medTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedCellValue = getSelectedMedicineCellValue(selectedViewRow, 0);
            ViewMedicine viewMedicine = new ViewMedicine(null, true, selectedCellValue);
            viewMedicine.setVisible(true);
            refreshMedicineTable(currentMedPage);
        }
    }//GEN-LAST:event_viewMedActionPerformed

    private void changePatientStatusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePatientStatusBtnActionPerformed
        int selectedViewRow = patientTable.getSelectedRow();
        if (selectedViewRow == -1) {
            JOptionPane.showMessageDialog(null, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure to discharge this patiend?", "Discharging Patient Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {

                int selectedCellValue = getSelectedPatientCellValue(selectedViewRow, 0);
                if (selectedCellValue != 0) {
                    PatientServiceImpl patientService = new PatientServiceImpl();
                    try ( SessionFactory factory = new Configuration()
                            .configure("/DatabaseSettings/hibernate.cfg.xml")
                            .addAnnotatedClass(PatientsRecord.class)
                            .buildSessionFactory();  Session session = factory.openSession()) {

                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<PatientsRecord> query = builder.createQuery(PatientsRecord.class);
                        Root<PatientsRecord> root = query.from(PatientsRecord.class);

                        Predicate predicate = builder.equal(root.get("count"), selectedCellValue);
                        query.where(predicate);

                        List<PatientsRecord> results = session.createQuery(query).getResultList();
                        if (results != null && !results.isEmpty()) {
                            FormatDate formatedDate = new FormatDate();
                            PatientsRecord patientRecord = results.get(0);
                            patientRecord.setStatus("old");
                            patientRecord.setDateReleased(formatedDate.formattedDate());
                            if (patientService.updatePatientAdmission(patientRecord)) {
                                JOptionPane.showMessageDialog(null, "Patient has been released");
                                refreshPatientTbl.doClick();
                            }
                        }

                    } catch (Exception e) {
                        // Handle exceptions
                        e.printStackTrace();
                    }

                } else {
                    showMessage("Failed to discharged patient, Please try again.");
                }
            }

        }
    }//GEN-LAST:event_changePatientStatusBtnActionPerformed

    private void searchBoxPatientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxPatientKeyPressed
        onSearchingKeyPressedMethod(searchBoxPatient);
    }//GEN-LAST:event_searchBoxPatientKeyPressed

    private void searchBoxPatientFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxPatientFocusLost
        onFocusLostMethod(searchBoxPatient);
    }//GEN-LAST:event_searchBoxPatientFocusLost

    private void searchPatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientBtnActionPerformed
        String searchTerm = searchBoxPatient.getText(); // Retrieve search term from search box
        if (!searchTerm.isBlank() || !searchTerm.isEmpty()) {
            patientSearchMode = true;
            searchPatient(searchTerm, currentPatientPage); // Call searchPatient() method with search term
        } else {
            patientSearchMode = false;
        }

    }//GEN-LAST:event_searchPatientBtnActionPerformed

    private void searchBoxPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxPatientActionPerformed
        searchPatientBtn.doClick();
    }//GEN-LAST:event_searchBoxPatientActionPerformed

    private void firstPatientTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstPatientTableActionPerformed
        if (patientSearchMode == true) {
            currentSearchPatientPage = 1;
            String text = searchBoxPatient.getText();
            searchPatient(text, currentSearchPatientPage);
        } else {
            currentPatientPage = 1; // set current page to first page
            refreshPatientTable(currentPatientPage); // refresh table with first page
            updateButtonStates(); // update button states
        }


    }//GEN-LAST:event_firstPatientTableActionPerformed

    private void previousPatientTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPatientTableActionPerformed
        if (patientSearchMode == true) {
            if (currentSearchPatientPage > 1) {
                currentSearchPatientPage--;
                String text = searchBoxPatient.getText();
                searchPatient(text, currentSearchPatientPage);
            }
        } else {
            if (currentPatientPage > 1) {
                currentPatientPage--; // decrement current page number if not already on the first page
                refreshPatientTable(currentPatientPage); // refresh table with previous page
                updateButtonStates(); // update button states
            }
        }
    }//GEN-LAST:event_previousPatientTableActionPerformed

    private void nextPatientTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPatientTableActionPerformed
        if (patientSearchMode == true) {
            currentSearchPatientPage++;
            String text = searchBoxPatient.getText();
            searchPatient(text, currentSearchPatientPage);
        } else {
            currentPatientPage++; // increment current page number
            refreshPatientTable(currentPatientPage); // refresh table with next page
            updateButtonStates(); // update button states
        }
    }//GEN-LAST:event_nextPatientTableActionPerformed

    private void lastPatientTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPatientTableActionPerformed
        if (patientSearchMode == true) {
            currentSearchPatientPage = (int) Math.ceil((double) totalSearchPatientPage / maxSearchResults);
            searchPatient(searchBoxPatient.getText(), currentSearchPatientPage);
        } else {
            currentPatientPage = (int) Math.ceil((double) totalPatientRecords / maxResults); // set current page to last page
            refreshPatientTable(currentPatientPage); // refresh table with last page
            updateButtonStates(); // update button states
        }

    }//GEN-LAST:event_lastPatientTableActionPerformed

    private void firstAccTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstAccTableActionPerformed
        if (accSearchMode == true) {
            currentAccPage = 1; // set current page to first page
            searchAccount(searchBoxAcc.getText(), currentAccPage); // refresh table with first page
            updateAccButtonStatesForSearchTerm(); // update button states
        } else {

            currentAccPage = 1; // set current page to first page
            refreshAccountTable(currentAccPage); // refresh table with first page
            updateAccButtonStates(); // update button states
        }

    }//GEN-LAST:event_firstAccTableActionPerformed

    private void previousAccTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousAccTableActionPerformed

        if (accSearchMode == true) {
            if (currentAccPage > 1) {
                currentAccPage--; // set current page to first page
                searchAccount(searchBoxAcc.getText(), currentAccPage); // refresh table with first page
                updateAccButtonStatesForSearchTerm(); // update button states
            }
        } else {
            if (currentAccPage > 1) {
                currentAccPage--; // decrement current page number if not already on the first page
                refreshAccountTable(currentAccPage); // refresh table with previous page
                updateAccButtonStates(); // update button states
            }
        }

    }//GEN-LAST:event_previousAccTableActionPerformed

    private void nextAccTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextAccTableActionPerformed
        if (accSearchMode == true) {
            currentAccPage++; // set current page to first page
            searchAccount(searchBoxAcc.getText(), currentAccPage); // refresh table with first page
            updateAccButtonStatesForSearchTerm(); // update button states
        } else {
            currentAccPage++; // increment current page number
            refreshAccountTable(currentAccPage); // refresh table with next page
            updateAccButtonStates(); // update button states
        }

    }//GEN-LAST:event_nextAccTableActionPerformed

    private void lastAccTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastAccTableActionPerformed
        if (accSearchMode == true) {
            currentAccPage = (int) Math.ceil((double) totalAccRecords / maxSearchResults); // set current page to last page
            searchAccount(searchBoxAcc.getText(), currentAccPage); // refresh table with first page
            updateAccButtonStatesForSearchTerm(); // update button states
        } else {
            currentAccPage = (int) Math.ceil((double) totalAccRecords / maxResults); // set current page to last page
            refreshPatientTable(currentAccPage); // refresh table with last page
            updateAccButtonStates(); // update button states
        }

    }//GEN-LAST:event_lastAccTableActionPerformed

    private void firstMedTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstMedTableActionPerformed
        if (medSearchMode == true) {
            currentMedPage = 1; // set current page to first page
            searchMedicine(medSearchBox.getText(), currentMedPage); // Call searchPatient() method with search term
            updateMedButtonStatesForSearchTerm(); // update button states
        } else {
            currentMedPage = 1; // set current page to first page
            refreshMedicineTable(currentMedPage); // refresh table with first page
            updateMedButtonStates(); // update button states
        }

    }//GEN-LAST:event_firstMedTableActionPerformed

    private void previousMedTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousMedTableActionPerformed

        if (medSearchMode == true) {

            if (currentMedPage > 1) {
                currentMedPage--; // decrement current page number if not already on the first page
                searchMedicine(medSearchBox.getText(), currentMedPage); // Call searchPatient() method with search term
                updateMedButtonStatesForSearchTerm();
            }

        } else {
            if (currentMedPage > 1) {
                currentMedPage--; // decrement current page number if not already on the first page
                refreshMedicineTable(currentMedPage); // refresh table with previous page
                updateMedButtonStates(); // update button states
            }
        }

    }//GEN-LAST:event_previousMedTableActionPerformed

    private void nextMedTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMedTableActionPerformed
        if (medSearchMode == true) {
            currentMedPage++; // increment current page number
            searchMedicine(medSearchBox.getText(), currentMedPage); // Call searchPatient() method with search term
            updateMedButtonStatesForSearchTerm(); // update button states
        } else {
            currentMedPage++; // increment current page number
            refreshMedicineTable(currentMedPage); // refresh table with next page
            updateMedButtonStates(); // update button states
        }
    }//GEN-LAST:event_nextMedTableActionPerformed

    private void lastMedTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastMedTableActionPerformed

        if (medSearchMode == true) {
            currentMedPage = (int) Math.ceil((double) totalMedRecords / maxSearchResults); // set current page to last page
            searchMedicine(medSearchBox.getText(), currentMedPage); // Call searchPatient() method with search term
            updateMedButtonStatesForSearchTerm(); // update button states
        } else {
            currentMedPage = (int) Math.ceil((double) totalMedRecords / maxResults); // set current page to last page
            refreshMedicineTable(currentMedPage); // refresh table with last page
            updateMedButtonStates(); // update button states
        }
    }//GEN-LAST:event_lastMedTableActionPerformed

    private void searchMedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMedBtnActionPerformed
        String searchTerm = medSearchBox.getText(); // Retrieve search term from search box
        if (!searchTerm.isBlank() || !searchTerm.isEmpty()) {
            medSearchMode = true;
            searchMedicine(searchTerm, currentMedPage); // Call searchPatient() method with search term
        } else {
            medSearchMode = false;
        }
    }//GEN-LAST:event_searchMedBtnActionPerformed

    private void searchMedBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchMedBtnFocusLost
    }//GEN-LAST:event_searchMedBtnFocusLost

    private void searchMedBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMedBtnKeyPressed
    }//GEN-LAST:event_searchMedBtnKeyPressed

    private void medSearchBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medSearchBoxKeyPressed
        onSearchingKeyPressedMethod(medSearchBox);
    }//GEN-LAST:event_medSearchBoxKeyPressed

    private void medSearchBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_medSearchBoxFocusLost
        onFocusLostMethod(medSearchBox);
    }//GEN-LAST:event_medSearchBoxFocusLost

    private void searchBoxAccFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxAccFocusLost
        onFocusLostMethod(searchBoxAcc);
    }//GEN-LAST:event_searchBoxAccFocusLost

    private void searchBoxAccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxAccKeyPressed
        onSearchingKeyPressedMethod(searchBoxAcc);
    }//GEN-LAST:event_searchBoxAccKeyPressed

    private void searchAccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAccBtnActionPerformed
        String searchTerm = searchBoxAcc.getText(); // Retrieve search term from search box
        if (!searchTerm.isBlank() || !searchTerm.isEmpty()) {
            accSearchMode = true;
            searchAccount(searchTerm, currentAccPage); // Call searchPatient() method with search term
        } else {
            accSearchMode = false;
        }
    }//GEN-LAST:event_searchAccBtnActionPerformed

    private void exportAllPatientDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportAllPatientDataBtnActionPerformed

        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to export patient data to an Excel file?", "Exporting Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            ExportPatientData exportPatient = new ExportPatientData();
            Thread t = new Thread(new Runnable() {
                public void run() {

                    // Call the exportPatientData() method here
                    exportPatient.exportPatientData();

                }
            });
            t.start();
        }

    }//GEN-LAST:event_exportAllPatientDataBtnActionPerformed

    public void onSearchingKeyPressedMethod(JTextField textField) {
        String text = textField.getText();
        if (text.equalsIgnoreCase("Search Here")) {
            textField.setText("");
            textField.setForeground(new Color(0, 0, 0));
        }
    }

    public void onFocusLostMethod(JTextField textField) {
        String text = textField.getText();
        if (text.isBlank() || text.isEmpty()) {
            textField.setText("Search Here");
            textField.setForeground(new Color(204, 204, 204));
        }
    }

    // Update button states based on current page number and total number of records
    public void updateButtonStates() {
        totalPatientPage = (int) Math.ceil((double) totalPatientRecords / maxResults);
        pagePatientInfo.setText("Showing Records of " + currentPatientPage + " out of " + totalPatientPage);

        // Disable Next button if already on the last page or no records
        if (currentPatientPage >= totalPatientPage || totalPatientRecords == 0) {
            nextPatientTable.setEnabled(false);
            lastPatientTable.setEnabled(false);

        } else {
            nextPatientTable.setEnabled(true);
            lastPatientTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentPatientPage <= 1) {
            previousPatientTable.setEnabled(false);
            firstPatientTable.setEnabled(false);
        } else {
            previousPatientTable.setEnabled(true);
            firstPatientTable.setEnabled(true);
        }
    }

    // Update button states based on current page number and total number of records
    public void updateButtonStatesForSearchTerm() {
        totalSearchPatientPage = (int) Math.ceil((double) totalSearchPatientRecords / maxSearchResults);
        pagePatientInfo.setText("Showing Records of " + currentSearchPatientPage + " out of " + totalSearchPatientPage);

        // Disable Next button if already on the last page or no records
        if (currentSearchPatientPage >= totalSearchPatientPage || totalSearchPatientRecords == 0) {
            nextPatientTable.setEnabled(false);
            lastPatientTable.setEnabled(false);

        } else {
            nextPatientTable.setEnabled(true);
            lastPatientTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentSearchPatientPage <= 1) {
            previousPatientTable.setEnabled(false);
            firstPatientTable.setEnabled(false);
        } else {
            previousPatientTable.setEnabled(true);
            firstPatientTable.setEnabled(true);
        }
    }

    public void updateAccButtonStates() {
        totalAccPage = (int) Math.ceil((double) totalAccRecords / maxResults);
        pageAccInfo.setText("Showing Records of " + currentAccPage + " out of " + totalAccPage);

        // Disable Next button if already on the last page or no records
        if (currentAccPage >= totalAccPage || totalAccRecords == 0) {
            nextAccTable.setEnabled(false);
            lastAccTable.setEnabled(false);

        } else {
            nextAccTable.setEnabled(true);
            lastAccTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentAccPage <= 1) {
            previousAccTable.setEnabled(false);
            firstAccTable.setEnabled(false);
        } else {
            previousAccTable.setEnabled(true);
            firstAccTable.setEnabled(true);
        }
    }

    public void updateMedButtonStates() {
        totalMedPage = (int) Math.ceil((double) totalMedRecords / maxResults);
        pageMedInfo.setText("Showing Records of " + currentMedPage + " out of " + totalMedPage);

        // Disable Next button if already on the last page or no records
        if (currentMedPage >= totalMedPage || totalMedRecords == 0) {
            nextMedTable.setEnabled(false);
            lastMedTable.setEnabled(false);

        } else {
            nextMedTable.setEnabled(true);
            lastMedTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentMedPage <= 1) {
            previousMedTable.setEnabled(false);
            firstMedTable.setEnabled(false);
        } else {
            previousMedTable.setEnabled(true);
            firstMedTable.setEnabled(true);
        }
    }

    public void updateMedButtonStatesForSearchTerm() {
        totalMedPage = (int) Math.ceil((double) totalMedRecords / maxSearchResults);
        pageMedInfo.setText("Showing Records of " + currentMedPage + " out of " + totalMedPage);

        // Disable Next button if already on the last page or no records
        if (currentMedPage >= totalMedPage || totalMedRecords == 0) {
            nextMedTable.setEnabled(false);
            lastMedTable.setEnabled(false);

        } else {
            nextMedTable.setEnabled(true);
            lastMedTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentMedPage <= 1) {
            previousMedTable.setEnabled(false);
            firstMedTable.setEnabled(false);
        } else {
            previousMedTable.setEnabled(true);
            firstMedTable.setEnabled(true);
        }
    }

    public void updateAccButtonStatesForSearchTerm() {
        totalAccPage = (int) Math.ceil((double) totalAccRecords / maxSearchResults);
        pageAccInfo.setText("Showing Records of " + currentAccPage + " out of " + totalAccPage);

        // Disable Next button if already on the last page or no records
        if (currentAccPage >= totalAccPage || totalAccRecords == 0) {
            nextAccTable.setEnabled(false);
            lastAccTable.setEnabled(false);

        } else {
            nextAccTable.setEnabled(true);
            lastAccTable.setEnabled(true);
        }

        // Disable Previous button if already on the first page
        if (currentAccPage <= 1) {
            previousAccTable.setEnabled(false);
            firstAccTable.setEnabled(false);
        } else {
            previousAccTable.setEnabled(true);
            firstAccTable.setEnabled(true);
        }
    }

    @Override
    public void pack() {
        super.pack(); //To change body of generated methods, choose Tools | Templates.
    }
=======
    private void addPatientBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientBtn1ActionPerformed
     new AddingPatient(null,true).setVisible(true);
    }//GEN-LAST:event_addPatientBtn1ActionPerformed
>>>>>>> origin/master

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
<<<<<<< HEAD
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
=======

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountFilterBox;
    private javax.swing.JButton accountFilterBox1;
>>>>>>> origin/master
    private javax.swing.JPanel accountsPanel;
    private javax.swing.JTable accountsTable;
    private javax.swing.JScrollPane accountsTablePane;
    private javax.swing.JButton addAccBtn;
<<<<<<< HEAD
    private javax.swing.JButton addMedBtn;
    private javax.swing.JButton addPatient;
    private javax.swing.JButton changePatientStatusBtn;
    private javax.swing.JLabel currentPatientsLabelCount;
    private javax.swing.JButton dashBtn;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JButton delBtnAcc;
    private javax.swing.JButton delBtnMed;
    private javax.swing.JButton delBtnPatient;
=======
    private javax.swing.JButton addAccBtn1;
    private javax.swing.JButton addPatientBtn1;
    private javax.swing.JButton addRecord;
    private javax.swing.JButton dashBtn;
    private javax.swing.JPanel dashboardPanel;
>>>>>>> origin/master
    private javax.swing.JPanel destribCardPanel;
    private javax.swing.JPanel destribCardPanel1;
    private javax.swing.JPanel destribCardPanel2;
    private javax.swing.JPanel destribCardPanel3;
<<<<<<< HEAD
    private javax.swing.JLabel dischargedPatientLabelCount;
    private javax.swing.JButton editBtnAcc;
    private javax.swing.JButton editBtnMed;
    private javax.swing.JButton editBtnPatient;
    private javax.swing.JButton exportAllPatientDataBtn;
    private javax.swing.JComboBox<String> filterMedBy;
    private javax.swing.JButton firstAccTable;
    private javax.swing.JButton firstMedTable;
    private javax.swing.JButton firstPatientTable;
=======
    private javax.swing.JComboBox<String> filterByRequest;
    private javax.swing.JComboBox<String> filterByRequest1;
    private javax.swing.JComboBox<String> filterByRequest2;
>>>>>>> origin/master
    private javax.swing.JLabel icon_CurrentPati;
    private javax.swing.JLabel icon_Discharged;
    private javax.swing.JLabel icon_TotalMed;
    private javax.swing.JLabel icon_outOfMed;
    public javax.swing.JLabel imageIcon;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
<<<<<<< HEAD
=======
    private javax.swing.JLabel jLabel11;
>>>>>>> origin/master
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel19;
=======
>>>>>>> origin/master
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
<<<<<<< HEAD
=======
    private javax.swing.JLabel jLabel8;
>>>>>>> origin/master
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
<<<<<<< HEAD
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastAccTable;
    private javax.swing.JButton lastMedTable;
    private javax.swing.JButton lastPatientTable;
    private javax.swing.JButton logBtn;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JTable logsTable;
    private javax.swing.JScrollPane logsTablePane;
    private javax.swing.JPanel mainCardPanel;
    private javax.swing.JButton manageAcc;
    private javax.swing.JButton manageMed;
    private javax.swing.JButton manageRequests;
    private javax.swing.JPanel medPanel;
    private javax.swing.JTextField medSearchBox;
    private javax.swing.JTable medTable;
    private javax.swing.JScrollPane medTablePane;
    private javax.swing.JButton nextAccTable;
    private javax.swing.JButton nextMedTable;
    private javax.swing.JButton nextPatientTable;
    private javax.swing.JLabel pageAccInfo;
    private javax.swing.JLabel pageMedInfo;
    private javax.swing.JLabel pagePatientInfo;
=======
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logBtn;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JPanel mainCardPanel;
    private javax.swing.JButton manageAcc;
    private javax.swing.JButton manageRequests;
>>>>>>> origin/master
    private javax.swing.JLabel panelsTitles;
    private javax.swing.JLabel panelsTitles2;
    private javax.swing.JTable patientTable;
    private javax.swing.JScrollPane patientTablePane;
    private javax.swing.JPanel patientsPanel;
<<<<<<< HEAD
    private javax.swing.JButton previousAccTable;
    private javax.swing.JButton previousMedTable;
    private javax.swing.JButton previousPatientTable;
    private javax.swing.JButton refreshAccTbl;
    private javax.swing.JButton refreshAccTbl1;
    private javax.swing.JButton refreshMedTble;
    private javax.swing.JButton refreshPatientTbl;
    private javax.swing.JButton searchAcc1;
    private javax.swing.JButton searchAccBtn;
    private javax.swing.JTextField searchBoxAcc;
    private javax.swing.JTextField searchBoxAcc2;
    private javax.swing.JTextField searchBoxPatient;
    private javax.swing.JButton searchMedBtn;
=======
    private javax.swing.JButton refreshAccTbl;
    private javax.swing.JButton refreshAccTbl1;
    private javax.swing.JButton refreshPatientTbl;
    private javax.swing.JButton searchAcc;
    private javax.swing.JButton searchAcc1;
    private javax.swing.JTextField searchBoxAcc1;
    private javax.swing.JTextField searchBoxAcc2;
    private javax.swing.JTextField searchBoxPatient;
>>>>>>> origin/master
    private javax.swing.JButton searchPatientBtn;
    private javax.swing.JButton suppliesBtn;
    private javax.swing.JPanel suppliesPanelCard;
    private javax.swing.JPanel titlePanel;
<<<<<<< HEAD
    private javax.swing.JButton viewAcc;
    private javax.swing.JButton viewLog;
    private javax.swing.JButton viewMed;
    private javax.swing.JButton viewPatient;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
=======
    private javax.swing.JButton updateFilter;
    // End of variables declaration//GEN-END:variables

>>>>>>> origin/master
    public void showMessage(String message) {

        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
<<<<<<< HEAD

    public void refreshAccountTable(int pageNumber) {
        int firstResult = (pageNumber - 1) * maxResults;
        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
            model.setRowCount(0);

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(UserAccount.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            Query<UserAccount> limiterQuery = session.createQuery("FROM UserAccount", UserAccount.class);
            limiterQuery.setMaxResults(maxResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<UserAccount> userAccounts = limiterQuery.getResultList();

            Query<UserAccount> query = session.createQuery("FROM UserAccount", UserAccount.class);
            List<UserAccount> ua = query.getResultList();

            totalAccRecords = ua.size();

            for (UserAccount userAccount : userAccounts) {
                // create a new Object array to hold the data for each row
                Object[] row = {
                    userAccount.getCount(),
                    userAccount.getName(),
                    userAccount.getUsername(),
                    "*********"
                };

                // add the row to the table model
                model.addRow(row);

            }

            // set the table model for the JTable
            accountsTable.setModel(model);

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }

    public void refreshPatientTable(int pageNumber) {
        int firstResult = (pageNumber - 1) * maxResults;
        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
            int rowCount = model.getRowCount();

            // System.out.println(rowCount);
            for (int i = 0; i < rowCount; i++) {
                model.removeRow(0);
                //System.out.println(i);
            }

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(PatientsRecord.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            Query<PatientsRecord> limiterQuery = session.createQuery("FROM PatientsRecord", PatientsRecord.class);
            limiterQuery.setMaxResults(maxResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<PatientsRecord> patientsRecord = limiterQuery.getResultList();

            Query<PatientsRecord> query = session.createQuery("FROM PatientsRecord", PatientsRecord.class);
            List<PatientsRecord> pr = query.getResultList();
            String dateReleased = "";
            totalPatientRecords = pr.size();
            for (PatientsRecord patient : patientsRecord) {
                // create a new Object array to xold the data for each row
                if (patient.getDateReleased() == null) {
                    dateReleased = "N/A";
                } else {
                    dateReleased = patient.getDateReleased();
                }
                Object[] row = {
                    patient.getCount(),
                    patient.getId().toUpperCase(),
                    patient.getName().toUpperCase(),
                    patient.getDate().toUpperCase(),
                    dateReleased.toUpperCase(),
                    patient.getStatus().toUpperCase()
                };

                // add the row to the table model
                model.addRow(row);

            }

            // set the table model for the JTable
            patientTable.setModel(model);

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }

    public void searchPatient(String searchTerm, int pageNumber) {
        int firstResult = (pageNumber - 1) * maxSearchResults;

        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
            int rowCount = model.getRowCount();

            // System.out.println(rowCount);
            for (int i = 0; i < rowCount; i++) {
                model.removeRow(0);
                //System.out.println(i);
            }

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(PatientsRecord.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            // Use a query with a WHERE clause to search for patients with matching criteria
            Query<PatientsRecord> limiterQuery = session.createQuery("FROM PatientsRecord WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR date LIKE :searchTerm OR dateReleased LIKE :searchTerm OR status LIKE :searchTerm", PatientsRecord.class);
            limiterQuery.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            limiterQuery.setMaxResults(maxSearchResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<PatientsRecord> patientsRecord = limiterQuery.getResultList();
            Query<PatientsRecord> query = session.createQuery("FROM PatientsRecord WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR date LIKE :searchTerm OR dateReleased LIKE :searchTerm OR status LIKE :searchTerm", PatientsRecord.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            List<PatientsRecord> pr = query.getResultList();
            totalSearchPatientRecords = pr.size();
            String dateReleased = "";
            if (patientsRecord.isEmpty()) {
                showMessage("No results found for the given search term : " + searchTerm);
                refreshPatientTbl.doClick();
            }
            for (PatientsRecord patient : patientsRecord) {
                // create a new Object array to hold the data for each row
                if (patient.getDateReleased() == null) {
                    dateReleased = "N/A";
                } else {
                    dateReleased = patient.getDateReleased();
                }
                Object[] row = {
                    patient.getCount(),
                    patient.getId().toUpperCase(),
                    patient.getName().toUpperCase(),
                    patient.getDate().toUpperCase(),
                    dateReleased.toUpperCase(),
                    patient.getStatus().toUpperCase()
                };

                // add the row to the table model
                model.addRow(row);
            }

            // set the table model for the JTable
            patientTable.setModel(model);
            updateButtonStatesForSearchTerm();

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void searchMedicine(String searchTerm, int pageNumber) {
        int firstResult = (pageNumber - 1) * maxSearchResults;

        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) medTable.getModel();
            int rowCount = model.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                model.removeRow(0);
            }

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(Medicines.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            Query<Medicines> limiterQuery = session.createQuery("FROM Medicines WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR brand LIKE :searchTerm OR expDate LIKE :searchTerm OR quantity LIKE :searchTerm OR status LIKE :searchTerm OR guides LIKE :searchTerm", Medicines.class);
            limiterQuery.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            limiterQuery.setMaxResults(maxSearchResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<Medicines> medRecords = limiterQuery.getResultList();

            Query<Medicines> query = session.createQuery("FROM Medicines WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR brand LIKE :searchTerm OR expDate LIKE :searchTerm OR quantity LIKE :searchTerm OR status LIKE :searchTerm OR guides LIKE :searchTerm", Medicines.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            List<Medicines> md = query.getResultList();
            totalMedRecords = md.size();

            if (medRecords.isEmpty()) {
                showMessage("No results found for the given search term : " + searchTerm);
                refreshMedTble.doClick();
            }
            for (Medicines med : medRecords) {
                Object[] row = {
                    med.getCount(),
                    med.getId().toUpperCase(),
                    med.getName().toUpperCase(),
                    med.getBrand().toUpperCase(),
                    med.getExpDate().toUpperCase(),
                    med.getQuantity(),
                    med.getStatus().toUpperCase(),
                    med.getGuides()
                };
                // add the row to the table model
                model.addRow(row);
            }

            // set the table model for the JTable
            medTable.setModel(model);
            updateMedButtonStatesForSearchTerm();

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void searchAccount(String searchTerm, int pageNumber) {
        int firstResult = (pageNumber - 1) * maxSearchResults;

        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) medTable.getModel();
            int rowCount = model.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                model.removeRow(0);
            }

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(Medicines.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            Query<UserAccount> limiterQuery = session.createQuery("FROM UserAccount WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR username LIKE :searchTerm", UserAccount.class);
            limiterQuery.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            limiterQuery.setMaxResults(maxSearchResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<UserAccount> userRecords = limiterQuery.getResultList();

            Query<UserAccount> query = session.createQuery("FROM UserAccount WHERE id LIKE :searchTerm OR name LIKE :searchTerm OR username LIKE :searchTerm", UserAccount.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%"); // Using LIKE to search for partial matches
            List<UserAccount> ua = query.getResultList();
            totalAccRecords = ua.size();

            if (userRecords.isEmpty()) {
                showMessage("No results found for the given search term : " + searchTerm);
                refreshMedTble.doClick();
            }
            for (UserAccount userAccount : userRecords) {
                Object[] row = {
                    userAccount.getCount(),
                    userAccount.getName(),
                    userAccount.getUsername(),
                    "*********"
                };
                // add the row to the table model
                model.addRow(row);
            }

            // set the table model for the JTable
            medTable.setModel(model);
            updateAccButtonStatesForSearchTerm();

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void refreshMedicineTable(int pageNumber) {
        int firstResult = (pageNumber - 1) * maxResults;

        try {
            // get the table model for the JTable
            DefaultTableModel model = (DefaultTableModel) medTable.getModel();
            int rowCount = model.getRowCount();

            // System.out.println(rowCount);
            for (int i = 0; i < rowCount; i++) {
                model.removeRow(0);
                //System.out.println(i);
            }

            factory = new Configuration()
                    .configure("/DatabaseSettings/hibernate.cfg.xml")
                    .addAnnotatedClass(PatientsRecord.class)
                    .buildSessionFactory();
            Session session = factory.openSession();

            Query<Medicines> limiterQuery = session.createQuery("FROM Medicines", Medicines.class);
            limiterQuery.setMaxResults(maxResults); // set maximum number of records to fetch
            limiterQuery.setFirstResult(firstResult); // set starting record index
            List<Medicines> medicineRecord = limiterQuery.getResultList();

            Query<Medicines> query = session.createQuery("FROM Medicines", Medicines.class);
            List<Medicines> mr = query.getResultList();
            totalMedRecords = mr.size();

            for (Medicines med : medicineRecord) {
                Object[] row = {
                    med.getCount(),
                    med.getId().toUpperCase(),
                    med.getName().toUpperCase(),
                    med.getBrand().toUpperCase(),
                    med.getExpDate().toUpperCase(),
                    med.getQuantity(),
                    med.getStatus().toUpperCase(),
                    med.getGuides()
                };
                // add the row to the table model
                model.addRow(row);
            }

            // set the table model for the JTable
            medTable.setModel(model);

            // close the Hibernate session and factory
            session.close();
            factory.close();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }

    private int getSelectedAccountCellValue(int row, int column) {
        int selectedCellValue = 0;
        try {
            Object value = accountsTable.getValueAt(row, column);
            if (value != null) {
                selectedCellValue = Integer.parseInt(String.valueOf(value));
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid value in row " + row + " column " + column + ": " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            showMessage("Index out of bounds in row " + row + " column " + column + ": " + e.getMessage());
        }
        return selectedCellValue;
    }

    private int getSelectedPatientCellValue(int row, int column) {
        int selectedCellValue = 0;
        try {
            Object value = patientTable.getValueAt(row, column);
            if (value != null) {
                selectedCellValue = Integer.parseInt(String.valueOf(value));
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid value in row " + row + " column " + column + ": " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            showMessage("Index out of bounds in row " + row + " column " + column + ": " + e.getMessage());
        }
        return selectedCellValue;
    }

    private int getSelectedMedicineCellValue(int row, int column) {
        int selectedViewRow = medTable.getSelectedRow();

        int selectedCellValue = 0;
        try {
            Object value = medTable.getValueAt(row, column);
            if (value != null) {
                String stringValue = String.valueOf(value);
                if (!stringValue.isEmpty()) {
                    selectedCellValue = Integer.parseInt(stringValue);
                }
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid value in row " + row + " column " + column + ": " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            showMessage("Index out of bounds in row " + row + " column " + column + ": " + e.getMessage());
        }
        return selectedCellValue;
    }

=======
>>>>>>> origin/master
}
