<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
// for (int i = 0; i < lista.size(); i++) {
// 	out.println(lista.get(i).getId());
// 	out.println(lista.get(i).getNome());
// 	out.println(lista.get(i).getFone());
// 	out.println(lista.get(i).getEmail());
// } testando valores vindo do javabeans
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="icon" href="imagens/phone-call.png">
<link rel="stylesheet" href="style.css">
<title>Agenda de contatos</title>
</head>
<body>
	<header>
		<div class="box-title">
			<div class="box-img">
				<a href="main"> <img class="img-agenda logo"
					src="imagens/notebook.png">
				</a>

			</div>
			<div class="icon">
				<a href="novo.html"> <img alt="novo contato"
					src="./imagens/user.svg">
				</a> <a href="report"> <img alt="novo contato"
					src="./imagens/pdf.svg">
				</a>
			</div>
		</div>

	</header>
	<div class="box-container">
		<p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>

		<div class="icon"></div>

		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>Email</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td>
						<div class="box-icon">
							<div class="icon">
								<a href="select?id=<%=lista.get(i).getId()%>" class="icon"><img
									alt="novo contato" src="./imagens/editar.png"> </a>
							</div>
							<div class="icon">
								<a href="javascript: confirmar(<%=lista.get(i).getId()%>)"
									class="icon"><img alt="novo contato"
									src="./imagens/excluir.png"></a>
							</div>
						</div>
					</td>

				</tr>
				<%
				}
				%>
			</tbody>
		</table>

	</div>
	<script src="scripts/confirmador.js"></script>
</body>
</html>