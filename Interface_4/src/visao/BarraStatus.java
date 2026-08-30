package visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Barra inferior (altura 25) que mostra a pasta e o nome do arquivo aberto,
 * e também o botão de alternar entre tema claro e escuro.
 */
public class BarraStatus extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JLabel label;
	private final JButton botaoTema;

	public BarraStatus(Runnable aoClicarTema) {
		super(new BorderLayout());
		setPreferredSize(new Dimension(1, 25));
		setBorder(new EmptyBorder(2, 8, 2, 8));

		label = new JLabel(" ");
		label.setHorizontalAlignment(SwingConstants.LEFT);

		botaoTema = new JButton();
		botaoTema.setFocusPainted(false);
		botaoTema.setBorderPainted(false);
		botaoTema.setContentAreaFilled(false);
		botaoTema.addActionListener(e -> aoClicarTema.run());

		add(label, BorderLayout.WEST);
		add(botaoTema, BorderLayout.EAST);

		aplicarTema();
	}

	public void atualizar(String caminho) {
		label.setText(caminho == null || caminho.isEmpty() ? " " : caminho);
	}

	public void limpar() {
		atualizar(null);
	}

	/** Reaplica as cores do tema atual e o texto do botão (chamado ao trocar claro/escuro). */
	public void aplicarTema() {
		setBackground(Tema.fundoBarra());
		label.setForeground(Tema.textoPrincipal());
		botaoTema.setForeground(Tema.textoPrincipal());
		botaoTema.setText(Tema.isEscuro() ? "☀ Tema claro" : "☾ Tema escuro");
		repaint();
	}
}
