package robots.vista;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import robots.recursos.Ctes;

public class Grilla {
    private GridPane grilla;

    public Grilla(int filas, int columnas) {
        var grilla = new GridPane();
        grilla.setVgap(0);
        grilla.setHgap(0);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                var celda = new Celda(i, j, Ctes.IMG_CELDA);
                grilla.add(celda.getVista(), j, i);
            }
        }

        grilla.setGridLinesVisible(true);

        this.grilla = grilla;
    }

    public GridPane getGrilla() {
        return grilla;
    }
}
