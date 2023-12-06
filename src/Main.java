import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class Main {
    public static void salvarTodos(ArrayList<Salvavel> salvaveis) {
        for(Salvavel salvavel : salvaveis) {
            salvavel.salvar(); // polimorfismo (coisas diferentes com o mesmo comportamento {jogador, termo})
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
        
        System.out.println(" - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("|    Bem-vindo(a) ao jogo T E R M O       |");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - -\n");
        System.out.println(ITALICO + CIANO_TEXT + "?   COMO FUNCIONA   ?" + RESET);
        System.out.println(ITALICO + CIANO_TEXT + "O jogo consiste em adivinhar qual a palavra de SOMENTE 5 letras" + RESET);
        System.out.println(NEGRITO_TEXT + GREEN_BG + "A " + RESET + "Faz parte da palavra e está na posição correta");
        System.out.println(NEGRITO_TEXT + YELLOW_BG + "A " + RESET + "Faz parte da palavra mas não está na posição correta");
        System.out.println(NEGRITO_TEXT + "A " + RESET + "Não faz parte da palavra\n");

        System.out.print(ITALICO + CIANO_TEXT + "Digite o seu primeiro nome: " + RESET);
        String nome = scanner.nextLine();
        
        Termo termo = new Termo();
        Jogador jogador = new Jogador(nome);

        ArrayList<Salvavel> salvaveis = new ArrayList<Salvavel>();
        salvaveis.add(jogador);
        salvaveis.add(termo);

        while(true) {
            System.out.println("\033[H\033[2J"); // limpa a tela
            System.out.println("Olá, " + nome + ", aqui estão alguns comandos que você pode usar:\n");
            System.out.println(CIANO_TEXT + "iniciar " + RESET + RED_ARROW + " inicia o jogo");
            System.out.println(CIANO_TEXT + "backup " + RESET + RED_ARROW + " restaurar última partida interrompida");
            System.out.println(CIANO_TEXT + "ranking " + RESET + RED_ARROW + " mostra o ranking");
            System.out.println(CIANO_TEXT + "jogador " + RESET + ROXO + "<nome> " + RESET + RED_ARROW + " troca o jogador");
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
                System.out.println("Fim de jogo, aperte enter para continuar...");
                scanner.nextLine();
            } else if (comando.equals("ranking")) {
                Ranking.mostrarRanking();
                System.out.println("Aperte enter para continuar...");
                scanner.nextLine();
            } else if (comando.equals("jogador")) {
                String novoNome = argumentos[1];
                jogador.salvar();
                jogador = new Jogador(novoNome);
                nome = novoNome;
                salvaveis.set(0, jogador);
                System.out.println("Jogador trocado com sucesso!");
                System.out.println("Aperte enter para continuar...");
                scanner.nextLine();
            } else if (comando.equals("sair")) {
                break;
            } else {
                System.out.println("Comando inválido");
            }
        }

        scanner.close();
    }
}

// public class Main {
//     public static void main(String[] args) {
//         Jogador jogador = new Jogador("");

//         /*  | Bem vinde ao jogo T E R M O.
//             | Digite o seu primeiro nome:
//         -----------------------------------
//         //Laura
//         -----------------------------------
//         Digite: 
//         iniciar -> para iniciar a partida
//         ranking -> para ver o ranking
//         trocar -> para trocar de jogador
//         sair -> para sair da partida
//         -----------------------------------
//         //iniciar
//         -----------------------------------
//         Digite uma palavras de 5 letras. Restam 6 chances!
//         ------------------------------------
//         //viola
//         ----------------------------------
//         V I O L A
//         Restam 5 chances!
//         -----------------------------------
//         //achar
//         -----------------------------------
//         V I O L A 
//         A C H A R
//         Restam 4 chances!
//         ------------------------------------
//         fisga
//         ------------------------------------
//         V I O L A 
//         A C H A R
//         F I S G A
//         Restam 3 chances!
// */
//     }
// }
