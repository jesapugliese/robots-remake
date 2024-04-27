package robots.vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;
import robots.vista.eventos.BotonEsperarRobotsPresionadoEventHandler;
import robots.vista.eventos.BotonTPAleatoriaPresionadoEventHandler;
import robots.vista.eventos.BotonTPSeguraPresionadoEventHandler;

public class JuegoVista {
    private final JuegoModelo juegoModelo;
    private final VBox vista;
    private final Stage stage;
/*** Constructor de la ventana en la que se muestra el juego ***/
    public JuegoVista(JuegoModelo juegoModelo, Stage stage) {
        
        this.juegoModelo = juegoModelo;
        var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));

        var grilla = new Grilla(juegoModelo, this, stage);



        var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
        var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
        var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
        // configuro los botones para que ocupen todo el ancho de la ventana
        HBox.setHgrow(botonTPAleatoria, Priority.ALWAYS);
        HBox.setHgrow(botonTPSegura, Priority.ALWAYS);
        HBox.setHgrow(botonEsperarPorRobots, Priority.ALWAYS);

        // Configuramos la expansión horizontal en los botones
        botonTPSegura.setMaxWidth(Double.MAX_VALUE);
        botonTPAleatoria.setMaxWidth(Double.MAX_VALUE);
        botonEsperarPorRobots.setMaxWidth(Double.MAX_VALUE);

        HBox hboxBotones = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);
        hboxBotones.setAlignment(Pos.CENTER);
        // asigno handler a los eventos
        botonTPSegura.setOnAction(new BotonTPSeguraPresionadoEventHandler(grilla, this));
        botonTPAleatoria.setOnAction(new BotonTPAleatoriaPresionadoEventHandler(grilla, this));
        botonEsperarPorRobots.setOnAction(new BotonEsperarRobotsPresionadoEventHandler(grilla, this));


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
/*** Actualiza las etiquetas de la vista***/
    public void actualizarEtiquetas(Grilla grilla) {
        if (vista != null) {
            var labelInfoJuego = new Label("Nivel: %d Puntaje: %d".formatted(juegoModelo.getNivelActual(), juegoModelo.getJugador().getPuntaje()));
            vista.getChildren().removeFirst();
            vista.getChildren().addFirst(labelInfoJuego);
            // bontones que interactuan en el juego inicializacion ?
            var botonTPAleatoria = new Button(Ctes.TITULO_BOTON_TP_ALEATORIO);
            var botonTPSegura = new Button(Ctes.TITULO_BOTON_TP_SEGURA + "\n(Quedan: %d)".formatted(juegoModelo.getJugador().getUsosRestantesTPSegura()));
            var botonEsperarPorRobots = new Button(Ctes.TITULO_BOTON_ESPERAR_ROBOTS);
            // configuro los botones para que ocupen todo el ancho de la ventana
            HBox.setHgrow(botonTPAleatoria, Priority.ALWAYS);
            HBox.setHgrow(botonTPSegura, Priority.ALWAYS);
            HBox.setHgrow(botonEsperarPorRobots, Priority.ALWAYS);

            // Configuramos la expansión horizontal en los botones
            botonTPSegura.setMaxWidth(Double.MAX_VALUE);
            botonTPAleatoria.setMaxWidth(Double.MAX_VALUE);
            botonEsperarPorRobots.setMaxWidth(Double.MAX_VALUE);

            var hboxBotones = new HBox(botonTPAleatoria, botonTPSegura, botonEsperarPorRobots);
            hboxBotones.setAlignment(Pos.CENTER);
            // asigno handler a los eventos
            botonTPSegura.setOnAction(new BotonTPSeguraPresionadoEventHandler(grilla, this));
            botonTPAleatoria.setOnAction(new BotonTPAleatoriaPresionadoEventHandler(grilla, this));
            botonEsperarPorRobots.setOnAction(new BotonEsperarRobotsPresionadoEventHandler(grilla, this));

            vista.getChildren().removeLast();
            vista.getChildren().addLast(hboxBotones);
        }
    }
}
