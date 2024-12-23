package view.JPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.Cliente;
import utils.FileManager;
import view.Main;

public class DadosPessoaisJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String[] nomes = { "Nome", "Sobrenome", "Email", "Senha", "Sexo", "CPF", "Telefone", "Nascimento", "Cep",
			"Cidade" };
	private JLabel[] labels = new JLabel[10];
	private JTextField[] fields = new JTextField[10];
	private JButton[] buttons = new JButton[10];
	private ArrayList<String> credentials = FileManager.credentialsToArrayList();

	private final int x = 500;
	private final int y = 30;
	private final int separadorY = 130;
	private final int width = 150;
	private final int height = 30;

	// fontes
	private final Font font1 = new Font("Open Sans", Font.BOLD, 20);
	private final Font font2 = new Font("Montserrat", Font.BOLD, 14);
	private Color colorDefaultForeground = Color.decode("#cad0d6");

	public DadosPessoaisJPanel() {
		setLayout(null);
		setOpaque(false);
		setPreferredSize(new Dimension(1320, 1500));

		for (int i = 0; i < 10; i++) {
			labels[i] = new JLabel();
			fields[i] = new JTextField();
			buttons[i] = new JButton();

			// setText
			labels[i].setText(nomes[i]);
			fields[i].setText(credentials.get(i));
			buttons[i].setText("Alterar campo");

			// setBounds
			labels[i].setBounds(x, y + separadorY * i, width + 20, height);
			fields[i].setBounds(x, y + 50 + separadorY * i, width + 200, height);
			buttons[i].setBounds(x + 200, y + separadorY * i, width, height);

			// setFont
			labels[i].setFont(font1);
			fields[i].setFont(font2);
			buttons[i].setFont(font2);

			// other
			labels[i].setForeground(colorDefaultForeground);
			fields[i].setEditable(false);
			fields[i].setBackground(colorDefaultForeground);
//			fields[i].setForeground(Color.black);
			fields[i].setBorder(new EmptyBorder(0, 5, 0, 0)); // Margem 5 pixels
			buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttons[i].setBorder(null);
			buttons[i].setBackground(Color.decode("#238636")); // verde
			buttons[i].setForeground(colorDefaultForeground); // branco
			final int index = i;
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JTextField field : fields) {
						field.setEditable(false);
					}
					fields[index].setEditable(true);
				}
			});

			// add
			add(labels[i]);
			add(fields[i]);
			add(buttons[i]);
		}

		// enviar
		JButton enviar = new JButton("Enviar");
		enviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enviar.setBounds(x, y + separadorY * 10, width, height);
		enviar.setBorder(null);
		enviar.setFont(font2);
		enviar.setBackground(Color.decode("#238636")); // verde
		enviar.setForeground(colorDefaultForeground); // branco
		add(enviar);
		enviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente(Main.frameUsuario.mainClient.getId_pessoa(), fields[0].getText(),
						fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(),
						fields[5].getText(), fields[6].getText(), fields[7].getText(), fields[8].getText(),
						fields[9].getText(), Main.frameUsuario.mainClient.getDinheiro(),
						Main.frameUsuario.mainClient.getDataEntrada());
				Main.conn.update(c);
			}
		});
	}

	// Ajustando a sensibilidade de rolagem
	public static void configScrollPane(JScrollPane scrollPane) {
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		scrollPane.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// Multiplicando o valor de rolagem por um fator
				int notches = e.getWheelRotation();
				scrollPane.getVerticalScrollBar()
						.setValue(scrollPane.getVerticalScrollBar().getValue() + (notches * 40)); // Ajuste o fator
																									// conforme
																									// necessário
			}
		});
	}

}
