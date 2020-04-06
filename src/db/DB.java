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
	 * 1º passo: - criar o método loadProperties() que servirá para carregar as
	 * propriedades que estão salvas no arquivo db.properties e guarda-los dentro de
	 * um objeto especifico, ou seja, vai abrir o arquivo db.properties e guarda
	 * dentro de um objeto do tipo properties. 2ª passo: criar o método de conexão
	 * com o banco (getConnection)
	 * 
	 * 
	 */

	private static Connection conn = null; // Connection vai ser o objeto de conexã com o banco do JDBC

	public static Connection getConnection() { // método do tipo Connection
		if (conn == null) { // teste para ver se objeto esta vazio, se estiver, vou ter q conectar com o
							// banco
			try {
				Properties props = loadProperties(); // pegar as propriedades de conexão criadas no método
														// loadProperties()
				String url = props.getProperty("dburl"); // pegar a URL do banco de dados através do método getProperty
				// dburl - endereço da URL do banco definido no arquivo db.properties
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
			// está conectado a alguma fonte de dados: arquivo, conexão de internet, vídeo e
			// etc.
			Properties props = new Properties(); // criação de uma instanacia do tipo Properties;
			props.load(fs); // o comando load ele faz a leitura do arquivo props apontado pelo InputStream
							// (fs)
			// e vai guarda os dados dentro do objeto props
			return props; // ao final retorna o objeto props
			// teremos que fazer o cath pois essa operação pode gerar um erro
		} catch (IOException e) {
			throw new DbException(e.getMessage()); // caso de uma execeção eu lançarei a excessao que criei
		}

	}

	public static void closeStatement(Statement st) { // métod para fechar o objeto: Statement
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) { // métod para fechar o  objeto: ResultSet
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
