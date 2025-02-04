import controller.ScoreController;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;
import visuals.imageServers.ImageCache;

import java.util.Objects;

/**
 * Launcher class for the application
 *
 */
public class Start {

    /**
     * main method, loads the images, fonts, and scores. lastly starts the application.
     */
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            ScoreController sc = new ScoreController();
            sc.fetchScores(ModeType.EASY);
            sc.fetchScores(ModeType.MEDIUM);
            sc.fetchScores(ModeType.HARD);
        });
        thread.start();

        ImageCache.getInstance().addToMenuCache();
        ImageCache.getInstance().addToEasyCache();
        ImageCache.getInstance().addToMediumCache();
        ImageCache.getInstance().addToHardCache();
        ImageCache.getInstance().addToGameBackGroundCache();

        // load atari classic font to memory.
        Font.loadFont(Objects.requireNonNull(Start.class.getClassLoader().getResource("fonts/AtariClassic-gry3.ttf")).toExternalForm(), 14);



        Navigaattori.main(args);
    }
}

