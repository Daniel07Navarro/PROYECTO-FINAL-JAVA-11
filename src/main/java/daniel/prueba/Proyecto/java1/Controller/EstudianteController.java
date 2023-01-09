package daniel.prueba.Proyecto.java1.Controller;

import daniel.prueba.Proyecto.java1.dto.EstudianteDTO;
import daniel.prueba.Proyecto.java1.exception.ModelNotFountException;
import daniel.prueba.Proyecto.java1.modelo.Estudiante;
import daniel.prueba.Proyecto.java1.service.IEstudianteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

    @Autowired
    private IEstudianteService service;

    @Autowired
    @Qualifier("estudianteMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> buscarTodos() throws Exception {
        List<EstudianteDTO> list = service.readAll().stream().map(estudiante -> mapper.map(estudiante, EstudianteDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ordenarDescendente")
    public ResponseEntity<List<EstudianteDTO>> ordernarPorEdad() throws Exception {
        Comparator<EstudianteDTO> edadDescendente = (e1, e2) -> String.valueOf(e2.getEdad()).compareTo(String.valueOf(e1.getEdad()));
        //LISTA ORDENADA DE MANERA DESCENDENTE 
        List<EstudianteDTO> list = service.readAll().stream().map(estudiante -> mapper.map(estudiante, EstudianteDTO.class))
                //.sorted(Comparator.comparingInt(EstudianteDTO::getEdad))
                .sorted(edadDescendente)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        EstudianteDTO obj = mapper.map(service.buscarById(id), EstudianteDTO.class);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO:" + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) throws Exception {
        Estudiante obj = service.save(mapper.map(estudianteDTO, Estudiante.class));
        return new ResponseEntity<>(mapper.map(obj, EstudianteDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EstudianteDTO> updateEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) throws Exception {
        Estudiante obj = service.save(mapper.map(estudianteDTO, Estudiante.class));
        return new ResponseEntity<>(mapper.map(obj, EstudianteDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable("id") Integer id) throws Exception {
        Estudiante obj = service.buscarById(id);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO: " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void estudianteDescendente(List<EstudianteDTO> lista) {
        List<EstudianteDTO> ordenado = lista.stream()
                .sorted(Comparator.comparingInt(EstudianteDTO::getEdad))
                .collect(Collectors.toList());
    }

}
