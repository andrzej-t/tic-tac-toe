package tic_tac_toe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
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
        Image imageLblBack = new Image("file:src/main/resources/blackboard.png");
        BackgroundSize backgroundSize = new BackgroundSize(1920, 1280, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        primary = arg0;
        gridPane = new GridPane();
        scene = new Scene(gridPane, 700,900);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setBackground(background);

//        Label label = new Label("Label sample");
//        label.setPrefSize(450,120);
//        label.setStyle("-fx-translate-y: 100px; -fx-translate-x: -1px; -fx-border-color: white");
//        gridPane.add(label,0,5);


        buttons = new Button[maxButtons];

        for (int i = 0; i< maxButtons; i++) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(150,150);
            buttons[i].setOnAction(this);
            buttons[i].setOpacity(0.3);
            buttons[i].setStyle("-fx-text-fill: #ff0000; -fx-font-size: 60");
        }

        javafx.scene.control.Label label = new javafx.scene.control.Label("YOUR \nTURN!");
        gridPane.add(label,1,0);
        label.setPrefSize(150,150);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-translate-y: -180px; -fx-border-color: white; -fx-font-size: 24px; -fx-text-fill: white");

        javafx.scene.control.Button oBtn = new javafx.scene.control.Button();
        gridPane.add(oBtn,0,6);
        oBtn.setStyle("-fx-background-color: #258325; -fx-font-size: 22px; -fx-text-fill: white; -fx-min-width: 150px; -fx-translate-y: 70px");
        oBtn.setText("Play as 'O'");
        oBtn.setOnAction(this);
        oBtn.addEventFilter(MouseEvent.MOUSE_CLICKED,playAsO);

        javafx.scene.control.Button xBtn = new javafx.scene.control.Button();
        gridPane.add(xBtn, 2,6);
        xBtn.setStyle("-fx-background-color: #a01010; -fx-font-size: 22px; -fx-text-fill: white; -fx-min-width: 150px; -fx-translate-y: 70px");
        xBtn.setText("Play as 'X'");
        xBtn.setOnAction(this);
        xBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, playAsX);

        javafx.scene.control.Button quitBtn = new javafx.scene.control.Button();
        gridPane.add(quitBtn, 1,6);
        quitBtn.setStyle("-fx-opacity: 0.5; -fx-background-color: #8a8484; -fx-font-size: 22px; -fx-text-fill: white; -fx-min-width: 150px; -fx-translate-y: 180px");
        quitBtn.setText("QUIT");
        quitBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, quit);

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

                for (int i = 0; i < maxButtons; i++) {
                    if (buttons[i] == event.getSource()) {
                        buttons[i].changeState();
                    }
                }

    }

    Button button = new Button();
    EventHandler<MouseEvent> playAsX = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            button.computerMove();
        }
    };

    EventHandler<MouseEvent> playAsO = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            button.computerMove();
        }
    };

    EventHandler<MouseEvent> quit = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            button.quitGame();
        }
    };


}
