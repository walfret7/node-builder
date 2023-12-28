package components;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class ManageProyectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelManage;
	private JButton btnEjecutar;
	private JButton btnCrearModulo;
	private JTree tree;
	private String path;
	
	public ManageProyectPanel(String path) {
		this.path = path;
		setLayout(new BorderLayout(0, 0));

		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				if (e.isAddedPath() && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					Object userObject = node.getUserObject();

					if (userObject instanceof File) {
						File selectedFile = (File) userObject;
						System.out.println("Doble clic en: " + selectedFile.getAbsolutePath());

					}
				}
			}
		});
		tree.setCellRenderer(new FileTreeCellRenderer());
		tree.setPreferredSize(new Dimension(200, Short.MIN_VALUE));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.NORTH);

		btnEjecutar = new JButton("Ejecutar Proyecto");
		panel_1.add(btnEjecutar);

		btnCrearModulo = new JButton("Crear Modulo");
		panel_1.add(btnCrearModulo);

		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.WEST);

		panelManage = new JPanel();
		panelManage.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panelManage, BorderLayout.CENTER);
		panelManage.setLayout(new BoxLayout(panelManage, BoxLayout.X_AXIS));
	
		actualizarArchivos();
	}

	public void actualizarArchivos() {
		File rootFile = new File(path);
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new FileNode(rootFile));
		buildFileTree(rootFile, rootNode);
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		
	}

	public JButton getBtnCrearModulo() {
		return btnCrearModulo;
	}

	public void addManagePanel(JPanel panel) {
		panelManage.removeAll();
		panelManage.add(panel);

		panelManage.updateUI();
		panelManage.repaint();
		panelManage.revalidate();

	}

	public JButton getBtnEjecutar() {
		return btnEjecutar;
	}

	private void buildFileTree(File file, DefaultMutableTreeNode parentNode) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File child : files) {
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(child));
					parentNode.add(childNode);
					buildFileTree(child, childNode);
				}
			}
		}
	}

}

class FileNode {
	private final File file;

	public FileNode(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return file.getName();
	}

	public File getFile() {
		return file;
	}
}

class FileTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		// Personaliza el icono según si es un archivo o una carpeta
		if (value instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Object userObject = node.getUserObject();
			if (userObject instanceof FileNode) {
				FileNode fileNode = (FileNode) userObject;
				if (fileNode.getFile().isDirectory()) {
					setIcon(UIManager.getIcon("FileView.directoryIcon"));
				} else {
					setIcon(UIManager.getIcon("FileView.fileIcon"));
				}
			}
		}

		return this;
	}
}
