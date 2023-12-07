// PRONTA
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

public class Ranking {
    // Métodos
    public static int getPontuacao(String nome) {
        // Lendo o arquivo Ranking.txt
        File arquivo = new File("Ranking.txt");
        if(!arquivo.exists()) {
            return 0;
        }
        String textoArquivo = "";
        try {
            //convertendo pra string
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); 
        } catch(Exception excecao) {
            System.out.println("Erro ao ler o arquivo Ranking.txt");
        }

        // para identificar o nome procurado
        //linha[0] = "Laura 30"
        //linha[1] = "Pedro 60"
        // split = sempre que encontra \n quebra em dois
        String[] linhas = textoArquivo.split("\n");
        for(int i = 0; i<linhas.length; i++) {
            //lê até encontrar um espaço, que será o nome 
            String[] registro = linhas[i].split(" ");
            if(nome.equals(registro[0])) {
                // mudando registro para int, pq é uma string
                return Integer.parseInt(registro[1]); 
            }
        }
        //novo jogador que não esteja no ranking recebe pontuaçao 0
        return 0;
    }

    public static void setPontuacao(String nome, int novaPontuacao) {
        //percorre o arquivo até achar o nome e muda a pontuação.
        //cria um novo temp.txt vazio.
        //abre o ranking.txt, quebra em linhas depois percorre cada linha
        //quebrando cada uma em registros e verifica:
        // se (nome == registro[0]) {
        //   registro[1] = pontuacao
        // escreve no temp.txt o registro (nome e pontuacao)
        // else { escrevo o registro no temp.txt}
        //apagar o ranking.txt
        //renomear o temp.txt para o ranking.txt
        
        //abre o arquivo
        File arquivo = new File("Ranking.txt");
        
        // se não existir um ranking
        // cria um novo arquivo
        if(!arquivo.exists()) {
            try {
                //tenta criar um novo arquivo
                arquivo.createNewFile();
                FileWriter escritor = new FileWriter(arquivo);
                escritor.write(nome + " " + novaPontuacao + "\n");
                escritor.close();
            } catch(Exception excecao) {
                System.out.println("Erro ao criar o arquivo Ranking.txt");
            }
            return;
        }

        // caso o arquivo existir
        File arquivoTemp = new File("Temp.txt");
        try {
            //cria um arquivo temporario vazio
            arquivoTemp.createNewFile();
        } catch(Exception excecao) {
            System.out.println("Erro ao criar o arquivo Temp.txt");
        }

        String textoArquivo = "";
        try {
            //lê todo texto que tem dentro do ranking.txt
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); //convertendo pra string
        } catch(Exception excecao) {
            System.out.println("Erro ao ler o arquivo Ranking.txt");
        }

        //pegamos o texto de arquivos e quebro linha por linha
        String[] linhas = textoArquivo.split("\n");
        boolean jogadorEncontrado = false;
        // quebro em linhas e percorro elas e crio um registro pra cada linha
        for(int i = 0; i < linhas.length; i++) {
            String[] registro = linhas[i].split(" ");
            // vejo se o registro tem o nome do jogador que eu procuro 
            // para mudar a pontuação
            if(nome.equals(registro[0])) {
                linhas[i] = nome + " " + novaPontuacao;
                jogadorEncontrado = true;
            }
        }

        // crio um escritor para escrever dentro do arquivoTemp
        try {
            FileWriter escritor = new FileWriter(arquivoTemp);
            // escrevo linha por linha
            for(int i = 0; i < linhas.length; i++) {
                escritor.write(linhas[i] + "\n");
            }
            if(!jogadorEncontrado) {
                escritor.write(nome + " " + novaPontuacao + "\n");
            }
            escritor.close();
        } catch(Exception excecao) {
            System.out.println("Erro ao escrever no arquivo Temp.txt");
        }
        //apago o ranking.txt
        arquivo.delete();
        //renomeio o temp.txt para o Ranking.txt, sendo o novo Ranking
        arquivoTemp.renameTo(new File("Ranking.txt"));
    }

    // momento em que peço para o ranking ser exibido
    public static void mostrarRanking() {
        // Lendo o arquivo Ranking.txt
        File arquivo = new File("Ranking.txt");
        String textoArquivo = "";
        try {
            //convertendo pra string
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); 
        } catch(Exception excecao) {
            System.out.println("Erro ao ler o arquivo Ranking.txt");
        }
        // quebro o bloco de texto em linhas
        String[] linhas = textoArquivo.split("\n");
        // crio um novo vetor com o mesmo tamanho de linhas
        String[] linhasNovas = new String[linhas.length];

        //troco a ordem de nome pra pontuação
        for(int i = 0; i < linhas.length; i++) {
            String[] registro = linhas[i].split(" ");
            String registroNovo = registro[1] + " " + registro[0];
            linhasNovas[i] = registroNovo;
        }
        
        // deixo o ranking em ordem decrescente
        Arrays.sort(linhasNovas, Collections.reverseOrder());
        // printa as linhas
        for(int i = 0; i < linhasNovas.length; i++) {
            System.out.println(linhasNovas[i]);
        }
    }
}
