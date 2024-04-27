package robots.vista;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;

public class JuegoVista {
    private final JuegoModelo juegoModelo;
    private final VBox vista;
    private final Stage stage;

    public JuegoVista(JuegoModelo juegoModelo, Stage stage) {
        this.juegoModelo = juegoModelo;
        var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));

        var grilla = new Grilla(juegoModelo, this, stage);

        var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
        var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
        var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
        var hboxBotones = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);
        hboxBotones.setAlignment(Pos.CENTER);

        var vboxVentana = new VBox(labelInfoJuego, grilla.getGrilla(), hboxBotones);
        vboxVentana.setAlignment(Pos.CENTER);

        this.vista = vboxVentana;
        this.stage = stage;
    }

    public void iniciar() {
        stage.setTitle("Robots");
        var scene = new Scene(vista);
        stage.setScene(scene);
        stage.show();
    }

    public void actualizarEtiquetas() {
        if (vista != null) {
            var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));
            vista.getChildren().removeFirst();
            vista.getChildren().addFirst(labelInfoJuego);

            var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
            var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
            var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
            var hboxBotones = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);
            hboxBotones.setAlignment(Pos.CENTER);

            vista.getChildren().removeLast();
            vista.getChildren().addLast(hboxBotones);
        }
    }
}
