package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente extends Pessoa {
	protected String sobrenome;
	protected String email;
	protected String senha;
	protected String sexo;
	protected String cpf;
	protected String telefone;
	protected String cep;
	protected String cidade;
	protected int dinheiro;
	protected String dataEntrada;

	// construtor mysql
	public Cliente(int id_pessoa, String nome, String sobrenome, String email, String senha,
			String sexo, String cpf, String telefone, String nascimento, String cep, String cidade, int dinheiro, String dataEntrada) {
		super(id_pessoa, nome, nascimento);
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.dinheiro = dinheiro;
		this.dataEntrada = dataEntrada;
	}

	// construtor cadastro
	public Cliente(String nome, String sobrenome, String email, String senha, String sexo,
			String cpf, String telefone, String nascimento, String cep, String cidade) {
		super(nome, nascimento);
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
	}

	// construtor login
	public Cliente(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	// construtor vazio
	public Cliente() {

	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public int getIdade() {
		LocalDate dataNascimento = LocalDate.parse(nascimento, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataAtual = LocalDate.now();
		
		Period period = Period.between(dataNascimento, dataAtual);
		return period.getYears();
	}

}
