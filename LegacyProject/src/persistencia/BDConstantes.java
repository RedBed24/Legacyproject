package persistencia;

public interface BDConstantes {
	// Driver para conectar con bases de datos MySQL
	String DRIVER ="com.mysql.cj.jdbc.Driver";
	// Información varia sobre la DB y la tabla
	String DBNAME ="iso";
	String TABLENAME = "usuario";
	String FIRSTCOLUMN = "login";
	String SECONDCOLUMN = "pass";
	String DBUSER ="root";
	String DBPASS ="root";
	// Identificador ODBC de la base de datos
	String URL = "jdbc:mysql://localhost:3306/"+DBNAME+"?user="+DBUSER+"&password="+DBPASS;

}
