package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	/*
	 * 1� passo: - criar o m�todo loadProperties() que servir� para carregar as
	 * propriedades que est�o salvas no arquivo db.properties e guarda-los dentro de
	 * um objeto especifico, ou seja, vai abrir o arquivo db.properties e guarda
	 * dentro de um objeto do tipo properties. 2� passo: criar o m�todo de conex�o
	 * com o banco (getConnection)
	 * 
	 * 
	 */

	private static Connection conn = null; // Connection vai ser o objeto de conex� com o banco do JDBC

	public static Connection getConnection() { // m�todo do tipo Connection
		if (conn == null) { // teste para ver se objeto esta vazio, se estiver, vou ter q conectar com o
							// banco
			try {
				Properties props = loadProperties(); // pegar as propriedades de conex�o criadas no m�todo
														// loadProperties()
				String url = props.getProperty("dburl"); // pegar a URL do banco de dados atrav�s do m�todo getProperty
				// dburl - endere�o da URL do banco definido no arquivo db.properties
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() { // retorna um objeto do tipo properties
		try (FileInputStream fs = new FileInputStream("db.properties")) { // InputStream faz parte da leitura de dados,
																			// ou seja,
			// est� conectado a alguma fonte de dados: arquivo, conex�o de internet, v�deo e
			// etc.
			Properties props = new Properties(); // cria��o de uma instanacia do tipo Properties;
			props.load(fs); // o comando load ele faz a leitura do arquivo props apontado pelo InputStream
							// (fs)
			// e vai guarda os dados dentro do objeto props
			return props; // ao final retorna o objeto props
			// teremos que fazer o cath pois essa opera��o pode gerar um erro
		} catch (IOException e) {
			throw new DbException(e.getMessage()); // caso de uma exece��o eu lan�arei a excessao que criei
		}

	}

	public static void closeStatement(Statement st) { // m�tod para fechar o objeto: Statement
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) { // m�tod para fechar o  objeto: ResultSet
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
