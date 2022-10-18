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
	// Conexi�n con la base de datos
	protected static Connection mBD;

	// Constructor
	private Agente() throws Exception {
		conectar();

	}

	// Implementaci�n del patr�n singleton
	// Este patr�n de dise�o permite implementar clases de las cuales
	// solo existe una instancia
	// http://es.wikipedia.org/wiki/Singleton
	public static Agente getAgente() throws Exception {
		if (mInstancia == null) {
			mInstancia = new Agente();
		}
		return mInstancia;
	}

	// M�todo para realizar la conexion a la base de datos
	private void conectar() throws Exception {
		Class.forName(DRIVER);
		mBD = DriverManager.getConnection(URL, DBUSER, DBPASS);
	}

	// M�todo para desconectar de la base de datos
	public void desconectar() throws Exception {
		mBD.close();
	}

	// Metodo para realizar una insercion en la base de datos
	public int insert(String login, String password) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement("insert into `"+DBNAME+"`.`"+TABLENAME+"` ("+FIRSTCOLUMN+", "+SECONDCOLUMN+") Values ('"+login+"', '"+password+"')");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// Metodo para realizar una eliminacion en la base de datos
	public int delete(String login, String password) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement("delete from `"+DBNAME+"`.`"+TABLENAME+"` where "+FIRSTCOLUMN+" = '"+login+"' AND "+SECONDCOLUMN+" = '"+password+"'");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// M�todo para realizar una eliminaci�n en la base de datos
	public int update(String SQL) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement(SQL);
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	public Vector<Object> select(String login, String password) throws SQLException, Exception {
		/*
		 * M�todo para realizar una b�squeda o selecci�n de informaci�n en la base de
		 * datos, podemos observar como el m�todo select devuelve un vector de vectores, donde cada uno de los
		 * vectores que contiene el vector principal representa los registros que se
		 * recuperan de la base de datos.
		 */

		Vector<Object> vectoradevolver = new Vector<Object>();
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
			vectoradevolver.add(v);
		}
		stmt.close();
		desconectar();
		return vectoradevolver;

	}
}
