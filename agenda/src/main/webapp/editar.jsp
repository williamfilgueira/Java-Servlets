<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/phone-call.png">
<link rel="stylesheet" href="style.css">
</head>
<title>Editar contato</title>
</head>
<body>
<header>
		<div class="box-title">
			<div class="box-img">
				<a href="main"> <img class="img-agenda logo"
					src="imagens/notebook.png">
				</a>
				<h1>Agenda de contatos</h1>
			</div>
			<div class="icon">
				<a href="main"> <img alt="novo contato"
					src="./imagens/back.svg">
				</a> 
			</div>
		</div>

	</header>
	<div class="container">
		<div class="box-container-contact">
			<form name="frmContato" action="update">
				<h1>Editar Contato</h1>
				<table>
					<tr>
						<td><input id="caixa3" class="cx1" type="text" name="id"
							placeholder="id" readonly
							value="<%out.print(request.getAttribute("id"));%>"></td>
					</tr>
					<tr>
						<td><input class="cx1" type="text" name="nome"
							placeholder="Nome" value="<%out.print(request.getAttribute("nome"));%>"></td>
					</tr>
					<tr>
						<td><input class="cx2" type="text" name="fone"
							placeholder="Telefone" value="<%out.print(request.getAttribute("fone"));%>"></td>
					</tr>
					<tr>
						<td><input class="cx3" type="text" name="email"
							placeholder="E-mail" value="<%out.print(request.getAttribute("email"));%>"></td>
					</tr>
				</table>

				<input id="botao1" class="botao1" type="button" value="Salvar"
					onClick="validar()">
			</form>
		</div>
	</div>
	<script src="scripts/validador.js"></script>
</body>
</html>