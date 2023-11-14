package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexão;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection con;

	public DAOUsuarioRepository() {
		con = Conexão.getConnection();

	}
	
	public ModelLogin InsertUser(ModelLogin user, Long userLogado) {

		try { 
			if (user.isNovo()) {/*Grava um novo*/

				String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id, perfil, sexo) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getNome());
				statement.setString(4, user.getEmail());
				statement.setLong(5, userLogado);
				statement.setString(6, user.getPerfilUser());
				statement.setString(7, user.getSexo());
				
				statement.execute();
				con.commit();
				
			} else { //Atualizar
				String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=? , sexo=? WHERE id=?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getNome());
				statement.setString(4, user.getEmail());
				statement.setString(5, user.getPerfilUser());
				statement.setString(6, user.getSexo());
				statement.setLong(7, user.getId());
				
				statement.executeUpdate();
				con.commit();		
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}	
		return this.consultaUser(user.getLogin(), userLogado);	
	}

	public ModelLogin consultaUser(String login, Long userLogado) {

		ModelLogin userConsulta = new ModelLogin();
		try {

			String sql = "SELECT * FROM model_login where(login) = upper(?) and useradmin = false and usuario_id = "+ userLogado;
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				userConsulta.setNome(resultado.getString("nome"));
				userConsulta.setLogin(resultado.getString("login"));
				userConsulta.setPassword(resultado.getString("senha"));
				userConsulta.setEmail(resultado.getString("email"));
				userConsulta.setId(resultado.getLong("id"));
				userConsulta.setPerfilUser(resultado.getString("perfil"));
				userConsulta.setSexo(resultado.getString("sexo"));
			}

			return userConsulta;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userConsulta;
	}
	
	
	
	public ModelLogin consultaUserLogin(String login) {

		ModelLogin userConsulta = new ModelLogin();
		try {

			String sql = "SELECT * FROM model_login where(login) = upper(?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				userConsulta.setNome(resultado.getString("nome"));
				userConsulta.setLogin(resultado.getString("login"));
				userConsulta.setPassword(resultado.getString("senha"));
				userConsulta.setEmail(resultado.getString("email"));
				userConsulta.setId(resultado.getLong("id"));
				userConsulta.setUserAdmin(resultado.getBoolean("useradmin"));
				userConsulta.setPerfilUser(resultado.getString("perfil"));
				userConsulta.setSexo(resultado.getString("sexo"));
			}

			return userConsulta;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userConsulta;
	}
		
	public ModelLogin consultaUserLogado(String login) {

		ModelLogin userConsulta = new ModelLogin();
		try {

			String sql = "SELECT * FROM model_login where(login) = upper(?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				userConsulta.setNome(resultado.getString("nome"));
				userConsulta.setLogin(resultado.getString("login"));
				userConsulta.setPassword(resultado.getString("senha"));
				userConsulta.setEmail(resultado.getString("email"));
				userConsulta.setId(resultado.getLong("id"));
				userConsulta.setPerfilUser(resultado.getString("perfil"));
				userConsulta.setSexo(resultado.getString("sexo"));
			}

			return userConsulta;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userConsulta;
	}
	
	

	public boolean validarLogin(String usuario) {

		boolean validacao = true;
		try {
			String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, usuario);

			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				validacao = resultado.getBoolean("existe");

			} else {
				validacao = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validacao;
	}
	
	public void deletarUser(String id) {
		
		try {
			String sql = "DELETE FROM model_login where id = ? and useradmin = false";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			statement.executeUpdate();
			con.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public List<ModelLogin> buscarUser(String nome, Long usuarioLogado) {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
				
		try {
		String sql = "SELECT * FROM model_login where upper(nome) like upper (?) and  useradmin = false and usuario_id = "+ usuarioLogado;
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, "%"+nome+"%");
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModelLogin userPesquisa = new ModelLogin();
			
			userPesquisa.setNome(resultado.getString("nome"));
			userPesquisa.setLogin(resultado.getString("login"));
			userPesquisa.setEmail(resultado.getString("email"));
			userPesquisa.setId(resultado.getLong("id"));	
			userPesquisa.setPerfilUser(resultado.getString("perfil"));
			userPesquisa.setSexo(resultado.getString("sexo"));
			
			retorno.add(userPesquisa);			
		}
			
		return retorno;
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
		
	public ModelLogin consultaUserId(String id, Long userLogado) {

		ModelLogin userConsulta = new ModelLogin();

		try {

			String sql = "SELECT * FROM model_login where id = ? and useradmin = false and usuario_id = "+ userLogado;
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				userConsulta.setNome(resultado.getString("nome"));
				userConsulta.setLogin(resultado.getString("login"));
				userConsulta.setPassword(resultado.getString("senha"));
				userConsulta.setEmail(resultado.getString("email"));
				userConsulta.setId(resultado.getLong("id"));
				userConsulta.setPerfilUser(resultado.getString("perfil"));
				userConsulta.setSexo(resultado.getString("sexo"));
			}
			return userConsulta;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userConsulta;
	}
	
	public  List<ModelLogin> listagem(Long userLogado) {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		try {
			
			String sql = "SELECT * FROM model_login where useradmin = false and usuario_id = " + userLogado;
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {

				ModelLogin usuario = new ModelLogin();
				
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setId(resultado.getLong("id"));
				usuario.setPerfilUser(resultado.getString("perfil"));
				usuario.setSexo(resultado.getString("sexo"));
				
				retorno.add(usuario);
			}
			return retorno;		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}

	

}