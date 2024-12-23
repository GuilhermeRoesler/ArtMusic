package view.JPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Evento;
import view.DetalhesJFrame;
import view.IngressoComprarJFrame;
import view.Main;

public class HomeJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblSeusShows, lblAgendaVazia;
	private JButton btnComprarIngresso;
	private ArrayList<JLabel> showslbl = new ArrayList<JLabel>();

	private Dimension separadorY = new Dimension(0, 20);

	// fonts and colors
	private Font font1 = new Font("Nimbus Sans", Font.BOLD, 42);
	private Font font2 = new Font("Nimbus Sans", Font.BOLD, 25);
	private Font fontButton = new Font("Montserrat", Font.BOLD, 14);
	private Color fontColor = Color.decode("#cad0d6"); // branco
	private Color fontColor2 = Color.decode("#eff6f0"); // branco
	private Color colorButton = Color.decode("#238636"); // verde
	private Color colorlbls = Color.decode("#8C68DE"); // cor dos shows
	private Color colorlblsHover = Color.decode("#6890DE"); // cor dos shows selecionados

	public IngressoComprarJFrame frameIngresso;
	private DetalhesJFrame frameDetalhes;

	public HomeJPanel() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initComponents();
		configLayout();
	}

	private void initComponents() {
		// lblSeusShows
		lblSeusShows = new JLabel("Seus Shows");
		lblSeusShows.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSeusShows.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeusShows.setPreferredSize(new Dimension(100, 50));
		lblSeusShows.setForeground(fontColor);
		lblSeusShows.setFont(font1);

		// lblSeusShows2
		lblAgendaVazia = new JLabel("Sua agenda está vazia...");
		lblAgendaVazia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAgendaVazia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendaVazia.setPreferredSize(new Dimension(100, 50));
		lblAgendaVazia.setForeground(fontColor);
		lblAgendaVazia.setFont(font2);

		// btnComprarIngresso
		btnComprarIngresso = new JButton("Comprar ingresso");
		btnComprarIngresso.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnComprarIngresso.setBackground(colorButton);
		btnComprarIngresso.setForeground(fontColor2);
		btnComprarIngresso.setFont(fontButton);
		btnComprarIngresso.setBorder(BorderFactory.createLineBorder(colorButton, 10));
		btnComprarIngresso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// btnComprarIngresso
		btnComprarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frameIngresso = new IngressoComprarJFrame();
					frameIngresso.setVisible(true);
					frameIngresso.setLocationRelativeTo(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	// reseta layout e cria de novo, atualizando-o
	private void configLayout() {
		removeAll(); // limpa layout, começa do zero

		verificarShows(); // pega todos os shows do banco de dados

		add(Box.createRigidArea(separadorY));
		add(lblSeusShows);
		add(Box.createRigidArea(separadorY));
		add(Box.createRigidArea(separadorY));

		if (showslbl.isEmpty()) {
			add(lblAgendaVazia);
		} else {
			for (JLabel label : showslbl) {
				add(label);
				add(Box.createRigidArea(separadorY));
			}
		}

		add(Box.createRigidArea(separadorY));
		add(btnComprarIngresso);

		revalidate();
		repaint();
	}
	
	public void update() {
		configLayout();
	}

	// armazena shows em que o usuário está inscrito
	private void verificarShows() {
		showslbl.clear(); // limpa o ArrayList
		ArrayList<Evento> shows = Main.eventoDAO.getShowsOwned(Main.mainClient); // pega do banco de dados

		if (!shows.isEmpty()) {
			int numShows = shows.size();

			// popula o ArrayList com todas as JLabels
			for (int i = 0; i < numShows; i++) {
				JLabel show = new JLabel();
				show.setText("• " + shows.get(i).getNome());
				configJLabel(show, i);
				showslbl.add(show);
			}
		}
	}

	// cria labels de maneira mais prática e eficiente
	private void configJLabel(JLabel label, int i) {
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(colorlbls);
		label.setFont(font2);

		final int index = i;
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				showslbl.get(index).setForeground(colorlblsHover); // Altera a cor do texto para amarelo
			}

			@Override
			public void mouseExited(MouseEvent e) {
				showslbl.get(index).setForeground(colorlbls); // Retorna a cor original ao sair
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frameDetalhes = new DetalhesJFrame(
						Main.eventoDAO.getByName(showslbl.get(index).getText().replace("• ", "")));
				frameDetalhes.setLocationRelativeTo(null);
				frameDetalhes.setVisible(true);
			}
		});
	}
}
