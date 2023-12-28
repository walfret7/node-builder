package components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AtributoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtLabel;
	private JComboBox<String> cbDataType;
	private JCheckBox cbxRequired;
	private JCheckBox cbxForm;
	private JCheckBox cbxTable;
	private JButton btnBorrar;
	private JButton btnMore;
	private JTextArea txtConfig;

	public AtributoPanel() {

		JLabel lblNewLabel = new JLabel("Label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtLabel = new JTextField();
		txtLabel.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);

		cbDataType = new JComboBox<String>();
		cbDataType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbDataType.setModel(new DefaultComboBoxModel<String>(new String[] { "Text", "INT","DOUBLE", "Select", "Data Modal",
				"Child", "Check Box", "DATE", "DATETIME", "TIME", "Email", "Password", "Image Path", "Image","File Path","File", "TextArea" }));

		cbxRequired = new JCheckBox("Required");
		cbxRequired.setFont(new Font("Tahoma", Font.PLAIN, 12));

		cbxForm = new JCheckBox("Form");
		cbxForm.setFont(new Font("Tahoma", Font.PLAIN, 12));

		cbxTable = new JCheckBox("Table");
		cbxTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		add(lblNewLabel);
		add(txtLabel);

		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblName_1);
		add(txtName);

		JLabel lblName = new JLabel("Tipo de dato");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblName);
		add(cbDataType);
		add(cbxRequired);
		add(cbxForm);
		add(cbxTable);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateModulePanel panel = (CreateModulePanel) AtributoPanel.this.getParent().getParent().getParent().getParent();
				panel.eliminarAtributo(AtributoPanel.this);				
				AtributoPanel.this.getParent().remove(AtributoPanel.this);


			}
		});
		
		txtConfig = new JTextArea();
		txtConfig.setPreferredSize(new Dimension(400,200));	
				
		btnMore = new JButton("Config");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, txtConfig);
			}
		});
		add(btnMore);
		add(btnBorrar);

	}

	public Atributo getAtributo() {

		return new Atributo(
				txtLabel.getText(), 
				txtName.getText(), 
				cbDataType.getSelectedItem().toString(),
				txtConfig.getText(), 
				cbxRequired.isSelected(),
				cbxForm.isSelected(), 
				cbxTable.isSelected());

	}
}
