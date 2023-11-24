package pe.edu.upc.easyjob.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.easyjob.dto.EmployerDTO;
import pe.edu.upc.easyjob.dto.ServiceContractWorkerDTO;
import pe.edu.upc.easyjob.dto.WorkerOccupationDTO;
import pe.edu.upc.easyjob.entity.*;
import pe.edu.upc.easyjob.interfaceservice.ServiceContractService;
import pe.edu.upc.easyjob.interfaceservice.ServiceContractWorkerService;
import pe.edu.upc.easyjob.keys.Service_Contract_Worker_Id;
import pe.edu.upc.easyjob.repository.ServiceContractRepository;
import pe.edu.upc.easyjob.repository.ServiceContractWorkerRepository;
import pe.edu.upc.easyjob.repository.WorkerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceContractWorkerServiceImpl implements ServiceContractWorkerService {

    @Autowired
    private ServiceContractWorkerRepository serviceContractWorkerRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ServiceContractRepository serviceContractRepository;


    @Override
    public ServiceContractWorkerDTO register(ServiceContractWorkerDTO serviceContractWorkerDTO) throws Exception {

        ServiceContractWorkerDTO dto = new ServiceContractWorkerDTO();

        Service_Contract service_contract = serviceContractRepository.findById(serviceContractWorkerDTO.getService_contract_id()).
                        orElseThrow(()-> new Exception("No se encontró servicio de contrato"));

        Worker worker = workerRepository.findById(serviceContractWorkerDTO.getWorker_id()).
                        orElseThrow(()-> new Exception("No se encontró trabajador"));

        Service_Contract_Worker service_contract_worker = new Service_Contract_Worker();

        Service_Contract_Worker_Id service_contract_worker_id = new Service_Contract_Worker_Id();

        service_contract_worker_id.setService_contract(service_contract);
        service_contract_worker_id.setWorker(worker);

        service_contract_worker.setId(service_contract_worker_id);
        service_contract_worker.setMonto(serviceContractWorkerDTO.getMonto());

        service_contract_worker = serviceContractWorkerRepository.save(service_contract_worker);

        dto.setMonto(service_contract_worker.getMonto());
        dto.setWorker_id(service_contract_worker.getId().getWorker().getId());
        dto.setService_contract_id(serviceContractWorkerDTO.getService_contract_id());

        return dto;
    }

    @Override
    public List<ServiceContractWorkerDTO> getAllGreaterThanMonto(double monto) throws Exception {
        List<ServiceContractWorkerDTO> contractDTOs = new ArrayList<>();
        List<Service_Contract_Worker> service_contract_workers = serviceContractWorkerRepository.getMontoGreatherThanMonto(monto);

        for (Service_Contract_Worker contract : service_contract_workers) {
            ServiceContractWorkerDTO dto = new ServiceContractWorkerDTO();
            dto.setMonto(contract.getMonto());
            dto.setWorker_id(contract.getId().getWorker().getId());
            dto.setService_contract_id(contract.getId().getService_contract().getId());

            contractDTOs.add(dto);
        }

        return contractDTOs;
    }

    @Override
    public List<ServiceContractWorkerDTO> getAllServContractW() throws Exception {
        List<ServiceContractWorkerDTO> contractDTOs = new ArrayList<>();
        List<Service_Contract_Worker> service_contract_workers = serviceContractWorkerRepository.findAll();

        for (Service_Contract_Worker contract : service_contract_workers) {
            ServiceContractWorkerDTO dto = new ServiceContractWorkerDTO();
            dto.setMonto(contract.getMonto());
            dto.setWorker_id(contract.getId().getWorker().getId());
            dto.setService_contract_id(contract.getId().getService_contract().getId());

            contractDTOs.add(dto);
        }

        return contractDTOs;
    }

    private Service_Contract_Worker convertToEntity(ServiceContractWorkerDTO serviceContractWorkerDTO){
        ModelMapper modelMapper = new ModelMapper();
        Service_Contract_Worker post = modelMapper.map(serviceContractWorkerDTO, Service_Contract_Worker.class);
        return post;
    }

    private ServiceContractWorkerDTO convertToDTO(Service_Contract_Worker service_contract_worker){
        ModelMapper modelMapper = new ModelMapper();
        ServiceContractWorkerDTO post = modelMapper.map(service_contract_worker, ServiceContractWorkerDTO.class);
        return post;
    }

    private List<ServiceContractWorkerDTO> convertToListDto(List<Service_Contract_Worker> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
