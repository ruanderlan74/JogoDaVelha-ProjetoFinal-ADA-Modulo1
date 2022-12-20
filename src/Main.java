import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        System.out.println("Olá! vamos jogar o JOGO DA VELHA !");
        var jogador = 1;
        while (true){
            System.out.println("--------------------------------------");
            jogo.imprimir();
            System.out.println("Jogador "+jogador+" \nDigite a linha e coluna da posição que dejesa jogar :");
            try{
                var sc  = new Scanner(System.in);
                var linha = sc.nextInt();
                var coluna = sc.nextInt();
                if(jogo.isPosicaoValida(linha,coluna)){
                    jogo.marcar(jogador,linha,coluna);
                    if(jogo.isVitoria()){
                        System.out.println("--------------------------------------");
                        System.out.println("Vitoria do jogador "+jogador+" !!!!!!!!!");
                        jogo.imprimir();
                        System.out.println("Deseja jogar novamente ?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        var jogarNovamente = sc.nextInt();
                        if(jogarNovamente==1){
                            jogador++;
                            jogo.novoJogo();
                        }else{
                            return;
                        }
                    }
                    if(jogo.isVelha(jogador)){
                        System.out.println("--------------------------------------");
                        System.out.println("Deu velha !!!!!!!!!");
                        jogo.imprimir();
                        System.out.println("Deseja jogar novamente ?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        var jogarNovamente = sc.nextInt();
                        if(jogarNovamente==1){
                            jogador++;
                            jogo.novoJogo();
                        }else{
                            return;
                        }
                    }
                    jogador = jogador==1?2:1;
                }else {
                    System.out.println("ALERTA!!! \njogador "+jogador+" \nPosiçao já foi escolhida\nPor favor escolha novamente:");
                }
            }catch (InputMismatchException e){
                System.out.println("ALERTA!!!");
                System.out.println("[ERR0]: Valor informado invalido");
                System.out.println("jogador "+jogador+" \nO valor digitado deve ser um numero\nPor favor escolha novamente:");
            }
            catch (LimiteUtrapassadoException e){
                System.out.println("ALERTA!!!");
                System.out.println(e.getMessage());
                System.out.println("jogador "+jogador+" \nPosiçao escolhida já foi escolhida\nPor favor escolha novamente:");
            }

        }
    }
}