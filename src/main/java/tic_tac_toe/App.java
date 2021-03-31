package tic_tac_toe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App extends Application {

    private boolean turnX;
    private boolean playable = true;
    private final List<Combo> combos = new ArrayList<>();
    private final Tile [][] board = new Tile [3][3];
    private final Pane root = new Pane();
    Stage primaryStage;

    Image imageBack = new Image("file:src/main/resources/blackboard.png");
    BackgroundSize backgroundSize = new BackgroundSize(1920, 1280, false, false, false, false);
    BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);

    Label label = new Label();
    Button buttonX = new Button();
    Button buttonO = new Button();
    Button buttonRestart = new Button();

    private Parent createContent() {

        root.setPrefSize(450,600);
        root.setTranslateY(120);
        root.setBackground(background);
        root.getChildren().add(label);
        label.setPrefSize(450,120);
        label.setTranslateY(-120);
        label.setBackground(background);
        label.setAlignment(Pos.BASELINE_CENTER);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: beige");
        label.setText("You can \n chose: \n O or X");

        EventHandler<MouseEvent> playAsO = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                label.setText("Make \nmove!");
                buttonO.setManaged(true);
                buttonX.setDisable(true);
                buttonO.setDisable(true);
                turnX=false;
            }
        };

        root.getChildren().add(buttonO);
        buttonO.setPrefSize(140,30);
        buttonO.setTranslateY(480);
        buttonO.setTranslateX(10);
        buttonO.setStyle("-fx-background-color: #258325; -fx-font-size: 18px; -fx-text-fill: beige");
        buttonO.setText("Play as 'O'");
        buttonO.addEventFilter(MouseEvent.MOUSE_CLICKED, playAsO);

        EventHandler<MouseEvent> playAsX = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                label.setText("Your turn!");
                buttonO.setManaged(false);
                buttonO.setDisable(true);
                buttonX.setDisable(true);
                turnX=true;
            }
        };

        root.getChildren().add(buttonX);
        buttonX.setPrefSize(140,30);
        buttonX.setTranslateY(480);
        buttonX.setTranslateX(300);
        buttonX.setStyle("-fx-background-color: #a01010; -fx-font-size: 18px; -fx-text-fill: beige");
        buttonX.setText("Play as 'X'");
        buttonX.addEventFilter(MouseEvent.MOUSE_CLICKED, playAsX);

        EventHandler<MouseEvent> restart = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                buttonRestart.setOnMouseClicked(e->{
                    App app=new App();
                    app.start(primaryStage);
                });
            }
        };

        root.getChildren().add(buttonRestart);
        buttonRestart.setPrefSize(140,30);
        buttonRestart.setTranslateY(540);
        buttonRestart.setTranslateX(155);
        buttonRestart.setStyle("-fx-background-color: #242121; -fx-font-size: 18px; -fx-text-fill: beige");
        buttonRestart.setText("RESTART");
        buttonRestart.addEventFilter(MouseEvent.MOUSE_CLICKED, restart);

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                Tile tile = new Tile();
                tile.setAlignment(Pos.CENTER);
                tile.setTranslateX(j*150);
                tile.setTranslateY(i*150);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }

        }

        //horizontal
        for (int y=0; y<3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        //vertical
        for (int x=0; x<3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        //diagonal
        for (int x=0; x<3; x++) {
            combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
            combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void checkState() {
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                playable=false;
                playWinAnimation(combo);
                label.setText("End of the game!");
                break;
            }
        }
    }

    private void playWinAnimation(Combo combo) {
        Line line = new Line();
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());


        root.getChildren().add(line);

        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
        timeline.play();

    }

    private class Combo {
        public final Tile[] tiles;
        public Combo(Tile... tiles) {
            this.tiles=tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty())
                return false;
            return tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());

        }
    }

    private class Tile extends StackPane {
        Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(147,147);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            text.setFont(Font.font(72));
            text.setFill(Color.WHITE);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (!playable)
                    return;

                if (event.getButton() == MouseButton.PRIMARY && !buttonO.isManaged()) {
                    if (!turnX || text.isDisable())
                        return;
                    drawX();
                    checkState();
                    turnX = false;
                    computerMoveO();
                    checkState();
                    turnX = true;

                }

                else if (event.getButton() == MouseButton.PRIMARY && buttonO.isManaged()) {
                    if (turnX || text.isDisable())
                        return;
                    drawO();
                    checkState();
                    turnX = true;
                    computerMoveX();
                    checkState();
                    turnX = false;
                }

            });
        }

        Random randomX = new Random();
        Random randomY = new Random();

        int n = randomX.nextInt(3);
        int k = randomY.nextInt(3);

        public void computerMoveO() {
            board[n][k].drawO();
        }

        public void computerMoveX() {
            board[n][k].drawX();
        }

        public double getCenterX() {
            return getTranslateX() + 75;
        }

        public double getCenterY() {
            return getTranslateY() + 75;
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            if (text.isDisable()) {
                return;
            }
            text.setDisable(true);
            text.setFill(Color.BEIGE);
            text.setText("X");
        }

        private void drawO() {
            if (text.isDisable()){
                return;
            }
            text.setDisable(true);
            text.setFill(Color.BLACK);
            text.setText("O");

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
