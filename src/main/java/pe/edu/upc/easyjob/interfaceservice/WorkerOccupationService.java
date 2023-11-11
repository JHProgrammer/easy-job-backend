package pe.edu.upc.easyjob.interfaceservice;

import pe.edu.upc.easyjob.dto.WorkerOccupationDTO;
import pe.edu.upc.easyjob.entity.Worker_Occupation;

public interface WorkerOccupationService {

    public WorkerOccupationDTO register(WorkerOccupationDTO workerOccupationDTO) throws Exception;

}
