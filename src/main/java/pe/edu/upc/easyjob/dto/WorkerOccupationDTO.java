package pe.edu.upc.easyjob.dto;

import lombok.Data;
import pe.edu.upc.easyjob.entity.Occupation;
import pe.edu.upc.easyjob.entity.Worker;

@Data
public class WorkerOccupationDTO {

    private Long occupationId;

    private Long workerId;

    private String certificate;

    private String Reference;
}
