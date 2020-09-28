package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.Telefones;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private DaoUsuario daoUsuario = new DaoUsuario();
	
	private DaoTelefones daoTelefones = new DaoTelefones();
	
	
    public TelefonesServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		try {
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("addFone")) {
				
			
			
			
			String user = request.getParameter("user");		
			BeanCursoJsp usuario = daoUsuario.consultar(user);
			
			
			
			request.getSession().setAttribute("usua", usuario);			
			request.setAttribute("usua", usuario);
			
		
			
			//request.getSession().setAttribute("userId", usuario.getId());			
			//request.getSession().setAttribute("userName", usuario.getNome());
		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
		//aqui ele lista os trelefones do usuario
		request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
		request.setAttribute("msg", "Salvo com sucesso");
		request.setAttribute("userEscolhido", user);
		view.forward(request, response);
		
			}else if(acao.equalsIgnoreCase("deleteFone")) {
				String foneId = request.getParameter("foneId");
				
				daoTelefones.delete(foneId);
				
				
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("usua");
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				//aqui ele lista os trelefones do usuario
				request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
				request.setAttribute("msg", "Deletado com sucesso");
				
				view.forward(request, response);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
		BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("usua");	
		
		String numero = request.getParameter("numero");
		
		
		String tipo = request.getParameter("tipo");		
		System.out.println(tipo);
		
		Telefones telefones = new Telefones();
		
		telefones.setNumero(numero);
		
		telefones.setTipo(tipo);
		
		telefones.setUsuario(beanCursoJsp.getId());
		
		daoTelefones.salvar(telefones);
		
		
		 
		request.getSession().setAttribute("usua", beanCursoJsp);			
		request.setAttribute("usua", beanCursoJsp);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
		request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
		request.setAttribute("msg", "Salvo com sucesso");
		
		view.forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
