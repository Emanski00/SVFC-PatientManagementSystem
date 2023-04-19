/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Globals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author chang
 */
public class FormatDate {
    public String formattedDate(){
         LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d, yyyy 'at' ha");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
