package daniel.prueba.Proyecto.java1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EstudianteDTO {

    private Integer idEstudiante;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    //@JsonProperty(value = "nombre_estudiante")
    private String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String apellido;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    private int edad;

}
