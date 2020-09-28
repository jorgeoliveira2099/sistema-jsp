package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import beans.Telefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;
	
	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}
	
public void salvar(Telefones telefone){
		
		try {
		String sql = "INSERT INTO telefone(numero, tipo, usuario) values (?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		
		insert.setString(1, telefone.getNumero());
		insert.setString(2, telefone.getTipo());
		insert.setLong(3, telefone.getUsuario());
		
		
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

public List<Telefones> listar(Long user) throws Exception{
	List<Telefones> lista = new ArrayList<Telefones>();
	
	String sql = "SELECT * FROM telefone where usuario = " + user;
	PreparedStatement insert = connection.prepareStatement(sql);
	ResultSet resultset = insert.executeQuery();
	
	while(resultset.next()) {
		
		Telefones telefones = new Telefones();
		
		telefones.setId(resultset.getLong("id"));
		telefones.setNumero(resultset.getString("numero"));
		telefones.setTipo(resultset.getString("tipo"));
		telefones.setUsuario(resultset.getLong("usuario"));
		
		System.out.println(telefones);
		lista.add(telefones);
	
	}		
	return lista;		
}



public void delete(String id) {
	
	try {
	String sql = "DELETE FROM telefone WHERE id ='" + id +"'";
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


	
	
}
