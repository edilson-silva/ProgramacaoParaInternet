package br.com.flf.servlets;

import br.com.flf.dao.FilmeDAO;
import br.com.flf.modelos.Filme;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FilmeServlet", urlPatterns = {"/filme"})
public class FilmeServlet extends HttpServlet {

    // REFERENTES AS MENSAGENS EXIBIDAS NA INSERÇÃO, ALTERAÇÃO E EXCLUSÃO.
    private short messageCode = 0;
    private String messageBody = "";

    // REFERENTE A INSERÇÃO E ATUALIZAÇÃO DE UM FILME
    private Filme filme;

    /*
        OBTER LISTA DE FILMES
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String operacao = request.getParameter("operacao");

        List<Filme> filmes = null;

        /* SELECIONA TODOS OS FILMES */
        if (operacao == null || operacao.equals("")) {
            filmes = FilmeDAO.getFilmes();
        } else if (operacao.equalsIgnoreCase("filtrar")) { /* SELECIONA TODOS OS FILMES A PARTIR DE UM FILTRO */
            String filtro = request.getParameter("filtro").toLowerCase();
            String valor = request.getParameter("valor").toLowerCase();

            filmes = FilmeDAO.getFilmesFiltrados(filtro, valor);
        }

        request.setAttribute("filmes", filmes);
        request.getRequestDispatcher("listarFilmes.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        /* 
            VARIÁVEL OPERACAO PARA INDICAR A AÇÃO UTILIZADA
            1 - POST(INSERÇÃO - INSERT)
            2 - PUT(ATUALIZAÇÃO - UPDATE)
            3 - DELETE (EXCLUSÃO - DELETE)        
         */
        String operacao = request.getParameter("operacao");
        System.err.println("Valor de operação: " + operacao);

        // OPERAÇÕES A SEREM REALIZADAS
        switch (operacao) {            
            case "post": // POST - CRIAÇÃO                
                // PARAMETROS 
                String titulo = request.getParameter("titulo");
                String genero = request.getParameter("genero");
                String data = request.getParameter("data_lancamento");
                String duracao = request.getParameter("duracao");
                Double notaIMDB = Double.parseDouble(request.getParameter("nota_imdb"));

                if (titulo.trim().equals("") || notaIMDB < 0 || notaIMDB > 10) {
                    messageCode = -1;
                    messageBody = "Erro ao adicionar o Filme, Dados Inválidos!";
                } else {

                    Date dataLancamento = null;

                    try {
                        DateFormat dataFormatar = new SimpleDateFormat("dd/MM/yyyy");
                        dataLancamento = new Date(dataFormatar.parse(data).getTime());
                    } catch (ParseException ex) {
                        System.err.println("########## Erro ao formatar a Data. ##########");
                    }

                    filme = new Filme(titulo, genero, dataLancamento, duracao, notaIMDB);
                    FilmeDAO.addFilme(filme);

                    messageCode = 0;
                    messageBody = "Filme \"" + titulo + "\" Adicionado com Sucesso!";

                }

                request.setAttribute("messageCode", messageCode);
                request.setAttribute("messageBody", messageBody);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
                
            case "delete": // DELETE
                doDelete(request, response);
                break;
                
            case "select": // SELECIONAR UM FILME
                selectFilme(request, response);
                break;
                
            case "put": // PUT
                doPut(request, response);
                break;
                
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        FilmeDAO.deleteFile(id);

        request.setAttribute("filmes", FilmeDAO.getFilmes());
        request.getRequestDispatcher("listarFilmes.jsp").forward(request, response);

    }

    // SELECIONAR UM ÚNICO FILME PARA ALTERAÇÃO E O ENVIA PARA A TELA DE EDIÇÃO.
    protected void selectFilme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        filme = FilmeDAO.selectFilme(id);
        
        if (filme != null) {
            request.setAttribute("filme", filme);
            request.getRequestDispatcher("editarFilme.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String genero = request.getParameter("genero");
        String data = request.getParameter("data_lancamento");
        String duracao = request.getParameter("duracao");
        Double notaIMDB = Double.parseDouble(request.getParameter("nota_imdb"));

        Date dataLancamento = null;

        try {
            DateFormat dataFormatar = new SimpleDateFormat("dd/MM/yyyy");
            dataLancamento = new Date(dataFormatar.parse(data).getTime());
        } catch (ParseException ex) {
            System.err.println("########## Erro ao formatar a Data. ##########");
        }

        filme = new Filme(filme.getId(), titulo, genero, dataLancamento, duracao, notaIMDB);

        if (FilmeDAO.updateFilme(filme) > 0) {
            request.setAttribute("operacao", "");
            doGet(request, response);
        } else {
            request.setAttribute("id", filme.getId());
            request.setAttribute("operacao", "");
            doPost(request, response);
        }

    }

}
