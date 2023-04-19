/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eman
 */
public class DB_Connection {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/patients_management_system";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private static int db_called = 0;

    Connection connection = null;

    public DB_Connection() {

        try {

            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            if (db_called == 0) {
                JOptionPane.showMessageDialog(null, "Connected to database successfully...", "Database Connection", JOptionPane.INFORMATION_MESSAGE);
                db_called++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    

    private void closedDbConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Invalid Actions, please contact the administrator.", "Database Connection", JOptionPane.INFORMATION_MESSAGE);

        }
    }

}
