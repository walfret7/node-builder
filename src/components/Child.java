package components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Child extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Child() {
		
		JLabel lblNewLabel = new JLabel("Table");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

	}

}
