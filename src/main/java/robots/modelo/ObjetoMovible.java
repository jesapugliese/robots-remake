package robots.modelo;

public class ObjetoMovible {
    private int pasos;
    private int posFil;
    private int posCol;

    public ObjetoMovible(int pasos, int posFil, int posCol) {
        this.pasos = pasos;
        this.posFil = posFil;
        this.posCol = posCol;
    }

    public int getPasos() {
        return pasos;
    }

    public int getPosFil() {
        return posFil;
    }

    public int getPosCol() {
        return posCol;
    }

    void setPasos(int pasos) { this.pasos = pasos; }

    void setPosFil(int posFil) {
        this.posFil = posFil;
    }

    void setPosCol(int posCol) {
        this.posCol = posCol;
    }

    /**
     * Declaración de los métodos privados que utilizará el método público moverse(),
     * hay uno contemplado por cada movimiento posible.
     */
    private int[] moverseAbajo(){ return new int[]{posFil, posCol + pasos}; }
    private int[] moverseArriba(){ return new int[]{posFil, posCol - pasos}; }
    private int[] moverseDerecha(){ return new int[]{posFil + pasos, posCol}; }
    private int[] moverseIzquierda(){ return new int[]{posFil - pasos, posCol}; }
    private int[] moverseDiagonalSuperiorDerecha() { return new int[]{posFil + pasos, posCol - pasos}; }
    private int[] moverseDiagonalSuperiorIzquierda() { return new int[]{posFil - pasos, posCol - pasos}; }
    private int[] moverseDiagonalInferiorDerecha() { return new int[]{posFil + pasos, posCol + pasos}; }
    private int[] moverseDiagonalInferiorIzquierda() { return new int[]{posFil - pasos, posCol + pasos}; }

    /**
     * moverse recibe dos parámetros enteros dirFil y dirCol, que corresponden
     * a las direcciones en las que moverse.
     */
    public int[] moverse(int dirFil, int dirCol) {
        if (dirFil < posFil) {
            if (dirCol < posCol){
                return moverseDiagonalSuperiorIzquierda();
            } else if (dirCol > posCol) {
                return moverseDiagonalSuperiorDerecha();
            } else {
                return moverseArriba();
            }
        } else if (dirFil > posFil) {
            if (dirCol < posCol){
                return moverseDiagonalInferiorIzquierda();
            } else if (dirCol > posCol) {
                return moverseDiagonalInferiorDerecha();
            } else {
                return moverseAbajo();
            }
        } else {
            if (dirCol < posCol){
                return moverseIzquierda();
            } else if (dirCol > posCol) {
                return moverseDerecha();
            }
        }

        return null;
    }
}
