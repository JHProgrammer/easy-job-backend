package pe.edu.upc.easyjob.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.easyjob.dto.*;
import pe.edu.upc.easyjob.entity.Worker;
import pe.edu.upc.easyjob.interfaceservice.ServiceContractWorkerService;
import pe.edu.upc.easyjob.interfaceservice.WorkerOccupationService;
import pe.edu.upc.easyjob.interfaceservice.WorkerService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorkerOccupationService workerOccupationService;

    @Autowired
    private ServiceContractWorkerService serviceContractWorkerService;



    Logger logger = LoggerFactory.getLogger(WorkerController.class);


    // User Story 2 -- US02
    @PostMapping("/worker/register")
    ResponseEntity<WorkerDTO> register(@RequestBody WorkerDTO workerDTO){
        Worker worker;
        WorkerDTO dto;

        try{
            worker = convertToEntity(workerDTO);
            worker = workerService.register(worker);
            dto = convertToDTO(worker);

        }catch (Exception e){
            logger.error(e.toString());
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo registrar");
        }

        return new ResponseEntity<WorkerDTO>(dto,HttpStatus.OK);
    }

    @GetMapping("/getAllWorkers")
    ResponseEntity<List<WorkerDTO>> listWorkers(){
        List<Worker> workerList;
        List<WorkerDTO> listDTO = null;

        try {
            workerList = workerService.listWorkers();
            listDTO = convertToListDto(workerList);


        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al consultar trabajadores");
        }

        return new ResponseEntity<List<WorkerDTO>>(listDTO,HttpStatus.OK);

    }

    @GetMapping("/worker/{id}")
    ResponseEntity<WorkerDTO> getWorkerById(@PathVariable(value = "id") Long id){
        Worker worker;
        WorkerDTO workerDTO;

        try {
            worker = workerService.getWorkerById(id);
            workerDTO = convertToDTO(worker);

        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al consultar trabajador");
        }

        return new ResponseEntity<WorkerDTO>(workerDTO,HttpStatus.OK);

    }

    @PutMapping("/worker/update")
    ResponseEntity<Worker> updateWorker(@RequestBody Worker worker){

        Worker worker1;

        try {
            worker1 = workerService.updateWorker(worker);
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al actualizar trabajador");
        }

        return new ResponseEntity<Worker>(worker1,HttpStatus.OK);

    }


    @DeleteMapping("/worker/{id}")
    Worker delete(@PathVariable(value = "id") Long id){
        Worker worker;
        try{
            worker = workerService.deleteWorker(id);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se puede eliminar trabajador");
        }
        return worker ;
    }

    // User Story 3 - - US03
    @PostMapping("/registerWorkerOccupation")
    ResponseEntity<WorkerOccupationDTO> registerWorkerOccupation(@RequestBody WorkerOccupationDTO workerOccupationDTO) {
        WorkerOccupationDTO dto;

        try{
            dto = workerOccupationService.register(workerOccupationDTO);

        } catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo registrar");
        }

        return new ResponseEntity<WorkerOccupationDTO>(dto,HttpStatus.OK);
    }


    //User Story 08 - US08
    @PostMapping("/register/serviceContractWorker")
    ResponseEntity<ServiceContractWorkerDTO> registerServiceContractWorker(@RequestBody ServiceContractWorkerDTO serviceContractWorkerDTO){

        ServiceContractWorkerDTO dtoResponse;
        try {
            dtoResponse =   serviceContractWorkerService.register(serviceContractWorkerDTO);
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al registrar servicio contrato por trabajador");
        }

        return new ResponseEntity<ServiceContractWorkerDTO>(dtoResponse,HttpStatus.OK);

    }

    //User Story 09 - US09
    @GetMapping("/serviceContract/getAllGreaterThanMonto/{monto}")
    ResponseEntity<List<ServiceContractWorkerDTO>> getAllGreaterThanMonto(@PathVariable(value = "monto") double monto){
        List<ServiceContractWorkerDTO> dtosResponse;
        try {
            dtosResponse = serviceContractWorkerService.getAllGreaterThanMonto(monto);
        }catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al consultar la tabla servicio de contrato");
        }

        return new ResponseEntity<>(dtosResponse,HttpStatus.OK);
    }


    private Worker convertToEntity(WorkerDTO workerDTO){
        ModelMapper modelMapper = new ModelMapper();
        Worker post = modelMapper.map(workerDTO, Worker.class);
        return post;
    }

    private WorkerDTO convertToDTO(Worker worker){
        ModelMapper modelMapper = new ModelMapper();
        WorkerDTO workerDTO = modelMapper.map(worker,WorkerDTO.class);
        return  workerDTO;
    }

    private List<WorkerDTO> convertToListDto(List<Worker> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


}
