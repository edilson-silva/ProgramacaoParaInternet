<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Editar Livro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/principal.css"/>
        <link rel="stylesheet" type="text/css" href="css/formularios.css"/>
    </head>
    <body>
        <div id="messageBox">
            <p><span id="message_code" hidden="true">${messageCode}</span><span id="message_text">${messageBody}</span></p>
        </div>
        <div id="content">
            <nav id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="listarLivros.jsp">Listar Livros</a></li>
                    <li><a href="listarLivrosFiltrados.jsp">Listar Livros com Filtro</a></li>
                </ul>
            </nav>
            <div id="mForm">
                <form action="livro" method="post">
                	<span>título:</span><br/>
                    <input type="text" id="titulo" name="titulo" value="${livro.getTitulo()}" required/><br/>
                    <span>autores:</span><br/>
                    <input type="text" id="autores" name="autores" value="${livro.getAutores()}" required/><br/>
                    <span>genero:</span><br/>
                    <input type="text" id="genero" name="genero" value="${livro.getGenero()}" required/><br/>
                    <span>editora:</span><br/>
                    <input type="text" id="editora" name="editora" value="${livro.getEditora()}" required/><br/>
                    <span>ano:</span><br/>
                    <input type="number" id="ano" name="ano" placeholder="2000" min="1500" value="${livro.getAno()}" /><br/>

                    <button name="operacao" value="put">Salvar</button>
                </form>
            </div>
        </div>
        
        <script type="text/javascript" src="js/principal.js"></script>
        <script type="text/javascript" src="js/formularios.js"></script>
    </body>
</html>