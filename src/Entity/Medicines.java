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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Medicines {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long count;
     @Column(unique = true)
    private String id;
    private String name;
    private String brand;
    private String expDate;
    private long quantity;
    private String status;
    @Column(columnDefinition="text")
     private String guides;
    
}
