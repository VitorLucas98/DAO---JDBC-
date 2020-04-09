package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	}// o contrutor irá força uma injeção de dependencia, ou seje, o objeto conn estará disponivel em qualquer lugar da classe
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
							"SELECT seller.*,department.Name as DepName  FROM "
							+ "seller INNER JOIN department  ON "
							+ "seller.DepartmentId = department.Id  WHERE"
							+ " seller.Id = ?"); 
						
					st.setInt(1, id);
					rs = st.executeQuery();
					/*
					 como o rs(ResultSet) retorna os dados em forma de tabela, porém estamos programando em OO
					 a nossa classe DAO é reponsavel por pegar os dados e transformar em objetos associados;
					 Na verdade vamos criar um objeto, com os dados do id selecionado;
					 
					  Relembrando que quando eu acabo de fazer uma consulta com o ResultSet e vem o resultado no rs 
					 esse ResultSet está apontando para a posição 0, a posição 0 não contem objeto só na poição 1 que contem !
					 
					 */	
					if(rs.next()) { // esse if serve para verificar se veio algum resultado caso não venha ele vai 
//retorna null ou seja não existe o id desejad, se deu verdadeiro entao é pq retornou dados existentes, então iremos navegar,
// nos dados para instaciar os objetos !
						Department dep = new Department();
						dep.setId(rs.getInt("DepartmentId"));
						dep.setNome(rs.getString("DepName"));
						Seller sl = new Seller();
						sl.setId(rs.getInt("Id"));
						sl.setName(rs.getString("Name"));
						sl.setEmail(rs.getString("Email"));
						sl.setBirthDate(rs.getDate("BirthDate"));
						sl.setBaseSalary(rs.getDouble("BaseSalary"));
						sl.setDepartment(dep);
						
						return sl;
								
						
					}return null;
					
				}catch(SQLException e) {
					throw new DbException(e.getMessage());
				}finally {
					DB.closeStatement(st);
					DB.closeResultSet(rs);
		//DB.closeConnection(); não é necessario realizar o fechamento da conexao pois o mesmo pode ser usado em outra operação
		//  será fechado depois !
				}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
