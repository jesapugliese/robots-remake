package robots.vista.eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import robots.modelo.JuegoModelo;
import robots.vista.Grilla;
import robots.vista.JuegoVista;
import robots.vista.Ventana;

public class CeldaPresionadaEventHandler implements EventHandler<MouseEvent> {
    private final int posFil;
    private final int posCol;
    private final JuegoModelo juegoModelo;
    private Grilla grilla;

    public CeldaPresionadaEventHandler(int posFil, int posCol, JuegoModelo juegoModelo, Grilla grilla) {
        this.posFil = posFil;
        this.posCol = posCol;
        this.juegoModelo = juegoModelo;
        this.grilla = grilla;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.printf("Apretaste la celda (%d, %d)\n", posFil, posCol);

        var seMovio = juegoModelo.moverJugador(posFil, posCol);
        grilla.actualizarGrilla();
        if (!seMovio) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Desea continuar jugando?");
            alert.setContentText("Seleccione una opción.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("El usuario eligió continuar jugando.");

                    //preguntar filas columnas
                    //juegoModelo = new JuegoModelo(filas, columnas);

                } else {
                    System.out.println("El usuario eligió salir del juego.");
                }
            });
            //poner ventana seguir jugando
        }

        //aca tendriamos que poner el codigo que llama a las cosas de JuegoModelo creo.
        //Al final de que se hacen los cambios necesarios, tengo entendido que hay que actualizar la grilla y los personajes
        //y todo.
    }
}
