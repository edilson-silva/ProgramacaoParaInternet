package br.com.flf.model;

public class Livro {

	private long id;
	private String titulo;
	private String autores;
	private String genero;
	private String editora;
	private int ano;
	
	public Livro() {
		// TODO Auto-generated constructor stub
	}
	
	public Livro(String titulo, String autores, String genero, String editora, int ano) {
		super();
		this.titulo = titulo;
		this.autores = autores;
		this.genero = genero;
		this.editora = editora;
		this.ano = ano;
	}

	public Livro(long id, String titulo, String autores, String genero, String editora, int ano) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autores = autores;
		this.genero = genero;
		this.editora = editora;
		this.ano = ano;
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

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
