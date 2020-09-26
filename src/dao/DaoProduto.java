package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import beans.Produto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
public void salvar(Produto produto){
		
		try {
		String sql = "INSERT INTO produto(nome_produto, valor_produto, quantidade_produto) values (?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		
		insert.setString(1, produto.getNome());
		insert.setString(2, produto.getValor());
		insert.setString(3, produto.getQuantidade());
		
		
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

public List<Produto> listar() throws Exception{
	List<Produto> lista = new ArrayList<Produto>();
	
	String sql = "SELECT * FROM produto";
	PreparedStatement insert = connection.prepareStatement(sql);
	ResultSet resultset = insert.executeQuery();
	
	while(resultset.next()) {
		
		Produto produto = new Produto();
		
		produto.setId(resultset.getLong("id"));
		produto.setNome(resultset.getString("nome_produto"));
		produto.setValor(resultset.getString("valor_produto"));
		produto.setQuantidade(resultset.getString("quantidade_produto"));
		
		System.out.println(produto);
		lista.add(produto);
	
	}		
	return lista;		
}



public void delete(String id) {
	
	try {
	String sql = "DELETE FROM produto WHERE id ='" + id +"'";
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

public Produto consultar(String id) throws Exception {
	
		String sql = "SELECT * FROM produto WHERE id ='" + id +"'";
		
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		
		if(resultSet.next()) {
			Produto produto = new Produto();
			
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome_produto"));
			produto.setValor(resultSet.getString("valor_produto"));
			produto.setQuantidade(resultSet.getString("quantidade_produto"));
			
			
			return produto;
		}				
		
	return null;
	
}

public boolean validarNomeProduto(String nome) throws Exception {
	
	String sql = "SELECT count(1) as qtd FROM produto WHERE nome_produto ='" + nome +"'";
	
	PreparedStatement select = connection.prepareStatement(sql);
	ResultSet resultSet = select.executeQuery();
	
	if(resultSet.next()) {		
		return resultSet.getInt("qtd") <= 0;
	}				
	
return false;

}

public boolean validarNomeProdutoUpdate(String nome, String id) throws Exception {
	
	String sql = "SELECT count(1) as qtd FROM produto WHERE nome_produto ='" + nome +"' and id <> "+ id;
	
	PreparedStatement select = connection.prepareStatement(sql);
	ResultSet resultSet = select.executeQuery();
	
	if(resultSet.next()) {		
		return resultSet.getInt("qtd") <= 0;
	}				
	
return false;

}	



public void atualizar(Produto produto) {
	try {
		String sql = "UPDATE produto set nome_produto = ?, valor_produto = ?, quantidade_produto = ? WHERE id ='" + produto.getId() +"'";
		PreparedStatement atualizar = connection.prepareStatement(sql);
		
		atualizar.setString(1, produto.getNome());
		atualizar.setString(2, produto.getValor());
		atualizar.setString(3, produto.getQuantidade());
		
		
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
