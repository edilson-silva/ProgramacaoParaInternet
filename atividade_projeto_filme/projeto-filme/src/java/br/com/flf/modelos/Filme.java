package br.com.flf.modelos;

import java.sql.Date;

public class Filme {

    private long id;
    private String titulo;
    private String genero;
    private Date dataLancamento;
    private String duracao;
    private double notaIMDB;

    public Filme() {
    }

    public Filme(String titulo, String genero, Date dataLancamento, String duracao, double notaIMDB) {
        this.titulo = titulo;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.notaIMDB = notaIMDB;
    }

    public Filme(long id, String titulo, String genero, Date dataLancamento, String duracao, double notaIMDB) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.notaIMDB = notaIMDB;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public double getNotaIMDB() {
        return notaIMDB;
    }

    public void setNotaIMDB(double notaIMDB) {
        this.notaIMDB = notaIMDB;
    }

}
