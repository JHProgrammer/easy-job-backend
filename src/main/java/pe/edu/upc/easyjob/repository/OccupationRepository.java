package pe.edu.upc.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.easyjob.entity.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation,Long> {
}
