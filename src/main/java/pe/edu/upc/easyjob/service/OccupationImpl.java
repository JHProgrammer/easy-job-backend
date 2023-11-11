package pe.edu.upc.easyjob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.easyjob.entity.Occupation;
import pe.edu.upc.easyjob.interfaceservice.OccupationService;
import pe.edu.upc.easyjob.repository.OccupationRepository;

import java.util.List;

@Service
public class OccupationImpl implements OccupationService {

    @Autowired
    private OccupationRepository occupationRepository;

    @Override
    public Occupation register(Occupation occupation) {
        return occupationRepository.save(occupation);
    }

    @Override
    public List<Occupation> listOccupations() {
        return occupationRepository.findAll();
    }
}
