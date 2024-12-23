package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Cliente;
import view.Main;

//BufferedReader reader = new BufferedReader(new InputStreamReader(Main.loadTextFile("lastClient.txt")));

public class FileManager {
	/*
	 * initializes all the files the program needs to run checks if the main folder
	 * already exists in the AppData directory, and if it doesn't, creates it checks
	 * if the lastClient.txt file already exists, and if not, creates it
	 */
	public static void initializeFile() {
		try {
			File directory = new File(Constants.APP_FOLDER);

			if (!directory.exists()) {
				directory.mkdirs();
			}

			File lastClient = new File(Constants.LAST_CLIENT_PATH);
			if (!lastClient.exists()) {
				lastClient.createNewFile();
				FileWriter writer = new FileWriter(lastClient);
				writer.write("0");
				writer.close();
			}
		} catch (IOException e) {
			String message = "Erro ao criar arquivo: " + e.getMessage()
					+ "/nPara resolver este problema, por favor, crie um arquivo nomeado \"lastClient.txt\" dentro da pasta AppData\\Roaming\\ArtMusic e insira um 0 na primeira linha.";
			System.out.println("Erro ao criar arquivo: " + e.getMessage());
			JOptionPane.showMessageDialog(null, message,
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * reads the content of the lastClient.txt file and returns the first value. If
	 * the first value is 0, the file can be edited.
	 */
	public static boolean isRememberMeOn() {
		boolean rememberMe;
		try (BufferedReader reader = new BufferedReader(new FileReader(Constants.LAST_CLIENT_PATH))) {
			Object firstLine = reader.readLine();
			if (firstLine.equals("1")) {
				rememberMe = true;
			} else {
				rememberMe = false;
			}

			// set mainClient
			if (verifyIfLastClientExists()) {
				Cliente mainClient = new Cliente(Integer.parseInt(reader.readLine()), reader.readLine(),
						reader.readLine(),
						reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(),
						reader.readLine(), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()),
						reader.readLine());
				Main.setMainClient(mainClient);
			}

			reader.close();
			return rememberMe;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<String> credentialsToArrayList() {
		ArrayList<String> credentials = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(Constants.LAST_CLIENT_PATH))) {
			String line = reader.readLine(); // deleting check byte
			line = reader.readLine(); // deleting id
			while ((line = reader.readLine()) != null) {
				credentials.add(line);
			}
			reader.close();
			return credentials;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * reads the content of the lastClient.txt file and returns a Cliente object
	 * with all the credentials. this file is always read from the start of the
	 * running code. If the first value is 0, then the mainClient won't be writen.
	 * If the value is equals to 1, then the mainClient will be written
	 */
	public static Cliente readLastClient() {
		try (BufferedReader reader = new BufferedReader(new FileReader(Constants.LAST_CLIENT_PATH))) {
			reader.readLine(); // deleting check byte

			// set mainClient
			Cliente client = new Cliente(Integer.parseInt(reader.readLine()), reader.readLine(), reader.readLine(),
					reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(),
					reader.readLine(), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()),
					reader.readLine());

			reader.close();
			return client;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * saves the mainClient credentials at the lastClient.txt file, so that the
	 * program can later access it and determinates if the credencials will be saved
	 * at the login page or not. Always will save the credentials at the file, even
	 * if the rememberMe checkbox is unchecked. If that is the case, the program
	 * will put a 0 at the first line of the file, indicating that the credentials
	 * shouldn't be written at the login page.
	 */
	public static boolean storeCredentials(Cliente c, boolean rememberMe) {
		try (FileWriter writer = new FileWriter(Constants.LAST_CLIENT_PATH)) {
			writer.write("");

			// creates de check byte
			if (rememberMe) {
				writer.append("1\n");
			} else {
				writer.append("0\n");
			}

			// writes the rest of the information
			writer.append(String.valueOf(c.getId_pessoa()) + "\n");
			writer.append(c.getNome() + "\n");
			writer.append(c.getSobrenome() + "\n");
			writer.append(c.getEmail() + "\n");
			writer.append(c.getSenha() + "\n");
			writer.append(c.getSexo() + "\n");
			writer.append(c.getCpf() + "\n");
			writer.append(c.getTelefone() + "\n");
			writer.append(c.getNascimento() + "\n");
			writer.append(c.getCep() + "\n");
			writer.append(c.getCidade() + "\n");
			writer.append(String.valueOf(c.getDinheiro()) + "\n");
			writer.append(c.getDataEntrada() + "\n");
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean verifyIfLastClientExists() {
		try (BufferedReader reader = new BufferedReader(new FileReader(Constants.LAST_CLIENT_PATH))) {
			reader.readLine(); // deleting check byte

			if (reader.readLine() != null) {
				reader.close();
				return true;
			}

			reader.close();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
