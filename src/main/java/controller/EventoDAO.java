package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Evento;

public class EventoDAO extends SQLController {
	public EventoDAO() {
		super();
	}

	public boolean create() {
		String query = "";

		try {
			PreparedStatement st = db.prepareStatement(query);
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Evento> read() {
		ArrayList<Evento> eventos = new ArrayList<>();

		String query = "SELECT * FROM Evento";

		try {
			PreparedStatement st = db.prepareStatement(query);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id_evento = res.getInt("id_evento");
				String nome = res.getString("nome");
				String data = res.getString("data");
				String local = res.getString("local");
				int id_artista = res.getInt("id_artista");
				double preco = res.getDouble("preco_ingresso");
				int capacidade = res.getInt("capacidade");
				String descricao = res.getString("descricao");
				String tipo = res.getString("tipo_evento");
				String status = res.getString("status");

				Evento e = new Evento(id_evento, nome, data, local, id_artista, preco, capacidade, descricao, tipo,
						status);
				eventos.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return eventos;
	}

	public boolean update() {
		String query = "";

		try {
			PreparedStatement st = db.prepareStatement(query);
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete() {
		String query = "";

		try {
			PreparedStatement st = db.prepareStatement(query);
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Evento getByName(String name) {
		String query = "SELECT * FROM Evento WHERE nome = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, name);
			ResultSet res = st.executeQuery();
			return criarEvento(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Evento criarEvento(ResultSet res) {
		try {
			if (res.next()) {
				int id_evento = res.getInt("id_evento");
				String nome = res.getString("nome");
				String data = res.getString("data");
				String local = res.getString("local");
				int id_artista = res.getInt("id_artista");
				double preco = res.getDouble("preco_ingresso");
				int capacidade = res.getInt("capacidade");
				String descricao = res.getString("descricao");
				String tipo = res.getString("tipo_evento");
				String status = res.getString("status");

				Evento evento = new Evento(id_evento, nome, data, local, id_artista, preco, capacidade, descricao, tipo,
						status);
				return evento;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Evento> getShowsOwned(Cliente c) {
		ArrayList<Evento> shows = new ArrayList<>();
		String query = "SELECT * FROM Participacao WHERE id_cliente = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, c.getId_pessoa());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				shows.add(getById(res.getInt("id_evento")));
			}
			return shows;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Evento getById(int id) {
		String query = "SELECT * FROM Evento WHERE id_evento = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			return criarEvento(res);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> obterShowsComprados(int id_cliente) {
		ArrayList<String> showsComprados = new ArrayList<String>();
		String query = "SELECT e.nome FROM Evento e " + "JOIN Participacao p ON e.id_evento = p.id_evento "
				+ "WHERE p.id_cliente = ?";
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, id_cliente);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				showsComprados.add(res.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showsComprados;
	}

}