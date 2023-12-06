import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.lang.Math;

public class Dicionario {
    // Atributo
    private ArrayList<Palavra> palavras; 

    // Construtor
    public Dicionario() {
        this.palavras = new ArrayList<Palavra>();
        carregarPalavras();
    }

    // Método para carregar as palavras do dicionario.txt
    private void carregarPalavras() {
        // Lendo o arquivo Dicionario.txt
        File arquivo = new File("Dicionario.txt");
        String textoArquivo = "";
        try {
            //convertendo pra string
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); 
        } catch(Exception excecao) {
            System.out.println("Arquivo invalido");
        }
        //criando uma nova palavra e add no dicionario
        String[] linhas = textoArquivo.split("\n");
        for(int i = 0; i < linhas.length; i++) {
            palavras.add(new Palavra(linhas[i]));
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
