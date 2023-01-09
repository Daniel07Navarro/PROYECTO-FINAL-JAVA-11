package daniel.prueba.Proyecto.java1.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice //interseptador de excepciones
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class) //PARA SI ALGUNA DESCRIPCION SE ESCAPA
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ModelNotFountException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFountException ex, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @Override //PARA CONTROLAR LAS URL QUE NO SE ENCUENTRAN
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        //MEJORANDO EXCEPCIONES
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())//para cada error se obtiene el campo que ocasiono el error
                .collect(Collectors.joining(" ; ")); //devuelve un stream pero lo convertirmos a una cadena, todas las cadenas iteradas se transforman en una
        /*
        //SIN PROGRAMACION FUNCIONAL
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            message+= error.getField() +" : "+error.getDefaultMessage() + " ";
        }
         */

        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), message, req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST); //CORROMPE LAS ESTRUCTURAS
    }


}
