package DatabaseService;

import Entity.PatientsRecord;
import java.io.File;
import java.util.List;

/**
 *
 * @author Eman
 */
public interface PatientService {
    
    public long patientCountsByStatus(String status);

    public boolean insertPatient(PatientsRecord patientInfos);

    public boolean updatePatientData(int id, PatientsRecord patient);

    public boolean updatePatientStatus(PatientsRecord patient);

    public boolean updatePatientAdmission(PatientsRecord patient);

    public boolean deletePatient(int id);
}
