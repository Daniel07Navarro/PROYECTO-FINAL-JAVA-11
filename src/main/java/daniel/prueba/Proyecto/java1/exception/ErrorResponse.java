package daniel.prueba.Proyecto.java1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse { //LA RESPUESTA QUE VA A MANEJAR EL SERVIDOR

    private LocalDateTime dateTime;

    private String message;

    private String path;


}
