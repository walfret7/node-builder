package components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;

public class MoreConfig extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtData;

	
	public MoreConfig() {
		
		JLabel lblNewLabel_1_1 = new JLabel("Data source");
		lblNewLabel_1_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		add(lblNewLabel_1_1);
		
		JComboBox<String> cbDatasource = new JComboBox<String>();
		cbDatasource.setModel(new DefaultComboBoxModel<String>(new String[] {"Datatable", "Dataenum", ""}));
		add(cbDatasource);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		add(lblNewLabel_1);
		
		txtData = new JTextField();
		add(txtData);
		txtData.setColumns(20);

	}
	
	enum Type{
		SELECT,DATAMODAL
	}
	
	

}
