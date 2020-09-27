package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.Produto;
import dao.DaoProduto;


@WebServlet("/salvarProduto")
public class salvarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       DaoProduto daoProduto = new DaoProduto();
  
    public salvarProduto() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String acao = request.getParameter("acao");
			String produto1 = request.getParameter("produto");
			System.out.println(produto1);
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto1);
				// quando deletar, recarregar para a mesma página
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("msg", "Produto Deletado!");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				Produto produto2 = daoProduto.consultar(produto1);
				//daoProduto.atualizar(produto2);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				
				request.setAttribute("produto", produto2);
				
				view.forward(request, response);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());
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

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			
		

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String valor = request.getParameter("valor");
			String quantidade = request.getParameter("quantidade");
			
			
			Produto produto = new Produto();
			
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setValor(valor);
			produto.setQuantidade(quantidade);		
			
			if(nome == null || nome.isEmpty())  {
				request.setAttribute("msg", "O nome do produto é obrigatório!");
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produto);
				view.forward(request, response);
			}	
			else if(valor == null || valor.isEmpty())  {
				request.setAttribute("msg", "O valor do produto é obrigatório!");
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produto);
				view.forward(request, response);
			}			
			else if(quantidade == null || quantidade.isEmpty()) {
				request.setAttribute("msg", "A quantidade do produto é obrigatório!");
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produto);
				view.forward(request, response);
				
			}else {
			//se o validar retornar true, o uisuario não existe, e pode ser cadastrado
			try {
				if (id == null || id.isEmpty() && !daoProduto.validarNomeProduto(nome)) {
					
					//
					request.setAttribute("msg", "Um produto com este nome já está cadastrado!");
					
				} 
				
				if (id == null || id.isEmpty() && daoProduto.validarNomeProduto(nome)) {
					daoProduto.salvar(produto);
					request.setAttribute("msg", "Produto Cadastrado!");
					request.setAttribute("produtos", daoProduto.listar());
				}else if (id != null && !id.isEmpty()) {
					
				if(!daoProduto.validarNomeProdutoUpdate(nome, id)) {
						
					request.setAttribute("msg", "Um produto com este nome já está cadastrado!");
					request.setAttribute("produto", produto);
				}else {
					daoProduto.atualizar(produto);
					request.setAttribute("msg", "produto Atualizado!");
					request.setAttribute("produtos", daoProduto.listar());
					}
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produto);
				view.forward(request, response);

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
				//pode excluuir
			//daoProduto.salvar(produto);

		
			
			
		
			//em baixo, fim do else
		}
		//em cima, fim do else
		
	}
		
	}

}
