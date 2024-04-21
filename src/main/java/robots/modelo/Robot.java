package robots.modelo;

import robots.recursos.Ctes;

public class Robot extends ObjetoMovible {
    private final int id;
    private int puntaje; // robot simple --> puntaje = 10. robot doble --> puntaje = 20. explosion --> puntaje = 0
    private boolean destruido;

    public Robot(int posFil, int posCol, int pasos, int puntaje, int id) { //pasos = 0 si es explosion, 1 si es robot simple, 2 si es doble
        super(pasos, posFil, posCol);
        this.id = id;
        this.puntaje = puntaje;
        this.destruido = false;
    }

    public int getId() {
        return id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isDestruido() {
        return destruido;
    }

    /**
     * destruir destruye al robot impidiendole moverse y asignandole un puntaje de 0
     */
    public void destruir() {
        super.setPasos(Ctes.SIN_PASOS);
        puntaje = 0;
        destruido = true;
    }
}