package pe.edu.upc.easyjob.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.easyjob.dto.EmployerDTO;
import pe.edu.upc.easyjob.dto.OccupationDTO;
import pe.edu.upc.easyjob.dto.WorkerDTO;
import pe.edu.upc.easyjob.entity.Employer;
import pe.edu.upc.easyjob.entity.Occupation;
import pe.edu.upc.easyjob.entity.Worker;
import pe.edu.upc.easyjob.interfaceservice.OccupationService;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/occupation")
public class OccupationController {

    @Autowired
    private OccupationService occupationService;

    Logger logger = LoggerFactory.getLogger(OccupationController.class);

    @PostMapping("/register")
    ResponseEntity<OccupationDTO> register(@RequestBody OccupationDTO occupationDTO){
        Occupation occupation;
        OccupationDTO dto;
        try {

            occupation = convertToEntity(occupationDTO);
            occupation = occupationService.register(occupation);
            dto = convertToDTO(occupation);


        }catch (Exception e){
            logger.error(e.toString());
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo registrar ocupación");
        }

        return new ResponseEntity<OccupationDTO>(dto,HttpStatus.OK);
    }

    //User story 5 - US05
    @GetMapping("/all")
    ResponseEntity<List<OccupationDTO>> getListOccupations(){

        List<Occupation> occupationList;
        List<OccupationDTO> occupationDTOList;

        try {

            occupationList = occupationService.listOccupations();
            occupationDTOList = convertToListDto(occupationList);

        } catch (Exception e){
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error al consultar ocupaciones");
        }

        return new ResponseEntity<>(occupationDTOList,HttpStatus.OK);
    }

    private Occupation convertToEntity(OccupationDTO occupationDTO){
        ModelMapper modelMapper = new ModelMapper();
        Occupation post = modelMapper.map(occupationDTO, Occupation.class);
        return post;
    }

    private OccupationDTO convertToDTO(Occupation occupation){
        ModelMapper modelMapper = new ModelMapper();
        OccupationDTO occupationDTO = modelMapper.map(occupation,OccupationDTO.class);
        return  occupationDTO;
    }

    private List<OccupationDTO> convertToListDto(List<Occupation> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
