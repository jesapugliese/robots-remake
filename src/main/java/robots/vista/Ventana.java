package robots.vista;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;

public class Ventana {
    private VBox vista;

    public Ventana(JuegoModelo juegoModelo) {
        var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));
        var hboxSuperior = new HBox(labelInfoJuego);

        var grilla = new Grilla(juegoModelo.getTotalFilas(), juegoModelo.getTotalColumnas());
        var hboxGrilla = new HBox(grilla.getGrilla());
        hboxGrilla.setAlignment(Pos.CENTER); // Center the grid within the HBox

        var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
        var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
        var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
        var hboxInferior = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);

        this.vista = new VBox(hboxSuperior, hboxInferior);
    }

    public VBox getVista() {
        return vista;
    }
}
