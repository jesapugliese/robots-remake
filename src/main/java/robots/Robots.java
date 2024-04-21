package robots;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import robots.modelo.JuegoModelo;
import robots.vista.JuegoVista;
import robots.vista.PreguntarTamanioGrilla;

public class Robots extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Robots");
        var tamanioGrilla = new PreguntarTamanioGrilla();
        var juegoModelo = new JuegoModelo(tamanioGrilla.getFilas(), tamanioGrilla.getColumnas());
        var juegoVista = new JuegoVista(juegoModelo);

        VBox vbox = juegoVista.getVista();
        Group raiz = new Group(vbox);

        var scene = new Scene(raiz, 640, 480); // en realidad hay que setear el tamaño dela ventana segun el de la grilla
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}