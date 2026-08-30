package visao;

import java.awt.Color;
import java.awt.Font;

/**
 * Centraliza as cores e fontes usadas na interface, com suporte a tema
 * claro e escuro. Chame Tema.alternar() e depois peça para cada painel
 * chamar seu próprio aplicarTema() para redesenhar com as cores novas.
 */
public final class Tema {

	private static boolean escuro = false;

	// tema claro
	private static final Color FUNDO_BARRA_CLARO = new Color(230, 230, 230);
	private static final Color BOTAO_FUNDO_CLARO = new Color(248, 248, 248);
	private static final Color TEXTO_PRINCIPAL_CLARO = new Color(20, 20, 20);
	private static final Color TEXTO_SECUNDARIO_CLARO = new Color(110, 110, 110);
	private static final Color EDITOR_FUNDO_CLARO = Color.WHITE;
	private static final Color EDITOR_TEXTO_CLARO = Color.BLACK;
	private static final Color NUMERACAO_CLARO = new Color(164, 164, 164);

	// tema escuro
	private static final Color FUNDO_BARRA_ESCURO = new Color(45, 45, 45);
	private static final Color BOTAO_FUNDO_ESCURO = new Color(62, 62, 62);
	private static final Color TEXTO_PRINCIPAL_ESCURO = new Color(230, 230, 230);
	private static final Color TEXTO_SECUNDARIO_ESCURO = new Color(170, 170, 170);
	private static final Color EDITOR_FUNDO_ESCURO = new Color(30, 30, 30);
	private static final Color EDITOR_TEXTO_ESCURO = new Color(220, 220, 220);
	private static final Color NUMERACAO_ESCURO = new Color(130, 130, 130);

	public static final Font FONTE_BOTAO = new Font("SansSerif", Font.PLAIN, 12);

	private Tema() {
	}

	public static boolean isEscuro() {
		return escuro;
	}

	public static void alternar() {
		escuro = !escuro;
	}

	public static Color fundoBarra() {
		return escuro ? FUNDO_BARRA_ESCURO : FUNDO_BARRA_CLARO;
	}

	public static Color fundoBotao() {
		return escuro ? BOTAO_FUNDO_ESCURO : BOTAO_FUNDO_CLARO;
	}

	public static Color textoPrincipal() {
		return escuro ? TEXTO_PRINCIPAL_ESCURO : TEXTO_PRINCIPAL_CLARO;
	}

	public static Color textoSecundario() {
		return escuro ? TEXTO_SECUNDARIO_ESCURO : TEXTO_SECUNDARIO_CLARO;
	}

	public static Color editorFundo() {
		return escuro ? EDITOR_FUNDO_ESCURO : EDITOR_FUNDO_CLARO;
	}

	public static Color editorTexto() {
		return escuro ? EDITOR_TEXTO_ESCURO : EDITOR_TEXTO_CLARO;
	}

	public static Color numeracao() {
		return escuro ? NUMERACAO_ESCURO : NUMERACAO_CLARO;
	}
}
