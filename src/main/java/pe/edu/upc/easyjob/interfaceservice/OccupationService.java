package pe.edu.upc.easyjob.interfaceservice;

import pe.edu.upc.easyjob.entity.Occupation;

import java.util.List;

public interface OccupationService {

    public Occupation register(Occupation occupation);

    public List<Occupation> listOccupations();

}
