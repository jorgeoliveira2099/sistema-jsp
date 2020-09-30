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
		String sql = "INSERT INTO usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, fotobase64, contenttype) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		
		insert.setString(1, usuario.getLogin());
		insert.setString(2, usuario.getSenha());
		insert.setString(3, usuario.getNome());
		insert.setString(4, usuario.getTelefone());
		
		insert.setString(5, usuario.getCep());
		insert.setString(6, usuario.getRua());
		insert.setString(7, usuario.getBairro());
		insert.setString(8, usuario.getCidade());
		insert.setString(9, usuario.getEstado());
		
		insert.setString(10, usuario.getFotoBase64());
		insert.setString(11, usuario.getContentType());
		
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
			beanCursoJsp.setTelefone(resultset.getString("telefone"));
			
			beanCursoJsp.setCep(resultset.getString("cep"));
			beanCursoJsp.setRua(resultset.getString("rua"));
			beanCursoJsp.setBairro(resultset.getString("bairro"));
			beanCursoJsp.setCidade(resultset.getString("cidade"));
			beanCursoJsp.setEstado(resultset.getString("estado"));
			
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
				beanCursoJsp.setTelefone(resultSet.getString("telefone"));
				
				beanCursoJsp.setCep(resultSet.getString("cep"));
				beanCursoJsp.setRua(resultSet.getString("rua"));
				beanCursoJsp.setBairro(resultSet.getString("bairro"));
				beanCursoJsp.setCidade(resultSet.getString("cidade"));
				beanCursoJsp.setEstado(resultSet.getString("estado"));
				
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
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		
		String sql = "SELECT count(1) as qtd FROM usuario WHERE login ='" + login +"' and id <> "+ id;
		
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		
		if(resultSet.next()) {		
			return resultSet.getInt("qtd") <= 0;
		}				
		
	return false;
	
}	
	
	public boolean validarSenha(String senha) throws Exception {
	
	String sql = "SELECT count(1) as qtd FROM usuario WHERE senha ='" + senha +"'";
	
	PreparedStatement select = connection.prepareStatement(sql);
	ResultSet resultSet = select.executeQuery();
	
	if(resultSet.next()) {		
		return resultSet.getInt("qtd") <= 0;
	}				
	
return false;

}

	public void atualizar(BeanCursoJsp usuario) {
		try {
			String sql = "UPDATE usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ? WHERE id ='" + usuario.getId() +"'";
			PreparedStatement atualizar = connection.prepareStatement(sql);
			
			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getSenha());
			atualizar.setString(3, usuario.getNome());
			atualizar.setString(4, usuario.getTelefone());
			
			atualizar.setString(5, usuario.getCep());
			atualizar.setString(6, usuario.getRua());
			atualizar.setString(7, usuario.getBairro());
			atualizar.setString(8, usuario.getCidade());
			atualizar.setString(9, usuario.getEstado());
			
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
