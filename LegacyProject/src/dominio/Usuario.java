package dominio;

import java.util.Vector;

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
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
	}
	
	//Seleccion de un usuario de la base de datos a partir del login y el password
	@SuppressWarnings("unchecked")
	public static Usuario read(String login, String password) throws Exception {
		
		Agente agente= Agente.getAgente();
		Vector<Object> leido= agente.select("SELECT * FROM iso.usuario WHERE login = '"+login+"' AND pass = '"+password+"'");
		if (leido.size()==1)
			return new Usuario((String)((Vector)leido.get(0)).get(0), (String)((Vector)leido.get(0)).get(1));
		return null;
	}
	
	//Inserción de un nuevo usuario en la base de datos
	public int insert() throws Exception{
		Agente agente= Agente.getAgente();
		return agente.insert("INSERT INTO `iso`.`usuario` (`login`, `pass`) VALUES ('"+mLogin+"', '"+mPassword+"');");
	}
	
	public int eliminar() throws Exception {
		Agente agente= Agente.getAgente();
		return agente.delete("DELETE FROM `iso`.`usuario` WHERE (`login` = '"+mLogin+"')");
	}

	public int update () throws Exception{
		//por ahora no nos ha hecho falta actualizar nada...
		return 0;
	}
	
	public String toString() {
		return mLogin+ mPassword;
	}
	
}
