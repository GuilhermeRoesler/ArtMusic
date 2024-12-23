package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClienteDAO;
import model.Cliente;
import utils.Constants;
import utils.FileManager;

public class LoginJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// JComponents
	private JPanel contentPane, panel1, panel12;
	private JLabel lblCadastrar, lblBackground;
	private JTextField tfdEmail;
	private JPasswordField tfdSenha;
	private JButton btnLogin, btnVerSenha, btnEsqueciSenha, btnCadastrar, btnBack;
	private JCheckBox chckbxLembrarDeMim;

	// images
	private Font defaulFont = new Font("Open Sans", Font.BOLD, 20);
	private String strtfdEmail;
	
	// listeners
	private ActionListener loginActionListener, recuperarSenhaActionListener;
	private KeyAdapter enterKeyAdapter;

	private ClienteDAO conn;

	// constructor
	public LoginJFrame(ClienteDAO conn) {
		setTitle("Login");
		setResizable(false);
		setIconImage(Constants.LOGO);
		
		this.conn = conn;
		initComponents();
		initListeners();

		if (FileManager.isRememberMeOn()) {
			tfdEmail.setText(Main.mainClient.getEmail());
			tfdSenha.setText(Main.mainClient.getSenha());
			tfdSenha.setEchoChar('•');
		}
	}

	private void initComponents() {
//		setIconImage(Main.logo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		// contentPane.setBackground(Color.decode("#2E2F32"));
		contentPane.setLayout(null);

		// panel1
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 584, 611);
		contentPane.add(panel1);
		panel1.setLayout(null);
		panel1.setOpaque(false);

		// btnLogin
		btnLogin = new JButton();
		btnLogin.setBounds(188, 497, 208, 56);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel1.add(btnLogin);
		btnLogin.addActionListener(loginActionListener);

		// panel12
		panel12 = new JPanel();
		panel12.setBounds(126, 372, 330, 211);
		panel1.add(panel12);
		panel12.setLayout(null);
		panel12.setOpaque(false);

		// tfdEmail
		tfdEmail = new JTextField("Email");
		tfdEmail.setBounds(133, 268, 329, 56);
		tfdEmail.setFont(defaulFont);
		tfdEmail.setForeground(Color.decode("#22224e"));
		tfdEmail.setOpaque(false);
		tfdEmail.setBorder(null);
		panel1.add(tfdEmail);

		// tfdSenha
		tfdSenha = new JPasswordField("Senha");
		tfdSenha.setBounds(7, 0, 283, 56);
		tfdSenha.setEchoChar((char) 0);
		tfdSenha.setFont(defaulFont);
		tfdSenha.setForeground(Color.decode("#22224e"));
		tfdSenha.setOpaque(false);
		tfdSenha.setBorder(null);
		panel12.add(tfdSenha);

		// btnVerSenha
		btnVerSenha = new JButton(Constants.OPEN_EYE);
		btnVerSenha.setBounds(300, 13, 30, 30);
		panel12.add(btnVerSenha);
		btnVerSenha.setContentAreaFilled(false);
		btnVerSenha.setBorderPainted(false);
		btnVerSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// chckbxLembrarDeMim
		chckbxLembrarDeMim = new JCheckBox("Lembrar de mim");
		chckbxLembrarDeMim.setBounds(0, 63, 130, 23);
		panel12.add(chckbxLembrarDeMim);
		chckbxLembrarDeMim.setSelected(true);
		chckbxLembrarDeMim.setContentAreaFilled(false);
		chckbxLembrarDeMim.setFont(new Font("Open Sans", Font.PLAIN, 13));
		chckbxLembrarDeMim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// btnEsqueciSenha
		btnEsqueciSenha = new JButton("Esqueceu a senha?");
		btnEsqueciSenha.setBounds(224, 63, 105, 23);
		panel12.add(btnEsqueciSenha);
		btnEsqueciSenha.setContentAreaFilled(false);
		btnEsqueciSenha.setBorder(null);
		btnEsqueciSenha.setFont(new Font("Open Sans", Font.ITALIC, 13));
		btnEsqueciSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// lblCadastrar
		lblCadastrar = new JLabel("Não tem uma conta?");
		lblCadastrar.setBounds(59, 192, 146, 14);
		panel12.add(lblCadastrar);
		lblCadastrar.setFont(new Font("Open Sans", Font.ITALIC, 14));

		// btnCadastrar
		btnCadastrar = new JButton("Cadastre-se");
		btnCadastrar.setBounds(lblCadastrar.getBounds().x + lblCadastrar.getWidth() - 15,
				lblCadastrar.getBounds().y - 4, 88, 23);
		panel12.add(btnCadastrar);
		btnCadastrar.setContentAreaFilled(false);
		btnCadastrar.setBorder(null);
		btnCadastrar.setFont(new Font("Open Sans", Font.BOLD, 14));
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// btnBack
		btnBack = new JButton(Constants.BACK);
		btnBack.setBounds(10, 11, 30, 30);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel1.add(btnBack);

		// lblBackground
		lblBackground = new JLabel(Constants.TELA_LOGIN);
		lblBackground.setBounds(0, 0, 584, 611);
		contentPane.add(lblBackground);
	}

	// initListeners
	private void initListeners() {
		// windowListenerClosing
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				Main.frameApresentacao.setVisible(true);
			}
		});

		// tfdEmail focus
		tfdEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfdEmail.getText().equals("Email")) {
					tfdEmail.setText(""); // Limpar o texto ao ganhar foco
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfdEmail.getText().isEmpty()) {
					tfdEmail.setText("Email"); // Retornar texto padrão se estiver vazio
				}
			}
		});

		// tfdSenha focus
		tfdSenha.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (tfdSenha.getText().equals("Senha")) {
					tfdSenha.setText(""); // Limpar o texto ao ganhar foco
					tfdSenha.setEchoChar('•');
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (tfdSenha.getText().isEmpty()) {
					tfdSenha.setText("Senha"); // Retornar texto padrão se estiver vazio
					tfdSenha.setEchoChar((char) 0);
				}
			}
		});

		// btnCadastrar listener
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.frameCadastro.setLocationRelativeTo(null);
					Main.frameCadastro.setVisible(true);
					Main.frameLogin.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// btnEsqueciSenha listener
		btnEsqueciSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recuperarSenha();
			}
		});

		// btnVerSenha listener
		btnVerSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnVerSenha.getIcon().equals(Constants.OPEN_EYE)) {
					btnVerSenha.setIcon(Constants.CLOSED_EYE);
					tfdSenha.setEchoChar((char) 0);
				} else if (btnVerSenha.getIcon().equals(Constants.CLOSED_EYE)) {
					btnVerSenha.setIcon(Constants.OPEN_EYE);
					tfdSenha.setEchoChar('•');
				}
			}
		});

		// loginActionListener
		loginActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = tfdEmail.getText();
				@SuppressWarnings("deprecation")
				String senha = tfdSenha.getText();
				Cliente clienteVerificacao = new Cliente(email, senha);

				if (conn.canLogin(clienteVerificacao)) {
					// set mainClient
					Main.mainClient = conn.getByEmail(email);

					// store mainClient
					if (chckbxLembrarDeMim.isSelected()) {
						FileManager.storeCredentials(Main.mainClient, true);
					} else {
						FileManager.storeCredentials(Main.mainClient, false);
					}
					Main.frameUsuario = new UsuarioJFrame(Main.mainClient);
					Main.frameUsuario.setVisible(true);
					Main.frameLogin.setVisible(false);
				} else {
					System.out.println("Error");
					JOptionPane.showMessageDialog(null,
							"Houve um erro durante o procedimento, tente novamente inserindo outros dados!");
				}
			}
		};
		btnLogin.addActionListener(loginActionListener);

		// recuperarSenhaActionListener
		recuperarSenhaActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String senha;
				if ((senha = conn.recuperarSenha(tfdEmail.getText())) != null) {
					JOptionPane.showMessageDialog(null,
							"Sua senha: " + senha + "\nNão esquece da próxima vez, animal!");
					btnBack.doClick();

					// preenche campos
					Cliente c1 = conn.getBySenha(senha);
					tfdEmail.setText(c1.getEmail());
					tfdSenha.setText(c1.getSenha());
					tfdSenha.setEchoChar('•');
				} else {
					JOptionPane.showMessageDialog(null, "Email inválido, tente novamente!");
				}
			}
		};

		// btnBack listener
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel12.isVisible()) {
					dispose();
					Main.frameApresentacao.setVisible(true);
				} else {
					lblBackground.setIcon(Constants.TELA_LOGIN);
					panel12.setVisible(true);
					tfdEmail.setLocation(133, 268);
					tfdEmail.setText(strtfdEmail);
					btnLogin.addActionListener(loginActionListener);
					btnLogin.removeActionListener(recuperarSenhaActionListener);
				}
			}
		});

		// enterKeyAdapter
		enterKeyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		};
		tfdEmail.addKeyListener(enterKeyAdapter);
		tfdSenha.addKeyListener(enterKeyAdapter);
	}

	// recuperarSenha
	private void recuperarSenha() {
		lblBackground.setIcon(Constants.TELA_RECUPERAR_SENHA);
		panel12.setVisible(false);
		tfdEmail.setLocation(133, 327);
		strtfdEmail = tfdEmail.getText();
		tfdEmail.setText(null);
		tfdEmail.requestFocus();
		btnLogin.removeActionListener(loginActionListener);
		btnLogin.addActionListener(recuperarSenhaActionListener);
	}
}
