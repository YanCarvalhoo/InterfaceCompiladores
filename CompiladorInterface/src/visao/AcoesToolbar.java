package visao;

/**
 * Ações disparadas pelos botões da barra de ferramentas.
 * A janela Principal implementa esta interface para receber os cliques.
 */
public interface AcoesToolbar {
	void novo();
	void abrir();
	void salvar();
	void copiar();
	void colar();
	void recortar();
	void compilar();
	void equipe();
}
