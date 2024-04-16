
import java.util.List;
import java.util.ArrayList;
public class Jugador {
    private final int usosRestantesTPSegura;
    private final int[] posicion;
    private final int puntaje;

    // Constructor
    public Jugador(int fila ,int columna){
        this.posicion = new int[2];

        this.posicion[0] = fila;

        this.posicion[1] = columna;

        this.puntaje = 0;

        this.usosRestantesTPSegura = 4;
    }

    public AumentarPuntaje(int puntos){
        this.puntaje += puntos;
    }

    public ReiniciarEstado(){
        this.posicion[0] = ??;

        this.posicion[1] = ??;

        this.puntaje = 0;

        this.usosRestantesTPSegura = ??;
    }

    // Declaracion de los metodos privados que utilizara el metodo publico Moverse
    // hay uno contemplado por cada movimiento posible
    private void moverseAbajo(){this.posicion[1] += this.pasos;}
    private void moverseArriba(){this.posicion[1] -= this.pasos;}
    private void moverseDerecha(){this.posicion[0] += this.pasos;}
    private void moverseIzquierda(){this.posicion[0] -= this.pasos;}

    private void moverseDiagonalSuperiorDerecha(){
        this.posicion[0] += this.pasos;
        this.posicion[1] -= this.pasos;
    }

    private void moverseDiagonalSuperiorIzquierda(){
        this.posicion[0] -= this.pasos;
        this.posicion[1] -= this.pasos;
    }

    private void moverseDiagonalInferiorDerecha(){
        this.posicion[0] += this.pasos;
        this.posicion[1] += this.pasos;
    }

    private void moverseDiagonalInferiorIzquierda(){
        this.posicion[0] -= this.pasos;
        this.posicion[1] += this.pasos;
    }

}
