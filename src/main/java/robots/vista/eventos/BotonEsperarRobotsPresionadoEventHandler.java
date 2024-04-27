package robots.vista.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import robots.vista.Grilla;
import robots.vista.JuegoVista;

public class BotonEsperarRobotsPresionadoEventHandler implements EventHandler<ActionEvent> {
    private final Grilla grilla;
    private final JuegoVista juegoVista;

    public void BotonEsperarRobotsPresionadoEventHandler( Grilla grilla, JuegoVista juegoVista) {
        this.grilla = grilla;
        this.juegoVista = juegoVista;
    }
}
