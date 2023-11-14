package model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String perfilUser;
	private String sexo;
	
	private String fotoUser;
	private String extensaofotoUser;
	
	private String login;
	private String password;
	
	private boolean userAdmin;
	
	
	public boolean isNovo() {
		if(this.id == null) {
			return true; //inserir um novo
		}else {
			return false; //atualizar
		}
	}
	
	
	public ModelLogin() {
		
	}

	public ModelLogin(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	

	public ModelLogin(Long id, String nome, String email, String login, String password, String perfilUser, String sexo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.password = password;
		this.perfilUser = perfilUser;
		this.sexo = sexo;

	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean getUserAdmin() {
		return userAdmin;
	}


	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}


	public String getPerfilUser() {
		return perfilUser;
	}


	public void setPerfilUser(String perfilUser) {
		this.perfilUser = perfilUser;
	}



	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getFotoUser() {
		return fotoUser;
	}


	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}


	public String getExtensaofotoUser() {
		return extensaofotoUser;
	}


	public void setExtensaofotoUser(String extensaofotoUser) {
		this.extensaofotoUser = extensaofotoUser;
	}
	
	
	
	
	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", nome=" + nome + ", email=" + email + ", perfilUser=" + perfilUser
				+ ", login=" + login + ", password=" + password + ", userAdmin=" + userAdmin + "]";
	}

	
}
