package tic_tac_toe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application implements EventHandler<ActionEvent> {

    private Stage primary;
    private Scene scene;
    private GridPane gridPane;
    private Button[] buttons;
    private final int maxButtons = 9;

    public  static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage arg0) throws Exception {

        Image imageBack = new Image("file:src/main/resources/blackboard.png");
        BackgroundSize backgroundSize = new BackgroundSize(1920, 1280, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        primary = arg0;
        gridPane = new GridPane();
        scene = new Scene(gridPane, 700,900);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setBackground(background);

        buttons = new Button[maxButtons];

        for (int i = 0; i< maxButtons; i++) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(150,150);
            buttons[i].setOnAction(this);
            buttons[i].setOpacity(0.3);
            buttons[i].setStyle("-fx-text-fill: #ff0000; -fx-font-size: 60");
        }

        javafx.scene.control.Button playBtn = new javafx.scene.control.Button();
        gridPane.add(playBtn,0,6);
        playBtn.setStyle("-fx-background-color: #258325; -fx-font-size: 25px; -fx-text-fill: white; -fx-min-width: 150px; -fx-translate-y: 100px");
        playBtn.setText("PLAY");

        javafx.scene.control.Button stopBtn = new javafx.scene.control.Button();
        gridPane.add(stopBtn, 2,6);
        stopBtn.setStyle("-fx-background-color: #a01010; -fx-font-size: 25px; -fx-text-fill: white; -fx-min-width: 150px; -fx-translate-y: 100px");
        stopBtn.setText("STOP");

        int buttonIndex = 0;
        for (int rowIndex=0; rowIndex<3; rowIndex++) {
            for (int colIndex=0; colIndex<3; colIndex++) {
                gridPane.add(buttons[buttonIndex], colIndex, rowIndex);
                buttonIndex++;
            }
        }

        primary.setTitle("Tic-Tac-Toe");
        primary.setScene(scene);
        primary.show();

    }


    @Override
    public void handle(ActionEvent event) {
        for (int i = 0; i< maxButtons; i++) {
            if (buttons[i]==event.getSource()) {
                buttons[i].changeState();
            }
        }
    }
}
