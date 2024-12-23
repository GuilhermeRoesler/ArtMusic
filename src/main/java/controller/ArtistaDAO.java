package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Artista;

public class ArtistaDAO extends SQLController {
	public ArtistaDAO() {
		super();
	}
	
	public boolean create(Artista p) {
		String query = "";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings(value = { })
	public boolean read() {
		String query = "SELECT * FROM Artista";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				System.out.println(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean update() {
		String query = "";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered", numRegistros);
			
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
			System.out.printf("%d record(s) registered", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Artista getById(int id) {
		String query = "SELECT * FROM Artista WHERE id_artista = ?";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			
			return criarArtista(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Artista criarArtista(ResultSet res) {
		try {
			if (res.next()) {
				int id_artista = res.getInt("id_artista");
				String nome = res.getString("nome");
				String genero = res.getString("genero");
				String nacionalidade = res.getString("nacionalidade");
				String data_nascimento = res.getString("data_nascimento");
				String biografia = res.getString("biografia");
				String site_oficial = res.getString("site_oficial");
				String premios = res.getString("premios");
				String redes_sociais = res.getString("redes_sociais");
				String discografia = res.getString("discografia");
				
				Artista artista = new Artista(id_artista, nome, data_nascimento, genero, nacionalidade, biografia, site_oficial, premios, redes_sociais, discografia);
				return artista;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}