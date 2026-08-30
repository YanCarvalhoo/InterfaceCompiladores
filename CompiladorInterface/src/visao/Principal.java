package visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import controle.ArquivoControle;
import controle.CompilacaoControle;
import controle.EquipeControle;
import modelo.ArquivoAberto;

/**
 * Janela principal da interface do compilador.
 * Monta o layout (toolbar + editor/mensagens + status), liga os botões e as
 * teclas de atalho às ações correspondentes.
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private final ArquivoAberto arquivoAtual = new ArquivoAberto();
	private final ArquivoControle arquivoControle = new ArquivoControle(arquivoAtual);
	private final CompilacaoControle compilacaoControle = new CompilacaoControle();
	private final EquipeControle equipeControle = new EquipeControle();

	private final PainelEditor painelEditor = new PainelEditor();
	private final PainelMensagens painelMensagens = new PainelMensagens();
	private final BarraStatus barraStatus = new BarraStatus(this::alternarTema);

	private PainelFerramentas painelFerramentas;
	private JSplitPane splitCentral;

	public Principal() {
		super("Interface do Compilador");

		montarLayout();
		configurarAtalhos();

		setSize(1500, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void montarLayout() {
		setLayout(new BorderLayout());

		painelFerramentas = new PainelFerramentas(new AcoesToolbar() {
			@Override public void novo() { acaoNovo(); }
			@Override public void abrir() { acaoAbrir(); }
			@Override public void salvar() { acaoSalvar(); }
			@Override public void copiar() { painelEditor.getEditor().copy(); }
			@Override public void colar() { painelEditor.getEditor().paste(); }
			@Override public void recortar() { painelEditor.getEditor().cut(); }
			@Override public void compilar() { acaoCompilar(); }
			@Override public void equipe() { acaoEquipe(); }
		});

		splitCentral = new JSplitPane(JSplitPane.VERTICAL_SPLIT, painelEditor, painelMensagens);
		splitCentral.setResizeWeight(0.75);
		splitCentral.setContinuousLayout(true);

		add(painelFerramentas, BorderLayout.WEST);
		add(splitCentral, BorderLayout.CENTER);
		add(barraStatus, BorderLayout.SOUTH);
	}

	private void configurarAtalhos() {
		JComponent conteudo = (JComponent) getContentPane();
		int janela = JComponent.WHEN_IN_FOCUSED_WINDOW;

		registrarAtalho(conteudo, janela, "control N", "acaoNovo", this::acaoNovo);
		registrarAtalho(conteudo, janela, "control O", "acaoAbrir", this::acaoAbrir);
		registrarAtalho(conteudo, janela, "control S", "acaoSalvar", this::acaoSalvar);
		registrarAtalho(conteudo, janela, "control C", "acaoCopiar", () -> painelEditor.getEditor().copy());
		registrarAtalho(conteudo, janela, "control V", "acaoColar", () -> painelEditor.getEditor().paste());
		registrarAtalho(conteudo, janela, "control X", "acaoRecortar", () -> painelEditor.getEditor().cut());
		registrarAtalho(conteudo, janela, "F7", "acaoCompilar", this::acaoCompilar);
		registrarAtalho(conteudo, janela, "F1", "acaoEquipe", this::acaoEquipe);
		registrarAtalho(conteudo, janela, "F12", "acaoAlternarTema", this::alternarTema);
	}

	private void registrarAtalho(JComponent componente, int condicao, String tecla, String nome, Runnable acao) {
		componente.getInputMap(condicao).put(KeyStroke.getKeyStroke(tecla), nome);
		componente.getActionMap().put(nome, new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				acao.run();
			}
		});
	}

	private void acaoNovo() {
		arquivoControle.novo();
		painelEditor.limpar();
		painelMensagens.limpar();
		barraStatus.limpar();
	}

	private void acaoAbrir() {
		String conteudo = arquivoControle.abrir(this);
		if (conteudo != null) {
			painelEditor.setTexto(conteudo);
			painelMensagens.limpar();
			barraStatus.atualizar(arquivoAtual.getCaminhoParaExibicao());
		}
		// se conteudo == null (cancelado), nada é alterado
	}

	private void acaoSalvar() {
		boolean eraNovo = arquivoAtual.isNovo();
		boolean salvou = arquivoControle.salvar(this, painelEditor.getTexto());
		if (salvou) {
			painelMensagens.limpar();
			if (eraNovo) {
				barraStatus.atualizar(arquivoAtual.getCaminhoParaExibicao());
			}
			// se já não era novo, a barra de status é mantida como estava
		}
	}

	private void acaoCompilar() {
		painelMensagens.mostrar(compilacaoControle.compilar());
	}

	private void acaoEquipe() {
		painelMensagens.mostrar(equipeControle.getEquipe());
	}

	/** Alterna entre tema claro e escuro e redesenha todos os painéis. */
	private void alternarTema() {
		Tema.alternar();
		painelFerramentas.aplicarTema();
		painelEditor.aplicarTema();
		painelMensagens.aplicarTema();
		barraStatus.aplicarTema();
		splitCentral.setBackground(Tema.fundoBarra());
		repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Principal janela = new Principal();
			janela.setVisible(true);
		});
	}
}
