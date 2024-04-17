package robots.modelo;

public class Robot extends ObjetoMovible {
    private final int id;
    private int puntaje; // robot simple --> puntaje = 10. robot doble --> puntaje = 20. explosion --> puntaje = 0
    private boolean destruido;

    public int getId() {
        return id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public Robot(int posFil, int posCol, int pasos, int puntaje, int id) { //pasos = 0 si es explosion, 1 si es robot simple, 2 si es doble
        super(pasos, posFil, posCol);
        this.id = id;
        this.puntaje = puntaje;
        this.destruido = false;
    }

    public void Destruir() {
        super.setPasos(Ctes.SIN_PASOS);
        puntaje = 0;
        destruido = true;
    }
}