package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private Tile board[][] = new Tile[3][3]; //Tile Pane
    boolean draw = false; //Used to see if all tiles are used = Draw
    boolean valid = true;//Check for winner
    private Pane root = new Pane();
    Label label = new Label("");
    Button restart = new Button("Restart");

    enum State {
        X, O;           //Is used to set a state to each tile.. If there is not a state for a tile it is NULL
    }

    State nextTurn = State.X;


    private Parent startGame() {
        root.setPrefSize(850, 600);

        for (int y = 0; y < 3; y++) { //Place all the Tiles on the board
            for (int x = 0; x < 3; x++) {
                Tile tile = new Tile();
                tile.setTranslateX(x * 150);
                tile.setTranslateY(y * 150);

                root.getChildren().add(tile);
                board[x][y] = tile;
            }
        }

        label = new Label("X" + " Turn");   //Turn label Starts on turn Xs
        label.setLayoutX(500);
        label.setLayoutY(50);

        restart.setLayoutX(500);    //Restart button
        restart.setLayoutY(300);

        root.getChildren().addAll(label);

        return root;
    }

    private class Tile extends StackPane {
        private State state = null;
        ImageView imgX = new ImageView("sample/x.jpg");
        ImageView imgO = new ImageView("sample/o.jpg");

        public Tile() {
            Rectangle border = new Rectangle(150, 150); //Set each picture into a rectangle

            border.setStroke(Color.GREEN);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border);

            setOnMouseClicked(event ->
            {
                if(valid) {
                    if (event.getButton() == MouseButton.PRIMARY && state == null) {
                        if (nextTurn == State.X) {
                            imgX.setFitHeight(149);
                            imgX.setFitWidth(149);
                            getChildren().addAll(imgX);
                            state = State.X;
                            nextTurn = State.O;
                            label.setText(nextTurn + " Turn");
                            isWin();
                            return;
                        } else {
                            imgO.setFitHeight(149);
                            imgO.setFitWidth(149);
                            getChildren().addAll(imgO);
                            state = State.O;
                            nextTurn = State.X;
                            label.setText(nextTurn + " Turn");
                            isWin();
                            return;
                        }
                    }
                }
                else{
                    return;
                }
            });
        }

        public State getState() {
            return state;
        }


    }



    private boolean isWin() // Make 1 if
    {
        State winner = nextTurn;
        if(nextTurn == State.X){
            winner = State.O;
        }else{
            winner = State.X;
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i].getState() == board[1][i].getState() &&
                    board[0][i].getState() == board[2][i].getState() &&
                    board[0][i].getState() != null ||
                    board[i][0].getState() == board[i][1].getState() && board[i][0].getState() == board[i][2].getState() && board[i][0].getState() != null) {

                valid = false;

                label.setText("Congratulations\n" + winner + "\nwin the game\n");
                root.getChildren().addAll(restart);
                return true;    //If there is a straight win (Horizontal & Vertical)
            }
        }


        if (board[0][0].getState() == board[1][1].getState() &&
                board[0][0].getState() == board[2][2].getState() &&
                board[0][0].getState() != null ||
                board[2][0].getState() == board[1][1].getState() && board[2][0].getState() == board[0][2].getState() && board[2][0].getState() != null) {
            valid = false;
            label.setText("Congratulations\n" + winner + "\nwin the game\n");
            root.getChildren().addAll(restart);
            return true; //Diagnal
        }
        draw = true;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[x][y].getState() == null) {
                    draw = false;
                }
            }
        }
        if (draw) {
            valid = false;
            label.setText("Draw");
            root.getChildren().addAll(restart);
            return true;
        }

        return false; //IF not win return false
    }


    @Override
    public void start(Stage primaryStage) {
        restart.setOnAction(__ ->
        {
            primaryStage.close();
            Platform.runLater(() -> new TicTacToe().start(new Stage()));
        });

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(new Scene(startGame()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
