/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseService;

import Entity.UserAccount;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Eman
 */
public interface AccountService {

    public String updateAccount(int id, String name, String username, String password, File selectedFile, byte[] byteImg);
}
