package dominio;

import java.util.Vector;

import excepciones.InvalidLoginException;
import excepciones.InvalidPasswordException;
import excepciones.InvalidUserException;
import persistencia.Agente;

public class Usuario {
	
	private String mLogin;
	private String mPassword;
	
	//Constructor para la creacion de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}

	//Constructor para la creacion de un Usuario
	public Usuario(String login, String password) throws InvalidUserException {
		if ((this.mLogin = login).length() < 4) throw new InvalidLoginException("Usuario demasiado corto, debe tener al menos 4 carÃ¡cteres.");
		if ((this.mPassword = password).length() < 4) throw new InvalidPasswordException("ContraseÃ±a demasiado corta, debe tener al menos 4 carÃ¡cteres.");
	}

	//Seleccion de un usuario de la base de datos mediante el login y el password
  public boolean read() throws Exception{
		Agente agente= Agente.getAgente();
		Vector<Object> leido= agente.select(mLogin, mPassword);
		return leido.size() == 1;
	}
	
	//Inserta un nuevo usuario en la base de datos
	public boolean insert() throws Exception{
		Agente agente= Agente.getAgente();
		return agente.insert(mLogin, mPassword) == 1;
	}
	
	public boolean eliminar() throws Exception {
		Agente agente= Agente.getAgente();
		return agente.delete(mLogin, mPassword) == 1;
	}

	public int update () throws Exception{
		//por ahora no nos ha hecho falta actualizar nada...
		return 0;
	}
	
	public String toString() {
		return mLogin+ mPassword;
	}
	
}
