<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Filmes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/principal.css"/>
        <link rel="stylesheet" type="text/css" href="css/listarFilmes.css"/>
    </head>
    <body>
        <div id="content">
            <nav id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="listarFilmes.jsp">Listar Filmes</a></li>
                    <li><a href="listarFilmesFiltrados.jsp">Listar Filmes com Filtro</a></li>
                </ul>
            </nav>
            <div id="listaFilmesHeader">
                <p id="listaFilmesHeaderText">Lista de Filmes</p>
            </div>
            <div id="mLista">
                <table id="mTable">
                    <thead>
                        <tr>
                            <th>titulo</th>
                            <th>gênero</th>
                            <th>data de lancamento</th>
                            <th>duração</th>
                            <th>nota imdb</th>
                            <th>Operações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <f:forEach var="filme" items="${filmes}">
                            <tr>
                                <td>${filme.getTitulo()}</td>
                                <td>${filme.getGenero()}</td>
                                <td><fmt:formatDate value="${filme.getDataLancamento()}" pattern="dd/MM/yyyy"/></td>
                                <td>${filme.getDuracao()}</td>
                                <td>${filme.getNotaIMDB()}</td>
                                <td>
                                    <form action="filme" method="post">
                                        <input name="id" type="hidden" value="${filme.getId()}"/>
                                        <button name="operacao" value="select"/>&#9998;</button>
                                        <button name="operacao" value="delete">&#10006;</button>
                                    </form>
                                </td>
                            </tr>
                        </f:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <script type="text/javascript" src="js/listarFilmes.js"></script>
    </body>
</html>
