package robots.modelo;

import robots.recursos.Ctes;

public class Jugador extends ObjetoMovible {
    private int usosRestantesTPSegura;
    private int puntaje;
    private boolean perdedor;

    public Jugador(int posFil, int posCol) {
        super(Ctes.PASO_SIMPLE, posFil, posCol);
        this.puntaje = 0;
        this.usosRestantesTPSegura = Ctes.CANT_INICIAL_TPSEGURA;
        this.perdedor = false;
    }

    public int getUsosRestantesTPSegura() {
        return usosRestantesTPSegura;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isPerdedor() { return perdedor; }

    public void perder() { perdedor = true; }

    /**
     * tp teletransporta al jugador a la posicion de fila y columna pasados por parametro.
     */
    public void actualizarPosicion(int posFil, int posCol) {
        super.setPosFil(posFil);
        super.setPosCol(posCol);
    }

    /**
     * aumentarPuntaje incrementa el puntaje del jugador en base a la cantidad de puntos ganados.
     */
    public void aumentarPuntaje(int puntos){
        this.puntaje += puntos;
    }

    /**
     * incrementarUsosRestantesTPSegura incrementa en uno la cantidad de usos restantes de la teletransportacion segura
     */
    public void incrementarUsosRestantesTPSegura(){
        this.usosRestantesTPSegura++;
    }

    /**
     * decrementarUsosRestantesTPSegura decrementa en uno la cantidad de usos restantes de la teletransportacion segura
     */
    public void decrementarUsosRestantesTPSegura(){
        this.usosRestantesTPSegura--;
    }
}
