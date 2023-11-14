package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Conexão;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection con ;
	
	
	public DAOLoginRepository() {
		con = Conexão.getConnection();
	}
	
	
	public boolean validarAutenticar(ModelLogin modelLogin) throws Exception {
	
		String sql = "select * from  model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getPassword());
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true; /*Autenticado*/
		}else {
			return false; /*N autenticado*/
		}		
	}
	

	
	
	
}
