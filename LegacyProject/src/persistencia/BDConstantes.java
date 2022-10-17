package persistencia;

public interface BDConstantes {
	// Driven para conectar con bases de datos MySQL
	final static String DRIVER ="com.mysql.cj.jdbc.Driver";
	final static String CONNECTION_STRING ="jdbc:derby:iso1_db;create=true";
	final static String DBNAME ="iso";
	final static String DBUSER ="root";
	final static String DBPASS ="root"; // instancia del agente
	// Identificador ODBC de la base de datos
	final static String URL = "jdbc:mysql://localhost:3306/iso?user=root&password=root";

}
