// Representa uma palavra no jogo

import java.lang.Exception;

// classe Palavra que extende da classe Salvavel
public class Palavra {
    // Atributo
    private String palavra ;
    
    // Construtor
    public Palavra(String palavra) {
        this.palavra = palavra;
    }

    //Métodos
    public String getPalavra() {
        return palavra;
    }
    
    // Método para saber se o palpite está correto
    public boolean palpitar(String palpite) throws Exception {
        // declaração para mudança de cor do texto de saída
        String RED_TEXT = "\u001B[31m";
        String RESET = "\u001B[0m";
        
        // verificar se o palpite não é de um tamanho diferente da palavra correta
        if(palpite.length() != palavra.length()) {
            throw new Exception(RED_TEXT + "error: a palavra tem que ter cinco letras" + RESET);
        } 
        
        // declaração para mudança de cor do texto de saída
        String BLACK_BG = "\u001B[40m";
        String GREEN_BG = "\u001B[42m";
        String YELLOW_BG = "\u001B[43m";
        String WHITE_TEXT = "\u001B[37;1m";
        String NEGRITO_TEXT = "\u001B[1m";
        String resultado = "";

        // laço para modificar as cores das letras de acordo com a posição
        for(int i = 0; i<palavra.length(); i++) {
            if(palpite.charAt(i) == palavra.charAt(i)) {
                //letra sai na cor verde
                resultado += NEGRITO_TEXT + WHITE_TEXT + GREEN_BG + palpite.charAt(i) + RESET;
            } else if(palavra.contains("" + palpite.charAt(i))) { //se o palpite na posicao(i) está contido na palavra
                //letra sai na cor amarela
                resultado += NEGRITO_TEXT + WHITE_TEXT + YELLOW_BG + palpite.charAt(i) + RESET;
            } else { // se a letra não estiver nem na posição correta nem na palavra
                // letra fica branca
                resultado += NEGRITO_TEXT + WHITE_TEXT + BLACK_BG + palpite.charAt(i) + RESET;
            }

            // adicionando espaço para identar a palavra no terminal
            if(i < palavra.length() - 1) {
                resultado += " ";
            }
        }

        System.out.print(resultado);
        return palavra.equals(palpite); //acertou a palavra
    }

}

