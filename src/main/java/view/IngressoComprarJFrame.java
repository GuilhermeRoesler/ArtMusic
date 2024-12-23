package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controller.EventoDAO;
import controller.ParticipacaoDAO;
import model.Evento;
import utils.Constants;
import utils.ConversorUtils;
import utils.MaskUtil;
import utils.MeuModeloTabela1Utils;
import view.JPanels.HomeJPanel;

public class IngressoComprarJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// JComponents
	private JPanel contentPane, pnlHeader;
	private JLabel lblIngressos, lblPreco, lblShowsProximos, lblDinheiro;
	private JFormattedTextField tfdCpf;
	private JPasswordField tfdSenha;
	private JButton btnSair, btnComprar;
	private JComboBox<String> cmbboxShow;
	private JScrollPane scrollPane;
	private JTable table;

	private int y = 110;

	// fonts
	private Font font1 = new Font("Nimbus Sans", Font.BOLD, 30);
	private Font font2 = new Font("Open Sans", Font.BOLD, 14);
	private Font font3 = new Font("Montserrat Medium", Font.PLAIN, 14);
	private Font fonteCabecalho = new Font("Open Sans", Font.BOLD, 16);
	private Font fonteTabela = new Font("Consolas", Font.PLAIN, 12);
	private Color colorDefaultForeground = Color.decode("#cad0d6");

	private EventoDAO eventoDAO = new EventoDAO();
	private ParticipacaoDAO participacaoDAO = new ParticipacaoDAO();
	private ArrayList<Evento> eventos;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					IngressoComprarJFrame frame = new IngressoComprarJFrame();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// constructor
	public IngressoComprarJFrame() {
		eventos = eventoDAO.read();
		setResizable(false);
		setTitle("Ingressos");
		setIconImage(Constants.LOGO);

		initComponents();
		initListeners();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#0d1117"));

		// pnlHeader
		pnlHeader = new JPanel();
		pnlHeader.setBounds(5, 5, 474, 50);
		pnlHeader.setOpaque(false);
		contentPane.add(pnlHeader);

		// lblIngressos
		lblIngressos = new JLabel("Ingressos");
		lblIngressos.setFont(font1);
		lblIngressos.setForeground(colorDefaultForeground);
		lblIngressos.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlHeader.add(lblIngressos);

		// cmbboxShow
		cmbboxShow = new JComboBox<String>();
		cmbboxShow.setModel(comboBoxModel());
		cmbboxShow.setBounds(47, y, 260, 30);
		cmbboxShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(cmbboxShow);
		cmbboxShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cmbboxShow.getSelectedItem().equals("Selecione"))
					cmbboxShow.removeItem("Selecione");
				String preco = String.valueOf(eventos.get(cmbboxShow.getSelectedIndex()).getPreco());
				preco = String.format("R$%s0", preco);
				lblPreco.setText(preco);
			}
		});

		// lblPreco
		lblPreco = new JLabel("R$0.00");
		lblPreco.setBounds(345, y, 80, 30);
		lblPreco.setFont(font3);
		lblPreco.setForeground(colorDefaultForeground);
		contentPane.add(lblPreco);

		// tfdCpf
		tfdCpf = new JFormattedTextField(MaskUtil.createMaskFormatter("###.###.###-##"));
		tfdCpf.setText("cpf");
		tfdCpf.setBounds(47, y + 60, 196, 30);
		tfdCpf.setFont(font2);
		tfdCpf.setBorder(new EmptyBorder(0, 5, 0, 0)); // Margem 5 pixels
		tfdCpf.setBackground(colorDefaultForeground);
		contentPane.add(tfdCpf);

		// tfdSenha
		tfdSenha = new JPasswordField("Senha");
		tfdSenha.setEchoChar((char) 0);
		tfdSenha.setBounds(302, y + 60, 133, 30);
		tfdSenha.setFont(font2);
		tfdSenha.setBorder(new EmptyBorder(0, 5, 0, 0));
		tfdSenha.setBackground(colorDefaultForeground);
		contentPane.add(tfdSenha);

		// btnComprar
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(190, y + 120, 100, 28);
		btnComprar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprar.setBorder(null);
		btnComprar.setFont(font2);
		btnComprar.setBackground(Color.decode("#238636")); // verde
		btnComprar.setForeground(Color.decode("#cad0d6")); // branco
		contentPane.add(btnComprar);

		// lblShowsProximos
		lblShowsProximos = new JLabel("Shows próximos");
		lblShowsProximos.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowsProximos.setFont(font1);
		lblShowsProximos.setBounds(119, 290, 249, 39);
		lblShowsProximos.setForeground(colorDefaultForeground);
		contentPane.add(lblShowsProximos);

		// scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 340, 437, 276);
		contentPane.add(scrollPane);

		// table
		table = new JTable();
		table.setModel(setTableModel());
		table.setFillsViewportHeight(true);
		table.setFont(fonteTabela);
		table.getTableHeader().setFont(fonteCabecalho);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);

		// btnSair
		btnSair = new JButton("Sair");
		btnSair.setBounds(406, 627, 68, 23);
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setFont(font2);
		btnSair.setBorder(null);
		btnSair.setBackground(Color.decode("#862323")); // vermelho
		btnSair.setForeground(Color.decode("#cad0d6")); // branco
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// lblDinheiro
		lblDinheiro = new JLabel("R$" + Main.mainClient.getDinheiro() + ",00");
		lblDinheiro.setHorizontalAlignment(SwingConstants.LEFT);
		lblDinheiro.setBounds(10, 631, 111, 14);
		lblDinheiro.setFont(font3);
		lblDinheiro.setForeground(colorDefaultForeground);
		contentPane.add(lblDinheiro);
	}

	private void initListeners() {
		// windowListenerClosing
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cmbboxShow.setModel(comboBoxModel());
				tfdSenha.setText("Senha");
				tfdCpf.setText(null);
				lblPreco.setText("R$0.00");
				table.clearSelection();
				dispose();
			}
		});

		KeyAdapter enterKeyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnComprar.doClick();
				}
			}
		};
		tfdCpf.addKeyListener(enterKeyAdapter);
		tfdSenha.addKeyListener(enterKeyAdapter);

		tfdCpf.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfdCpf.getText().equals("Cpf")) {
					tfdCpf.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfdCpf.getText().isEmpty()) {
					tfdCpf.setText("Cpf");
				}
			}
		});

		tfdSenha.addFocusListener(new FocusListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (tfdSenha.getText().equals("Senha")) {
					tfdSenha.setText("");
					tfdSenha.setEchoChar('•');
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (tfdSenha.getText().isEmpty()) {
					tfdSenha.setText("Senha");
					tfdSenha.setEchoChar((char) 0);
				}
			}
		});

		tfdCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfdCpf.getText().trim().isEmpty()) {
					tfdCpf.setText(""); // Limpa o campo ao ganhar foco apenas se estiver vazio
				}
			}
		});

		// btnComprarAction
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String show = cmbboxShow.getSelectedItem().toString();
				String cpf = tfdCpf.getText(); // .replaceAll("[^0-9]", "");
				@SuppressWarnings("deprecation")
				String senha = tfdSenha.getText().trim();
				int preco = Integer.parseInt(lblPreco.getText().replace("R$", "").replace(".00", "").trim());
				int dinheiro = Integer.parseInt(lblDinheiro.getText().replace("R$", "").replace(",00", "").trim());
				int newDinheiro = dinheiro - preco;

				// credenciais válidas
				if (!show.equals("Selecione") && cpf.equals(Main.mainClient.getCpf())
						&& senha.equals(Main.mainClient.getSenha())) {
					// dinheiro suficiente
					if (preco <= dinheiro) {
						if (participacaoDAO.create(Main.mainClient,
								eventoDAO.getByName(cmbboxShow.getSelectedItem().toString()))) {
							JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso!");
							lblDinheiro.setText(String.valueOf("R$" + newDinheiro + ",00"));
							Main.conn.updateDinheiro(newDinheiro, Main.mainClient.getId_pessoa());
							dispose();
							((HomeJPanel) Main.frameUsuario.pnlHome).update();
//							Main.frameUsuario.reloadComponents();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Você não tem dinheiro o suficiente, trabalhe!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Credenciais incorretas!");
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				if (linhaSelecionada != -1) {
					String itemSelecionado = (String) table.getValueAt(linhaSelecionada, 0);
					cmbboxShow.setSelectedItem(itemSelecionado);
				}
			}
		});
	}

	private ComboBoxModel<String> comboBoxModel() {
		ArrayList<String> showsDisponiveis = new ArrayList<>();
		ArrayList<String> showsComprados = eventoDAO.obterShowsComprados(Main.mainClient.getId_pessoa());
		showsDisponiveis.add("Selecione");

		for (Evento evento : eventos) {
			String nomeShow = evento.getNome();
			if (!showsComprados.contains(nomeShow)) {
				showsDisponiveis.add(nomeShow);
			}
		}

		return new DefaultComboBoxModel<String>(showsDisponiveis.toArray(new String[0]));
	}

	private AbstractTableModel setTableModel() {
		ArrayList<Evento> dados = eventoDAO.read();

		// dimensions
		int linhas = dados.size();
		int colunas = 3;

		Object[][] dadosArray = new Object[linhas][colunas];
		for (int i = 0; i < linhas; i++) {
			dadosArray[i][0] = dados.get(i).getNome();
			dadosArray[i][1] = ConversorUtils.DateTimeToUser(dados.get(i).getData());
			dadosArray[i][2] = dados.get(i).getLocal();
		}

		MeuModeloTabela1Utils model = new MeuModeloTabela1Utils(dadosArray);

		return model;
	}
}