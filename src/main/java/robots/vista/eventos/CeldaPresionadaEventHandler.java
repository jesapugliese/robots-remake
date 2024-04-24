package robots.vista.eventos;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CeldaPresionadaEventHandler implements EventHandler<MouseEvent> {
    private final int posFil;
    private final int posCol;

    public CeldaPresionadaEventHandler(int posFil, int posCol) {
        this.posFil = posFil;
        this.posCol = posCol;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.printf("Apretaste la celda (%d, %d)\n", posFil, posCol);
        //aca tendriamos que poner el codigo que llama a las cosas de JuegoModelo creo.
        //Al final de que se hacen los cambios necesarios, tengo entendido que hay que actualizar la grilla y los personajes
        //y todo.
    }
}
