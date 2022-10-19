package persistencia;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Agente  implements BDConstantes {
	
	// Instancia del agente
	protected static Agente mInstancia = null;
	
	// Conexión con la base de datos
	protected static Connection mBD;

	// Constructor
	private Agente() throws Exception {
		conectar();

	}

	// Implementación del patrón Singleton --> http://es.wikipedia.org/wiki/Singleton 
	
	public static Agente getAgente() throws Exception {
		if (mInstancia == null) {
			mInstancia = new Agente();
		}
		return mInstancia;
	}

	// Conexión a la base de datos
	
	private void conectar() throws Exception {
		Class.forName(DRIVER);
		mBD = DriverManager.getConnection(URL, DBUSER, DBPASS);
	}

	// Desconectar de la base de datos
	
	public void desconectar() throws Exception {
		mBD.close();
	}

	// Inserción en la base de datos
	
	public int insert(String login, String password) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement("insert into `"+DBNAME+"`.`"+TABLENAME+"` ("+FIRSTCOLUMN+", "+SECONDCOLUMN+") Values ('"+login+"', '"+password+"')");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// Eliminación de la base de datos
	
	public int delete(String login, String password) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement("delete from `"+DBNAME+"`.`"+TABLENAME+"` where "+FIRSTCOLUMN+" = '"+login+"' AND "+SECONDCOLUMN+" = '"+password+"'");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	/*
	 * Búsqueda o selección de información en la base de datos.
	 * El método select devuelve un vector de vectores donde cada uno representa 
	 * los registros que se recuperan de la base de datos.
	 */
	
	public Vector<Object> select(String login, String password) throws SQLException, Exception {
		Vector<Object> vectorAdevolver = new Vector<Object>();
		conectar();
		Statement stmt = mBD.createStatement();
		ResultSet res = stmt.executeQuery("select * from `"+DBNAME+"`.`"+TABLENAME+"` where "+FIRSTCOLUMN+" = '"+login+"' AND "+SECONDCOLUMN+" = '"+password+"'");
		ResultSetMetaData rsmd = res.getMetaData();
		int numCol = rsmd.getColumnCount();
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			for (int i = 1; i <= numCol; i++) {
				v.add(res.getObject(i));
			}
			vectorAdevolver.add(v);
		}
		stmt.close();
		desconectar();
		return vectorAdevolver;

	}
}
