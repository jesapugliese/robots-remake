package robots.vista;

import javafx.scene.layout.VBox;
import robots.modelo.JuegoModelo;

public class JuegoVista {
    private final JuegoModelo juegoModelo;
    private final VBox vista;

    public JuegoVista(JuegoModelo juegoModelo) {
        this.juegoModelo = juegoModelo;
        var ventana = new Ventana(juegoModelo);
        this.vista = ventana.getVista();
    }

    public VBox getVista() {
        return vista;
    }
}
