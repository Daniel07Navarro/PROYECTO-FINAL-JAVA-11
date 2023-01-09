package daniel.prueba.Proyecto.java1.modelo;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idDetalleMatricula;

    @ManyToOne
    @JoinColumn(name = "idMatricula",nullable = false)
    private Matricula matricula;

    @ManyToOne //LLAVE FORÁNEA
    @JoinColumn(name = "idCurso",nullable = false)
    private Curso curso;

    private String aula;

}
