package modelo.dao;

import java.util.List;

import modelo.entidade.Department;

public interface DepartmentDao {
 // criar os métodos que manipulam o departamento !
	
	void insert(Department obj); // método que vai inserir um objeto deparment
	void update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id); // essa operação vai ser responsavel por pegar esse Id e 
	//consulta no banco de dados um objeto com esse id, se existir vai retornar se não existir vai retorna nulo;
	List<Department> findAll();// retorna todos os objetos
	
	
	}
