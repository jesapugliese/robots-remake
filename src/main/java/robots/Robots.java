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
-poner el tamaño de los botones ajustado a la ventana
-poner los setOnAction de los botones, porque todavia no hacen nada
-settear la cantidad de robots segun la dificultad, tenemos que ver eso de la proporcion
-poner los comentarios a las funciones
-pasar lo que tengamos que pasar a constantes
 */