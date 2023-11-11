package pe.edu.upc.easyjob.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.easyjob.dto.ServiceContractDTO;

import pe.edu.upc.easyjob.dto.ServiceContractWorkerDTO;
import pe.edu.upc.easyjob.entity.Employer;
import pe.edu.upc.easyjob.entity.Service_Contract;

import pe.edu.upc.easyjob.entity.Service_Contract_Worker;
import pe.edu.upc.easyjob.interfaceservice.ServiceContractService;
import pe.edu.upc.easyjob.repository.EmployerRepository;
import pe.edu.upc.easyjob.repository.ServiceContractRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceContractImpl implements ServiceContractService {

    @Autowired
    private ServiceContractRepository serviceContractRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public ServiceContractDTO register(ServiceContractDTO serviceContractDTO) throws Exception{

        ServiceContractDTO dto;
        Service_Contract service_contract = new Service_Contract();
        Employer employer = employerRepository.findById(serviceContractDTO.getEmployer_id()).
                            orElseThrow( ()-> new Exception("No se encontró empleador"));
        service_contract.setEmployer(employer);

        service_contract.setDesc_service(serviceContractDTO.getDesc_service());
        service_contract.setService_date(serviceContractDTO.getService_date());

        dto = convertToDTO(serviceContractRepository.save(service_contract));

        return dto;
    }

    @Override
    public ServiceContractDTO getServiceContractById(Long id) throws  Exception {
        Service_Contract service_contract;
        ServiceContractDTO serviceContractDTO = new ServiceContractDTO();
        service_contract = serviceContractRepository.findById(id).
                orElseThrow( ()-> new Exception("no se encontró empleador"));

        serviceContractDTO.setEmployer_id(service_contract.getEmployer().getId());
        serviceContractDTO.setId(service_contract.getId());
        serviceContractDTO.setService_date(service_contract.getService_date());
        serviceContractDTO.setDesc_service(service_contract.getDesc_service());
        return serviceContractDTO;
    }

    @Override
    public List<ServiceContractDTO> getServicesContractByDate(LocalDate dFechaServ) throws Exception {
        List<Service_Contract> serviceContracts;
        List<ServiceContractDTO> dtos;

        serviceContracts = serviceContractRepository.getServiceContractByDate(dFechaServ);

        dtos = convertToListDto(serviceContracts);

        return dtos;
    }


    //METODOS PARA SERVICE CONTRACT
    private Service_Contract convertToEntity(ServiceContractDTO serviceContractDTO){
        ModelMapper modelMapper = new ModelMapper();
        Service_Contract post = modelMapper.map(serviceContractDTO, Service_Contract.class);
        return post;
    }

    private ServiceContractDTO convertToDTO(Service_Contract service_contract){
        ModelMapper modelMapper = new ModelMapper();
        ServiceContractDTO post = modelMapper.map(service_contract, ServiceContractDTO.class);
        return post;
    }

    private List<ServiceContractDTO> convertToListDto(List<Service_Contract> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
