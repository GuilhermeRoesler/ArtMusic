package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import model.Evento;

public class ParticipacaoDAO extends SQLController {
	public ParticipacaoDAO() {
		super();
	}
	
	public boolean create(Cliente c, Evento e) {
		String query = "INSERT INTO Participacao VALUES(null, ?, ?)";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, c.getId_pessoa());
			st.setInt(2, e.getId_evento());

			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public void read() {
		String query = "SELECT * FROM Participacao";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				System.out.println(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean update() {
		return true;
	}
	
	public boolean delete(Cliente c, Evento e) {
		String query = "DELETE FROM Participacao WHERE id_cliente = ? AND id_evento = ?";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, c.getId_pessoa());
			st.setInt(2, e.getId_evento());
			
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

}
