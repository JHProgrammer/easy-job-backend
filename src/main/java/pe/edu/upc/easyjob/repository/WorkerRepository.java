package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.easyjob.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
}
