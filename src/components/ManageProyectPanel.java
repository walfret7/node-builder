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
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

public class ManageProyectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnEjecutar;
	private JButton btnCrearModulo;
	private JTree tree;
	private String path;
	private JTabbedPane panelManage;
	private List<String> filesEditing;

	public ManageProyectPanel(String path) {
		this.path = path;
		this.filesEditing = new ArrayList<String>();

		setLayout(new BorderLayout(0, 0));

		tree = new JTree();
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

		panelManage = new JTabbedPane(JTabbedPane.TOP);
		add(panelManage, BorderLayout.CENTER);

		actualizarArchivos();
	}

	public File getSelectedFile() {
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			Object userObject = node.getUserObject();

			if (userObject instanceof FileNode) {
				FileNode fileNode = (FileNode) userObject;
				File selectedFile = fileNode.getFile();
				return selectedFile;
			}
		}

		return null;
	}

	public JTree getTree() {
		return tree;
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

	public void addManagePanel(String title, JPanel panel) {

		String filePath = title.equals("Crear Modulo") ? title : ((EditorPanel) panel).getFilePath();

		if (filesEditing.contains(filePath)) {
			panelManage.setSelectedIndex(filesEditing.indexOf(filePath));
			return;
		}

		panelManage.addTab(title, panel);
		filesEditing.add(filePath);
		panelManage.setSelectedIndex(filesEditing.indexOf(filePath));

		int index = panelManage.indexOfTab(title);
		panelManage.setTabComponentAt(index, new ClosableTabComponent(panelManage, this.filesEditing));

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

	public List<String> getFilesEditing() {
		return this.filesEditing;
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

class ClosableTabComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTabbedPane tabbedPane;

	public ClosableTabComponent(final JTabbedPane tabbedPane, List<String> filesEditing) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.tabbedPane = tabbedPane;
		setOpaque(false);

		JLabel label = new JLabel() {

			private static final long serialVersionUID = 1L;

			public String getText() {
				int i = tabbedPane.indexOfTabComponent(ClosableTabComponent.this);
				if (i != -1) {
					return tabbedPane.getTitleAt(i);
				}
				return null;
			}
		};

		add(label);

		JButton closeButton = new JButton("x");
		closeButton.setPreferredSize(new Dimension(20, 20));
		closeButton.setBorder(null);
		closeButton.setBorderPainted(false);
		closeButton.setFocusPainted(false);
		closeButton.setContentAreaFilled(false);

		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				closeButton.setContentAreaFilled(true);
				closeButton.setBackground(Color.lightGray); // Puedes ajustar el color según tus preferencias
			}

			public void mouseExited(MouseEvent e) {
				closeButton.setContentAreaFilled(false);
			}
		});

		closeButton.addActionListener(e -> {
			int index = tabbedPane.indexOfTabComponent(ClosableTabComponent.this);
			filesEditing.remove(index);
			if (index != -1) {
				tabbedPane.remove(index);
			}
		});
		add(closeButton);
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
