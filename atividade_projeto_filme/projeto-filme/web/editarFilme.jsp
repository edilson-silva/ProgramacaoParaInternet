<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate var="dataLancamento" value="${filme.getDataLancamento()}" pattern="dd/MM/yyyy"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Livro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/principal.css"/>
        <link rel="stylesheet" type="text/css" href="css/formularios.css"/>
    </head>
    <body>
        <div id="messageBox">
            <p><span id="message_code" hidden>${messageCode}</span><span id="message_text">${messageBody}</span></p>
        </div>
        <div id="content">
            <nav id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="listarFilmes.jsp">Listar Filmes</a></li>
                </ul>
            </nav>
            <div id="mForm">
                <form action="filme" method="post">
                    <span>título:</span><br/>
                    <input type="text" id="titulo" name="titulo" value="${filme.getTitulo()}" required/><br/>
                    <span>gênero:</span><br/>
                    <input type="text" id="genero" name="genero" value="${filme.getGenero()}" required/><br/>
                    <span>data de lançamento:</span><br/>
                    <input type="text" id="data_lancamento" name="data_lancamento" placeholder="00/00/00" maxlength="10" value="${dataLancamento}" required/><br/>
                    <span>duração:</span><br/>
                    <input type="text" id="duracao" name="duracao" placeholder="00:00:00" maxlength="8" value="${filme.getDuracao()}" required/><br/>
                    <span>nota imdb:</span><br/>
                    <input type="number" id="nota_imdb" name="nota_imdb" placeholder="0" min="0" max="10" value="${filme.getNotaIMDB()}" required/><br/>

                    <button name="operacao" value="put"/>Salvar</button>
                </form>
            </div>
        </div>
        
        <script type="text/javascript" src="js/principal.js"></script>
        <script type="text/javascript" src="js/formularios.js"></script>
    </body>
</html>
