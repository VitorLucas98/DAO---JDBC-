package modelo.dao;

import modelo.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	// essa Classe vai ter operações estaticas responsaveis por instanciar os DAOs, 
	// dessa forma as classe vai expor o método que retorna o tipo da interface mais
	//internamente ela vai instanciar a implementação,isso é uma macete para não expor a implementação deixar somente a interface !
	// No programa principal vai ser diferente;
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}

}
