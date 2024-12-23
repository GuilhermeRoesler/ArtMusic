package model;

public class Evento {
		int id_evento;
		String nome;
		String data;
		String local;
		int id_artista;
		double preco;
		int capacidade;
		String descricao;
		String tipo;
		String status;
		
		public Evento(int id_evento, String nome, String data, String local, int id_artista, double preco,
				int capacidade, String descricao, String tipo, String status) {
			super();
			this.id_evento = id_evento;
			this.nome = nome;
			this.data = data;
			this.local = local;
			this.id_artista = id_artista;
			this.preco = preco;
			this.capacidade = capacidade;
			this.descricao = descricao;
			this.tipo = tipo;
			this.status = status;
		}

		public int getId_evento() {
			return id_evento;
		}

		public void setId_evento(int id_evento) {
			this.id_evento = id_evento;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public int getId_artista() {
			return id_artista;
		}

		public void setId_artista(int id_artista) {
			this.id_artista = id_artista;
		}

		public double getPreco() {
			return preco;
		}

		public void setPreco(double preco) {
			this.preco = preco;
		}

		public int getCapacidade() {
			return capacidade;
		}

		public void setCapacidade(int capacidade) {
			this.capacidade = capacidade;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
}
