package robots;

import javafx.application.Application;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;
import robots.vista.JuegoVista;

public class Robots extends Application {
    @Override
    public void start(Stage stage) {
        var juegoModelo = new JuegoModelo(Ctes.TAM_POR_DEFECTO_FILA, Ctes.TAM_POR_DEFECTO_COLUMNA);
        var juegoVista = new JuegoVista(juegoModelo, stage);
        juegoVista.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}

/*
-poner los setOnAction de los botones, porque todavia no hacen nada (falta chequear si funcionan los 2 hechos)
-settear la cantidad de robots segun la dificultad, tenemos que ver eso de la proporcion
-poner los comentarios a las funciones
-pasar lo que tengamos que pasar a constantes
-eventos teclado
-completar la espera de robots
-corregir cuando pierde o no el juego
 */