package dominio;

import java.util.Vector;

import persistencia.Agente;

public class Usuario {
	
	public String mLogin;
	public String mPassword;
	
	//Constructor para la creacion de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}
	
	//Constructor para la creacion de un Usuario
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
	}
	
	//Seleccion de un usuario de la base de datos a partir del login y el password
	public boolean read() throws Exception{
		Agente agente= Agente.getAgente();
		Vector<Object> leido= agente.select(mLogin, mPassword);
		return leido.size() == 1;
	}
	
	//Inserción de un nuevo usuario en la base de datos
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
