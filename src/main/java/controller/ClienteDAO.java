package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Cliente;
import utils.ConversorUtils;
import utils.FileManager;
import view.Main;

public class ClienteDAO extends SQLController {

	public ClienteDAO() {
		super();
	}

	public boolean create(Cliente p) {
		String query = "INSERT INTO Cliente VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?)";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, p.getNome());
			st.setString(2, p.getSobrenome());
			st.setString(3, p.getEmail());
			st.setString(4, p.getSenha());
			st.setString(5, ConversorUtils.sexoToMysql(p.getSexo()));
			st.setString(6, p.getCpf());
			st.setString(7, p.getTelefone());
			st.setString(8, nascimentoToMysql(p.getNascimento()));
			st.setString(9, p.getCep());
			st.setString(10, p.getCidade());
			st.setString(11, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);

			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nEste usuário já existe no cadastro! Tente novamento com outras credenciais!\n");
			JOptionPane.showMessageDialog(null,
					"Este usuário já existe no cadastro! Tente novamento com outras credenciais!");
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean read() {
		return true;
	}

	public boolean update(Cliente c) {
		String query = "UPDATE Cliente SET nome = ?, sobrenome = ?, email = ?, senha = ?, sexo = ?, cpf = ?, telefone = ?, data_nascimento = ?, cep = ?, cidade = ?, dinheiro = ?, data_entrada = ? WHERE id_cliente = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, c.getNome());
			st.setString(2, c.getSobrenome());
			st.setString(3, c.getEmail());
			st.setString(4, c.getSenha());
			st.setString(5, c.getSexo());
			st.setString(6, c.getCpf());
			st.setString(7, c.getTelefone());
			st.setString(8, c.getNascimento());
			st.setString(9, c.getCep());
			st.setString(10, c.getCidade());
			st.setInt(11, c.getDinheiro());
			st.setString(12, c.getDataEntrada());
			st.setInt(13, c.getId_pessoa());

			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Cliente c) {
		String query = "DELETE FROM Cliente WHERE id_cliente = ?";
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, c.getId_pessoa());
			
			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean canLogin(Cliente p) {
		String query = "SELECT * FROM Cliente WHERE email = ? AND senha = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, p.getEmail());
			st.setString(2, p.getSenha());
			ResultSet res = st.executeQuery();

			return res.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String recuperarSenha(String email) {
		String query = "SELECT * FROM Cliente WHERE email = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, email);

			ResultSet res = st.executeQuery();

			if (res.next()) {
				return res.getString("senha");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cliente getBySenha(String senha) {
		String query = "SELECT * FROM Cliente WHERE senha = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, senha);

			ResultSet res = st.executeQuery();
			return criarCliente(res);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cliente getByEmail(String email) {
		String query = "SELECT * FROM Cliente WHERE email = ?";

		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setString(1, email);

			ResultSet res = st.executeQuery();
			return criarCliente(res);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Cliente criarCliente(ResultSet res) {
		try {
			if (res.next()) {
				int id_pessoa = res.getInt("id_cliente");
				String nome = res.getString("nome");
				String sobrenome = res.getString("sobrenome");
				String email = res.getString("email");
				String senha = res.getString("senha");
				String sexo = res.getString("sexo");
				String cpf = res.getString("cpf");
				String telefone = res.getString("telefone");
				String data_nascimento = res.getString("data_nascimento");
				String cep = res.getString("cep");
				String cidade = res.getString("cidade");
				int dinheiro = res.getInt("dinheiro");
				String dataEntrada = res.getString("data_entrada");

				Cliente cliente = new Cliente(id_pessoa, nome, sobrenome, email, senha, sexo, cpf, telefone,
						data_nascimento, cep, cidade, dinheiro, dataEntrada);
				return cliente;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cliente criarCliente(ArrayList<String> credentials) {
		int id_pessoa = Integer.parseInt(credentials.get(0));
		String nome = credentials.get(1);
		String sobrenome = credentials.get(2);
		String email = credentials.get(3);
		String senha = credentials.get(4);
		String sexo = credentials.get(5);
		String cpf = credentials.get(6);
		String telefone = credentials.get(7);
		String data_nascimento = credentials.get(8);
		String cep = credentials.get(9);
		String cidade = credentials.get(10);
		int dinheiro = Integer.parseInt(credentials.get(11));
		String dataEntrada = credentials.get(12);

		Cliente cliente = new Cliente(id_pessoa, nome, sobrenome, email, senha, sexo, cpf, telefone, data_nascimento,
				cep, cidade, dinheiro, dataEntrada);
		return cliente;
	}

	private String nascimentoToMysql(String nascimento) {
		return LocalDate.parse(nascimento, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
	}
	
	public boolean updateDinheiro(int novoValor, int idCliente) {
		String query = "UPDATE Cliente SET dinheiro = ? WHERE id_cliente = ?";

		Main.mainClient.setDinheiro(novoValor);
		FileManager.storeCredentials(Main.mainClient, FileManager.isRememberMeOn());
		Main.frameUsuario.updateDinheiro(novoValor);
		
		try {
			PreparedStatement st = db.prepareStatement(query);
			st.setInt(1, novoValor);
			st.setInt(2, idCliente);

			int numRegistros = st.executeUpdate();
			System.out.printf("%d record(s) registered\n", numRegistros);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
