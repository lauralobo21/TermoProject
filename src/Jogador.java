// Representa um jogador no jogo.
// Pode ter atributos como nome, pontucao, etc.
public class Jogador extends Salvavel {
    private String nome;
    private int pontuacao;

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = Ranking.getPontuacao(nome);
    }

    // Métodos para manipular a pontuação.
    public void salvar() {
        Ranking.setPontuacao(nome, pontuacao);
    }

    public void incrementarPontuacao() {
        this.pontuacao += 10;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

}
