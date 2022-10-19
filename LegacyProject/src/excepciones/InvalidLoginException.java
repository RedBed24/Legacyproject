package excepciones;

/**
 * @name InvalidLoginException
 * 
 * @description Excepci�n lanzada cuando se pretende usar un login de una longitud menor a 4 caracteres.
 * 
 */

public class InvalidLoginException extends InvalidUserException {

	public InvalidLoginException() {
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
