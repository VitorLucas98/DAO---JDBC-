package aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import modelo.dao.DaoFactory;
import modelo.dao.DepartmentDao;
import modelo.entidade.Department;
import modelo.entidade.Seller;

public class ProgramaTesteDepartment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Scanner sc = new Scanner(System.in);

		System.out.println("=== Teste 1 === Department findById");
		Department obj = departmentDao.findById(3);
		System.out.println(obj);

		System.out.println("\n === Teste 2 === Department findAll");
		List<Department> lista = departmentDao.findAll();
		for (Department department : lista) {
			System.out.println(department);
		}

		System.out.println("\n === Teste 3 === Department Insert");
		Department newDepartment = new Department(null, "Alimentos");
		departmentDao.insert(newDepartment);
		System.out.println("ID = " + newDepartment.getId());

		System.out.println("\n === Teste 4 === Department Update");
		Department department = departmentDao.findById(9);
		department.setNome("Cosmeticos");
		departmentDao.update(department);
		System.out.println("Update concluido ! ");

		System.out.println("\n=== TEST 5: delete =======");

		System.out.print("Qual id deseja deletar: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete concluido !!");

		sc.close();

	}

}
