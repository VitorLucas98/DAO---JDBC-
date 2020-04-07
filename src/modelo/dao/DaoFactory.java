package modelo.dao;

import modelo.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	// essa Classe vai ter opera��es estaticas responsaveis por instanciar os DAOs, 
	// dessa forma as classe vai expor o m�todo que retorna o tipo da interface mais
	//internamente ela vai instanciar a implementa��o,isso � uma macete para n�o expor a implementa��o deixar somente a interface !
	// No programa principal vai ser diferente;
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}

}
