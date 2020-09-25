package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;
	
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanCursoJsp usuario){
		
		try {
		String sql = "INSERT INTO usuario(login, senha, nome) values (?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		
		insert.setString(1, usuario.getLogin());
		insert.setString(2, usuario.getSenha());
		insert.setString(3, usuario.getNome());
		insert.execute();
		connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}			
		}
		
	}
	
	public List<BeanCursoJsp> listar() throws Exception{
		List<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
		
		String sql = "SELECT * FROM usuario";
		PreparedStatement insert = connection.prepareStatement(sql);
		ResultSet resultset = insert.executeQuery();
		
		while(resultset.next()) {
			
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			
			beanCursoJsp.setId(resultset.getLong("id"));
			beanCursoJsp.setNome(resultset.getString("nome"));
			beanCursoJsp.setLogin(resultset.getString("login"));
			beanCursoJsp.setSenha(resultset.getString("senha"));
			
			
			lista.add(beanCursoJsp);
		}		
		return lista;		
	}
	
	public void delete(String id) {
		
		try {
		String sql = "DELETE FROM usuario WHERE id ='" + id +"'";
		PreparedStatement delete = connection.prepareStatement(sql);
		delete.execute();
		connection.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}			
		}
		
	}
	
	public BeanCursoJsp consultar(String id) throws Exception {
		
			String sql = "SELECT * FROM usuario WHERE id ='" + id +"'";
			
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultSet = select.executeQuery();
			
			if(resultSet.next()) {
				BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
				
				beanCursoJsp.setId(resultSet.getLong("id"));
				beanCursoJsp.setNome(resultSet.getString("nome"));
				beanCursoJsp.setLogin(resultSet.getString("login"));
				beanCursoJsp.setSenha(resultSet.getString("senha"));
				
				
				return beanCursoJsp;
			}				
			
		return null;
		
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "SELECT count(1) as qtd FROM usuario WHERE login ='" + login +"'";
		
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		
		if(resultSet.next()) {		
			return resultSet.getInt("qtd") <= 0;
		}				
		
	return false;
	
}

	public void atualizar(BeanCursoJsp usuario) {
		try {
			String sql = "UPDATE usuario set login = ?, senha = ?, nome = ? WHERE id ='" + usuario.getId() +"'";
			PreparedStatement atualizar = connection.prepareStatement(sql);
			
			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getSenha());
			atualizar.setString(3, usuario.getNome());
			
			atualizar.executeUpdate();
			connection.commit();
			
			}catch(Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}			
			}
		
	}
}
