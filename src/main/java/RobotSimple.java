public class RobotSimple implements Enemigo {
    private int pasos; // Declaration of the variable
    private int[] posicion;

    // Constructor to initialize the variable
    public RobotSimple(int fila, int columna) {
        this.pasos = 1;
        this.posicion = new int[2];
        this.posicion[0] = fila;
        this.posicion[1] = columna;

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
