package view.JPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.Main;

public class OptionJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[8];
	private String[] nameButtons = { "Home", "Configurações", "Dados Pessoais", "Atividade", "Notificações",
			"Ajuda", "Dinheiro", "Sair" };

	private int separadorY = 20;

	// fontes
	private Font defaultFont = new Font("Montserrat", Font.BOLD, 16);
	private Color defaultTextColor = Color.decode("#cad0d6");
	private Color colorHover = Color.decode("#eeeeee");
	private Dimension size = new Dimension(200, 50);

	public OptionJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// pnlConfig boxLayout
		Dimension pnlConfigDimension = new Dimension(0, separadorY);

		for (int i = 0; i < 8; i++) {
			buttons[i] = new JButton(nameButtons[i]);
			buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			buttons[i].setPreferredSize(size);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorder(null);
			buttons[i].setForeground(defaultTextColor);
			buttons[i].setFont(defaultFont);
			final int index = i;
			buttons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					buttons[index].setForeground(colorHover);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					buttons[index].setForeground(defaultTextColor);
				}
			});
			add(Box.createRigidArea(pnlConfigDimension));
			add(buttons[i]);
		}
		
		buttons[7].setForeground(Color.decode("#ba2c2c"));
		buttons[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttons[7].setForeground(Color.decode("#e03636"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				buttons[7].setForeground(Color.decode("#ba2c2c"));
			}
		});

		buttons[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.frameUsuario.dispose();
				Main.frameLogin.setVisible(true);
			}
		});
	}

	public JButton[] getButtons() {
		return Arrays.copyOfRange(buttons, 0, 7); // exclui botão sair
	}
}
