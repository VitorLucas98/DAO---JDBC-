package db;

// Classe criada para tratar erro de integridade referencial, isto �, caso eu queira excluir um dado de que 
//� chave estrangeira em outra tabela
public class DbIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}

}
