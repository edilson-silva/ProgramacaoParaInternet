<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Livros</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/principal.css"/>
        <link rel="stylesheet" type="text/css" href="css/listarLivros.css"/>
    </head>
    <body>
        <div id="content">
            <nav id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="listarLivros.jsp">Listar Livros</a></li>
                    <li><a href="listarLivrosFiltrados.jsp">Listar Livros com Filtro</a></li>
                </ul>
            </nav>
            <div id="listaLivrosHeader">
                <p id="listaLivrosHeaderText">Lista de Livros</p>
            </div>
            <div id="mLista">
                <table id="mTable">
                    <thead>
                        <tr>
                            <th>título:</th>
                            <th>autores:</th>
                            <th>genero:</th>
                            <th>editora:</th>
                            <th>ano:</th>
                            <th>operações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <f:forEach var="livro" items="${livros}">
                            <tr>
                                <td>${livro.getTitulo()}</td>
                                <td>${livro.getAutores()}</td>
                                <td>${livro.getGenero()}</td>
                                <td>${livro.getEditora()}</td>
                                <td>${livro.getAno()}</td>
                                <td>
                                    <form action="livro" method="post">
										<input name="id" type="hidden" value="${livro.getId()}"/>
                                        <button name="operacao" value="select">&#9998;</button>
                                        <button name="operacao" value="delete">&#10006;</button>
                                    </form>
                                </td>
                            </tr>
                        </f:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <script type="text/javascript" src="js/listarLivros.js"></script>
    </body>
</html>