package daniel.prueba.Proyecto.java1.service.impl;

import daniel.prueba.Proyecto.java1.modelo.Estudiante;
import daniel.prueba.Proyecto.java1.repo.IEstudianteRepo;
import daniel.prueba.Proyecto.java1.repo.IGenericRepo;
import daniel.prueba.Proyecto.java1.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServiceImpl extends CRUDImpl<Estudiante,Integer> implements IEstudianteService {
    @Autowired
    private IEstudianteRepo repo;

    @Override
    IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }
}
