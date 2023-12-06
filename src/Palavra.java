// Representa uma palavra no jogo
import java.lang.Exception;

public class Palavra extends Salvavel {
    private String palavra ;
    
    public Palavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPalavra() {
        return palavra;
    }
    
    public boolean palpitar(String palpite) throws Exception {
        String RED_TEXT = "\u001B[31m";
        String RESET = "\u001B[0m";
        
        if(palpite.length() != palavra.length()) {
            throw new Exception(RED_TEXT + "error: a palavra tem que ter cinco letras" + RESET);
        } 

        String BLACK_BG = "\u001B[40m";
        String GREEN_BG = "\u001B[42m";
        String YELLOW_BG = "\u001B[43m";
        String WHITE_TEXT = "\u001B[37;1m";
        String NEGRITO_TEXT = "\u001B[1m";

        String resultado = "";

        for(int i = 0; i<palavra.length(); i++) {
            if(palpite.charAt(i) == palavra.charAt(i)) {
                //letra sai na cor verde
                resultado += NEGRITO_TEXT + WHITE_TEXT + GREEN_BG + palpite.charAt(i) + RESET;
            } else if(palavra.contains("" + palpite.charAt(i))) {
                //letra sai na cor amarela
                resultado += NEGRITO_TEXT + WHITE_TEXT + YELLOW_BG + palpite.charAt(i) + RESET;
            } else {
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
    
    public void salvar() {
        // TODO
    }
}

