package robots;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.recursos.Ctes;
import robots.vista.JuegoVista;

public class Robots extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Robots");
        var juegoModelo = new JuegoModelo(Ctes.TAM_POR_DEFECTO_FILA, Ctes.TAM_POR_DEFECTO_COLUMNA);
        var juegoVista = new JuegoVista(juegoModelo);

        var scene = new Scene(juegoVista.getVista(), 640, 480); // en realidad hay que setear el tamaño dela ventana segun el de la grilla??
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}