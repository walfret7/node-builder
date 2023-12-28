package components;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import queryComponents.DB;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class CreateProyectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtPath;
	private JButton btnAceptar;
	private JTextField txtPort;
	private JTextField txtDriver;
	private JTextField txtHost;
	private JTextField txtDbPort;
	private JTextField txtDatabase;
	private JTextField txtUser;
	private JTextField txtPass;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public CreateProyectPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Crear Proyecto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel_1);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		add(verticalStrut);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Datos del Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setMaximumSize(new Dimension(32767, 30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Nombre del Proyecto");
		panel.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setMaximumSize(new Dimension(32767, 30));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Ubicacion");
		lblNewLabel_2.setPreferredSize(new Dimension(100, 14));
		lblNewLabel_2.setMinimumSize(new Dimension(100, 14));
		panel_1.add(lblNewLabel_2);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_1);
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		panel_1.add(txtPath);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		        fileChooser.setDialogTitle("Seleccionar Carpeta");
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		        fileChooser.setFileFilter(new FileFilter() {
		            @Override
		            public boolean accept(File file) {
		                return file.isDirectory();
		            }

		            @Override
		            public String getDescription() {
		                return "Carpetas";
		            }
		        });

		        int returnValue = fileChooser.showOpenDialog(null);

		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File selectedFolder = fileChooser.getSelectedFile();
		            txtPath.setText(selectedFolder.getAbsolutePath());		 
		        } 
			}
		});
		panel_1.add(btnNewButton);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_5.setMaximumSize(new Dimension(32767, 30));
		panel_3.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("Port");
		lblNewLabel_3.setPreferredSize(new Dimension(100, 14));
		lblNewLabel_3.setMinimumSize(new Dimension(100, 14));
		panel_5.add(lblNewLabel_3);
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_1_1);
		
		txtPort = new JTextField();
		panel_5.add(txtPort);
		txtPort.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Base de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(32767, 30));
		panel_6.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Driver");
		lblNewLabel_4.setPreferredSize(new Dimension(100, 14));
		panel_6.add(lblNewLabel_4);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_2);
		
		txtDriver = new JTextField();
		panel_6.add(txtDriver);
		txtDriver.setColumns(10);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setMaximumSize(new Dimension(32767, 30));
		panel_6_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6_1);
		panel_6_1.setLayout(new BoxLayout(panel_6_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4_1 = new JLabel("Host");
		lblNewLabel_4_1.setPreferredSize(new Dimension(100, 14));
		panel_6_1.add(lblNewLabel_4_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_6_1.add(horizontalStrut_3);
		
		txtHost = new JTextField();
		txtHost.setColumns(10);
		panel_6_1.add(txtHost);
		
		JPanel panel_6_2 = new JPanel();
		panel_6_2.setMaximumSize(new Dimension(32767, 30));
		panel_6_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6_2);
		panel_6_2.setLayout(new BoxLayout(panel_6_2, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4_2 = new JLabel("Port");
		lblNewLabel_4_2.setPreferredSize(new Dimension(100, 14));
		panel_6_2.add(lblNewLabel_4_2);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_6_2.add(horizontalStrut_4);
		
		txtDbPort = new JTextField();
		txtDbPort.setColumns(10);
		panel_6_2.add(txtDbPort);
		
		JPanel panel_6_3 = new JPanel();
		panel_6_3.setMaximumSize(new Dimension(32767, 30));
		panel_6_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6_3);
		panel_6_3.setLayout(new BoxLayout(panel_6_3, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4_3 = new JLabel("Database");
		lblNewLabel_4_3.setPreferredSize(new Dimension(100, 14));
		panel_6_3.add(lblNewLabel_4_3);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		panel_6_3.add(horizontalStrut_6);
		
		txtDatabase = new JTextField();
		txtDatabase.setColumns(10);
		panel_6_3.add(txtDatabase);
		
		JPanel panel_6_4 = new JPanel();
		panel_6_4.setMaximumSize(new Dimension(32767, 30));
		panel_6_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6_4);
		panel_6_4.setLayout(new BoxLayout(panel_6_4, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4_4 = new JLabel("User");
		lblNewLabel_4_4.setPreferredSize(new Dimension(100, 14));
		panel_6_4.add(lblNewLabel_4_4);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel_6_4.add(horizontalStrut_5);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		panel_6_4.add(txtUser);
		
		JPanel panel_6_5 = new JPanel();
		panel_6_5.setMaximumSize(new Dimension(32767, 30));
		panel_6_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_6_5);
		panel_6_5.setLayout(new BoxLayout(panel_6_5, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_4_5 = new JLabel("Password");
		lblNewLabel_4_5.setPreferredSize(new Dimension(100, 14));
		panel_6_5.add(lblNewLabel_4_5);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		panel_6_5.add(horizontalStrut_7);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		panel_6_5.add(txtPass);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(30);
		add(verticalStrut_1_1);
		
		JPanel panel_7 = new JPanel();
		add(panel_7);
		
		btnVolver = new JButton("Volver");
		panel_7.add(btnVolver);
		btnVolver.setAlignmentX(0.5f);
		
		btnAceptar = new JButton("Aceptar");
		panel_7.add(btnAceptar);
		btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);

	}
	
	public JTextField getTxtPort() {
		return txtPort;
	}

	public DB getDBConfig() {
		return new DB(txtDriver.getText(), txtDatabase.getText(), txtDbPort.getText(), txtHost.getText(), txtPass.getText(), txtUser.getText());
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtPath() {
		return txtPath;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

}
