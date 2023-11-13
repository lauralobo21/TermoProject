
// Responsável por controlar o estado do jogo, gerenciar os jogadores
// escolher palavras, etc;

import java.util.List;
import java.util.Random;

public class JogoController {
    private List<Jogador> jogadores;
    private Palavra palavraAtual;

    // Construtor
    public JogoController(List<Jogador> jogadores, Palavra palavra) {
        this.jogadores = jogadores;
        this.palavraAtual = palavra;
    }

    public void iniciarRodada() {
        System.out.println("Iniciando uma nova rodada");

        // Lógica para escolher uma nova palavra aleatória (isso pode depender de como eu estou 
        // gerenciando as palavras).
        // Exemplo: escolher aleatoriamente uma palavra de uma lista predefinida
        String[] palavras = {"java", "programacao", "objetos", "classe", "metodo"};
        Random random = new Random();
        int indicePalavra = random.nextInt(palavras.length);
        String palavraEscolhida = palavras[indicePalavra];

        // Configurar a nova palavra para a rodada
        this.palavraAtual = new Palavra(palvaraEscolhida, "Dica para a palavra");

        // Notificar os jogadores sobre a nova rodada e a dica
        System.out.println("Dica: " + this.palavraAtual.obterDica());

        // Mostrar a palavra com espaços em branco para as letras não adivinhadas
        System.out.println("Palavra atual: " + palavraEscolhida.replaceAll(".", "_"));
    }

    public void verificarPalpite() {
        // Lógica para verificar se o palpite está certo
        String palavraAtual = this.palavraAtual.obterPalavra().toLowerCase();

        if(palavraAtual.contains(palpite.toLowerCase())) {
            // Atualizar a pontuação do jogador
            for( Jogador jogador : jogadores) {
                jogador.aumentarPontuacao(1);   // Aumentar a potuação por acerto
                System.out.println("Palpite correto! " + jogador.obterPontuacao() + " ponto(s).");

            }
        } else {
            System.out.println("Palpite incorreto");
        }
    }
}
