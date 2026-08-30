package visao;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Área de edição de programas. Mostra a numeração de linha (via
 * NumberedBorder) e mantém as barras de rolagem sempre visíveis, mesmo sem
 * texto editado.
 */
public class PainelEditor extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JTextArea editor;

	public PainelEditor() {
		super(new BorderLayout());

		editor = new JTextArea();
		editor.setFont(new Font("Monospaced", Font.PLAIN, 14));
		editor.setLineWrap(false);
		editor.setBorder(new NumberedBorder());

		JScrollPane scroll = new JScrollPane(editor,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		add(scroll, BorderLayout.CENTER);

		aplicarTema();
	}

	public JTextArea getEditor() {
		return editor;
	}

	public String getTexto() {
		return editor.getText();
	}

	public void setTexto(String texto) {
		editor.setText(texto);
		editor.setCaretPosition(0);
	}

	public void limpar() {
		editor.setText("");
	}

	/** Reaplica as cores do tema atual (chamado ao trocar claro/escuro). */
	public void aplicarTema() {
		editor.setBackground(Tema.editorFundo());
		editor.setForeground(Tema.editorTexto());
		editor.setCaretColor(Tema.editorTexto());
		editor.repaint();
	}
}
