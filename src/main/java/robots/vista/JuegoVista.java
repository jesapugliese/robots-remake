package robots.vista;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;

public class JuegoVista {
    private final JuegoModelo juegoModelo;
    private final VBox vista;

    public JuegoVista(JuegoModelo juegoModelo) {
        this.juegoModelo = juegoModelo;
        var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));

        //var grilla = ???
        var grilla = new Label("aca va la grilla");

        var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
        var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
        var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
        var botonesHbox = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);

        var vbox = new VBox(labelInfoJuego, grilla, botonesHbox);
        vbox.setAlignment(Pos.CENTER);

        //aca poner los set on action de los botones, lo del mouse y asi

        this.vista = vbox;
    }

    public VBox getVista() {
        return vista;
    }
}
