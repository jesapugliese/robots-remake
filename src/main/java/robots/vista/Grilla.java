package robots.vista;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import robots.modelo.JuegoModelo;
import robots.modelo.Robot;
import robots.recursos.Ctes;

import java.util.Objects;

public class Grilla {
    private GridPane grilla;
    private JuegoModelo juegoModelo;

    public Grilla(JuegoModelo juegoModelo) {
        var grilla = new GridPane();
        grilla.setVgap(0);
        grilla.setHgap(0);

        for (int i = 0; i < juegoModelo.getTotalFilas(); i++) {
            for (int j = 0; j < juegoModelo.getTotalColumnas(); j++) {
                var celda = new Celda(i, j, Ctes.IMG_CELDA, juegoModelo, this);
                var stackPane = new StackPane(celda.getVista());
                grilla.add(stackPane, j, i);
            }
        }
        grilla.setGridLinesVisible(true);

        actualizarGrilla();

        this.grilla = grilla;
        this.juegoModelo = juegoModelo;
    }

    public GridPane getGrilla() {
        return grilla;
    }

    public void actualizarGrilla() {
        for (Node nodo : grilla.getChildren()) {
            if (nodo instanceof StackPane stack) {
                if (stack.getChildren().size() > 1) {
                    stack.getChildren().remove(stack.getChildren().getLast());
                }
            }
        }

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
                    imageViewJugador.setFitHeight(20);
                    imageViewJugador.setFitWidth(20);
                } else if (juegoModelo.getRobots()[GridPane.getRowIndex(nodo)][GridPane.getColumnIndex(nodo)] != null) {
                    Robot robot = juegoModelo.getRobots()[GridPane.getRowIndex(nodo)][GridPane.getColumnIndex(nodo)];
                    Image imgRobot = null;
                    if (robot.isDestruido()) {
                        imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_EXPLOSION)));
                    } else if (robot.getPasos() == Ctes.PASO_SIMPLE) {
                        imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_ROBOT_SIMPLE)));
                    } else if (robot.getPasos() == Ctes.PASO_DOBLE) {
                        imgRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Ctes.IMG_ROBOT_DOBLE)));
                    }
                    var imageViewRobot = new ImageView(imgRobot);
                    imageViewRobot.setFitHeight(20);
                    imageViewRobot.setFitWidth(20);

                    stack.getChildren().add(imageViewRobot);
                }
            }
        }
    }
}
