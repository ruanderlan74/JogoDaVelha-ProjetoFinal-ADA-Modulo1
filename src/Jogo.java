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
        item = getItem(jogador);
        jogo[linha-1][coluna-1] = item;
    }

    private static Character getItem(int jogador) {
        Character item;
        if(jogador %2!=0){
            item = 'X';
        }else{
            item  = 'O';
        }
        return item;
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
                  System.out.print("   ");
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
                        System.out.print("---+");
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
        return isVitoriaLinha()||isVitoriaColuna()||isVitoriaDiagonal1()||isVitoriaDiagonal2();
    }

    private boolean isVitoriaDiagonal1() {
        Character diagonal1 = null;
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                if (i == j) {
                    var elemento = jogo[i][j];
                    if (elemento == null || (diagonal1 != null && diagonal1 != elemento)) {
                        return false;
                    }
                    diagonal1=elemento;
                }
            }
        }
        return true;
    }
    private boolean isVitoriaDiagonal2() {
        Character diagonal2 = null;
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                if (i + j==jogo.length-1) {
                    var elemento = jogo[i][j];
                    if (elemento == null || (diagonal2 != null && diagonal2 != elemento)) {
                        return false;
                    }
                    diagonal2=elemento;
                }
            }
        }
        return true;
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

    public boolean isVelha(int jogador) {
        return allPosicoesPreenchidas()||poucasJogadasSemPosibilidadesDeVitoria()||lastJogada(jogador);
    }

    private boolean poucasJogadasSemPosibilidadesDeVitoria() {
        if(faltaMenosDeTresPosicoes()){
            boolean flagExistePossibilidade =false;
            for (int i = 0; i < jogo.length; i++){
                for (int j = 0; j < jogo[i].length; j++){
                    var elemento = jogo[i][j];
                    if (elemento==null){
                        //verificar linha da posicao ainda não marcada
                        if (i==0){
                            if(jogo[i+1][j] == jogo[i+2][j]){
                                flagExistePossibilidade = true;
                            }
                        }
                        else if (i==jogo.length-1){
                            if(jogo[i-1][j] == jogo[i-2][j]){
                                flagExistePossibilidade = true;
                            }
                        }else{
                            if(jogo[i-1][j] == jogo[i+1][j]){
                                flagExistePossibilidade = true;
                            }
                        }
                        //verificar coluna da posicao ainda não marcada
                        if (j==0){
                            if(jogo[i][j+1] == jogo[i][j+2]){
                                flagExistePossibilidade = true;
                            }
                        }
                        else if (j==jogo[i].length-1){
                            if(jogo[i][j-1] == jogo[i][j-2]){
                                flagExistePossibilidade = true;
                            }
                        }else{
                            if(jogo[i][j-1] == jogo[i][j+1]){
                                flagExistePossibilidade = true;
                            }
                        }
                        //verificar diagonal 1
                        if(i==j){
                            if (i==0 && j==0){
                                if(jogo[i+1][j+1] == jogo[i+2][j+2]){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==jogo.length-1){
                                if(jogo[i-1][j-1] == jogo[i-2][j-2]){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i-1][j-1] == jogo[i+1][j+1]){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                        //verificar diagonal 1
                        if(i+j== jogo.length-1){
                            if (i==0 && j==jogo[0].length-1){
                                if(jogo[i+1][j-1] == jogo[i+2][j-2]){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==0){
                                if(jogo[i-1][j+1] == jogo[i-2][j+2]){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i+1][j-1] == jogo[i-1][j+1]){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                        //verificar diagonal 2
                        if(i==j){
                            if (i==0 && j==0){
                                if(jogo[i+1][j+1] == jogo[i+2][j+2]){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==jogo.length-1){
                                if(jogo[i-1][j-1] == jogo[i-2][j-2]){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i-1][j-1] == jogo[i+1][j+1]){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                    }

                }
            }
            return !flagExistePossibilidade;
        }
        return false;
    }

    private boolean lastJogada(int jogador) {
        if(lastPosicaoVazia()){
            boolean flagExistePossibilidade =false;
            var proximoJogador = getItem(jogador+1);
            for (int i = 0; i < jogo.length; i++){
                for (int j = 0; j < jogo[i].length; j++){
                    var elemento = jogo[i][j];
                    if (elemento==null){
                        //verificar linha da posicao ainda não marcada
                        if (i==0){
                            if(jogo[i+1][j] == jogo[i+2][j] && jogo[i+2][j] == proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }
                        else if (i==jogo.length-1){
                            if(jogo[i-1][j] == jogo[i-2][j] && jogo[i-1][j] ==proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }else{
                            if(jogo[i-1][j] == jogo[i+1][j] && jogo[i-1][j] ==proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }
                        //verificar coluna da posicao ainda não marcada
                        if (j==0){
                            if(jogo[i][j+1] == jogo[i][j+2] && jogo[i][j+1] ==proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }
                        else if (j==jogo[i].length-1){
                            if(jogo[i][j-1] == jogo[i][j-2] && jogo[i][j-1] == proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }else{
                            if(jogo[i][j-1] == jogo[i][j+1] && jogo[i][j-1] ==proximoJogador){
                                flagExistePossibilidade = true;
                            }
                        }
                        //verificar diagonal 1
                        if(i==j){
                            if (i==0 && j==0){
                                if(jogo[i+1][j+1] == jogo[i+2][j+2] && jogo[i+1][j+1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==jogo.length-1){
                                if(jogo[i-1][j-1] == jogo[i-2][j-2] && jogo[i-1][j-1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i-1][j-1] == jogo[i+1][j+1] && jogo[i-1][j-1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                        //verificar diagonal 1
                        if(i+j== jogo.length-1){
                            if (i==0 && j==jogo[0].length-1){
                                if(jogo[i+1][j-1] == jogo[i+2][j-2] && jogo[i+1][j-1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==0){
                                if(jogo[i-1][j+1] == jogo[i-2][j+2] && jogo[i-1][j+1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i+1][j-1] == jogo[i-1][j+1] && jogo[i+1][j-1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                        //verificar diagonal 2
                        if(i==j){
                            if (i==0 && j==0){
                                if(jogo[i+1][j+1] == jogo[i+2][j+2] && jogo[i+1][j+1] ==proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                            else if (i==jogo.length-1 && j==jogo.length-1){
                                if(jogo[i-1][j-1] == jogo[i-2][j-2] &&jogo[i-1][j-1] == proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }else{
                                if(jogo[i-1][j-1] == jogo[i+1][j+1] &&jogo[i-1][j-1] == proximoJogador){
                                    flagExistePossibilidade = true;
                                }
                            }
                        }
                    }

                }
            }
            return !flagExistePossibilidade;
        }
        return false;
    }

    private boolean faltaMenosDeTresPosicoes() {
        var cont = 0;
        for (Character[] linha : jogo){
            for (Character elemento : linha){
                if(elemento==null){
                    cont++;
                }
            }
        }
        if(cont>2){
            return false;
        }
        return true;
    }

    private boolean lastPosicaoVazia() {
        var cont = 0;
        for (Character[] linha : jogo){
            for (Character elemento : linha){
                if(elemento==null){
                    cont++;
                }
            }
        }
        if(cont==1){
            return true;
        }
        return false;
    }

    private boolean allPosicoesPreenchidas() {
        for (Character[] linha : jogo){
            for (Character elemento : linha){
                if(elemento==null){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean allPosicaoPreenchidas() {
        for (Character[] linha : jogo){
            for (Character elemento : linha){
                if(elemento==null){
                    return false;
                }
            }
        }
        return true;
    }
}
