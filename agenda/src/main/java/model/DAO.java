package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
//	modulo de conexão
//	parametros da conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?user=root?useTimezone=true&serverTimezone=UTC";

	private String user = "root";
	private String password = "Betani@123";

//	metodo de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
//	teste de conexão

	public void testeConexão() {
		try {
			Connection con = conectar();
			System.out.println("Bom dia!");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	CRUD CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome, fone, email) values (?,?,?)";
		try {
//			abrindo conexão com o banco
			Connection con = conectar();

//			preperar a query para execusão no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

//			subistituir as ??? pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

//			executando a query
			pst.executeUpdate();

//			encerrando conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	CRUD READ
	public ArrayList<JavaBeans> listarContatos() {
//		criando objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by nome";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

//			laço será executado em quanto houver contatos
			while (rs.next()) {
//				variaveis de apoio que recebem do BD
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

//			populando o arrayList	
				contatos.add(new JavaBeans(id, nome, fone, email));

			}
			con.close();
			return contatos;
		} catch (

		Exception e) {
			System.out.println(e);
			return null;
		}
	}

//	CRUD UPDATE
//	selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
//				setar as variaveis JavaBenas
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	editar contato
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?,email=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
