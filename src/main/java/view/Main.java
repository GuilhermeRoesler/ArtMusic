package view;

import controller.ArtistaDAO;
import controller.ClienteDAO;
import controller.EventoDAO;
import controller.ParticipacaoDAO;
import model.Cliente;
import utils.FileManager;

public class Main {

	public static final ClienteDAO conn = new ClienteDAO(); // initialize the connection
	public static final EventoDAO eventoDAO = new EventoDAO(); // initialize the connection
	public static final ArtistaDAO artistaDAO = new ArtistaDAO(); // initialize the connection
	public static final ParticipacaoDAO participacaoDAO = new ParticipacaoDAO(); // initialize the connection

	public static Cliente mainClient; // set mainClient

	// initialize frames
	public static final ApresentacaoJFrame frameApresentacao = new ApresentacaoJFrame();
	public static final LoginJFrame frameLogin = new LoginJFrame(conn);
	public static final CadastroJFrame frameCadastro = new CadastroJFrame(conn);
	public static UsuarioJFrame frameUsuario;

	public static void main(String[] args) {
		FileManager.initializeFile();
		if (FileManager.verifyIfLastClientExists()) {
			mainClient = FileManager.readLastClient();
		}

		// initialize frameApresentacao
		try {
			frameApresentacao.setLocationRelativeTo(null);
			frameApresentacao.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// File directory = new File();

		// System.getProperties().forEach((k, v) -> System.out.println(k + ": " + v));
	}

	public static void setMainClient(Cliente cliente) {
		mainClient = cliente;
	}

	// public static InputStream loadTextFile(String fileName) {
	// try {
	// InputStream input = Main.class.getResourceAsStream("/resources/" + fileName);
	// if (input != null) {
	// return input;
	// } else {
	// System.err.println("Couldn't find file: " + fileName);
	// return null;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

}
