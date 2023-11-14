package servelets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet  implements Serializable{

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
			
	public Long getUserLogado(HttpServletRequest request ) {
		
		HttpSession session = request.getSession();
		
		String usuarioLog = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUserLogado(usuarioLog).getId();
	}
	
	
	
	
	
	
}
