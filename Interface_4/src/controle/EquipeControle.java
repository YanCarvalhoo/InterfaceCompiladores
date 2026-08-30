package controle;

/**
 * Controla a ação "equipe": monta a mensagem com os nomes dos integrantes.
 *
 * IMPORTANTE: troque os nomes abaixo pelos nomes reais da equipe antes de entregar.
 */
public class EquipeControle {

	private static final String[] INTEGRANTES = {
			"Yan Carvalho Medeiros",
			"Renan Phelipe"
			
	};

	public String getEquipe() {
		StringBuilder sb = new StringBuilder("Equipe de desenvolvimento:\n");
		for (String nome : INTEGRANTES) {
			sb.append("- ").append(nome).append("\n");
		}
		return sb.toString().trim();
	}
}
