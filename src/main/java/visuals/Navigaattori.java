package visuals;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ModeType;
import java.io.IOException;
import java.util.Objects;

public class Navigaattori extends Application {

    //......................................FXML sijainnit.................................................

    private static Navigaattori instance;
    private final String MENU = "/visuals/menu/menu.fxml";
    private static Stage MAINSTAGE;
    public static PerspectiveCamera camera = new PerspectiveCamera();


    public static void main(String[] args) {
        launch(args);
    }

    public static Navigaattori getInstance() {

        if(instance == null) {
            instance = new Navigaattori();
        }
        return instance;
    }

    //.....................................Ruudun vaihto...................................................

    public void
    changeScene (ModeType type) throws IOException {

        Parent pane = new Pane();

        switch (type) {

            case MENU -> pane = FXMLLoader.load (Objects.requireNonNull(getClass().getResource(MENU)));
            case EASY -> pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/gameModes/easy/easy.fxml")));
            case MEDIUM -> pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/gameModes/medium/medium.fxml")));
            case HARD -> pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/gameModes/hard/hard.fxml")));
        }

        MAINSTAGE.getScene().setRoot(pane);
    }


    @Override public void start (Stage stage) throws Exception {

        MAINSTAGE = new Stage ();
        Platform.setImplicitExit(true);
        MAINSTAGE.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        Parent root = FXMLLoader.load (Objects.requireNonNull(getClass().getResource(MENU)));
        Scene scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        scene.setCamera(camera);
        MAINSTAGE.setScene (scene);
        MAINSTAGE.setResizable (false);
        MAINSTAGE.centerOnScreen ();
        MAINSTAGE.setFullScreen (false);
        MAINSTAGE.show ();
    }
}
