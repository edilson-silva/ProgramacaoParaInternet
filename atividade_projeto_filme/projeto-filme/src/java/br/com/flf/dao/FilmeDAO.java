package br.com.flf.dao;

import br.com.flf.conexao.Conexao;
import br.com.flf.modelos.Filme;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    private static Connection conn;
    private static int operacao = 0;

    public static int addFilme(Filme filme) {
        conn = Conexao.getConnection();
        String sql = "INSERT INTO filme(titulo, genero, data_lancamento, duracao, nota_imdb) VALUES(?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setDate(3, filme.getDataLancamento());
            stmt.setString(4, filme.getDuracao());
            stmt.setDouble(5, filme.getNotaIMDB());

            operacao = stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao cadastrar filme. ##########");
            ex.printStackTrace();
        }

        return operacao;
    }

    public static Filme selectFilme(int id) {
        conn = Conexao.getConnection();
        String sql = "SELECT titulo, genero, data_lancamento, duracao, nota_imdb FROM filme WHERE id = ?";

        Filme filme = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                Date dataLancamento = rs.getDate("data_lancamento");
                String duracao = rs.getString("duracao");
                double notaIMDB = rs.getDouble("nota_imdb");

                filme = new Filme(id, titulo, genero, dataLancamento, duracao, notaIMDB);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao obter lista de filmes. ##########");
            ex.printStackTrace();
        }

        return filme;
    }

    public static List<Filme> getFilmes() {
        conn = Conexao.getConnection();
        String sql = "SELECT * FROM filme";
        List<Filme> filmes = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            Filme filme;

            while (rs.next()) {
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                Date dataLancamento = rs.getDate("data_lancamento");
                String duracao = rs.getString("duracao");
                double notaIMDB = rs.getDouble("nota_imdb");

                filme = new Filme(id, titulo, genero, dataLancamento, duracao, notaIMDB);
                filmes.add(filme);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao obter lista de filmes. ##########");
            ex.printStackTrace();
        }

        return filmes;
    }

    public static List<Filme> getFilmesFiltrados(String filtro, String valor) {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM filme WHERE LOWER(" + filtro + ") = LOWER(?)";
        List<Filme> filmes = new ArrayList<>();

        System.err.print("FILTRO: " + filtro + ", VALOR: " + valor);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);

            ResultSet rs = stmt.executeQuery();

            Filme filme;

            while (rs.next()) {
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                Date dataLancamento = rs.getDate("data_lancamento");
                String duracao = rs.getString("duracao");
                double notaIMDB = rs.getDouble("nota_imdb");

                filme = new Filme(id, titulo, genero, dataLancamento, duracao, notaIMDB);
                filmes.add(filme);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao obter lista de filmes. ##########");
            ex.printStackTrace();
        }

        return filmes;
    }

    public static int updateFilme(Filme filme) {
        conn = Conexao.getConnection();
        String sql = "UPDATE filme SET titulo = ?, genero = ?, data_lancamento = ?, duracao = ?, nota_imdb = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setDate(3, filme.getDataLancamento());
            stmt.setString(4, filme.getDuracao());
            stmt.setDouble(5, filme.getNotaIMDB());
            stmt.setLong(6, filme.getId());

            operacao = stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao cadastrar filme. ##########");
            ex.printStackTrace();
        }

        return operacao;
    }

    public static int deleteFile(int id) {
        conn = Conexao.getConnection();
        String sql = "DELETE FROM filme WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            operacao = stmt.executeUpdate();
            conn.close();

        } catch (SQLException ex) {
            System.err.println("########## Erro ao excluir filme. ##########");
            ex.printStackTrace();
        }

        return operacao;
    }

}
