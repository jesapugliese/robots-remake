package robots.modelo;

import robots.recursos.Ctes;

public class Jugador extends ObjetoMovible {
    private int usosRestantesTPSegura;
    private int puntaje;
    private boolean perdedor;
    private boolean ganador;

    public Jugador(int posFil, int posCol) {
        super(Ctes.PASO_SIMPLE, posFil, posCol);
        this.puntaje = 0;
        this.usosRestantesTPSegura = Ctes.CANT_INICIAL_TPSEGURA;
        this.perdedor = false;
        this.ganador = false;
    }

    public int getUsosRestantesTPSegura() {
        return usosRestantesTPSegura;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public boolean isPerdedor() { return perdedor; }
    public boolean isGanador() { return ganador; }

    public void setPerdedor(boolean perdedor) { this.perdedor = perdedor; }
    public void setGanador(boolean ganador) { this.ganador = ganador; }

    public void reiniciarEstadoJugador(int posFil, int posCol) {
        super.setPosFil(posFil);
        super.setPosCol(posCol);
        this.puntaje = 0;
        this.usosRestantesTPSegura = Ctes.CANT_INICIAL_TPSEGURA;
        this.perdedor = false;
        this.ganador = false;
    }

    public void ganarNivel(int posFil, int posCol) {
        super.setPosFil(posFil);
        super.setPosCol(posCol);
        usosRestantesTPSegura++;
        perdedor = false;
        ganador = false;
    }

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
     * decrementarUsosRestantesTPSegura decrementa en uno la cantidad de usos restantes de la teletransportacion segura
     */
    public void decrementarUsosRestantesTPSegura(){
        this.usosRestantesTPSegura--;
    }
}
