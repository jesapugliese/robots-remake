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

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public void setPosFil(int posFil) {
        this.posFil = posFil;
    }

    public void setPosCol(int posCol) {
        this.posCol = posCol;
    }

    /**
     * Declaración de los métodos privados que utilizará el método público moverse(),
     * hay uno contemplado por cada movimiento posible.
     */
    private void moverseAbajo(){ posCol += this.pasos; }
    private void moverseArriba(){ posCol -= this.pasos; }
    private void moverseDerecha(){ posFil += this.pasos; }
    private void moverseIzquierda(){ posFil -= this.pasos; }

    private void moverseDiagonalSuperiorDerecha(){
        posFil += this.pasos;
        posCol -= this.pasos;
    }

    private void moverseDiagonalSuperiorIzquierda(){
        posFil -= this.pasos;
        posCol -= this.pasos;
    }

    private void moverseDiagonalInferiorDerecha(){
        posFil += this.pasos;
        posCol += this.pasos;
    }

    private void moverseDiagonalInferiorIzquierda(){
        posFil -= this.pasos;
        posCol += this.pasos;
    }

    /**
     * moverse recibe dos parámetros enteros dirFil y dirCol, que corresponden
     * a las direcciones en las que moverse.
     */
    public void moverse(int dirFil, int dirCol){
        if (dirFil < posFil) {
            if (dirCol < posCol){
                moverseDiagonalSuperiorIzquierda();
            } else if (dirCol > posCol) {
                moverseDiagonalSuperiorDerecha();
            } else {
                moverseArriba();
            }
        } else if (dirFil > posFil) {
            if (dirCol < posCol){
                moverseDiagonalInferiorIzquierda();
            } else if (dirCol > posCol) {
                moverseDiagonalInferiorDerecha();
            } else {
                moverseAbajo();
            }
        } else {
            if (dirCol < posCol){
                moverseIzquierda();
            } else if (dirCol > posCol) {
                moverseDerecha();
            }
        }
    }
}
