package dominio;

import java.util.Vector;

import excepciones.InvalidLoginException;
import excepciones.InvalidPasswordException;
import excepciones.InvalidUserException;
import persistencia.Agente;

public class Usuario {
	
	private String mLogin;
	private String mPassword;
	
	// Constructor para la creación de un objeto Usuario vacío
	
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}

	// Constructor para la creación de un objeto Usuario
	
	public Usuario(String login, String password) throws InvalidUserException {
		if ((this.mLogin = login).length() < 4) throw new InvalidLoginException("Usuario demasiado corto, debe tener al menos 4 caracteres.");
		if ((this.mPassword = password).length() < 4) throw new InvalidPasswordException("Contraseña demasiado corta, debe tener al menos 4 caracteres.");
	}

	// Selección de un usuario de la base de datos mediante el login y el password
	
    public boolean read() throws Exception{
		Agente agente= Agente.getAgente();
		Vector<Object> leido= agente.select(mLogin, mPassword);
		return leido.size() == 1;
	}
	
	// Inserción de un nuevo usuario en la base de datos
    
	public void insert() throws Exception{
		Agente agente= Agente.getAgente();
		agente.insert(mLogin, mPassword);
	}
	
	// Eliminación de un usuario de la base de datos
	public boolean eliminar() throws Exception {
		Agente agente= Agente.getAgente();
		return agente.delete(mLogin, mPassword) == 1;
	}
	
	// Método toString
	
	public String toString() {
		return mLogin+ mPassword;
	}
	
}
