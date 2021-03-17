package tic_tac_toe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

public class App extends Application {

    Image imageback = new Image("file:src/main/resources/blackboard.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane pane = new GridPane();
        pane.setBackground(background);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(4);
        pane.setVgap(4);

        Image imageX = new Image("file:src/main/resources/circle.gif");
        Image imageO = new Image("file:src/main/resources/cross.gif");

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                int status = (int) (Math.random() * 3);
                if (status == 0) {
                    pane.add(new ImageView(imageX), j, i);
                } else if (status == 1) {
                    pane.add(new ImageView(imageO), j, i);
                }
            }
        }

        Scene scene = new Scene(pane, 600, 600, Color.BLACK);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
