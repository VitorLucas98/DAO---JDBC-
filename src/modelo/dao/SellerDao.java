package modelo.dao;

import java.util.List;

import modelo.entidade.Department;
import modelo.entidade.Seller;


public interface SellerDao {
	
	void insert(Seller obj); // m�todo que vai inserir um objeto deparment
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id); // essa opera��o vai ser responsavel por pegar esse Id e 
	//consulta no banco de dados um objeto com esse id, se existir vai retornar se n�o existir vai retorna nulo;
	List<Seller> findAll();// retorna todos os objetos
	List<Seller> findDepartment(Department department); // m�tod que vai buscar por departamento 

}
