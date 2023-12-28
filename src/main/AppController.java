package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import components.CreateModulePanel;
import components.CreateProyectPanel;
import components.MainPanel;
import components.ManageProyectPanel;
import nodeComponents.Controller;
import nodeComponents.DbConfig;
import nodeComponents.Migrate;
import nodeComponents.Model;
import nodeComponents.RouterMain;
import nodeComponents.Routes;
import nodeComponents.Server;
import queryComponents.DB;

import components.Module;

public class AppController {

	private MainFrame frame;
	private String rutaProyecto;
	private String rutaSrc;
	private String rutaControllers;
	private String rutaModels;
	private String rutaRoutes;
	private String rutaConfig;
	private String rutaMigrate;
	private ManageProyectPanel proyectPanel;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AppController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AppController() {

		frame = new MainFrame();
		frame.setVisible(true);

		MainPanel mainPanel = (MainPanel) frame.getPanelShowing();
		mainPanel.getBtnAbrir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirProyecto();
			}
		});
		mainPanel.getBtnCrear().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearProyecto();
			}
		});

	}

	private void abrirProyecto() {

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
			rutaProyecto = selectedFolder.getAbsolutePath();
			rutaSrc = rutaProyecto + "/src";
			rutaControllers = rutaProyecto + "/src/controllers";
			rutaModels = rutaProyecto + "/src/models";
			rutaRoutes = rutaProyecto + "/src/routes";
			rutaConfig = rutaProyecto + "/src/config";
			rutaMigrate = rutaProyecto + "/src/migrations";

			manageProyect(rutaProyecto);

		}

	}

	private void crearProyecto() {

		CreateProyectPanel panel = new CreateProyectPanel();
		panel.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = panel.getTxtName().getText();
				String path = panel.getTxtPath().getText();
				String port = panel.getTxtPort().getText();
				DB db = panel.getDBConfig();
				rutaProyecto = path + "/" + name;
				rutaSrc = rutaProyecto + "/src";
				rutaControllers = rutaProyecto + "/src/controllers";
				rutaModels = rutaProyecto + "/src/models";
				rutaRoutes = rutaProyecto + "/src/routes";
				rutaConfig = rutaProyecto + "/src/config";
				rutaMigrate = rutaProyecto + "/src/migrations";

				if (name.length() > 0 || path.length() > 0 || port.length() > 0) {

					JDialog progressDialog = new JDialog(frame, "Creando Proyecto", true);
					JProgressBar progressBar = new JProgressBar();
					progressBar.setIndeterminate(true);

					progressDialog.setLayout(new BorderLayout());
					progressDialog.add(new JLabel("Creando proyecto..."), BorderLayout.NORTH);
					progressDialog.add(progressBar, BorderLayout.CENTER);

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() {
							try {

								crearCarpeta(rutaProyecto);
								crearCarpeta(rutaSrc);
								crearCarpeta(rutaControllers);
								crearCarpeta(rutaModels);
								crearCarpeta(rutaRoutes);
								crearCarpeta(rutaConfig);
								crearCarpeta(rutaMigrate);

								ejecutarComando(rutaProyecto, "cmd", "/c", "npm", "init", "-y");
								ejecutarComando(rutaProyecto, "cmd", "/c", "npm", "install", "express", "cors",
										db.getDriver());

								crearArchivo(rutaConfig + "/dbConfig.js", DbConfig.getConfig(db));
								crearArchivo(rutaSrc + "/server.js", Server.getServer(port));
								crearArchivo(rutaRoutes + "routes.js",
										RouterMain.getRouter(getModules(rutaControllers)));

							} catch (Exception ex) {
								ex.printStackTrace();
							}
							return null;
						}

						@Override
						protected void done() {
							progressDialog.dispose();
							JOptionPane.showMessageDialog(frame, "Proyecto creado");
							manageProyect(rutaProyecto);
						}
					};

					worker.execute();

					progressDialog.setSize(300, 100);
					progressDialog.setLocationRelativeTo(frame);
					progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					progressDialog.setVisible(true);

				} else {
					// datos incompletos
				}
			}
		});
		panel.getBtnVolver().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MainPanel());
			}
		});

		frame.changePanel(panel);

	}

	private List<String> getModules(String carpetaPath) {

		List<String> modules = new ArrayList<>();

		File carpeta = new File(carpetaPath);
		File[] archivos = carpeta.listFiles();

		if (archivos != null) {
		
			for (File archivo : archivos) {	
	
				if (archivo.isFile() && archivo.getName().endsWith("Controller.js")) {
				
					String nombreSinExtension = archivo.getName().replace("Controller.js", "");
					modules.add(nombreSinExtension);
				}
			}
		}

		return modules;
	}

	private void manageProyect(String rutaProyecto) {
		proyectPanel = new ManageProyectPanel(rutaProyecto);
		proyectPanel.getBtnEjecutar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ejecutarComando(rutaProyecto, "cmd", "/c", "node src/server.js");
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		proyectPanel.getBtnCrearModulo().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				crearModulo();

			}
		});
		frame.changePanel(proyectPanel);
	}

	private void crearArchivo(String pathFile, String content) {

		try (FileWriter writer = new FileWriter(pathFile)) {
			writer.write(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void crearCarpeta(String ruta) throws IOException {
		File carpeta = new File(ruta);
		carpeta.mkdirs();
	}

	private static void ejecutarComando(String directorioTrabajo, String... comando)
			throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(comando);
		builder.directory(new File(directorioTrabajo));
		Process proceso = builder.start();
		int exitCode = proceso.waitFor();
		System.out.println("Comando ejecutado con código de salida: " + exitCode);
	}

	private void crearModulo() {

		CreateModulePanel createModulePanel = new CreateModulePanel();
		createModulePanel.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Module module = createModulePanel.getModule();

				crearArchivo(rutaControllers + "/" + module.getName() + "Controller.js",Controller.getController(module));
				crearArchivo(rutaModels + "/" + module.getName() + "Model.js", Model.getModel(module));
				crearArchivo(rutaRoutes + "/" + module.getName() + "Routes.js", Routes.getRoutes(module));	
				
				
				crearArchivo(rutaRoutes + "/" + "routes.js",RouterMain.getRouter(getModules(rutaControllers)));
				crearArchivo(rutaMigrate + "/" + module.getName() + "Migration.js", Migrate.getMigrate(module));
				proyectPanel.actualizarArchivos();
				try {
					// ejecutarComando(rutaProyecto, "cmd", "/c", "node " + rutaMigrate +
					// module.getName() + "Migration.js");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		proyectPanel.addManagePanel(createModulePanel);
	}
}
