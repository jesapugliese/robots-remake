
import java.util.List;
import java.util.ArrayList;
public class Jugador {
    private final int usosRestantesTPSegura;
    private final int[] posicion;
    private final int puntaje;

    // Constructor
    public Jugador(){
        this.posicion = new int[];

        this.posicion[0] = 0;

        this.posicion[1] = 0;

        this.puntaje = 0;

        this.usosRestantesTPSegura = 4;
    }

    public AumentarPuntaje(int puntos){
        this.puntaje += puntos;
    }

    public ReiniciarEstado(){
        this.posicion[0] = 0;

        this.posicion[1] = 0;

        this.puntaje = 0;

        this.usosRestantesTPSegura = 4;
    }
}
