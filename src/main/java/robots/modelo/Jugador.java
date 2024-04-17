package robots.modelo;

public class Jugador extends ObjetoMovible {
    private int usosRestantesTPSegura;
    private int puntaje;

    public Jugador(int posFil, int posCol) {
        super(Ctes.PASO_SIMPLE, posFil, posCol);
        this.puntaje = 0;
        this.usosRestantesTPSegura = Ctes.CANT_INICIAL_TPSEGURA;
    }

    /**
     * Teletransporta al jugador a la posicion de fila y columna pasados por parametro.
     */
    public void TP(int posFil, int posCol, boolean esTPSegura){ //no se si implementar asi lo de esTPSegura o.o
        if (esTPSegura){
            usosRestantesTPSegura--;
        }
        super.setPosFil(posFil);
        super.setPosCol(posCol);
    }

    /**
     * Incrementa el puntaje del jugador en base a la cantidad de puntos ganados.
     */
    public void AumentarPuntaje(int puntos){
        this.puntaje += puntos;
    }

    /**
     * Incrementa en uno la cantidad de usos restantes de la teletransportacion segura
     */
    public void IncrementarUsosRestantesTPSegura(){
        this.usosRestantesTPSegura += 1;
    }

    /**
     * Recibe la posicion central de las grilla y la asigna al jugador y
     * establece el resto de valores del mismo en sus valores iniciales.
     */
    public void ReiniciarEstado(int posCentralFil, int posCentralCol){
        super.setPosFil(posCentralFil);
        super.setPosCol(posCentralCol);
        this.puntaje = 0;
        this.usosRestantesTPSegura = Ctes.CANT_INICIAL_TPSEGURA;
    }
}
