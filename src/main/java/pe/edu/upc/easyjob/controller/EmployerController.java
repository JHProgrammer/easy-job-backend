package pe.edu.upc.easyjob.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.easyjob.dto.EmployerDTO;
import pe.edu.upc.easyjob.dto.ServiceContractDTO;
import pe.edu.upc.easyjob.entity.Employer;
import pe.edu.upc.easyjob.entity.Service_Contract;
import pe.edu.upc.easyjob.interfaceservice.EmployerService;
import pe.edu.upc.easyjob.interfaceservice.ServiceContractService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private ServiceContractService serviceContractService;

    Logger logger = LoggerFactory.getLogger(EmployerController.class);

    // User Story 1 - - US01
    @PostMapping("/employeer/register")
    ResponseEntity<EmployerDTO> register(@RequestBody EmployerDTO employerDTO){
        Employer employer;
        EmployerDTO dto;

        try {
            employer = convertToEntity(employerDTO);
            employer = employerService.register(employer);
            dto = convertToDTO(employer);
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se ha podido registrar");
        }

        return new ResponseEntity<EmployerDTO>(dto,HttpStatus.OK);
    }

    @GetMapping("/listEmployers")
    ResponseEntity<List<EmployerDTO>> listEmployers(){
        List<Employer> list;
        List<EmployerDTO> listDTO = null;

        try{
            list = employerService.listEmployers();
            listDTO = convertToListDto(list);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista no disponible");
        }

        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    // User Story 4 - US04
    @GetMapping("/employersByCity/{city}")
    ResponseEntity<List<EmployerDTO>> getEmployersByCity(@PathVariable(value = "city") String city){

        List<Employer> employers;
        List<EmployerDTO> employerDTOS;

        try {

            employers = employerService.getEmployerByCity(city);
            employerDTOS = convertToListDto(employers);

        } catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al buscar empleadores por ciudad");
        }

        return new ResponseEntity<>(employerDTOS,HttpStatus.OK);
    }

    //User Story 6 - US06
    @PostMapping("/registerServiceContract")
    ResponseEntity<ServiceContractDTO> registerServiceContract(@RequestBody ServiceContractDTO serviceContractDTO){
        ServiceContractDTO dto;

        try {
            dto = serviceContractService.register(serviceContractDTO);
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo registrar el servicio de contrato");
        }

        return new ResponseEntity<ServiceContractDTO>(dto,HttpStatus.OK);
    }

    //User Story 7 - US07
    @GetMapping("/getServiceContract/{id}")
    ResponseEntity<ServiceContractDTO> getServiceContractById(@PathVariable(value = "id") Long id ){

        ServiceContractDTO serviceContractDTO;

        try{
            serviceContractDTO = serviceContractService.getServiceContractById(id);

        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al buscar servicio de contrato por id");
        }
        return new ResponseEntity<ServiceContractDTO>(serviceContractDTO,HttpStatus.OK);
    }

    @GetMapping("/getServiceContractAll")
    ResponseEntity<List<ServiceContractDTO>> getServiceContractAll(){

        //List<Service_Contract> list;
        List<ServiceContractDTO> listDTO = null;

        try{
            listDTO = serviceContractService.getServiceContractByAll();
            //listDTO = convertToListDto(list);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista no disponible");
        }

        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    //User Story 10 - US10
    @GetMapping("/getServiceContractByDate")
    ResponseEntity<List<ServiceContractDTO>> getServiceContractByDate(@RequestParam("dFechaServ") String dFechaServ){
        List<ServiceContractDTO> serviceContractDTOS;

        try {
            serviceContractDTOS = serviceContractService.getServicesContractByDate(LocalDate.parse(dFechaServ));
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al buscar por fecha");
        }

        return new ResponseEntity<>(serviceContractDTOS,HttpStatus.OK);

    }

    //Método para convertir dto a entidad o tabla
    private Employer convertToEntity(EmployerDTO employerDTO){
        ModelMapper modelMapper = new ModelMapper();
        Employer post = modelMapper.map(employerDTO, Employer.class);
        return post;
    }

    //Método para convertir entidad o tabla a dto
    private EmployerDTO convertToDTO(Employer employer){
        ModelMapper modelMapper = new ModelMapper();
        EmployerDTO employerDTO = modelMapper.map(employer,EmployerDTO.class);
        return  employerDTO;
    }

    //Método para convertir lista de entidad a lista dto
    private List<EmployerDTO> convertToListDto(List<Employer> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ServiceContractDTO convertToDTOSC(Service_Contract service_contract){
        ModelMapper modelMapper = new ModelMapper();
        ServiceContractDTO serviceContractDTO = modelMapper.map(service_contract,ServiceContractDTO.class);
        return  serviceContractDTO;
    }
    private List<ServiceContractDTO> convertToListDtoSC(List<Service_Contract> list){
        return list.stream().map(this::convertToDTOSC).collect(Collectors.toList());
    }

    //METODOS PARA SERVICE CONTRACT
    private ServiceContractDTO convertToDTO_SC(Service_Contract service_contract){
        ModelMapper modelMapper = new ModelMapper();
        ServiceContractDTO serviceContractDTO = modelMapper.map(service_contract,ServiceContractDTO.class);
        return  serviceContractDTO;
    }


}
