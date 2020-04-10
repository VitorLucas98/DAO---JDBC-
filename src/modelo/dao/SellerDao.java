package modelo.dao;

import java.util.List;

import modelo.entidade.Department;
import modelo.entidade.Seller;


public interface SellerDao {
	
	void insert(Seller obj); // método que vai inserir um objeto deparment
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id); // essa operação vai ser responsavel por pegar esse Id e 
	//consulta no banco de dados um objeto com esse id, se existir vai retornar se não existir vai retorna nulo;
	List<Seller> findAll();// retorna todos os objetos
	List<Seller> findDepartment(Department department); // métod que vai buscar por departamento 

}
