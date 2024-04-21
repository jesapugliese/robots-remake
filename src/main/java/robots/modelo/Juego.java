package robots.modelo;

import java.util.ArrayList;

public class Juego {
    private Jugador jugador;
    private ArrayList<Robot> robots;
    private int nivelActual;
    private int totalFilas;
    private int totalColumnas;

    public Juego(int totalFilas, int totalColumnas){
        this.jugador = new Jugador(totalFilas/2, totalColumnas/2);
        this.nivelActual = Ctes.NIVEL_INICAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    /**
     * inicializarRobots inicializa un conjunto de robots según la dificultad dada por el nivelActual.
     * La cantidad de robots varía según el nivel y los robots pueden ser simples (de un paso) o dobles (de dos pasos).
     */
    private void inicializarRobots(){
        robots = new ArrayList<Robot>();
        // Aca generar una cantidad de robots que dependa del nivelActual (dificultad) y
        // del tamaño de la grilla. Asignar ids distintos a cada uno.
    }

    /**
     * GanarNivel pasa al jugador al siguiente nivel del juego
     */
    public void GanarNivel(){
        jugador.setPosFil(totalFilas/2);
        jugador.setPosCol(totalColumnas/2);
        jugador.IncrementarUsosRestantesTPSegura();
        nivelActual++;
        inicializarRobots();
    }

    /**
     * Perder vuelve al juego al estado inicial del juego
     */
    public void Perder(int totalFilas, int totalColumnas){
        jugador.ReiniciarEstado(totalFilas/2, totalColumnas/2);
        nivelActual = Ctes.NIVEL_INICAL;
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        inicializarRobots();
    }

    /**
     * MoverJugador mueve al jugador y a los robots en dirección a éste. Si el jugador colisionó con algún
     * robot, la función devuelve false. En caso contrario devuelve true.
     */
    public boolean MoverJugador(int dirFil, int dirCol){
        jugador.Moverse(dirFil, dirCol);
        MoverRobots();
        for (Robot robot : robots){
            if (jugador.getPosFil() == robot.getPosFil() && jugador.getPosCol() == robot.getPosCol()){
                return false;
            }
        }
        return true;
    }

    /**
     * MoverRobots mueve los robots en dirección al jugador y si dos robots colisionan, los destruye.
     */
    public void MoverRobots(){ // esto se ejecuta tambien cuando se llama a esperar por robots.
        for (Robot robot1 : robots){
            robot1.Moverse(jugador.getPosFil(), jugador.getPosCol());
            for (Robot robot2 : robots){
                if (robot1.getId() == robot2.getId() || robot1.isDestruido() && robot2.isDestruido()){
                    continue;
                }
                if (robot1.getPosFil() == robot2.getPosFil() && robot1.getPosCol() == robot2.getPosCol()){
                    if (robot1.isDestruido()) {
                        jugador.AumentarPuntaje(robot2.getPuntaje());
                        robot2.Destruir();
                    } else if (robot2.isDestruido()){
                        jugador.AumentarPuntaje(robot1.getPuntaje());
                        robot1.Destruir();
                    } else {
                        jugador.AumentarPuntaje(robot1.getPuntaje()+robot2.getPuntaje());
                        robot1.Destruir();
                        robot2.Destruir();
                    }
                }
            }
        }
    }
}