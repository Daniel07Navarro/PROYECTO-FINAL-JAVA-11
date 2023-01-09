package daniel.prueba.Proyecto.java1.modelo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMatricula;

    @Column(nullable = false)
    private LocalDateTime fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;

    //NO VA A GENERAR UNA FK EN ESTA TABLA
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalleMatricula> detalleMatriculas;
    //ESTO DEBIDO A QUE EL ESTUDIANTE MATRICULADO PUEDE TENER MUCHOS CURSOS POR ELLO EN SU MATRICULA
    //DEBEN ESTAR LA LISTA DE CURSOS Y SU AULA RESPECTIVA

}
