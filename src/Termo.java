
// Responsável por controlar o estado do jogo, gerenciar os jogadores
// escolher palavras, etc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Termo extends Salvavel {
    // Atributos
    private Jogador jogador;
    private Palavra palavraSecreta;
    private Dicionario dicionario;
    private boolean jogoEmAndamento;
    private int tentativasRestantes;

    // Construtor
    public Termo() {
        this.jogador = null;
        this.palavraSecreta = null;
        this.dicionario = new Dicionario();
        this.jogoEmAndamento = false;
        this.tentativasRestantes = 0;
    }

    // Métodos
    public void iniciarJogo (Jogador jogador) {
        this.jogador = jogador;
        this.palavraSecreta = dicionario.getPalavraAleatoria();
        this.jogoEmAndamento = true;
        this.tentativasRestantes = 6;
    }

    public boolean getEmAndamento() {
        return jogoEmAndamento;
    }

    public boolean palpitar(String palpite) throws Exception {
        if(!this.jogoEmAndamento) {
            throw new Exception("error: o jogo não está iniciado.");
        }
        
        //cria um booleano
        boolean resultado;
        try {
            //confere as regras da palavra atraves do metodo palpitar
            resultado = palavraSecreta.palpitar(palpite);
        } catch (Exception excecao) {
            System.out.println(excecao.getMessage()); 
            return false;
        }
        
        // se o resultado for falso
        if(!resultado) { 
            this.tentativasRestantes--;
            if(this.tentativasRestantes == 0) {
                this.jogoEmAndamento = false;
            }
            System.out.println(" (" + this.tentativasRestantes + " tentativas restantes)");
        } else { // no caso de acertar a palavra
            this.jogoEmAndamento = false;
            this.jogador.incrementarPontuacao();
            String NEGRITO_TEXT = "\u001B[1m";
            String RESET = "\u001B[0m";
            System.out.println(NEGRITO_TEXT + " (você acertou!)" + RESET);
        }
        return resultado;
    }

    // realiza um backup da última partida mais recente que não foi terminada
    // logica parecida com a do ranking, mas adicionando em um .txt
    public void backup() {
        File arquivo = new File("Backup.txt");

        String textoArquivo = "";
        String CIANO_TEXT = "\u001B[96m";
        String RESET = "\u001B[0m";

        try {
            //convertendo pra string
            textoArquivo = new String (Files.readAllBytes(arquivo.toPath())); 
        } catch(Exception excecao) {
            System.out.println(CIANO_TEXT + "Nenhum backup encontrado" + RESET);
            return;
        }
        String[] linhas = textoArquivo.split("\n");
        if(linhas.length < 3) {
            System.out.println(CIANO_TEXT + "Nenhum backup encontrado" + RESET);
        }
        jogador = new Jogador(linhas[0]);
        palavraSecreta = new Palavra(linhas[1]);
        tentativasRestantes = Integer.parseInt(linhas[2]);
        if(tentativasRestantes > 0) {
            jogoEmAndamento = true;
            System.out.print(CIANO_TEXT + "Jogo recuperado! Aperte enter para continuar." + RESET);
        }
    }

    public void salvar() {
        try{
            BufferedWriter brEscritor = new BufferedWriter(new FileWriter("Backup.txt"));
        
            brEscritor.write(jogador.getNome()); 
            brEscritor.write("\n");
            brEscritor.write(palavraSecreta.getPalavra());
            brEscritor.write("\n");
            brEscritor.write(String.valueOf(tentativasRestantes));
            brEscritor.close();
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
