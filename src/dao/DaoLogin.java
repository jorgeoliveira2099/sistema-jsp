package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;
	
	//construtor padrao
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	//valida usuario no banco de dados
	public boolean validarLogin(String login, String senha) throws Exception{
		
		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();
		
		if(resultset.next()) {
			return true;
		}else {
			return false;
		}		
	}
	
}
