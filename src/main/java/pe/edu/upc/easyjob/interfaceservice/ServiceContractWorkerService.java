package pe.edu.upc.easyjob.interfaceservice;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.easyjob.dto.ServiceContractWorkerDTO;
import pe.edu.upc.easyjob.entity.Service_Contract_Worker;

import java.util.List;

public interface ServiceContractWorkerService {

    public ServiceContractWorkerDTO register(ServiceContractWorkerDTO serviceContractWorkerDTO) throws Exception;

    public List<ServiceContractWorkerDTO> getAllGreaterThanMonto(double monto) throws Exception;

    public List<ServiceContractWorkerDTO> getAllServContractW() throws Exception;
}
