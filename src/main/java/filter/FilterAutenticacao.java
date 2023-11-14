package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.Conexão;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisiçoes que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {

	private static final long serialVersionUID = 1L;
	
	private static Connection con;
	
	public FilterAutenticacao() {
		
    }
 
	//encessa os processo quando oservidor é parado
	public void destroy() {
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/*Intercepta as requisições e as respostas no sistema*/
	// Tudo passa por ele
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLog = (String) session.getAttribute("usuario");
			String urlAutenticar = req.getServletPath(); //url que ta sendo acessado
			
			if(usuarioLog == null && !urlAutenticar.equalsIgnoreCase("/principal/ServeletLogin")) {//N ta logando
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
				request.setAttribute("msg", "Realize o Login");
				redirecionar.forward(request, response);
				return; // para a execução e redireciona para o login
				
			}else {
				chain.doFilter(request, response);
			}
			con.commit(); //Deu tudo certo ent faz as auteração no banco de dados
		}
		catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*Inicia os processoes do sistema*/
	public void init(FilterConfig fConfig) throws ServletException {
		con = Conexão.getConnection();
	}

}
