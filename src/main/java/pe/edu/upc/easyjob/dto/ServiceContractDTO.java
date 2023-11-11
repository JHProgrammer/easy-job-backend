package pe.edu.upc.easyjob.dto;

import lombok.Data;
import pe.edu.upc.easyjob.entity.Employer;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ServiceContractDTO {
    private long id;
    private long employer_id;
    private LocalDate service_date;
    private String desc_service;
}
