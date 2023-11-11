package pe.edu.upc.easyjob.interfaceservice;

import pe.edu.upc.easyjob.dto.ServiceContractDTO;
import pe.edu.upc.easyjob.entity.Service_Contract;

import java.time.LocalDate;
import java.util.List;

public interface ServiceContractService {

    public ServiceContractDTO register(ServiceContractDTO serviceContractDTO) throws Exception;

    public ServiceContractDTO getServiceContractById(Long id) throws Exception;

    public List<ServiceContractDTO> getServicesContractByDate(LocalDate dFechaServ) throws Exception;
}
