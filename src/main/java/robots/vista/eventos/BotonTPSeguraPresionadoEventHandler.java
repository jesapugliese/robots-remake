package robots.vista.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import robots.vista.Grilla;
import robots.vista.JuegoVista;

public class BotonTPSeguraPresionadoEventHandler implements EventHandler<ActionEvent> {
    private final Grilla grilla;
    private final JuegoVista juegoVista;

    // constructor
    public BotonTPSeguraPresionadoEventHandler(Grilla grilla, JuegoVista juegoVista) {
        this.grilla = grilla;
        this.juegoVista = juegoVista;
    }
    @Override
    public void handle(ActionEvent ActionEvent){
        var juegoModelo = grilla.getJuegoModelo();
        juegoModelo.tpJugador(true);
        grilla.actualizarGrilla();
        juegoVista.actualizarEtiquetas(this.grilla);

    }
}
