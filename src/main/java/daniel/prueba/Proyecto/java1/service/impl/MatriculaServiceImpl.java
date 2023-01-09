package daniel.prueba.Proyecto.java1.service.impl;

import daniel.prueba.Proyecto.java1.modelo.Matricula;
import daniel.prueba.Proyecto.java1.repo.IGenericRepo;
import daniel.prueba.Proyecto.java1.repo.IMatriculaRepo;
import daniel.prueba.Proyecto.java1.service.IEstudianteService;
import daniel.prueba.Proyecto.java1.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaServiceImpl extends CRUDImpl<Matricula,Integer> implements IMatriculaService {

    @Autowired
    private IMatriculaRepo repo;
    @Override
    IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }
}
