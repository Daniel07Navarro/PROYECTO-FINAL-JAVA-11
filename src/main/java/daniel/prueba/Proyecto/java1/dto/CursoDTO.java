package daniel.prueba.Proyecto.java1.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    private Integer idCurso;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String siglas;

    @NotNull
    private boolean estado;

}
