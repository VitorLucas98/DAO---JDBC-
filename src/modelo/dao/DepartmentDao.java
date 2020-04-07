package modelo.dao;

import java.util.List;

import modelo.entidade.Department;

public interface DepartmentDao {
 // criar os m�todos que manipulam o departamento !
	
	void insert(Department obj); // m�todo que vai inserir um objeto deparment
	void update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id); // essa opera��o vai ser responsavel por pegar esse Id e 
	//consulta no banco de dados um objeto com esse id, se existir vai retornar se n�o existir vai retorna nulo;
	List<Department> findAll();// retorna todos os objetos
	
	
	}
