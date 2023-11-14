package servelets;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/*Controller são as servlets*/
@WebServlet(urlPatterns = {"/principal/ServeletLogin", "/ServeletLogin"}) /* Mapeamento de URL da Tela */
public class ServeletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository(); 
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public ServeletLogin() {
	}

	/* Recebe os dados pela Url em parametro */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		if(acao != null  && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //invalidar a sessa
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);			
		}
		
	}

	/* Recebe os dados enviados por um formulario */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String url = request.getParameter("url");
	
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin mLogin = new ModelLogin();
				mLogin.setLogin(login);
				mLogin.setPassword(senha);
			
				if (daoLoginRepository.validarAutenticar(mLogin)) {
					
					mLogin = daoUsuarioRepository.consultaUserLogin(login);
										
					request.getSession().setAttribute("usuario", mLogin.getLogin());
					request.getSession().setAttribute("perfil", mLogin.getPerfilUser());
					
					
					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					else {
						url = "principal/principal.jsp";
					}
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);	
				}
				else {
						RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
						request.setAttribute("msg", "Informe o login e senha corretamente");
						redirecionar.forward(request, response);
						}
			} 
			else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente");
				redirecionar.forward(request, response);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
		}
	}

}
