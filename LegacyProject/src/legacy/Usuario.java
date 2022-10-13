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
	
	//Selecci�n de un usuario de la base de datos a partir del login y el password
	@SuppressWarnings("unchecked")
	public static Usuario read(String login, String password) throws Exception{
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
	}
	
	//Inserción de un nuevo usuario en la base de datos
	public int insert() throws Exception{
		
		Agente agente= Agente.getAgente();
		agente.insert("INSERT INTO `iso`.`usuario` (`login`, `pass`) VALUES ('"+mLogin+"', '"+mPassword+"');");
		
		// estas líneas es para comprobar que se haya conseguid añadir bien el usuario
		System.out.println("Usuario a intentar añadir: "+this);
		Vector<Object> vect= agente.select("SELECT * FROM iso.usuario WHERE login='"+mLogin+"'");
		Usuario usr= new Usuario(vect.get(0).toString(), vect.get(0).toString());
		System.out.println("Usuario recogido de la base de datos: " +usr); 
		// estas líneas es para comprobar que se haya conseguido añadir bien el usuario

		return 0;

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
	
	public int update () throws Exception{
		//por ahora no nos ha hecho falta actualizar nada...
		return 0;
	}
	

	private String DBPORT="3308";
}
