package daniel.prueba.Proyecto.java1.modelo;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Curso {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String siglas;

    @Column(nullable = false)
    private boolean estado;

}
