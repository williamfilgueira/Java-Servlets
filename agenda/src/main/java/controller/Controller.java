package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

/**
 * criando rota main do servlet
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

//	listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
//		teste de recebindo vindo do JavaBeans
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getId());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//			System.out.println(lista.get(i).getEmail());
//		}
//	encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

//	novo contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		teste de recebimento da informações vindas do formulario
		// System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

//		instanciando  método inserirContato passando o objeto contato
		dao.inserirContato(contato);

//		redirecionando para documento agenda.jsp
		response.sendRedirect("main");

	}

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{

//		recebimento do contato que será editado
			String id = request.getParameter("id");
//setando a variaver JavaBenas
			contato.setId(id);
//		executar o método selecionarContato (DAO)
			dao.selecionarContato(contato);
//		teste de recebimento
//		System.out.println(contato.getId());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());

//		setar os atributos do formulario com o conteudo do JavaBeans
			request.setAttribute("id", contato.getId());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("fone", contato.getFone());
			request.setAttribute("email", contato.getEmail());
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);
		}

	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		teste de recebimento
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
//		executar o metodo alterarContato
		dao.alterarContato(contato);
//		redirecionando para o doc agenda.jsp (dados já atualizados
		response.sendRedirect("main");
	}

//	REMOVER UM CONTATO
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		recebimento do id do contato a ser excluído (validador.js)
		String id = request.getParameter("id");
		System.out.println(id);
		contato.setId(id);
		dao.deletarContato(contato);

//		redirecionando para o doc agenda.jsp (dados já atualizados
		response.sendRedirect("main");
	}

//	gerar relatorio em pdg
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
//			tipo de conteudo
			response.setContentType("aplication/pdf");
//			nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
//			criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
//			abrir documento fdp
			documento.open();
			documento.add(new Paragraph("Lista de contatos"));
//			criando a tabela
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome:"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone:"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email:"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
//			popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
