package pe.edu.upc.easyjob.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.easyjob.dto.WorkerOccupationDTO;
import pe.edu.upc.easyjob.entity.Occupation;
import pe.edu.upc.easyjob.entity.Worker;
import pe.edu.upc.easyjob.entity.Worker_Occupation;
import pe.edu.upc.easyjob.interfaceservice.WorkerOccupationService;
import pe.edu.upc.easyjob.keys.Worker_Occupation_Id;
import pe.edu.upc.easyjob.repository.OccupationRepository;
import pe.edu.upc.easyjob.repository.WorkerOccupationRepository;
import pe.edu.upc.easyjob.repository.WorkerRepository;

@Service
public class WorkerOccupationImpl implements WorkerOccupationService {

    @Autowired
    private WorkerOccupationRepository workerOccupationRepository;
    @Autowired
    private OccupationRepository occupationRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public WorkerOccupationDTO register(WorkerOccupationDTO workerOccupationDTO) throws Exception {

        //variable dto que retornara la funcion
        WorkerOccupationDTO dto;

        //Validamos si existe la ocupación
        Occupation occupation = occupationRepository.findById(workerOccupationDTO.getOccupationId()).
                orElseThrow(()-> new Exception("No se encontró ocupación"));

        //Validamos si existe el trabajador
        Worker worker = workerRepository.findById(workerOccupationDTO.getWorkerId()).
                orElseThrow(()-> new Exception("No se encontró trabajador"));

        //Referenciamos las clases para registrar en la tabla
        Worker_Occupation worker_occupation = new Worker_Occupation();
        Worker_Occupation_Id worker_occupation_id = new Worker_Occupation_Id();

        //Seteamos los ids
        worker_occupation_id.setOccupation(occupation);
        worker_occupation_id.setWorker(worker);

        //seteamos la data en worker_occupation
        worker_occupation.setId(worker_occupation_id);
        worker_occupation.setReference(workerOccupationDTO.getReference());
        worker_occupation.setCertificate(workerOccupationDTO.getCertificate());

        //realizamos el registro
        worker_occupation = workerOccupationRepository.save(worker_occupation);

        //convertimos a dto lo registrado
        dto = convertToDTOWO(worker_occupation);

        return dto;

    }

    private Worker_Occupation convertToEntityWO(WorkerOccupationDTO workerOccupationDTO){
        ModelMapper modelMapper = new ModelMapper();
        Worker_Occupation post = modelMapper.map(workerOccupationDTO, Worker_Occupation.class);
        return post;
    }

    private WorkerOccupationDTO convertToDTOWO(Worker_Occupation worker_occupation){
        ModelMapper modelMapper = new ModelMapper();
        WorkerOccupationDTO post = modelMapper.map(worker_occupation, WorkerOccupationDTO.class);
        return post;
    }
}
