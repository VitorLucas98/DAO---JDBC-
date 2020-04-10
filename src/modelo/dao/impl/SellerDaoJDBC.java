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
	// classe que usa os métodos da interface SellerDao

	private Connection conn; // classe DAO vai ter uma dependencia com a conexao

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}// o contrutor irá força uma injeção de dependencia, ou seje, o objeto conn
		// estará disponivel em qualquer lugar da classe

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
			 * como o rs(ResultSet) retorna os dados em forma de tabela, porém estamos
			 * programando em OO a nossa classe DAO é reponsavel por pegar os dados e
			 * transformar em objetos associados; Na verdade vamos criar um objeto, com os
			 * dados do id selecionado;
			 * 
			 * Relembrando que quando eu acabo de fazer uma consulta com o ResultSet e vem o
			 * resultado no rs esse ResultSet está apontando para a posição 0, a posição 0
			 * não contem objeto só na poição 1 que contem !
			 * 
			 */
			if (rs.next()) { // esse if serve para verificar se veio algum resultado caso não venha ele vai
//retorna null ou seja não existe o id desejad, se deu verdadeiro entao é pq retornou dados existentes, então iremos navegar,
// nos dados para instaciar os objetos !

				Department dep = instanciaDepartment(rs); // utilização do método instanciaDepartment para reutilização
															// de código
				Seller obj = instanciaSeller(rs, dep); // utilização do método instanciaSeller para reutilização de
														// código

				return obj;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			// DB.closeConnection(); não é necessario realizar o fechamento da conexao pois
			// o mesmo pode ser usado em outra operação
			// será fechado depois !
		}
	}

	private Seller instanciaSeller(ResultSet rs, Department dep) throws SQLException { // método de instancia do objeto
																						// Seller
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instanciaDepartment(ResultSet rs) throws SQLException { // método de instancia do objeto
																				// Department
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName  " + "FROM seller INNER JOIN department  "
							+ "ON seller.DepartmentId = department.Id ORDER BY Name");

			rs = st.executeQuery();

			// como são varios valores vamos ter q usar o while e usaremos uma lista
			List<Seller> lista = new ArrayList<>();

			Map<Integer, Department> map = new HashMap<>(); // criamso um Map vazio e ai eu vou guarda dentro desse map
			// qualquer departamento que eu instanciar

			// Ai a cada vez que passar no while vout ter que testar se o departamento já
			// existe.
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));// vou no map e tento buscar um departamento
				// que tenha esse id, se não existir, o map.get vai retornar nulo.

				if (dep == null) { // se for nulo, teremos que instanciar o dep a partir do rs
					dep = instanciaDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep); // vou salvar esse departamento dentro do map para que na
																// proxima vez
					// poder verificar e ver se ele já existe
				}
				Seller obj = instanciaSeller(rs, dep);
				lista.add(obj);

				/*
				 * se fosse desse jeito estaria errado pois iria instanciar 2 objetos com os
				 * mesmos valores, temos que fazer de um jeito para instanciar só um objeto
				 * 
				 * Department dep = instanciaDepartment(rs); Seller obj = instanciaSeller(rs,
				 * dep); lista.add(obj);
				 */
			}

			return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName  " + "FROM seller INNER JOIN department  "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			// como são varios valores vamos ter q usar o while e usaremos uma lista
			List<Seller> lista = new ArrayList<>();

			Map<Integer, Department> map = new HashMap<>(); // criamso um Map vazio e ai eu vou guarda dentro desse map
			// qualquer departamento que eu instanciar

			// Ai a cada vez que passar no while vout ter que testar se o departamento já
			// existe.
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));// vou no map e tento buscar um departamento
				// que tenha esse id, se não existir, o map.get vai retornar nulo.

				if (dep == null) { // se for nulo, teremos que instanciar o dep a partir do rs
					dep = instanciaDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep); // vou salvar esse departamento dentro do map para que na
																// proxima vez
					// poder verificar e ver se ele já existe
				}
				Seller obj = instanciaSeller(rs, dep);
				lista.add(obj);

				/*
				 * se fosse desse jeito estaria errado pois iria instanciar 2 objetos com os
				 * mesmos valores, temos que fazer de um jeito para instanciar só um objeto
				 * 
				 * Department dep = instanciaDepartment(rs); Seller obj = instanciaSeller(rs,
				 * dep); lista.add(obj);
				 */
			}

			return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
