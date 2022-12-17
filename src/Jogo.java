public class Jogo {
    public Character[][] jogo;

    public Jogo() {
        this.jogo = new Character[3][3];
    }

    public void novoJogo(){
        jogo = new Character[3][3];
    }
    public void marcar(int jogador, int linha, int coluna){
        Character item;
        if(jogador%2!=0){
            item = 'X';
        }else{
            item  = 'O';
        }
        jogo[linha-1][coluna-1] = item;
    }

    public void imprimir(){
        System.out.println();

        System.out.print("  ");
        for (int aux = 0; aux<jogo[0].length; aux++){
            System.out.print(" "+(aux+1)+"  ");
        }
        System.out.println();
        for (int i = 0; i< jogo.length ; i++){
            var linha =jogo[i];
            System.out.print( i+1 + " ");
            for (int j = 0; j < linha.length ; j++){
              if(linha[j] == null){
                  System.out.print(" * ");
              }else {
                  System.out.print(" "+jogo[i][j]+" ");
              }
                if(j != linha.length-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i!=jogo.length-1){
                System.out.print("  ");
                for (int aux = 0; aux<jogo[0].length; aux++){
                    if(aux == jogo[0].length-1){
                        System.out.print("---");
                    }else {
                        System.out.print("----");
                    }
                }
                System.out.println();
            }

        }
        System.out.println();

    }

    public boolean isPosicaoValida(int linha, int coluna) throws LimiteUtrapassadoException {
        try{
            return jogo[linha-1][coluna-1] == null;
        }catch (ArrayIndexOutOfBoundsException e){
            throw new LimiteUtrapassadoException("[ERR0]: Posicao linha "+linha+" e coluna "+coluna+" fora do tabuleiro");
        }
    }

    public boolean isVitoria(){
        return isVitoriaLinha()||isVitoriaColuna();
    }

    private boolean isVitoriaLinha(){
        Character aux;
        for (int i = 0; i < jogo.length; i++){
            aux = null;
            for (int j = 0; j < jogo[i].length; j++){
                var elemento = jogo[i][j];
                if(elemento == null || (aux != null && aux != elemento)){
                    break;
                }else{
                    if(j==jogo[i].length-1){
                        return true;
                    }
                    aux=elemento;
                }
            }
        }
        return false;
    }

    private boolean isVitoriaColuna(){
        Character aux;
        for (int j = 0; j < jogo[0].length; j++){
            aux = null;
            for (int i = 0; i < jogo.length; i++){
                var elemento = jogo[i][j];
                if(elemento == null || (aux != null && aux != elemento)){
                    break;
                }else{
                    if(i==jogo[0].length-1){
                        return true;
                    }
                    aux=elemento;
                }
            }
        }
        return false;
    }
}
