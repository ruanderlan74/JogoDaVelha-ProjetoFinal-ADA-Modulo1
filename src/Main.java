import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc  = new Scanner(System.in);
        Jogo jogo = new Jogo();
        System.out.println("Olá! vamos começar a jogar o JOGO DA VELHA !");
        var jogador = 1;
        while (true){
            jogo.imprimir();
            System.out.println("Jogador numero "+jogador+" \nDigite a linha e coluna da posição que dejesa jogar :");
            var linha = sc.nextInt();
            var coluna = sc.nextInt();
            try{
                if(jogo.isPosicaoValida(linha,coluna)){
                    jogo.marcar(jogador,linha,coluna);
                    jogador = jogador==1?2:1;
                }else {
                    System.out.println("ALERTA!!! jogador numero "+jogador+" \nPosiçao escolhida já foi escolhida\nPor favor escolha novamente:");
                }
            }catch (LimiteUtrapassadoException e){
                System.out.println("ALERTA!!!");
                System.out.println(e.getMessage());
                System.out.println("jogador numero "+jogador+" \nPosiçao escolhida já foi escolhida\nPor favor escolha novamente:");
            }


        }
    }
}