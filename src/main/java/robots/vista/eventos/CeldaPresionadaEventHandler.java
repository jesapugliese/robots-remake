package robots.vista.eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import robots.vista.Grilla;
import robots.vista.JuegoVista;

public class CeldaPresionadaEventHandler implements EventHandler<MouseEvent> {
    private final int posFil;
    private final int posCol;
    private final Grilla grilla;
    private final JuegoVista juegoVista;

    public CeldaPresionadaEventHandler(int posFil, int posCol, Grilla grilla, JuegoVista juegoVista) {
        this.posFil = posFil;
        this.posCol = posCol;
        this.grilla = grilla;
        this.juegoVista = juegoVista;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        var juegoModelo = grilla.getJuegoModelo();
        juegoModelo.moverJugador(posFil, posCol);
        grilla.actualizarGrilla();
        juegoVista.actualizarEtiquetas(this.grilla);

        if (juegoModelo.getJugador().isPerdedor()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Llegaste al nivel %d y tu puntaje fue de %d!".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));
            alert.setContentText("¿Deseas continuar jugando?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    var confFilCol = new ConfiguracionFilasColumnas(juegoModelo.getTotalFilas(), juegoModelo.getTotalColumnas());
                    confFilCol.preguntarConfFilasColumnas();
                    grilla.reiniciarJuego(confFilCol.getTotalFilasActual(), confFilCol.getTotalColumnasActual());
                    juegoVista.actualizarEtiquetas(this.grilla);
                } else {
                    System.exit(0);
                }
            });
        }
        if (juegoModelo.getJugador().isGanador()) {
            juegoModelo.ganarNivel();
            grilla.actualizarGrilla();
            juegoVista.actualizarEtiquetas(this.grilla);
        }
    }
}
