import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // recebe uma lista com varios elementos do tipo salvavel
    public static void salvarTodos(ArrayList<Salvavel> salvaveis) {
        // percorre a lista e salva cada um dos elementos
        for(Salvavel salvavel : salvaveis) {
            salvavel.salvar(); // polimorfismo (coisas diferentes com o mesmo comportamento {jogador, termo})
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // declaração para mudança de cor do texto de saída
        String RESET = "\u001B[0m";
        String BLACK_BG = "\u001B[40m";
        String GREEN_BG = "\u001B[42m";
        String YELLOW_BG = "\u001B[43m";
        String WHITE_TEXT = "\u001B[37;1m";
        String RED_TEXT = "\u001B[31m";
        String RED_ARROW =  RED_TEXT + "->" + RESET;
        String ITALICO = "\u001B[3m";
        String CIANO_TEXT = "\u001B[96m";
        String NEGRITO_TEXT = "\u001B[1m";
        String ROXO = "\u001B[35m";
        String [] textoBoasVindas = {" - - - - - - - - - - - - - - - - - - - - -\n"
                       , "|    Bem-vindo(a) ao jogo T E R M O       |\n" 
                       , " - - - - - - - - - - - - - - - - - - - - -\n"};
        
        int R = 255, G = 255, B = 0, MAX = 60, MIN = 0;
        String COLOR = "";      
        
        Random random = new Random();
        for(int j = 0; j<100; j++) {
            System.out.println("\033[H\033[2J"); // limpa a tela

            for(int i = 0; i < textoBoasVindas[0].length(); i++) {
                R += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                R %= 255;
                G += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                G %= 255;
                B += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                B %= 255;
                
                COLOR = "\u001B[38;2;" + R + ";" + G + ";" + B + "m";
                System.out.print(NEGRITO_TEXT + COLOR + textoBoasVindas[0].charAt(i) + RESET);
            }
            System.out.print(NEGRITO_TEXT + COLOR + textoBoasVindas[1].charAt(0) + RESET);
            System.out.print(textoBoasVindas[1].substring(1, textoBoasVindas[1].length() - 2));
            System.out.println(NEGRITO_TEXT + COLOR + textoBoasVindas[1].charAt(textoBoasVindas[1].length() - 2) + RESET);

            for(int i = 0; i < textoBoasVindas[2].length(); i++) {
                R += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                R %= 255;
                G += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                G %= 255;
                B += random.nextInt((MAX - (-MIN)) + 1) + (-MIN);
                B %= 255;

                COLOR = "\u001B[38;2;" + R + ";" + G + ";" + B + "m";
                System.out.print(NEGRITO_TEXT + COLOR + textoBoasVindas[2].charAt(i) + RESET);
            }
            
            System.out.println();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ITALICO + CIANO_TEXT + "?   COMO FUNCIONA   ?" + RESET);
        System.out.println(ITALICO + CIANO_TEXT + "O jogo consiste em adivinhar qual a palavra secreta de " + NEGRITO_TEXT + "5 letras" + RESET);
        System.out.println(NEGRITO_TEXT + GREEN_BG + "A " + RESET + "Faz parte da palavra e está na posição correta");
        System.out.println(NEGRITO_TEXT + YELLOW_BG + "A " + RESET + "Faz parte da palavra mas não está na posição correta");
        System.out.println(NEGRITO_TEXT + "A " + RESET + "Não faz parte da palavra\n");

        System.out.print(ITALICO + CIANO_TEXT + "Digite o seu primeiro nome: " + RESET);
        String nome = scanner.nextLine();
        
        //cria-se um novo jogo e um novo jogador
        Termo termo = new Termo();
        Jogador jogador = new Jogador(nome);
        
        //
        ArrayList<Salvavel> salvaveis = new ArrayList<Salvavel>();
        salvaveis.add(jogador);
        salvaveis.add(termo);

        while(true) {
            System.out.println("\033[H\033[2J"); // limpa a tela
            System.out.println(CIANO_TEXT + "Olá, " + RESET + NEGRITO_TEXT + nome + CIANO_TEXT + ", aqui estão alguns comandos que você pode usar:\n" + RESET);
            System.out.println(CIANO_TEXT + "iniciar " + RESET + RED_ARROW + " inicia o jogo");
            System.out.println(CIANO_TEXT + "backup " + RESET + RED_ARROW + " restaurar última partida interrompida");
            System.out.println(CIANO_TEXT + "ranking " + RESET + RED_ARROW + " mostra o ranking");
            System.out.println(CIANO_TEXT + "jogador " + RESET + ROXO + NEGRITO_TEXT + "<nome> " + RESET + RED_ARROW + " troca o jogador");
            System.out.println(CIANO_TEXT + "sair " + RESET + RED_ARROW + " sai do jogo\n");

            System.out.print(CIANO_TEXT + "Digite um comando: " + RESET);
            String linha = scanner.nextLine();
            String[] argumentos = linha.split(" ");
            String comando = argumentos[0];

            if(comando.equals("backup")) {
                termo.backup();
                scanner.nextLine();
                while(termo.getEmAndamento()) {
                    System.out.print(CIANO_TEXT + "Palpite: " + RESET);
                    String palpite = scanner.nextLine().toUpperCase();
                    try {
                        termo.palpitar(palpite);
                        salvarTodos(salvaveis);
                    } catch (Exception excecao) {
                        System.out.println(excecao.getMessage());
                    }
                }
            } else if (comando.equals("iniciar")) {
                termo.iniciarJogo(jogador);
                while(termo.getEmAndamento()) {
                    System.out.print(CIANO_TEXT + "Palpite: " + RESET);
                    String palpite = scanner.nextLine().toUpperCase();
                    try {
                        termo.palpitar(palpite);
                        salvarTodos(salvaveis);
                    } catch (Exception excecao) {
                        System.out.println(excecao.getMessage());
                    }
                }
                System.out.println(CIANO_TEXT + "Fim de jogo, aperte enter para continuar..." + RESET);
                scanner.nextLine();
            } else if (comando.equals("ranking")) {
                Ranking.mostrarRanking();
                System.out.println(CIANO_TEXT + "Aperte enter para continuar..." + RESET);
                scanner.nextLine();
            } else if (comando.equals("jogador")) {
                String novoNome = argumentos[1];
                jogador.salvar();
                jogador = new Jogador(novoNome);
                nome = novoNome;
                salvaveis.set(0, jogador);
                System.out.println(CIANO_TEXT + "Jogador trocado com sucesso!" + RESET);
                System.out.println(CIANO_TEXT + "Aperte enter para continuar..." + RESET);
                scanner.nextLine();
            } else if (comando.equals("sair")) {
                break;
            } else {
                System.out.println(CIANO_TEXT + "Comando inválido" + RESET);
            }
        }

        scanner.close();
    }
}

