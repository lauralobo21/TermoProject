import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.lang.Math;

public class Dicionario {
    private ArrayList<Palavra> palavras;

    public Dicionario() {
        this.palavras = new ArrayList<Palavra>();
        carregarPalavras();
    }

    private void carregarPalavras() {
        // Lendo o arquivo Ranking.txt
        File arquivo = new File("Dicionario.txt");
        String textoArquivo = "";
        try {
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); //convertendo pra string
        } catch(Exception excecao) {
            System.out.println("Arquivo invalido");
        }
        String[] linhas = textoArquivo.split("\n");
        for(int i = 0; i < linhas.length; i++) {
            palavras.add(new Palavra(linhas[i])); //criando uma nova palavra e add no dicionario
        }
    }

    public Palavra getPalavraAleatoria() {
        // criando numero aleatorio
        int max = palavras.size() - 1;
        int min = 0;
        int intervalo = max - min + 1;
        int numRandom = (int)(Math.random() * intervalo) + min;
        return palavras.get(numRandom);
    }
}
