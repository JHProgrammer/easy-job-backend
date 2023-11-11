package pe.edu.upc.easyjob.dto;

import lombok.Data;

@Data
public class ServiceContractWorkerDTO {

    private Long worker_id;
    private Long service_contract_id;
    private Double monto;
}
