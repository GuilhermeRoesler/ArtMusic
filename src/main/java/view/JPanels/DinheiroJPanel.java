package view.JPanels;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.Constants;
import view.Main;

public class DinheiroJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// JComponents
	private JButton btnMoney;

	private Dimension separadorY = new Dimension(0, 100);

	public DinheiroJPanel() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		initComponents();
		configLayout();
	}

	private void initComponents() {
		btnMoney = new JButton(Constants.MONEY);
		btnMoney.setPreferredSize(new Dimension(357, 185));
		btnMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMoney.setBorder(null);
		btnMoney.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMoney.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int novoValor = (Main.mainClient.getDinheiro() + 100);
				Main.conn.updateDinheiro(novoValor, Main.mainClient.getId_pessoa());
			}
		});
	}
	
	private void configLayout() {
		add(Box.createRigidArea(separadorY));
		add(btnMoney);
		add(Box.createRigidArea(separadorY));
	}

}
