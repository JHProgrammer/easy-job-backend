package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.easyjob.dto.ServiceContractWorkerDTO;
import pe.edu.upc.easyjob.entity.Service_Contract_Worker;

import java.util.List;

public interface ServiceContractWorkerRepository extends JpaRepository<Service_Contract_Worker,Long> {

    @Query("select scw from Service_Contract_Worker scw where scw.monto > :monto")
    List<Service_Contract_Worker> getMontoGreatherThanMonto(double monto);
}
