package robots.vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import robots.vista.eventos.CeldaPresionadaEventHandler;
import java.util.Objects;

public class Celda {
    private final int posFil;
    private final int posCol;
    private final ImageView vista;

    public Celda(int posFil, int posCol, String rutaImgCelda) {
        this.posFil = posFil;
        this.posCol = posCol;

        Image imgCelda = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaImgCelda)));
        var imageViewCelda = new ImageView(imgCelda);
        imageViewCelda.setFitHeight(20);
        imageViewCelda.setFitWidth(20);

        imageViewCelda.setOnMouseClicked(new CeldaPresionadaEventHandler(this.posFil, this.posCol));

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
