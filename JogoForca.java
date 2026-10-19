package jogoforca;
import java.util.Scanner;
import java.util.Arrays;

public class JogoForca {

    public static void main(String[] args) {
        String palavraSecreta = "Slipknot".toUpperCase();
        char[] palavraOculta = new char[palavraSecreta.length()];
        Arrays.fill(palavraOculta, '*');

        int maxErros = 6;
        int erros = 0;
        String letrasTentadas = "";
        int pontuacao = 1000;
        final int PONTOS_POR_LETRA_ERRADA = 150;
        boolean palpiteFinal = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Jogo da Forca ---");

        while (erros < maxErros && !new String(palavraOculta).equals(palavraSecreta) && !palpiteFinal) {
            System.out.println("\nPalavra: " + new String(palavraOculta) + "\nErros: " + erros + "/" + maxErros);
            System.out.println("Letras tentadas: " + letrasTentadas + "\nPontuação: " + pontuacao);
            System.out.print("Digite uma letra ou PALPITE para a palavra: ");
            String entrada = scanner.nextLine().toUpperCase();

            if (entrada.equals("PALPITE")) {
                System.out.print("Seu palpite: ");
                String palpite = scanner.nextLine().toUpperCase();
                if (palpite.equals(palavraSecreta)) {
                    System.arraycopy(palavraSecreta.toCharArray(), 0, palavraOculta, 0, palavraSecreta.length());
                } else {
                    System.out.println("Palpite errado!");
                    erros = maxErros;
                }
                palpiteFinal = true;
            } else if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                char letra = entrada.charAt(0);


                if (letrasTentadas.indexOf(letra) != -1) {
                    System.out.println("Letra ja tentada.");
                    continue;
                }
                letrasTentadas += letra + " ";

                boolean letraEncontrada = false;
                for (int i = 0; i < palavraSecreta.length(); i++) {
                    if (palavraSecreta.charAt(i) == letra) {
                        palavraOculta[i] = letra;
                        letraEncontrada = true;
                    }
                }

                if (letraEncontrada) {
                    System.out.println("Letra '" + letra + "' encontrada!");
                } else {
                    System.out.println("Letra '" + letra + "' nao encontrada.");
                    erros++;

                    pontuacao = Math.max(0, pontuacao - PONTOS_POR_LETRA_ERRADA);
                }
            } else {
                System.out.println("Entrada invalida.");
            }
        }

        System.out.println("\n--- Fim do Jogo ---");
        System.out.println("A palavra era: " + palavraSecreta);

        if (new String(palavraOculta).equals(palavraSecreta)) {
            System.out.println("Voce ganhou!");
        } else {
            System.out.println("Voce perdeu!");
        }
        System.out.println("Seus pontos finais: " + pontuacao);

        System.out.println("Jogar novamente? (SIM/NAO)");
        String resposta = scanner.next();
        if (resposta.toUpperCase().equals("S") || ("s").equals(resposta)){
            main(args);
        } else {
            System.out.println("Obrigada por jogar!");
        }
        scanner.close();
    }
}