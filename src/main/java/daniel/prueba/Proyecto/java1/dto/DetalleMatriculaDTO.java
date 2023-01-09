package daniel.prueba.Proyecto.java1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import daniel.prueba.Proyecto.java1.modelo.Matricula;
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
public class DetalleMatriculaDTO {

    @JsonBackReference
    private MatriculaDTO matricula;

    @NotNull
    private CursoDTO curso;

    @NotNull
    @NotEmpty
    @Size(min = 3,max = 20)
    private String aula;
}
