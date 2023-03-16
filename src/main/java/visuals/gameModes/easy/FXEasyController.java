package visuals.gameModes.easy;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.EasyEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;
import visuals.menu.Menu;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class FXEasyController extends FXAbstractGameController implements Initializable, FXIGameController {

    @FXML GridPane easyGridi;
    @FXML ImageView background;
    @FXML ImageView easyTop;
    @FXML ImageView easyL;
    @FXML ImageView easyBot;
    @FXML ImageView play;
    @FXML ImageView returngame;
    @FXML Pane scorePane;
    @FXML Label p1;
    @FXML Label p2;
    @FXML Label p3;
    @FXML Label p4;
    @FXML Label p5;
    @FXML Label w1;
    @FXML Label w2;
    @FXML Label w3;
    @FXML Label w4;
    @FXML Label w5;
    @FXML ImageView easy3Dgrid;
    @FXML ImageView easyneo;
    @FXML ImageView easyEnd;

    private ICubeFactory easyCubeFactory;
    private EasyEffects easyEffects;
    private ScoreController scoreController;


    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(this::setCamera);
        setImages();
        easyEffects = new EasyEffects();
        easyEffects.setImagesAndComponents(
                background,easyTop,easyBot,easyL,easy3Dgrid,
                play,returngame,easyGridi,easyEnd,easyneo,scorePane);
        Platform.runLater(() -> easyEffects.entrance());
        Platform.runLater(this::setWorldScore);
        Platform.runLater(this::setPersonalScore);
        setStartGame();
    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

    @Override
    public void setImages() {

        background.setImage(ImageCache.getInstance().getGameBackGroundCache().get(0));
        easyTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(7));
        easyTop.setOpacity(0);
        easyBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(8));
        easyBot.setOpacity(0);
        easyL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(9));
        easyL.setOpacity(0);
        easy3Dgrid.setImage(ImageCache.getInstance().getGameBackGroundCache().get(10));
        easy3Dgrid.setOpacity(0);
        play.setImage(ImageCache.getInstance().getGameBackGroundCache().get(14));
        play.setOpacity(0);
        returngame.setImage(ImageCache.getInstance().getGameBackGroundCache().get(15));
        returngame.setOpacity(0);
        easyGridi.setHgap(-80);
        easyEnd.setOpacity(0);
    }

    @Override
    public void setWorldScore() {

        if(Menu.worldList != null && !Menu.worldList.isEmpty()) {

            w1.setText(Menu.worldList.get(0));
            w2.setText(Menu.worldList.get(1));
            w3.setText(Menu.worldList.get(2));
            w4.setText(Menu.worldList.get(3));
            w5.setText(Menu.worldList.get(4));
        }
    }

    @Override
    public void setPersonalScore() {

        if(Menu.personalList != null && !Menu.personalList.isEmpty()) {

            p1.setText(Menu.personalList.get(0));
            p2.setText(Menu.personalList.get(1));
            p3.setText(Menu.personalList.get(2));
            p4.setText(Menu.personalList.get(3));
            p5.setText(Menu.personalList.get(4));
        }
    }

    // To gamecontroller
    @Override
    public void setStartGame() {

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        easyGridi.getChildren().clear();
        easyCubeFactory = new EasyCubeFactory(this);
        gameController.startGame(ModeType.EASY);
    }

    // From gamecontroller
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGridi, memoryObjects);
    }

    @FXML
    public void newGame() {

        setStartGame();
    }

    @FXML
    public void returnMenu() {

        Platform.runLater(() -> easyEffects.wallsOff());
    }

    @Override
    public void addToCubeList(BoxMaker cube) {
        super.addToCubeList(cube);
    }

    @Override
    public void clearPair(ArrayList<Integer> storage) {
        super.clearPair(storage);
    }

    @Override
    public void clearStorage() {
        super.clearStorage();
    }

    @Override
    public void gameOver() {

        Menu.getWorldScore(scoreController.getScores(ModeType.EASY));
        Menu.getPersonalScore(scoreController.getPersonalScores(ModeType.EASY));
        Platform.runLater(this::setPersonalScore);
        Platform.runLater(this::setWorldScore);
        System.out.println("game over");
    }

    @Override
    public void setActiveID(int activeID) {
        super.setActiveID(activeID);
    }

    @Override
    public void compareFoundMatch() {
        super.compareFoundMatch();
    }

    @Override
    public void getTime(int i) {
        super.getTime(i);
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }
}
