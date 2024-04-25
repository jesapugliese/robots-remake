package robots.vista;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import robots.recursos.Ctes;


public class Grilla {
    private GridPane grilla;

        public Grilla(int filas, int columnas) {
            var grilla = new GridPane();
            grilla.setVgap(0);
            grilla.setHgap(0);

            // Calculating cell size as a percentage of the grid size
            double cellWidthPercentage = 100.0 / columnas;
            double cellHeightPercentage = 100.0 / filas;

            // Configuramos las columnas y filas para que se ajusten al tamaño de la ventana
            for (int i = 0; i < columnas; i++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(cellWidthPercentage);
                grilla.getColumnConstraints().add(colConstraints);
            }
            for (int i = 0; i < filas; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPercentHeight(cellHeightPercentage);
                grilla.getRowConstraints().add(rowConstraints);
            }

            // Creamos las celdas del tablero alternando los colores
            for (int i = 0; i < columnas; i++) {
                for (int j = 0; j < filas; j++) {
                    Color color = (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK; // Alternar entre blanco y negro
                    Rectangle cell = new Rectangle();
                    cell.setFill(color);
                    grilla.add(cell, i, j);
                }
            }

            this.grilla = grilla;
        }

        public GridPane getGrilla() {
        return grilla;
    }
}
