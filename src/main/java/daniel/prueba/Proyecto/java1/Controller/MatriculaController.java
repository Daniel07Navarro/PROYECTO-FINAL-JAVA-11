package daniel.prueba.Proyecto.java1.Controller;

import daniel.prueba.Proyecto.java1.dto.MatriculaDTO;
import daniel.prueba.Proyecto.java1.dto.ResponseDetalleDTO;
import daniel.prueba.Proyecto.java1.exception.ModelNotFountException;
import daniel.prueba.Proyecto.java1.modelo.Matricula;
import daniel.prueba.Proyecto.java1.service.ICursoService;
import daniel.prueba.Proyecto.java1.service.IMatriculaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/matriculas")
public class MatriculaController {

    @Autowired
    private IMatriculaService service;

    @Autowired
    private ICursoService serviceCurso;

    @Autowired
    @Qualifier("matriculaMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> buscarTodos() throws Exception {
        List<MatriculaDTO> list = service.readAll().stream()
                .map(matricula -> mapper.map(matricula, MatriculaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        MatriculaDTO obj = mapper.map(service.buscarById(id), MatriculaDTO.class);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }



    @GetMapping("/relacionV1")
    public ResponseEntity<?> relacionV1() throws Exception {
        List<MatriculaDTO> list = service.readAll().stream()
                .map(matricula -> mapper.map(matricula, MatriculaDTO.class))
                .collect(Collectors.toList());
        //UNA LISTA DE ESTUDIANTES QUE PERTENECEN A ESE CURSO

        Map<Object, Map<Object, List<MatriculaDTO>>> relacion = list.stream()
                .collect(Collectors.groupingBy(m -> m.getDetalleMatriculas().get(0).getCurso().getNombre(), Collectors.groupingBy(m -> m.getEstudiante().getNombre())));
        //Map<Object, Map<Object,List<MatriculaDTO>>>
        /*
        Map<String, List<MatriculaDTO>> relacion = list.stream()
                .collect(Collectors.groupingBy(m -> m.getDetalleMatriculas().get(numero).getCurso().getNombre()));
         */
        return new ResponseEntity<>(relacion, HttpStatus.OK);
    }

    @GetMapping("/relacionV2")
    public ResponseEntity<?> relacionV2() throws Exception{
        List<MatriculaDTO> list = service.readAll().stream().map(matricula -> mapper.map(matricula, MatriculaDTO.class)).collect(Collectors.toList());
        Map<Object,Object>resultado = list.stream()
                //.flatMap(m -> {
                    //return Stream.of(m.getEstudiante().getNombre());
                //})
                .collect(Collectors.toMap(m->m.getDetalleMatriculas().get(0).getCurso().getNombre(),m-> m.getEstudiante() ));
         return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/relacionV3")
    public ResponseEntity<?> relacionV3() throws Exception{
        List<MatriculaDTO> list = service.readAll().stream().map(matricula -> mapper.map(matricula, MatriculaDTO.class)).collect(Collectors.toList());
        Map<String, List<MatriculaDTO>> listV2 = list.stream()
                .collect(Collectors.groupingBy(l -> l.getDetalleMatriculas().get(0).getCurso().getNombre()));
        return new ResponseEntity<>(listV2,HttpStatus.OK);
    }

    //RESULTADO VERDADERO
    @GetMapping("/relacionV4")
    public ResponseEntity<?> relacionV4() throws Exception{
            Map<String,List<ResponseDetalleDTO>> list = service.readAll()
                    .stream()
                    .map(matricula -> {
                        return new ResponseDetalleDTO(matricula.getDetalleMatriculas().get(0).getCurso().getNombre(),matricula.getEstudiante().getNombre());
                    })
                    .collect(Collectors.groupingBy(ResponseDetalleDTO::getNameCourse));
            return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> crear(@Valid @RequestBody MatriculaDTO matriculaDTO) throws Exception {
        Matricula obj = service.save(mapper.map(matriculaDTO, Matricula.class));
        return new ResponseEntity<>(mapper.map(obj, MatriculaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO matriculaDTO) throws Exception {
        Matricula obj = service.save(mapper.map(matriculaDTO, Matricula.class));
        return new ResponseEntity<>(mapper.map(obj, MatriculaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Matricula obj = service.buscarById(id);
        if (obj == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
