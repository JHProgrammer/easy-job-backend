package pe.edu.upc.easyjob.keys;

import lombok.Data;
import pe.edu.upc.easyjob.entity.Occupation;
import pe.edu.upc.easyjob.entity.Worker;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class Worker_Occupation_Id implements Serializable {
    @ManyToOne
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
