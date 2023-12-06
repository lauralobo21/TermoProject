// Representa um jogador no jogo.
public class Jogador extends Salvavel {
    // Atributos
    private String nome;
    private int pontuacao;

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        //pega a pontuação atual do ranking
        this.pontuacao = Ranking.getPontuacao(nome);
    }

    // Métodos para manipular a pontuação.
    public void salvar() {
        // salva o nome do jogador e a pontuaçao dele no ranking
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
