package daniel.prueba.Proyecto.java1.service.impl;

import daniel.prueba.Proyecto.java1.modelo.Curso;
import daniel.prueba.Proyecto.java1.repo.ICursoRepo;
import daniel.prueba.Proyecto.java1.repo.IGenericRepo;
import daniel.prueba.Proyecto.java1.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CRUDImpl<Curso,Integer> implements ICursoService {

    @Autowired
    private ICursoRepo repo;

    @Override
    IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}
