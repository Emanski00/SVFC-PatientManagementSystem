
package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Eman
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PatientsRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int count;
    @Column(unique = true)
    private String id;
    private String name;
    private String age;
    private String year;
    private String adviser;
    private String chiefComplaints;
    private String[] alergies;
    private String[] vitalSigns;
    private int painScale;
    private String lastDay_InCampus;
    private String contactTracing;
    private String subjective;
    private String objective;
    private String assesment;
    private String plans_And_Reccomendation;
    
    //check if discharged or not
    private String status;
}
