public class JogoDaVeha {
    public Character[][] jogo;

    public JogoDaVeha() {
        this.jogo = new Character[3][3];
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
}
