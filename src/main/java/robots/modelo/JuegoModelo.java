package robots.modelo;

import robots.recursos.Ctes;
import java.util.ArrayList;
import java.util.Random;

public class JuegoModelo {
    private Jugador jugador;
    private ArrayList<Robot> robots;
    private int nivelActual;
    private int totalFilas;
    private int totalColumnas;

    public JuegoModelo(int totalFilas, int totalColumnas) {
        this.jugador = new Jugador(totalFilas/2, totalColumnas/2);
        this.nivelActual = Ctes.NIVEL_INICAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public int getTotalColumnas() {
        return totalColumnas;
    }

    /**
     * inicializarRobots inicializa un conjunto de robots según la dificultad dada por el nivelActual.
     * La cantidad de robots varía según el nivel y los robots pueden ser simples (de un paso) o dobles (de dos pasos).
     */
    private void inicializarRobots() {
        Random random = new Random();
        robots = new ArrayList<>();
        int cantRobots = nivelActual*totalFilas; //ver, la idea seria asignar cada vez mas cantidad de robots por nivel

        for (int i = 0; i < cantRobots; i++) {
            int posFil = random.nextInt();
            int posCol = random.nextInt();
            while (noEsPosValida(posFil, posCol)) {
                posFil = random.nextInt();
                posCol = random.nextInt();
            }

            Robot nuevoRobot;
            if (i > cantRobots/2) {
                nuevoRobot = new Robot(posFil, posCol, Ctes.PASO_DOBLE, Ctes.PUNTAJE_ROBOT_DOBLE, i);
            } else {
                nuevoRobot = new Robot(posFil, posCol, Ctes.PASO_SIMPLE, Ctes.PUNTAJE_ROBOT_SIMPLE, i);
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
            if (robot.getPosFil() == posFil && robot.getPosCol() == posCol) {
                return true;
            }
        }
        return (posFil == jugador.getPosFil() && posCol == jugador.getPosCol());
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

        jugador.tp(posFil, posCol);
    }

    /**
     * ganarNivel pasa al jugador al siguiente nivel del juego
     */
    public void ganarNivel() {
        jugador.setPosFil(totalFilas/2);
        jugador.setPosCol(totalColumnas/2);
        jugador.incrementarUsosRestantesTPSegura();
        nivelActual++;
        inicializarRobots();
    }

    /**
     * perder vuelve al juego al estado inicial del juego
     */
    public void perder(int totalFilas, int totalColumnas) {
        jugador.reiniciarEstado(totalFilas/2, totalColumnas/2);
        nivelActual = Ctes.NIVEL_INICAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    /**
     * moverJugador mueve al jugador y a los robots en dirección a éste. Si el jugador colisionó con algún
     * robot, la función devuelve false. En caso contrario devuelve true.
     */
    public boolean moverJugador(int dirFil, int dirCol) {
        jugador.moverse(dirFil, dirCol);
        moverRobots();
        for (Robot robot : robots){
            if (jugador.getPosFil() == robot.getPosFil() && jugador.getPosCol() == robot.getPosCol()){
                return false;
            }
        }
        return true;
    }

    /**
     * moverRobots mueve los robots en dirección al jugador y si dos robots colisionan, los destruye.
     */
    public void moverRobots() { // esto se ejecuta tambien cuando se llama a esperar por robots.
        for (Robot robot1 : robots){
            robot1.moverse(jugador.getPosFil(), jugador.getPosCol());
            for (Robot robot2 : robots){
                if (robot1.getId() == robot2.getId() || robot1.isDestruido() && robot2.isDestruido()){
                    continue;
                }
                if (robot1.getPosFil() == robot2.getPosFil() && robot1.getPosCol() == robot2.getPosCol()){
                    if (robot1.isDestruido()) {
                        jugador.aumentarPuntaje(robot2.getPuntaje());
                        robot2.destruir();
                    } else if (robot2.isDestruido()){
                        jugador.aumentarPuntaje(robot1.getPuntaje());
                        robot1.destruir();
                    } else {
                        jugador.aumentarPuntaje(robot1.getPuntaje()+robot2.getPuntaje());
                        robot1.destruir();
                        robot2.destruir();
                    }
                }
            }
        }
    }


}