package robots.modelo;

import robots.recursos.Ctes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoModelo {
    private Jugador jugador;
    private Robot[][] robots;
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

    public Robot[][] getRobots() {
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
     * La mitad son de un tipo y la mitad del otro.
     */
    private void inicializarRobots() {
        Random random = new Random();
        robots = new Robot[totalFilas][totalColumnas];
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
            robots[posFil][posCol] = nuevoRobot;
        }
    }

    /**
     * noEsPosValida devuelve true si la posicion pasada por paremetro colisiona con la
     * posicion de otros robots o del jugador. En caso contrario devuelve false.
     */
    private boolean noEsPosValida(int posFil, int posCol) {
        if (robots[posFil][posCol] != null) {
            return true;
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

        jugador.actualizarPosicion(posFil, posCol);
    }

    /**
     * ganarNivel pasa al jugador al siguiente nivel del juego
     */
    public void ganarNivel() {
        jugador.actualizarPosicion(totalFilas/2, totalColumnas/2);
        jugador.incrementarUsosRestantesTPSegura();
        nivelActual++;
        inicializarRobots();
    }

    /**
     * perder vuelve al juego al estado inicial del juego
     */

    /**
     * moverJugador mueve al jugador y a los robots en dirección a éste. Si el jugador colisionó con algún
     * robot, la función devuelve false. En caso contrario devuelve true.
     */
    public boolean moverJugador(int dirFil, int dirCol) {
        var nuevaPos = jugador.moverse(dirFil, dirCol);
        jugador.actualizarPosicion(nuevaPos[0], nuevaPos[1]);
        moverRobots();

        if (robots[jugador.getPosFil()][jugador.getPosCol()] == null) {
            return true;
        }

        jugador.perder();
        return false;
    }

    /**
     * moverRobots mueve los robots en dirección al jugador y si dos robots colisionan, los destruye.
     */
    public void moverRobots() {
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColumnas; j++) {
                if (robots[i][j] != null) {
                    Robot robotActual = robots[i][j];
                    if (robotActual.isDestruido()) { continue; }
                    var nuevaPos = robotActual.moverse(jugador.getPosFil(), jugador.getPosCol());
                    if (robots[nuevaPos[0]][nuevaPos[1]] != null) {
                        Robot robotColision = robots[nuevaPos[0]][nuevaPos[1]];

                        if (robotColision.isDestruido()) {
                            jugador.aumentarPuntaje(robotActual.getPuntaje());
                            robotActual.actualizarPosicion(nuevaPos[0], nuevaPos[1]);
                            robotActual.destruir();
                        } else {
                            jugador.aumentarPuntaje(robotActual.getPuntaje());
                            jugador.aumentarPuntaje(robotColision.getPuntaje());
                            robotActual.actualizarPosicion(nuevaPos[0], nuevaPos[1]);
                            robotActual.destruir();
                            robotColision.destruir();
                        }
                    }
                }
            }
        }
    }
}