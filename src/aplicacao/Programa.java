package aplicacao;

import java.util.List;

import modelo.dao.DaoFactory;
import modelo.dao.SellerDao;
import modelo.entidade.Department;
import modelo.entidade.Seller;

public class Programa {

	public static void main(String[] args) {
		/*
		 Department obj = new Department(1, "Livros");
		System.out.println(obj);
		Seller sl = new Seller(21, "Vitor", "vitorluc.2013@gmail", new Date(), 2000.9, obj);
		System.out.println(sl);*/
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();// isso faz como que para instanciar uma DAO eu chamo a fabrica, 
		//o programa não conhece a implementação, conhece somente a interface, uma forma de fazer uma injeção de dependencia !
		
		System.out.println("=== Teste 1 === Seller findById");
		Seller obj = sellerDao.findById(7);
		System.out.println(obj);
		
		System.out.println();
		
		System.out.println("=== Teste 2 === Seller findByDepartment");
		Department department = new Department(2, null);
		List<Seller> lista = sellerDao.findDepartment(department);
		for (Seller seller : lista) {
			System.out.println(seller);
		}
	}

}
