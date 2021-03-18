package tic_tac_toe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import java.util.Random;

public class App extends Application {
    Image imageback = new Image("file:src/main/resources/blackboard.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(1920, 1280, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gridPane = new GridPane();
        gridPane.setBackground(background);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        Image imageX = new Image("file:src/main/resources/circle.gif");
        Image imageO = new Image("file:src/main/resources/cross.gif");
        Image rightLine = new Image("file:src/main/resources/rightLine.gif");
        Image bottomLine = new Image("file:src/main/resources/bottomLine.gif");

        Random random = new Random();

        for (int k=0; k<3; k++) {
            for (int l=0; l<2; l++) {
                    gridPane.add(new ImageView(rightLine), l, k);
            }
        }

        for (int m=0; m<3; m++) {
            for (int n=0; n<2; n++) {
                gridPane.add(new ImageView(bottomLine), m, n);
            }
        }

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                int status = (random.nextInt(2));
                if (status == 0) {
                    gridPane.add(new ImageView(imageX), j, i);
                } else if (status == 1) {
                    gridPane.add(new ImageView(imageO), j, i);
                }
            }
        }

        Scene scene = new Scene(gridPane, 600, 800, Color.BLACK);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

        Button playBtn = new Button();
        gridPane.add(playBtn, 0,5);
        playBtn.setStyle("-fx-background-color: #258325; -fx-font-size: 25px; -fx-text-fill: white ");
        playBtn.setText("PLAY");

        Button stopBtn = new Button();
        stopBtn.setText("STOP");
        gridPane.add(stopBtn, 2,5);
        stopBtn.setStyle("-fx-background-color: #a01010; -fx-font-size: 25px; -fx-text-fill: white ");

    }
}
