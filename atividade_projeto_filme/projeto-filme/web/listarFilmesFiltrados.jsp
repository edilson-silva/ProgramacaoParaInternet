<%-- 
    Document   : listarFilmesFiltrados
    Created on : 10/11/2017, 13:00:03
    Author     : edilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Listar Filmes Filtrados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/principal.css"/>
        <link rel="stylesheet" type="text/css" href="css/formularios.css"/>
        <link rel="stylesheet" type="text/css" href="css/listarFilmesFiltrados.css"/>
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
            <div id="mForm">
                <form action="filme" method="get">
                    
                    <span>Filtro de Pesquisa:</span><br/>
                    <select id="filtro" name="filtro">
                        <option value="titulo">Titulo</option>
                        <option value="genero">Genero</option>
                    </select><br/>
                    
                    <span>Dado de Pesquisa:</span><br/>
                    <input type="text" id="valor" name="valor" required/><br/>
                    
                    <button name="operacao" value="filtrar">Filtrar</button>
                </form>
            </div>
        </div>      
    </body>
</html>
