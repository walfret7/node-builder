package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.MainPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PanelShowing;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		PanelShowing = new MainPanel();
		contentPane.add(PanelShowing);
	}

	public JPanel getPanelShowing() {
		return PanelShowing;
	}

	public void changePanel(JPanel panel) {
		contentPane.removeAll();
		contentPane.add(panel);

		repaint();
		revalidate();
		contentPane.updateUI();
	}

}
