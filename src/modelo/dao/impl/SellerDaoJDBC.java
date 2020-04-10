package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import modelo.dao.SellerDao;
import modelo.entidade.Department;
import modelo.entidade.Seller;

public class SellerDaoJDBC implements SellerDao {
	// classe que usa os m�todos da interface SellerDao

	private Connection conn; // classe DAO vai ter uma dependencia com a conexao

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}// o contrutor ir� for�a uma inje��o de dependencia, ou seje, o objeto conn
		// estar� disponivel em qualquer lugar da classe

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName  FROM " + "seller INNER JOIN department  ON "
							+ "seller.DepartmentId = department.Id  WHERE" + " seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			/*
			 * como o rs(ResultSet) retorna os dados em forma de tabela, por�m estamos
			 * programando em OO a nossa classe DAO � reponsavel por pegar os dados e
			 * transformar em objetos associados; Na verdade vamos criar um objeto, com os
			 * dados do id selecionado;
			 * 
			 * Relembrando que quando eu acabo de fazer uma consulta com o ResultSet e vem o
			 * resultado no rs esse ResultSet est� apontando para a posi��o 0, a posi��o 0
			 * n�o contem objeto s� na poi��o 1 que contem !
			 * 
			 */
			if (rs.next()) { // esse if serve para verificar se veio algum resultado caso n�o venha ele vai
//retorna null ou seja n�o existe o id desejad, se deu verdadeiro entao � pq retornou dados existentes, ent�o iremos navegar,
// nos dados para instaciar os objetos !
				
				
				Department dep = instanciaDepartment(rs); // utiliza��o do m�todo instanciaDepartment para reutiliza��o de c�digo
				Seller obj = instanciaSeller(rs, dep); // utiliza��o do m�todo instanciaSeller para reutiliza��o de c�digo

				return obj;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			// DB.closeConnection(); n�o � necessario realizar o fechamento da conexao pois
			// o mesmo pode ser usado em outra opera��o
			// ser� fechado depois !
		}
	}

	private Seller instanciaSeller(ResultSet rs, Department dep) throws SQLException { // m�todo de instancia do objeto Seller
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instanciaDepartment(ResultSet rs) throws SQLException { // m�todo de instancia do objeto Department
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  "
					+ "FROM seller INNER JOIN department  "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			
			// como s�o varios valores vamos ter q usar o while e usaremos uma lista
			List<Seller> lista = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>(); // cria��o de uma estrutura map para controlar 
			//a n�o repeti��o de departamento; Integer = id , e Department =  objeto
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				// primeiro vamos testar se o departamento j� existe 
				
				if(dep == null) {
					dep = instanciaDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller obj = instanciaSeller(rs, dep);
				lista.add(obj);
				
				/* se fosse desse jeito estaria errado pois iria instanciar 2 objetos com os mesmos valores, 
				 temos que fazer de um jeito para instanciar s� um objeto 
				  
				 Department dep = instanciaDepartment(rs);
				Seller obj = instanciaSeller(rs, dep);
				lista.add(obj);
				*/
			}
			
			return lista;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
