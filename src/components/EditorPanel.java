package components;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import java.awt.Color;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean isEditing = false;
	private String filePath;
	private RSyntaxTextArea syntaxTextArea;
	private String contenido = null;
	private JButton btnGuardar;

	public EditorPanel(String filePath) {
		this.filePath = filePath;
		try {
			contenido = new String(Files.readAllBytes(new File(filePath).toPath()));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setMaximumSize(new Dimension(32767, 80));
		add(panel);

		btnGuardar = new JButton("Guardar");
		panel.add(btnGuardar);

		syntaxTextArea = new RSyntaxTextArea();
		syntaxTextArea.setSyntaxEditingStyle(getSyntax(filePath));
		syntaxTextArea.setCodeFoldingEnabled(true);
		syntaxTextArea.setText(contenido);
		syntaxTextArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				isEditing = true;
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				isEditing = true;

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		RTextScrollPane textScrollPane = new RTextScrollPane(syntaxTextArea);
		textScrollPane.setBorder(new EmptyBorder(0, 5, 0, 5));

		add(textScrollPane);
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	private String getSyntax(String fileName) {

		String fileExtension = getFileExtension(fileName);

		switch (fileExtension.toLowerCase()) {
		case "as":
			return SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT;
		case "asm":
			return SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86;
		case "asm6502":
			return SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_6502;
		case "bbcode":
			return SyntaxConstants.SYNTAX_STYLE_BBCODE;
		case "c":
			return SyntaxConstants.SYNTAX_STYLE_C;
		case "clojure":
			return SyntaxConstants.SYNTAX_STYLE_CLOJURE;
		case "cpp":
			return SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS;
		case "cs":
			return SyntaxConstants.SYNTAX_STYLE_CSHARP;
		case "css":
			return SyntaxConstants.SYNTAX_STYLE_CSS;
		case "csv":
			return SyntaxConstants.SYNTAX_STYLE_CSV;
		case "d":
			return SyntaxConstants.SYNTAX_STYLE_D;
		case "dockerfile":
			return SyntaxConstants.SYNTAX_STYLE_DOCKERFILE;
		case "dart":
			return SyntaxConstants.SYNTAX_STYLE_DART;
		case "delphi":
			return SyntaxConstants.SYNTAX_STYLE_DELPHI;
		case "dtd":
			return SyntaxConstants.SYNTAX_STYLE_DTD;
		case "fortran":
			return SyntaxConstants.SYNTAX_STYLE_FORTRAN;
		case "golang":
			return SyntaxConstants.SYNTAX_STYLE_GO;
		case "groovy":
			return SyntaxConstants.SYNTAX_STYLE_GROOVY;
		case "handlebars":
			return SyntaxConstants.SYNTAX_STYLE_HANDLEBARS;
		case "hosts":
			return SyntaxConstants.SYNTAX_STYLE_HOSTS;
		case "htaccess":
			return SyntaxConstants.SYNTAX_STYLE_HTACCESS;
		case "html":
			return SyntaxConstants.SYNTAX_STYLE_HTML;
		case "ini":
			return SyntaxConstants.SYNTAX_STYLE_INI;
		case "java":
			return SyntaxConstants.SYNTAX_STYLE_JAVA;
		case "js":
		case "javascript":
			return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
		case "json":
			return SyntaxConstants.SYNTAX_STYLE_JSON;
		case "jshintrc":
			return SyntaxConstants.SYNTAX_STYLE_JSON_WITH_COMMENTS;
		case "jsp":
			return SyntaxConstants.SYNTAX_STYLE_JSP;
		case "kotlin":
			return SyntaxConstants.SYNTAX_STYLE_KOTLIN;
		case "latex":
			return SyntaxConstants.SYNTAX_STYLE_LATEX;
		case "less":
			return SyntaxConstants.SYNTAX_STYLE_LESS;
		case "lisp":
			return SyntaxConstants.SYNTAX_STYLE_LISP;
		case "lua":
			return SyntaxConstants.SYNTAX_STYLE_LUA;
		case "makefile":
			return SyntaxConstants.SYNTAX_STYLE_MAKEFILE;
		case "markdown":
			return SyntaxConstants.SYNTAX_STYLE_MARKDOWN;
		case "mxml":
			return SyntaxConstants.SYNTAX_STYLE_MXML;
		case "nsis":
			return SyntaxConstants.SYNTAX_STYLE_NSIS;
		case "perl":
			return SyntaxConstants.SYNTAX_STYLE_PERL;
		case "php":
			return SyntaxConstants.SYNTAX_STYLE_PHP;
		case "proto":
			return SyntaxConstants.SYNTAX_STYLE_PROTO;
		case "properties":
			return SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE;
		case "py":
		case "python":
			return SyntaxConstants.SYNTAX_STYLE_PYTHON;
		case "ruby":
			return SyntaxConstants.SYNTAX_STYLE_RUBY;
		case "sas":
			return SyntaxConstants.SYNTAX_STYLE_SAS;
		case "scala":
			return SyntaxConstants.SYNTAX_STYLE_SCALA;
		case "sql":
			return SyntaxConstants.SYNTAX_STYLE_SQL;
		case "tcl":
			return SyntaxConstants.SYNTAX_STYLE_TCL;
		case "typescript":
		case "ts":
			return SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT;
		case "unix":
			return SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL;
		case "vb":
			return SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC;
		case "bat":
			return SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH;
		case "xml":
			return SyntaxConstants.SYNTAX_STYLE_XML;
		case "yaml":
			return SyntaxConstants.SYNTAX_STYLE_YAML;
		default:
			return SyntaxConstants.SYNTAX_STYLE_NONE;

		}
	}

	private String getFileExtension(String fileName) {
		int lastDotIndex = fileName.lastIndexOf(".");
		if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
			return fileName.substring(lastDotIndex + 1);
		} else {
			return ""; // No hay extensión de archivo
		}
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public String getFilePath() {
		return this.filePath;
	}
	
	public void guardarCambios() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			contenido = syntaxTextArea.getText();
			writer.write(contenido);
			isEditing = false;
			JOptionPane.showMessageDialog(this, "Guardado correctamente");

		} catch (IOException e) {
			isEditing = true;
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar cambios.");
		}

	}

}
