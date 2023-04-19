/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseService;

import Entity.Medicines;

/**
 *
 * @author Eman
 */
public interface MedicineService {
    
    Boolean insertMedicine(Medicines medicine);
    
    public boolean updateMedicineData(long id, Medicines medicine);

    public boolean deleteMedicineData(long id);
}
