import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc  = new Scanner(System.in);
        Jogo jogo = new Jogo();
        System.out.println("Olá! vamos jogar o JOGO DA VELHA !");
        var jogador = 1;
        while (true){
            jogo.imprimir();
            System.out.println("Jogador "+jogador+" \nDigite a linha e coluna da posição que dejesa jogar :");
            var linha = sc.nextInt();
            var coluna = sc.nextInt();
            try{
                if(jogo.isPosicaoValida(linha,coluna)){
                    jogo.marcar(jogador,linha,coluna);
                    if(jogo.isVitoria()){
                        jogo.imprimir();
                        System.out.println("Vitoria do jogador "+jogador+" !!!!!!!!!");
                        System.out.println("Deseja jogar novamente ?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        var jogarNovamente = sc.nextInt();
                        if(jogarNovamente==1){
                            jogador=1;
                            jogo.novoJogo();
                        }else{
                            return;
                        }
                    }
                    jogador = jogador==1?2:1;
                }else {
                    System.out.println("ALERTA!!! jogador "+jogador+" \nPosiçao escolhida já foi escolhida\nPor favor escolha novamente:");
                }
            }catch (LimiteUtrapassadoException e){
                System.out.println("ALERTA!!!");
                System.out.println(e.getMessage());
                System.out.println("jogador "+jogador+" \nPosiçao escolhida já foi escolhida\nPor favor escolha novamente:");
            }

        }
    }
}