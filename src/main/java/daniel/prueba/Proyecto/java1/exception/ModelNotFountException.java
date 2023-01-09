package daniel.prueba.Proyecto.java1.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) //sobreescribiendo la excepcion
public class ModelNotFountException extends RuntimeException{

    public ModelNotFountException(String message) {
        super(message);
    }
}
