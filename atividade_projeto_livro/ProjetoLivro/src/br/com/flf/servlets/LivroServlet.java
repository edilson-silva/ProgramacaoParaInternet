package br.com.flf.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.flf.dao.LivroDAO;
import br.com.flf.model.Livro;

/**
 * Servlet implementation class LivroServlet
 */
@WebServlet("/livro")
public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// REFERENTES AS MENSAGENS EXIBIDAS NA INSERÇÃO, ALTERAÇÃO E EXCLUSÃO.
	private short messageCode = 0;
	private String messageBody = "";

	// REFERENTE A INSERÇÃO E ATUALIZAÇÃO DE UM FILME
	private Livro livro;

	/*
	 * OBTER LISTA DE LIVROS
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String operacao = request.getParameter("operacao");

		List<Livro> livros = null;

		/* SELECIONA TODOS OS FILMES */
		if (operacao == null || operacao.equals("")) {
			livros = LivroDAO.getLivros();
		} else if (operacao.equalsIgnoreCase("filtrar")) { /* SELECIONA TODOS OS FILMES A PARTIR DE UM FILTRO */
			String filtro = request.getParameter("filtro").toLowerCase();
			String valor = request.getParameter("valor").toLowerCase();

			livros = LivroDAO.getLivrosFiltrados(filtro, valor);
		}

		request.setAttribute("livros", livros);
		request.getRequestDispatcher("listarLivros.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		/*
		 * VARIÁVEL OPERACAO PARA INDICAR A AÇÃO UTILIZADA 1 - POST(INSERÇÃO - INSERT) 2
		 * - PUT(ATUALIZAÇÃO - UPDATE) 3 - DELETE (EXCLUSÃO - DELETE)
		 */
		String operacao = request.getParameter("operacao");

		// OPERAÇÕES A SEREM REALIZADAS
		switch (operacao) {
			case "post": // POST - CRIAÇÃO
				// PARAMETROS
				String titulo = request.getParameter("titulo");
				String autores = request.getParameter("autores");
				String genero = request.getParameter("genero");
				String editora = request.getParameter("editora");
				int ano = Integer.parseInt(request.getParameter("ano"));
				
				livro = new Livro(titulo, autores, genero, editora, ano);
				int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
				
				
				if (titulo.trim().equals("") || autores.trim().equals("") || genero.trim().equals("") || editora.trim().equals("") || ano < 1500 || ano > anoAtual ) {
					messageCode = -1;
					messageBody = "Erro ao adicionar o Livro, Dados Inválidos!";
				} else {
					LivroDAO.add(livro);
	
					messageCode = 0;
					messageBody = "Livro \"" + titulo + "\" Adicionado com Sucesso!";
	
				}
	
				request.setAttribute("messageCode", messageCode);
				request.setAttribute("messageBody", messageBody);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
	
			case "delete": // DELETE
				doDelete(request, response);
				break;
	
			case "select": // SELECIONAR UM FILME
				selectLivro(request, response);
				break;
	
			case "put": // PUT
				doPut(request, response);
				break;
				
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String titulo = request.getParameter("titulo");
		String autores = request.getParameter("autores");
		String genero = request.getParameter("genero");
		String editora = request.getParameter("editora");
		int ano = Integer.parseInt(request.getParameter("ano"));		
		
		livro = new Livro(livro.getId(), titulo, autores, genero, editora, ano);

		if (LivroDAO.updateLivro(livro) > 0) {
			request.setAttribute("operacao", "");
			doGet(request, response);
		} else {
			request.setAttribute("id", livro.getId());
			request.setAttribute("operacao", "");
			doPost(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));

		LivroDAO.deleteLivro(id);

		request.setAttribute("livros", LivroDAO.getLivros());
		request.getRequestDispatcher("listarLivros.jsp").forward(request, response);

	}

	// SELECIONAR UM ÚNICO LIVRO PARA ALTERAÇÃO E O ENVIA PARA A TELA DE EDIÇÃO.
	protected void selectLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        
        livro = LivroDAO.selectLivro(id);

		if (livro != null) {
			request.setAttribute("livro", livro);
			request.getRequestDispatcher("editarLivro.jsp").forward(request, response);
		}

	}

}
