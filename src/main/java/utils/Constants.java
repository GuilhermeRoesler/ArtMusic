package utils;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Constants {
	// Paths
	public static final String APPDATA = System.getenv("APPDATA");
	public static final String APP_FOLDER = APPDATA + File.separator + "ArtMusic";
	public static final String LAST_CLIENT_PATH = APP_FOLDER + File.separator + "lastClient.txt";
	
	// Files paths
	public static final String CITIES_PATH = "data/cidades.txt";
	public static final String FIRST_NAMES_PATH = "data/nomes.txt";
	public static final String LAST_NAMES_PATH = "data/sobrenomes.txt";
	
	// Data lists
	public static final ArrayList<String> CITIES = FileLoader.loadFile(CITIES_PATH);
	public static final ArrayList<String> FIRST_NAMES = FileLoader.loadFile(FIRST_NAMES_PATH);
	public static final ArrayList<String> LAST_NAMES = FileLoader.loadFile(LAST_NAMES_PATH);

	// Images
	public static final Image LOGO = ImageLoader.loadImage("ART Music logo.png").getImage();
	public static final ImageIcon TELA_APRESENTACAO = ImageLoader.loadImage("tela apresentacao.png");
	public static final ImageIcon TELA_CADASTRO = ImageLoader.loadImage("tela cadastro.png");
	public static final ImageIcon TELA_DETALHES = ImageLoader.loadImage("tela detalhes.png");
	public static final ImageIcon TELA_LOGIN = ImageLoader.loadImage("tela login.png");
	public static final ImageIcon TELA_RECUPERAR_SENHA = ImageLoader.loadImage("tela recuperar senha.png");
	public static final ImageIcon BACK = ImageLoader.loadImage("back.png");
	public static final ImageIcon OPEN_EYE = ImageLoader.loadImage("openEye.png");
	public static final ImageIcon CLOSED_EYE = ImageLoader.loadImage("closedEye.png");
	public static final ImageIcon PROFILE_PIC_DEFAULT = ImageLoader.loadImage("default_profile_pic.png");
	public static final ImageIcon COIN = ImageLoader.loadImage("coin.png");
	public static final ImageIcon MONEY = ImageLoader.loadImage("money.png");
	
}
