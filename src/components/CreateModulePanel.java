package components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import queryComponents.Column;
import queryComponents.Table;

public class CreateModulePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private List<AtributoPanel> listAttr = new ArrayList<AtributoPanel>();
	private JPanel panelAttr;
	private JCheckBox cbxSelectAll;
	private JCheckBox cbxSelectById;
	private JCheckBox cbxCreate;
	private JCheckBox cbxUpdate;
	private JCheckBox cbxDelete;
	private JButton btnAceptar;

	public CreateModulePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Crear nuevo modulo");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_2.setMaximumSize(new Dimension(32767, 50));
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Nombre de la entidad");
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtName = new JTextField();
		panel_2.add(txtName);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_3.setMaximumSize(new Dimension(32767, 50));
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		cbxSelectAll = new JCheckBox("Select All");
		panel_3.add(cbxSelectAll);

		cbxSelectById = new JCheckBox("Select By Id");
		panel_3.add(cbxSelectById);

		cbxCreate = new JCheckBox("Create");
		panel_3.add(cbxCreate);

		cbxUpdate = new JCheckBox("Update");
		panel_3.add(cbxUpdate);

		cbxDelete = new JCheckBox("Delete");
		panel_3.add(cbxDelete);

		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32767, 50));
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_4);

		JButton btnNewButton = new JButton("Agregar Atributo");
		panel_4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAtributo();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		panelAttr = new JPanel();
		scrollPane.setViewportView(panelAttr);
		panelAttr.setLayout(new BoxLayout(panelAttr, BoxLayout.Y_AXIS));
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 50));
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setAlignmentY(0.0f);
		panel.add(btnVolver);

		btnAceptar = new JButton("Aceptar");
		panel.add(btnAceptar);
		btnAceptar.setAlignmentY(Component.TOP_ALIGNMENT);

		Component verticalStrut = Box.createVerticalStrut(50);
		add(verticalStrut);

	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public void eliminarAtributo(AtributoPanel attr) {
		remove(attr);
		this.listAttr.remove(attr);
		repaint();
		revalidate();
		updateUI();
	}

	public Module getModule() {

		Set<Column> columns = new HashSet<Column>();
		List<Atributo> attrList = new ArrayList<>();
		Set<Table> tableJoins =new HashSet<Table>();
		for (AtributoPanel attr : this.listAttr) {
			
			if(attr.getAtributo().getType().equals("Select") || attr.getAtributo().getType().equals("Datamodal")) {
				tableJoins.add(new Table("", columns));
			}
			
			attrList.add(attr.getAtributo());
			columns.add(new Column(attr.getAtributo().getName(), attr.getAtributo().getType()));
		}
		Table table = new Table(txtName.getText(), columns);
		return new Module(txtName.getText(), cbxSelectAll.isSelected(), cbxSelectById.isSelected(),
				cbxCreate.isSelected(), cbxUpdate.isSelected(), cbxDelete.isSelected(), table, attrList);
	}

	public void addAtributo() {
		AtributoPanel atributo = new AtributoPanel();
		atributo.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));

		listAttr.add(atributo);
		panelAttr.add(atributo);

		updateUI();
		revalidate();

	}

}
