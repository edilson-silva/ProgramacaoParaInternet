package br.com.flf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.flf.conexao.Conexao;
import br.com.flf.model.Livro;

public class LivroDAO {

	private static Connection conn;
	private static int operacao = 0;

	public static int add(Livro livro) {
		conn = Conexao.getConnection();
		String sql = "INSERT INTO livro(titulo, genero, autores, editora, ano) values(?,?,?,?,?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getGenero());
			stmt.setString(3, livro.getAutores());
			stmt.setString(4, livro.getEditora());
			stmt.setInt(5, livro.getAno());

			operacao = stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao add livro. ##########");
			ex.printStackTrace();
		}

		return operacao;
	}

	public static Livro selectLivro(int id) {
		conn = Conexao.getConnection();
		String sql = "SELECT titulo, autores, genero, editora, ano FROM livro WHERE id = ?";

		Livro livro = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String autores = rs.getString("autores");
				String genero = rs.getString("genero");
				String editora = rs.getString("editora");
				int ano = rs.getInt("ano");

				livro = new Livro(id, titulo, autores, genero, editora, ano);
			}

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao obter livro. ##########");
			ex.printStackTrace();
		}

		return livro;
	}

	public static List<Livro> getLivros() {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM livro";

		List<Livro> livros = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			Livro livro;

			while (rs.next()) {
				long id = rs.getLong("id");
				String titulo = rs.getString("titulo");
				String autores = rs.getString("autores");
				String genero = rs.getString("genero");
				String editora = rs.getString("editora");
				int ano = rs.getInt("ano");

				livro = new Livro(id, titulo, autores, genero, editora, ano);

				livros.add(livro);
			}

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao obter lista de livros. ##########");
			ex.printStackTrace();
		}

		return livros;
	}

	public static List<Livro> getLivrosFiltrados(String filtro, String valor) {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM livro WHERE LOWER(" + filtro + ") = LOWER(?)";

		List<Livro> livros = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, valor);
			ResultSet rs = stmt.executeQuery();

			Livro livro;

			while (rs.next()) {
				long id = rs.getLong("id");
				String titulo = rs.getString("titulo");
				String autores = rs.getString("autores");
				String genero = rs.getString("genero");
				String editora = rs.getString("editora");
				int ano = rs.getInt("ano");

				livro = new Livro(id, titulo, autores, genero, editora, ano);

				livros.add(livro);
			}

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao obter lista de livros. ##########");
			ex.printStackTrace();
		}

		return livros;
	}

	public static int updateLivro(Livro livro) {
		conn = Conexao.getConnection();
		String sql = "UPDATE livro SET titulo = ?, genero = ?, autores = ?, editora = ?, ano = ? WHERE id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getGenero());
			stmt.setString(3, livro.getAutores());
			stmt.setString(4, livro.getEditora());
			stmt.setInt(5, livro.getAno());
			stmt.setLong(6, livro.getId());

			operacao = stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao atualizar livro. ##########");
			ex.printStackTrace();
		}

		return operacao;
	}

	public static int deleteLivro(int id) {
		conn = Conexao.getConnection();
		String sql = "DELETE FROM livro WHERE id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, id);

			operacao = stmt.executeUpdate();
			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("########## Erro ao excluir livro. ##########");
			ex.printStackTrace();
		}

		return operacao;
	}

}
