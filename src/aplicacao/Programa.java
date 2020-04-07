package aplicacao;

import java.util.Date;

import modelo.entidade.Department;
import modelo.entidade.Seller;

public class Programa {

	public static void main(String[] args) {
		Department obj = new Department(1, "Livros");
		System.out.println(obj);
		Seller sl = new Seller(21, "Vitor", "vitorluc.2013@gmail", new Date(), 2000.9, obj);
		System.out.println(sl);
		

	}

}
