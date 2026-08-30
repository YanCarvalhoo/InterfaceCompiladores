package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Barra de ferramentas vertical (150 de largura), com os botões de ação do
 * compilador. A ordem dos botões segue exatamente a especificação: novo,
 * abrir, salvar, copiar, colar, recortar, compilar, equipe.
 *
 * O ícone de cada botão é um emoji de verdade, embutido no texto (HTML) do
 * botão. O Swing escolhe automaticamente uma fonte que tenha o glifo do
 * emoji; no Windows normalmente é a Segoe UI Emoji (colorida).
 */
public class PainelFerramentas extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Dimension TAMANHO_BOTAO = new Dimension(130, 58);

	private final List<BotaoInfo> botoes = new ArrayList<>();

	public PainelFerramentas(AcoesToolbar acoes) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		setPreferredSize(new Dimension(150, 0));

		// manipulação de arquivos
		adicionarBotao("\uD83C\uDD95", "novo", "ctrl-n", acoes::novo);
		adicionarBotao("\uD83D\uDCC2", "abrir", "ctrl-o", acoes::abrir);
		adicionarBotao("\uD83D\uDCBE", "salvar", "ctrl-s", acoes::salvar);
		adicionarEspaco();

		// edição de texto
		adicionarBotao("\uD83D\uDCCB", "copiar", "ctrl-c", acoes::copiar);
		adicionarBotao("\uD83D\uDCE5", "colar", "ctrl-v", acoes::colar);
		adicionarBotao("\u2702\uFE0F", "recortar", "ctrl-x", acoes::recortar);
		adicionarEspaco();

		// compilação
		adicionarBotao("\u25B6\uFE0F", "compilar", "F7", acoes::compilar);

		// informações da equipe
		adicionarBotao("\uD83D\uDC65", "equipe", "F1", acoes::equipe);

		aplicarTema();
	}

	private void adicionarEspaco() {
		add(Box.createVerticalStrut(10));
	}

	private void adicionarBotao(String emoji, String nome, String atalho, Runnable acao) {
		JButton botao = new JButton();
		botao.setHorizontalAlignment(SwingConstants.LEFT);
		botao.setFocusPainted(false);
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		botao.setMaximumSize(TAMANHO_BOTAO);
		botao.setPreferredSize(TAMANHO_BOTAO);
		botao.addActionListener(e -> acao.run());

		add(botao);
		add(Box.createVerticalStrut(6));

		botoes.add(new BotaoInfo(botao, emoji, nome, atalho));
	}

	/** Reaplica as cores do tema atual em todos os botões (chamado ao trocar claro/escuro). */
	public void aplicarTema() {
		setBackground(Tema.fundoBarra());

		String corNome = corHex(Tema.textoPrincipal());
		String corAtalho = corHex(Tema.textoSecundario());

		for (BotaoInfo info : botoes) {
			info.botao.setBackground(Tema.fundoBotao());
			info.botao.setText("<html><span style='font-family:\"Segoe UI Emoji\";font-size:16px;'>"
					+ info.emoji + "</span>&nbsp;&nbsp;<font color='" + corNome + "'>" + info.nome
					+ "</font><br><font size=-2 color='" + corAtalho + "'>[" + info.atalho + "]</font></html>");
		}
		repaint();
	}

	private static String corHex(Color c) {
		return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	}

	private static class BotaoInfo {
		final JButton botao;
		final String emoji;
		final String nome;
		final String atalho;

		BotaoInfo(JButton botao, String emoji, String nome, String atalho) {
			this.botao = botao;
			this.emoji = emoji;
			this.nome = nome;
			this.atalho = atalho;
		}
	}
}
