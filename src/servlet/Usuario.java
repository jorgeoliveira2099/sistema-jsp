package servlet;

import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
System.out.println(user);
			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				// quando deletar, recarregar para a mesma página
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
			//parte do metodo para gerar relatorio
			//else if(acao.equalsIgnoreCase("exportar")) {
				//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
				//String nomeFile = dateFormat.format(new Date());				
			//}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			try {
				
				//se o validar retornar false, o usuario ja existe
				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Usuário já existe com o mesmo login");
					request.setAttribute("user", usuario);
					
				}else if(id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Usuário já existe com a mesma senha");
					//isso mantém os dados em tela quando o login for invalido
					request.setAttribute("user", usuario);
				}
				
				//se o validar retornar true, o uisuario não existe, e pode ser cadastrado
				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
					
					daoUsuario.salvar(usuario);
					request.setAttribute("msg", "Usuário Cadastrado!");
					
				} else if (id != null && !id.isEmpty()) {
					
					//se retornar false, mostra a mensagem de erro
					if(!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Login já existe para outro usuário");
						request.setAttribute("user", usuario);
					}else {
					daoUsuario.atualizar(usuario);
					request.setAttribute("msg", "Usuário Atualizado!");
					}
				}

				// esse bloco do try, é responsavel apenas por fazer o redirecionamento, da
				// lista de todsos os usuarios

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
