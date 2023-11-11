package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.easyjob.entity.Service_Contract;

import java.time.LocalDate;
import java.util.List;

public interface ServiceContractRepository extends JpaRepository<Service_Contract,Long> {

    @Query("select sc from Service_Contract sc where sc.service_date <= :dFechaServ")
    List<Service_Contract> getServiceContractByDate(@Param("dFechaServ")LocalDate dFechaServ);
}
