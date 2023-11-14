package servelets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = {"/ServeletUsuarioController"})
public class ServeletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository dao = new DAOUsuarioRepository();

	public ServeletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarjax")) {
			
			String idUser = request.getParameter("id");
			
			if(idUser == null || idUser.isEmpty()) {
				response.getWriter().write("Usuario nao encontrado");
			
			}else {
				dao.deletarUser(idUser);
				response.getWriter().write("Excluido com sucesso!");;
			}
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscaUserjax")) {
			
			String nomeUser = request.getParameter("nomeUser");
												
			List<ModelLogin> resultadoJasonUser = dao.buscarUser(nomeUser, super.getUserLogado(request));
		
			if(!resultadoJasonUser.isEmpty()) {
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(resultadoJasonUser);
				
				response.getWriter().write(json);
				
			}else {
				response.getWriter().write("Usuario nao encontrado");
			}
			
			
	
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ){
		
			String idUser = request.getParameter("id");
			
			ModelLogin modelLogin = dao.consultaUserId(idUser, super.getUserLogado(request));
			
			
			request.setAttribute("modolLogin", modelLogin);
			request.setAttribute("msg", "Usuario em Edição!");
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/cadastroUser.jsp");
			redirecionar.forward(request, response);
			
			
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
			
			List<ModelLogin> usuarioLogin = dao.listagem(super.getUserLogado(request));
			
			
				
				/*
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(usuarioLogin);
				*/
				request.setAttribute("modelLogins", usuarioLogin);
				request.setAttribute("msg", "Usuario Carregados!");
				request.getRequestDispatcher("principal/cadastroUser.jsp").forward(request, response);
				
			}else{
				request.getRequestDispatcher("principal/cadastroUser.jsp");
			}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String perfilUser = request.getParameter("PerfilUser");
		String sexo = request.getParameter("sexo");
		String password = request.getParameter("password");
		
		if(request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
			
			Part part = request.getPart("fileFoto");
			
			if(part != null) {
				
				try (InputStream fileContent = part.getInputStream()){
					
					byte[] foto = fileContent.readAllBytes();
					
					String imagemBase64 = Base64.getEncoder().encodeToString(foto);
					
                    System.out.println(imagemBase64);

					
					
				}
				
			}
			
			
			
		}
		

		ModelLogin usuario = new ModelLogin(id != null && !id.isEmpty() ? Long.parseLong(id) : null, nome, email, login,password, perfilUser, sexo);
		
		if (dao.validarLogin(usuario.getLogin()) && usuario.getId() == null) {
			request.setAttribute("msg", "USUARIO JÁ EXISTENTE!");

		} else { 
			if(usuario.isNovo()) {
				request.setAttribute("msg", "CADASTRO SALVO!");
				usuario = dao.InsertUser(usuario, super.getUserLogado(request));
			} else {	
				request.setAttribute("msg", "ATUALIZADO COM SUCESSO!");
				usuario = dao.InsertUser(usuario, super.getUserLogado(request));
			}
			
		}

		
		List<ModelLogin> usuarioLogin = dao.listagem(super.getUserLogado(request));
		request.setAttribute("modelLogins", usuarioLogin);
		
		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/cadastroUser.jsp");
		request.setAttribute("modolLogin", usuario);
		System.out.print(usuario);
		redirecionar.forward(request, response);
		

	}

}
