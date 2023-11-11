package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.easyjob.entity.Worker_Occupation;

public interface WorkerOccupationRepository extends JpaRepository<Worker_Occupation,Long> {
}
