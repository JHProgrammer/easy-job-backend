package pe.edu.upc.easyjob.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.easyjob.keys.Worker_Occupation_Id;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "worker_occupation")
public class Worker_Occupation {

    @EmbeddedId
    private Worker_Occupation_Id id;

    @Column(name = "certificate", length = 50, nullable = false)
    private String certificate;

    @Column(name = "reference", length = 50, nullable = false)
    private String reference;


}


