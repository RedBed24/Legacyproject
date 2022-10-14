package legacy;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.derby.jdbc.EmbeddedDriver;

import persistencia.Agente;

public class Usuario {
	
	public String mLogin;
	public String mPassword;
	
	
	//Constructor para la creación de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}
	
	//Constructor para la creación de un Usuario
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
	}
	
	//Selección de un usuario de la base de datos a partir del login y el password
	@SuppressWarnings("unchecked")
	public static Usuario read(String login, String password) throws Exception{
		
		Agente agente= Agente.getAgente();
		Vector<Object> leido= agente.select("SELECT * FROM iso.usuario WHERE login = '"+login+"' AND pass = '"+password+"'");
		if (leido.size()==1)
			return new Usuario((String)((Vector)leido.get(0)).get(0), (String)((Vector)leido.get(0)).get(1));
		return null;
		
		/*
		String l,g;
		Usuario u = null;
		Vector<Object> aux = null;
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
		String SQL_Consulta = "SELECT login, pass FROM Usuario WHERE login = '"+login+"' AND pass = '"+password+"'";
		Vector<Object> vectoradevolver=new Vector<Object>();
		Statement stmt = mBD.createStatement();
		ResultSet res=stmt.executeQuery(SQL_Consulta);
		while (res.next()) {
			aux=new Vector<Object>();
			aux.add(res.getObject(1));
			aux.add(res.getObject(2));
			vectoradevolver.add(aux);
		}
    	stmt.close();
    	mBD.close();
		aux = new Vector<Object>();
		if (vectoradevolver.size() == 1){
			aux = (Vector<Object>) vectoradevolver.elementAt(0);
			u = new Usuario((String) aux.elementAt(0), (String) aux.elementAt(1));
		}
		return u;
		*/
	}
	
	//Inserción de un nuevo usuario en la base de datos
	public int insert() throws Exception{
		
		Agente agente= Agente.getAgente();
		return agente.insert("INSERT INTO `iso`.`usuario` (`login`, `pass`) VALUES ('"+mLogin+"', '"+mPassword+"');");

		/*
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
		PreparedStatement stmt = mBD.prepareStatement("INSERT INTO Usuario VALUES('"+this.mLogin+"','"+this.mPassword+"')");
    	int res=stmt.executeUpdate();
    	stmt.close();
    	mBD.close();
		return res;
		*/
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
	

	private String DBPORT="3308";

}
