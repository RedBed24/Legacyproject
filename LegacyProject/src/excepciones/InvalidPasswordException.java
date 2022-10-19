package excepciones;

/**
 * @name InvalidPasswordException
 * 
 * @description Excepci�n lanzada cuando se pretende usar una password de una longitud menor a 4 caracteres.
 * 
 */
public class InvalidPasswordException extends InvalidUserException {

	public InvalidPasswordException() {
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

}
