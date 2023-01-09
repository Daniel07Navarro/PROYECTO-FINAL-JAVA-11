package daniel.prueba.Proyecto.java1.Controller;

import daniel.prueba.Proyecto.java1.dto.CursoDTO;
import daniel.prueba.Proyecto.java1.exception.ModelNotFountException;
import daniel.prueba.Proyecto.java1.modelo.Curso;
import daniel.prueba.Proyecto.java1.service.ICursoService;
import daniel.prueba.Proyecto.java1.service.impl.CursoServiceImpl;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cursos")
public class CursoController {

    @Autowired
    private ICursoService service;

    @Autowired
    @Qualifier("cursoMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> buscarTodos() throws Exception {
        List<CursoDTO> list = service.readAll().stream().map(curso -> mapper.map(curso, CursoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarById(@PathVariable("id") Integer id) throws Exception {
        CursoDTO obj = mapper.map(service.buscarById(id), CursoDTO.class);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@Valid @RequestBody CursoDTO cursoDTO) throws Exception {
        Curso obj = service.save(mapper.map(cursoDTO, Curso.class));
        return new ResponseEntity<>(mapper.map(obj, CursoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> updateCurso(@Valid @RequestBody CursoDTO cursoDTO) throws Exception {
        Curso obj = service.save(mapper.map(cursoDTO, Curso.class));
        return new ResponseEntity<>(mapper.map(obj, CursoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Curso obj = service.buscarById(id);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO: " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
