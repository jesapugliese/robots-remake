package robots.vista;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.modelo.Robot;
import robots.recursos.Ctes;
import robots.vista.eventos.CeldaPresionadaEventHandler;

import java.util.Objects;

public class Grilla {
    private final GridPane grilla;
    private final JuegoModelo juegoModelo;
    private final JuegoVista juegoVista;
    private final Stage stage;


    public Grilla(JuegoModelo juegoModelo, JuegoVista juegoVista, Stage stage) {
        this.juegoModelo = juegoModelo;
        this.stage = stage;
        this.juegoVista = juegoVista;
        this.grilla = new GridPane();
        inicializarGrilla();
        actualizarGrilla();
        juegoVista.actualizarEtiquetas();
    }

    public GridPane getGrilla() {
        return grilla;
    }

    public JuegoModelo getJuegoModelo() {
        return juegoModelo;
    }

    public void reiniciarJuego(int totalFilas, int totalColumnas) {
        juegoModelo.reiniciarJuego(totalFilas, totalColumnas);
        inicializarGrilla();
        actualizarGrilla();
    }

    private void inicializarGrilla() {
        grilla.getChildren().clear();
        grilla.setVgap(0);
        grilla.setHgap(0);

        for (int i = 0; i < juegoModelo.getTotalFilas(); i++) {
            for (int j = 0; j < juegoModelo.getTotalColumnas(); j++) {
                Celda celda;
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    celda = new Celda(i, j, Ctes.IMG_CELDA_OSCURA, this, juegoVista);
                } else {
                    celda = new Celda(i, j, Ctes.IMG_CELDA_CLARA, this, juegoVista);
                }
                var stackPane = new StackPane(celda.getVista());
                grilla.add(stackPane, j, i);
            }
        }
        grilla.setAlignment(Pos.CENTER);
        stage.sizeToScene();
    }

    public void actualizarGrilla() {
        // Vacío la grilla
        for (Node nodo : grilla.getChildren()) {
            if (nodo instanceof StackPane stack) {
                if (stack.getChildren().size() > 1) {
                    stack.getChildren().remove(stack.getChildren().getLast());
                }
            }
        }

        // Lleno la grilla con la información actual
        for (Node nodo : grilla.getChildren()) {
            if (nodo instanceof StackPane stack) {
                if (juegoModelo.getJugador().getPosFil() == GridPane.getRowIndex(nodo) && juegoModelo.getJugador().getPosCol() == GridPane.getColumnIndex(nodo)) {
                    Image imgJugador;
                    if (juegoModelo.getJugador().isPerdedor()) {
                        imgJugador = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_JUGADOR_PERDEDOR)));
                    } else {
                        imgJugador = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_JUGADOR)));
                    }
                    var imageViewJugador = new ImageView(imgJugador);
                    imageViewJugador.setOnMouseClicked(new CeldaPresionadaEventHandler(GridPane.getRowIndex(nodo), GridPane.getColumnIndex(nodo), this, juegoVista));
                    imageViewJugador.setFitHeight(20);
                    imageViewJugador.setPreserveRatio(true);
                    stack.getChildren().add(imageViewJugador);
                } else {
                    for (Robot robot : juegoModelo.getRobots()) {
                        if (robot.getPosFil() == GridPane.getRowIndex(nodo) && robot.getPosCol() == GridPane.getColumnIndex(nodo)) {
                            Image imgRobot = null;
                            if (robot.isDestruido()) {
                                imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_EXPLOSION)));
                            } else if (robot.getPasos() == Ctes.PASO_SIMPLE) {
                                imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_ROBOT_SIMPLE)));
                            } else if (robot.getPasos() == Ctes.PASO_DOBLE) {
                                imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_ROBOT_DOBLE)));
                            }
                            var imageViewRobot = new ImageView(imgRobot);
                            imageViewRobot.setOnMouseClicked(new CeldaPresionadaEventHandler(GridPane.getRowIndex(nodo), GridPane.getColumnIndex(nodo), this, juegoVista));
                            imageViewRobot.setFitHeight(20);
                            imageViewRobot.setPreserveRatio(true);
                            stack.getChildren().add(imageViewRobot);
                            break;
                        }
                    }
                }
            }
        }
    }
}
