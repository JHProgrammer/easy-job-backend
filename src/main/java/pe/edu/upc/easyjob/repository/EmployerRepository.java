package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.easyjob.entity.Employer;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {

    @Query("select emp from Employer emp where emp.city_employer like %:city%")
    List<Employer> getEmployerByCity(@Param("city") String city);

}
