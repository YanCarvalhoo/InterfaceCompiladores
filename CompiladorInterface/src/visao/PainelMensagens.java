package visao;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Área de mensagens do compilador. Não é possível editar o texto aqui,
 * apenas visualizar mensagens geradas pelas ações da barra de ferramentas.
 */
public class PainelMensagens extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JTextArea mensagens;

	public PainelMensagens() {
		super(new BorderLayout());

		mensagens = new JTextArea();
		mensagens.setEditable(false);
		mensagens.setFont(new Font("Monospaced", Font.PLAIN, 13));

		JScrollPane scroll = new JScrollPane(mensagens,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		add(scroll, BorderLayout.CENTER);

		aplicarTema();
	}

	public void mostrar(String texto) {
		mensagens.setText(texto);
	}

	public void limpar() {
		mensagens.setText("");
	}

	/** Reaplica as cores do tema atual (chamado ao trocar claro/escuro). */
	public void aplicarTema() {
		mensagens.setBackground(Tema.editorFundo());
		mensagens.setForeground(Tema.editorTexto());
		mensagens.setCaretColor(Tema.editorTexto());
		mensagens.repaint();
	}
}
