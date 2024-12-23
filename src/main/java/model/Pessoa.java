package model;

public class Pessoa {
	protected int id_pessoa;
	protected String nome;
	protected String nascimento;

	public Pessoa(int id_pessoa, String nome, String nascimento) {
		this.id_pessoa = id_pessoa;
		this.nome = nome;
		this.nascimento = nascimento;
	}

	public Pessoa(String nome, String nascimento) {
		this.nome = nome;
		this.nascimento = nascimento;
	}
	
	public Pessoa() {
		
	}

	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

}
