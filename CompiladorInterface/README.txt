INTERFACE DO COMPILADOR — PARTE 1
===================================

COMO ABRIR NO ECLIPSE
----------------------
File > Open Projects from File System... > selecione a pasta "CompiladorInterface"
(o .project e o .classpath já deixam o projeto pronto para rodar).
Rode a classe visao.Principal (botão direito > Run As > Java Application).

COMO ABRIR SÓ CLICANDO (SEM ECLIPSE)
---------------------------------------
Dê duplo clique em "Abrir Compilador.bat".

Se aparecer um aviso dizendo que o Java não foi encontrado, é porque o Java
ainda não está instalado (ou não está no PATH) nesse computador. Baixe e
instale o JDK em https://adoptium.net (versão 17 ou mais nova), e na
instalação deixe marcada a opção "Add to PATH". Depois é só clicar de novo
no .bat.

Se preferir, também dá pra tentar clicar direto em CompiladorInterface.jar —
funciona se o Java estiver instalado e associado a arquivos .jar. Caso nada
aconteça ao clicar, use o .bat.

COMO RODAR PELO TERMINAL
--------------------------
java -jar CompiladorInterface.jar

ANTES DE ENTREGAR, AJUSTE:
-----------------------------
1) src/controle/EquipeControle.java
   Troque "Nome do integrante 1/2/3" pelos nomes reais da equipe.

2) Emojis dos botões (src/visao/PainelFerramentas.java)
   Os ícones são emojis de verdade (🆕 📂 💾 📋 📥 ✂️ ▶️ 👥), embutidos no
   texto de cada botão, forçando a fonte "Segoe UI Emoji" (a fonte de emoji
   colorida nativa do Windows). No meu ambiente de teste (Linux) eles só
   aparecem em preto e branco, porque o Linux não tem essa fonte — isso é
   uma limitação do ambiente de teste, não do código. No Windows de vocês
   devem aparecer coloridos. Se em algum Windows mais antigo/sem essa fonte
   ainda aparecer preto, me avisem que troco pra ícones desenhados (imagem)
   em vez de depender de fonte de emoji.

TEMA CLARO / ESCURO
----------------------
Tem um botão "☾ Tema escuro" no canto direito da barra de status — clique
pra alternar. Também dá pra usar a tecla F12 em qualquer lugar da janela.
Isso não é exigido pelo PDF, mas não interfere em nenhum item avaliado
(a interface abre sempre no tema claro por padrão).

ESTRUTURA DO PROJETO
-----------------------
src/
├── modelo/
│   └── ArquivoAberto.java        -> guarda pasta/nome do arquivo aberto
├── controle/
│   ├── ArquivoControle.java      -> novo / abrir / salvar (leitura e escrita em disco)
│   ├── CompilacaoControle.java   -> mensagem do botão compilar
│   └── EquipeControle.java       -> mensagem do botão equipe (AJUSTAR NOMES)
└── visao/
    ├── Principal.java            -> janela principal, monta o layout, atalhos e o toggle de tema
    ├── AcoesToolbar.java         -> interface com as 8 ações dos botões
    ├── PainelFerramentas.java    -> barra de ferramentas (150px, 8 botões, emojis)
    ├── PainelEditor.java         -> editor + numeração de linha
    ├── PainelMensagens.java      -> área de mensagens (somente leitura)
    ├── BarraStatus.java          -> barra de status + botão de alternar tema
    ├── Tema.java                 -> cores claro/escuro centralizadas
    └── NumberedBorder.java       -> a classe que vocês já tinham, movida para o pacote visao

O QUE JÁ FOI TESTADO
-----------------------
O projeto foi compilado (javac) e executado de verdade em um display virtual
(Xvfb) para conferir que a janela abre sem erros e que o layout bate com a
Figura 1 do PDF (toolbar à esquerda, editor com numeração de linha e
scrollbars sempre visíveis, divisor entre editor/mensagens, barra de status).
Novo/abrir/salvar/copiar/colar/recortar/compilar/equipe foram implementados
conforme os itens 10 a 15 da especificação. Mesmo assim, testem manualmente
no Windows antes de entregar (principalmente abrir/salvar arquivos .txt).

PONTOS QUE FICARAM COMO DECISÃO MINHA (confirmem se fizer sentido)
----------------------------------------------------------------------
- Pacotes na raiz do src (modelo/controle/visao), sem o prefixo "compilador"
  que estava no NumberedBorder.java original.
- Atalhos seguem exatamente o PDF: ctrl-n, ctrl-o, ctrl-s, ctrl-c, ctrl-v,
  ctrl-x, F7 e F1 — nenhum atalho extra foi inventado.

CHECAGEM ITEM A ITEM DO PDF (parte 1 - interface)
----------------------------------------------------
1. Janela fixa 1500x800, não redimensionável, minimiza/fecha       -> OK
2. Toolbar 150xn, editor, mensagens, status na disposição da Fig.1 -> OK
3. Divisor arrastável entre editor e mensagens                     -> OK
4. Numeração de linha à esquerda, começa em 1, não editável        -> OK
5. Scrollbars do editor sempre visíveis                            -> OK
6. Área de mensagens não editável                                  -> OK
7. Scrollbars da área de mensagens sempre visíveis                 -> OK
8. Status mostra pasta\nome do arquivo aberto                      -> OK
9. 8 botões, mesmo tamanho, ordem certa, ícone+nome+atalho         -> OK
10. Novo: limpa editor, mensagens e status                         -> OK
11. Abrir: carrega .txt / cancelar não altera nada                 -> OK
12. Salvar: pede local se novo / grava direto se já existe         -> OK
13. Copiar/colar/recortar padrão de editor de texto                -> OK
14. Compilar: mensagem fixa, substitui a anterior                  -> OK
15. Equipe: mostra nomes da equipe, substitui a anterior           -> OK (nomes ainda placeholder, ver item 1 acima)
