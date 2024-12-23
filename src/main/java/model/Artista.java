package model;

public class Artista extends Pessoa {
	protected String genero;
	protected String nacionalidade;
	protected String biografia;
	protected String siteOficial;
	protected String premios;
	protected String redesSociais;
	protected String discografia;
	
	public Artista(int id_pessoa, String nome, String nascimento, String genero, String nacionalidade, String biografia,
			String siteOficial, String premios, String redesSociais, String discografia) {
		super(id_pessoa, nome, nascimento);
		this.genero = genero;
		this.nacionalidade = nacionalidade;
		this.biografia = biografia;
		this.siteOficial = siteOficial;
		this.premios = premios;
		this.redesSociais = redesSociais;
		this.discografia = discografia;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getSiteOficial() {
		return siteOficial;
	}

	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}

	public String getPremios() {
		return premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	public String getRedesSociais() {
		return redesSociais;
	}

	public void setRedesSociais(String redesSociais) {
		this.redesSociais = redesSociais;
	}

	public String getDiscografia() {
		return discografia;
	}

	public void setDiscografia(String discografia) {
		this.discografia = discografia;
	}
	
}
