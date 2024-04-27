package robots.modelo;

import robots.Robots;
import robots.recursos.Ctes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JuegoModelo {
    private final Jugador jugador;
    private List<Robot> robots;
    private int nivelActual;
    private int totalFilas;
    private int totalColumnas;

    public JuegoModelo(int totalFilas, int totalColumnas) {
        this.jugador = new Jugador(totalFilas/2, totalColumnas/2);
        this.nivelActual = Ctes.NIVEL_INICIAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    public Jugador getJugador() {
        return jugador;
    }
    public List<Robot> getRobots() {
        return robots;
    }
    public int getNivelActual() { return nivelActual; }
    public int getTotalFilas() { return totalFilas; }
    public int getTotalColumnas() { return totalColumnas; }

    /**
     * inicializarRobots inicializa un conjunto de robots según la dificultad dada por el nivelActual.
     * La cantidad de robots varía según el nivel y los robots pueden ser simples (de un paso) o dobles (de dos pasos).
     * La mitad son de un tipo y la mitad del otro.
     */
    private void inicializarRobots() {
        Random random = new Random();
        this.robots = new ArrayList<>();
        int cantRobots = 5; //cambiar!!!!!!!!!!!!!!!!

        for (int i = 0; i < cantRobots; i++) {
            int posFil = random.nextInt(totalFilas);
            int posCol = random.nextInt(totalColumnas);
            while (noEsPosValida(posFil, posCol)) {
                posFil = random.nextInt();
                posCol = random.nextInt();
            }

            Robot nuevoRobot;
            if (i > cantRobots/2) {
                nuevoRobot = new Robot(posFil, posCol, Ctes.PASO_DOBLE, Ctes.PUNTAJE_ROBOT_DOBLE);
            } else {
                nuevoRobot = new Robot(posFil, posCol, Ctes.PASO_SIMPLE, Ctes.PUNTAJE_ROBOT_SIMPLE);
            }
            robots.add(nuevoRobot);
        }
    }

    /**
     * noEsPosValida devuelve true si la posicion pasada por paremetro colisiona con la
     * posicion de otros robots o del jugador. En caso contrario devuelve false.
     */
    private boolean noEsPosValida(int posFil, int posCol) {
        for (Robot robot : robots) {
            if (posFil == robot.getPosFil() && posCol == robot.getPosCol()) {
                return true;
            }
        }

        return (posFil == jugador.getPosFil() && posCol == jugador.getPosCol());
    }

    public void reiniciarJuego(int totalFilas, int totalColumnas) {
        this.jugador.reiniciarEstadoJugador(totalFilas/2, totalColumnas/2);
        this.nivelActual = Ctes.NIVEL_INICIAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    /**
     * ganarNivel pasa al jugador al siguiente nivel del juego
     */
    public void ganarNivel() {
        jugador.ganarNivel(totalFilas/2, totalColumnas/2);
        nivelActual++;
        inicializarRobots();
    }

    /**
     * tpJugador llama al jugador para que se teletransporte a una posicion aleatoria. Esta es una posicion
     * segura (no colisiona con robots) si tpJugador recibe el parametro true. Sino solo lo teletransporta
     * aleatoriamente.
     */
    public void tpJugador(boolean esTPSegura) {
        Random random = new Random();
        int posFil = random.nextInt();
        int posCol = random.nextInt();

        if (esTPSegura) {
            jugador.decrementarUsosRestantesTPSegura();
            while (noEsPosValida(posFil, posCol)) {
                posFil = random.nextInt();
                posCol = random.nextInt();
            }
        }

        jugador.actualizarPosicion(posFil, posCol);
    }

    /**
     * moverJugador mueve al jugador y a los robots en dirección a éste. Si el jugador colisionó con algún
     * robot, la función devuelve false. En caso contrario devuelve true.
     */
    public void moverJugador(int dirFil, int dirCol) {
        jugador.moverse(dirFil, dirCol);
        moverRobots();

        boolean hayRobotsNoDestruidos = false;
        for (Robot robot : robots) {
            if (robot.getPosFil() == jugador.getPosFil() && robot.getPosCol() == jugador.getPosCol()) {
                jugador.setPerdedor(true);
            }
            if (!robot.isDestruido()) {
                hayRobotsNoDestruidos = true;
            }
        }

        if (!hayRobotsNoDestruidos) {
            jugador.setGanador(true);
        }
    }

    /**
     * moverRobots mueve los robots en dirección al jugador y si dos robots colisionan, los destruye.
     */
    public void moverRobots() {
        for (Robot robot : robots) {
            robot.moverse(jugador.getPosFil(), jugador.getPosCol());
        }

        Robot primerRobot = null;
        var robotsAEliminar = new ArrayList<Robot>();
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColumnas; j++) {
                boolean robotEncontrado = false;

                for (Robot robot : robots) {
                    if (robot.getPosFil() == i && robot.getPosCol() == j) {
                        if (!robotEncontrado){
                            primerRobot = robot;
                            robotEncontrado = true;
                        } else { // encontré más robots en una misma posición
                            if (!primerRobot.isDestruido()) {
                                jugador.aumentarPuntaje(primerRobot.getPuntaje());
                                primerRobot.destruir();
                            }
                            jugador.aumentarPuntaje(robot.getPuntaje());
                            robotsAEliminar.add(robot);
                        }
                    }
                }
            }
        }

        for (Robot robotAEliminar : robotsAEliminar) {
            robots.remove((robotAEliminar));
        }
    }

    /**devuelve el estado en el que se encuentra el juego para ser utilizado en la ventana y elegir que ventana/alerta figura
     * o si continuara actualizando la grilla
     */
    public String Estado(){
        if (this.jugador.isPerdedor()){
            return "perdió";
        } else if (this.jugador.isGanador()){
            return "ganó";
        } else {
            return "sigue en juego";
        }
    }
}

