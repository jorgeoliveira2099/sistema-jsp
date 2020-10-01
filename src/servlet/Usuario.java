package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;
import org.omg.CORBA.portable.InputStream;


import beans.BeanCursoJsp;
import dao.DaoUsuario;

import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

@MultipartConfig
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

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
			}else if (acao.equalsIgnoreCase("download")) {
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				if(usuario != null) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuario.getContentType().split("\\/")[1]);
					
					/*converte a base 64 do banco para byte[] */
					byte[] imageFotoBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					
					//coloca os bytes em um objetp de entrada para processar
					ByteArrayInputStream is = new ByteArrayInputStream(imageFotoBytes);
				/* inicio da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[1024];
					try {
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			
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

	//converter a entrada de fluxo de dados da imagem para array de dados
	private byte[] converteStremParaByte(java.io.InputStream inputStream) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = inputStream.read();
		
		while(reads != -1) {
			baos.write(reads);
			reads = inputStream.read();
		}
		
		return baos.toByteArray();
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
			
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			
			try {
				/* INICIO file upload de imagens e pdf*/
				
				if(ServletFileUpload.isMultipartContent(request)) {
					Part imagemFoto = request.getPart("foto");
					
				String fotoBase64 = new Base64().encodeBase64String(converteStremParaByte(imagemFoto.getInputStream()));
					
				usuario.setFotoBase64(fotoBase64);
				usuario.setContentType(imagemFoto.getContentType());
					
					
				}
				//fim do file upload
			
			
				if(nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "Nome é obrigatório");
					request.setAttribute("user", usuario);
					
					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					try {
						request.setAttribute("usuarios", daoUsuario.listar());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					view.forward(request, response);
			}
			else if(login == null || login.isEmpty()) {
				request.setAttribute("msg", "Login é obrigatório");
				request.setAttribute("user", usuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				try {
					request.setAttribute("usuarios", daoUsuario.listar());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.forward(request, response);
			}else if(senha == null || senha.isEmpty()) {
				request.setAttribute("msg", "Senha é obrigatório");
				request.setAttribute("user", usuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				try {
					request.setAttribute("usuarios", daoUsuario.listar());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.forward(request, response);
				
			}else if(telefone == null || telefone.isEmpty()){
				request.setAttribute("msg", "Telefone é obrigatório");
				request.setAttribute("user", usuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				try {
					request.setAttribute("usuarios", daoUsuario.listar());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.forward(request, response);
				
			}else {					
				
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

			
		}
			//isso é daquele try da linha 105 mais ou menos	
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		//fechamento do else da linha 101
		}
		
		
		
	}
	


}
