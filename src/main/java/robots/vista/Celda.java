package robots.vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import robots.modelo.JuegoModelo;
import robots.vista.eventos.CeldaPresionadaEventHandler;
import java.util.Objects;

public class Celda {
    private final int posFil;
    private final int posCol;
    private final ImageView vista;

    public Celda(int posFil, int posCol, String rutaImgCelda, JuegoModelo juegoModelo, Grilla grilla) {
        this.posFil = posFil;
        this.posCol = posCol;

        Image imgCelda = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaImgCelda)));
        var imageViewCelda = new ImageView(imgCelda);
        imageViewCelda.setFitHeight(20);
        imageViewCelda.setFitWidth(20);

        imageViewCelda.setOnMouseClicked(new CeldaPresionadaEventHandler(this.posFil, this.posCol, juegoModelo, grilla));

        this.vista = imageViewCelda;
    }

    public int getPosFil() {
        return posFil;
    }

    public int getPosCol() {
        return posCol;
    }

    public ImageView getVista() {
        return vista;
    }
}
