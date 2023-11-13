// Representa um jogador no jogo
// Pode ter atributos como nome, pontucao, etc.
public class Jogador {
    private String nome;
    private int pontuacao;

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    // Métodos para manipular a pontuação
    public void aumentarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    public int obterPontuacao() {
        return pontuacao;
    }
}
