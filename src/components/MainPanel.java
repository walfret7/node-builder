package components;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnAbrir;
	private JButton btnCrear;

	public MainPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Node Builder");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);

		Component verticalStrut = Box.createVerticalStrut(50);
		add(verticalStrut);

		btnCrear = new JButton("Crear Proyecto");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnCrear);

		add(Box.createVerticalStrut(50));

		btnAbrir = new JButton("Abrir Proyecto");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAbrir.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnAbrir);

		Component verticalStrut_1 = Box.createVerticalStrut(50);
		add(verticalStrut_1);

	}

	public JButton getBtnAbrir() {
		return btnAbrir;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

}
