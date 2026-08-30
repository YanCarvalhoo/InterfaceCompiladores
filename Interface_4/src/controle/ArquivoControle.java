package controle;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.ArquivoAberto;

/**
 * Controla as operações de arquivo do editor: novo, abrir e salvar.
 * Toda a leitura/escrita em disco fica concentrada aqui.
 */
public class ArquivoControle {

	private final ArquivoAberto arquivoAtual;

	public ArquivoControle(ArquivoAberto arquivoAtual) {
		this.arquivoAtual = arquivoAtual;
	}

	/** Esquece o arquivo atual (usado pelo botão "novo"). */
	public void novo() {
		arquivoAtual.limpar();
	}

	/**
	 * Abre um arquivo .txt escolhido pelo usuário.
	 *
	 * @return o conteúdo do arquivo lido, ou null se o usuário cancelou a seleção
	 *         (nesse caso nada no editor deve ser alterado).
	 */
	public String abrir(Component pai) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Abrir arquivo");
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));

		int opcao = chooser.showOpenDialog(pai);
		if (opcao != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		File arquivo = chooser.getSelectedFile();
		try {
			String conteudo = new String(Files.readAllBytes(arquivo.toPath()), StandardCharsets.UTF_8);
			arquivoAtual.definir(arquivo);
			return conteudo;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(pai, "Não foi possível abrir o arquivo selecionado.",
					"Erro ao abrir", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * Salva o conteúdo do editor. Se o arquivo atual ainda é novo, pede para o
	 * usuário escolher pasta/nome; caso contrário grava direto no arquivo já
	 * associado.
	 *
	 * @return true se algo foi realmente salvo em disco.
	 */
	public boolean salvar(Component pai, String conteudo) {
		if (arquivoAtual.isNovo()) {
			return salvarComo(pai, conteudo);
		}
		return gravar(pai, arquivoAtual.getArquivo(), conteudo);
	}

	private boolean salvarComo(Component pai, String conteudo) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Salvar arquivo");
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));

		int opcao = chooser.showSaveDialog(pai);
		if (opcao != JFileChooser.APPROVE_OPTION) {
			return false;
		}

		File arquivo = chooser.getSelectedFile();
		if (!arquivo.getName().toLowerCase().endsWith(".txt")) {
			arquivo = new File(arquivo.getParentFile(), arquivo.getName() + ".txt");
		}

		if (gravar(pai, arquivo, conteudo)) {
			arquivoAtual.definir(arquivo);
			return true;
		}
		return false;
	}

	private boolean gravar(Component pai, File arquivo, String conteudo) {
		try {
			Files.write(arquivo.toPath(), conteudo.getBytes(StandardCharsets.UTF_8));
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(pai, "Não foi possível salvar o arquivo.",
					"Erro ao salvar", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
