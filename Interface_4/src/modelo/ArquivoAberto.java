package modelo;

import java.io.File;

/**
 * Representa o arquivo que está atualmente aberto no editor.
 * Enquanto o arquivo ainda não foi salvo em disco, ele é considerado "novo"
 * (isNovo() retorna true) e não tem pasta/nome definidos.
 */
public class ArquivoAberto {

	private String pasta;
	private String nome;
	private boolean novo;

	public ArquivoAberto() {
		limpar();
	}

	/** Esquece a pasta e o nome do arquivo editado (volta ao estado inicial). */
	public void limpar() {
		pasta = null;
		nome = null;
		novo = true;
	}

	/** Associa este objeto a um arquivo salvo/aberto em disco. */
	public void definir(File arquivo) {
		this.pasta = arquivo.getParent();
		this.nome = arquivo.getName();
		this.novo = false;
	}

	public boolean isNovo() {
		return novo;
	}

	public String getPasta() {
		return pasta;
	}

	public String getNome() {
		return nome;
	}

	public File getArquivo() {
		if (novo) {
			return null;
		}
		return new File(pasta, nome);
	}

	/** Texto exibido na barra de status, no formato pasta\nome. */
	public String getCaminhoParaExibicao() {
		if (novo || pasta == null) {
			return "";
		}
		return pasta + File.separator + nome;
	}
}
