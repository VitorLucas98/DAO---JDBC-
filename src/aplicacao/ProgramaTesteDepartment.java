package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.DepartmentDao;
import modelo.entidade.Department;

public class ProgramaTesteDepartment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DepartmentDao departementDao= DaoFactory.createDepartmentDao();	
		
		System.out.println("=== Teste 1 === Department findById");
		Department obj = departementDao.findById(3);
		System.out.println(obj);
		
		
		}

}
