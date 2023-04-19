/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Globals;

import Entity.PatientsRecord;
import MiscForms.Loading;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author Eman
 */
public class ExportPatientData {

    private SessionFactory factory;

    private Thread patientDataThread;
    private Thread loadingThread;
    private JDialog loading;

    public ExportPatientData() {
        factory = new Configuration()
                .configure("/DatabaseSettings/hibernate.cfg.xml")
                .addAnnotatedClass(PatientsRecord.class)
                .buildSessionFactory();
    }

    public void exportPatientData() {

        loading = new Loading(null, true);
        loading.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> {
            loading.setVisible(true); // Show the loading dialog
        });
        XSSFWorkbook workbook = new XSSFWorkbook();
        FutureTask<Void> task = new FutureTask<>(() -> {
            // Insert your code to load the data here

            Session session = factory.openSession();
            session.beginTransaction();
            Query<PatientsRecord> query = session.createQuery("FROM PatientsRecord", PatientsRecord.class);
            List<PatientsRecord> patientsRecords = query.getResultList();

            // Create Excel workbook and sheet
            XSSFSheet sheet = workbook.createSheet("Data");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Birth");
            header.createCell(3).setCellValue("Age");
            header.createCell(4).setCellValue("Year");
            header.createCell(5).setCellValue("Section");
            header.createCell(6).setCellValue("Date");
            header.createCell(7).setCellValue("Adviser");
            header.createCell(8).setCellValue("Chief Complaints");
            header.createCell(9).setCellValue("Allergies");
            header.createCell(10).setCellValue("Vital Signs");
            header.createCell(11).setCellValue("Pain Scale");
            header.createCell(12).setCellValue("Last Day In Campus");
            header.createCell(13).setCellValue("Contact Tracing");
            header.createCell(14).setCellValue("Subjective");
            header.createCell(15).setCellValue("Objective");
            header.createCell(16).setCellValue("Assessment");
            header.createCell(17).setCellValue("Plans and Recommendation");
            header.createCell(18).setCellValue("Status");
            header.createCell(19).setCellValue("Date Released");

            if (patientsRecords.size() < 1) {
                JOptionPane.showMessageDialog(null, "No Patient can be exported, Please add patient", "Export Failed", JOptionPane.INFORMATION_MESSAGE);

            }

            int rownum = 1;
            String dateReleased = "";
            for (PatientsRecord patientData : patientsRecords) {
                Row row = sheet.createRow(rownum++);
                // create a new Object array to hold the data for each row
                if (patientData.getDateReleased() == null) {
                    dateReleased = "N/A";
                } else {
                    dateReleased = patientData.getDateReleased();
                }
                row.createCell(0).setCellValue(patientData.getId());
                row.createCell(1).setCellValue(patientData.getName());
                row.createCell(2).setCellValue(patientData.getBirth());
                row.createCell(3).setCellValue(patientData.getAge());
                row.createCell(4).setCellValue(patientData.getYear());
                row.createCell(5).setCellValue(patientData.getSection());
                row.createCell(6).setCellValue(patientData.getDate());
                row.createCell(7).setCellValue(patientData.getAdviser());
                row.createCell(8).setCellValue(patientData.getChiefComplaints());
                row.createCell(9).setCellValue(patientData.getAlergies());
                row.createCell(10).setCellValue(patientData.getVitalSigns());
                row.createCell(11).setCellValue(patientData.getPainScale());
                row.createCell(12).setCellValue(patientData.getLastDay_InCampus());
                row.createCell(13).setCellValue(patientData.getContactTracing());
                row.createCell(14).setCellValue(patientData.getSubjective());
                row.createCell(15).setCellValue(patientData.getObjective());
                row.createCell(16).setCellValue(patientData.getAssesment());
                row.createCell(17).setCellValue(patientData.getPlans_And_Reccomendation());
                row.createCell(18).setCellValue(patientData.getStatus());
                row.createCell(19).setCellValue(dateReleased);

            }
            // Autosize all columns
            for (int i = 0; i < 20; i++) {
                sheet.autoSizeColumn(i);
            }

            // Set column width to a minimum of 15 characters
            for (int i = 0; i < 20; i++) {
                int width = sheet.getColumnWidth(i);
                if (width < 15 * 256) {
                    sheet.setColumnWidth(i, 15 * 256);
                }
            }
            // Wait for 1 seconds
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Dispose the loading window
            loading.dispose();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save As");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Excel Files", "xlsx"));
            int option = fileChooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                try {
                    FileOutputStream out = new FileOutputStream(filePath);
                    workbook.write(out);
                    out.close();

                    // Ask user if they want to open the file automatically
                    int openOption = JOptionPane.showConfirmDialog(null, "Patient data has been exported successfully!\nDo you want to open the file now?", "Export Success", JOptionPane.YES_NO_OPTION);

                    // If user chooses to open the file, launch it using the default system application
                    if (openOption == JOptionPane.YES_OPTION) {
                        Desktop.getDesktop().open(new File(filePath));
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while exporting the patient data.\nPlease try again.", "Export Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            return null;
        });
        new Thread(task).start();
//        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//
//                return null;
//            }
//
//            @Override
//            protected void done() {
//
//              
//            }
//        };
//        worker.execute();
    }

}
