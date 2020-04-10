package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.dao.DaoFactory;
import modelo.dao.SellerDao;
import modelo.entidade.Department;
import modelo.entidade.Seller;

public class Programa {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		/*
		 * Department obj = new Department(1, "Livros"); System.out.println(obj); Seller
		 * sl = new Seller(21, "Vitor", "vitorluc.2013@gmail", new Date(), 2000.9, obj);
		 * System.out.println(sl);
		 */

		SellerDao sellerDao = DaoFactory.createSellerDao();// isso faz como que para instanciar uma DAO eu chamo a
															// fabrica,
		// o programa não conhece a implementação, conhece somente a interface, uma
		// forma de fazer uma injeção de dependencia !

		System.out.println("=== Teste 1 === Seller findById");
		Seller obj = sellerDao.findById(7);
		System.out.println(obj);


		System.out.println("\n === Teste 2 === Seller findByDepartment");
		Department department = new Department(2, null); // como tenho que passar um obj departamento para
															// findDepartment
		List<Seller> lista = sellerDao.findDepartment(department);
		for (Seller seller : lista) {
			System.out.println(seller);
		}

		System.out.println("\n === Teste 3 === Seller findAll");
		// como tenho que passar um obj departamento para findDepartment
		lista = sellerDao.findAll();
		for (Seller seller : lista) {
			System.out.println(seller);
		}
		
		System.out.println("\n === Teste 4 === Seller Insert");
		Seller newSeller = new Seller(null, "Alan Turing", "alantun@gmail.com", 
				new Date(sdf.parse("23/06/1912").getTime()), 7598.9, department);
		sellerDao.insert(newSeller);
		System.out.println("ID = " + newSeller.getId());
	}

}
