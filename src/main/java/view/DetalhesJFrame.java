package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Evento;
import utils.Constants;
import utils.ConversorUtils;
import view.JPanels.HomeJPanel;

import javax.swing.JButton;

public class DetalhesJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBackground;
	private JButton btnExluir;
	private JLabel[] labels = new JLabel[9];

	private int x1 = 160;
	private int x2 = 525;
	private int y1 = 60;
	private int width = 200;
	private int height = 20;
	private int separadorY = 67;

	// image
	private Font font = new Font("Montserrat Bold", Font.PLAIN, 15);
	private Color fontColor = Color.decode("#202020");

	private Evento mainEvent;

	public DetalhesJFrame(Evento evento) {
		setTitle("Detalhes");
		setResizable(false);
		setIconImage(Constants.LOGO);
		mainEvent = evento;

		initComponents();
		addingBackground();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] texts = { mainEvent.getNome(), ConversorUtils.DateTimeToUser(mainEvent.getData()), mainEvent.getLocal(),
				Main.artistaDAO.getById(mainEvent.getId_artista()).getNome(), "R$" + mainEvent.getPreco() + "0",
				String.valueOf(mainEvent.getCapacidade()), mainEvent.getTipo(), mainEvent.getStatus(),
				mainEvent.getDescricao() };

		for (int i = 0; i < 9; i++) {
			int x;
			int y = 0;

			if (i >= 6) {
				x = x2;
				y = y1 + separadorY * (i - 6);
			} else {
				x = x1;
				y = y1 + separadorY * i;
			}

			labels[i] = new JLabel();
			labels[i].setText(texts[i]);
			labels[i].setBounds(x, y, width, height);
			labels[i].setFont(font);
			labels[i].setForeground(fontColor);
			contentPane.add(labels[i]);
			
			btnExluir = new JButton("Excluir");
			btnExluir.setBounds(700, 440, 90, 30);
			btnExluir.setBorder(null);
			btnExluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnExluir.setFont(new Font("Open Sans", Font.BOLD, 14));
			btnExluir.setBackground(Color.decode("#862323")); // vermelho
			btnExluir.setForeground(Color.decode("#cad0d6")); // branco
			contentPane.add(btnExluir);
			btnExluir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.participacaoDAO.delete(Main.mainClient, mainEvent);
					dispose();
					((HomeJPanel) Main.frameUsuario.pnlHome).update();
				}
			});
		}
	}

	private void addingBackground() {
		ImageIcon imgBackground = Constants.TELA_DETALHES;
		lblBackground = new JLabel(imgBackground);
		lblBackground.setBounds(0, 0, imgBackground.getIconWidth(), imgBackground.getIconHeight());
		contentPane.add(lblBackground);
	}
}
