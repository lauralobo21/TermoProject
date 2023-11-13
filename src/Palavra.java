// Representa uma palavra no jogo
public class Palavra {
    private String palavra;
    private String dica;

    // Construtor
    public Palavra(String palavra, String dica) {
        this.palavra = palavra;
        this.dica = dica;
    }

    // Métodos para obter palavra e dica
    public String obterPalavra() {
        return palavra;
    }

    public String obterDica() {
        return dica;
    }
}
