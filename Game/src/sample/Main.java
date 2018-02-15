package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Snake snake = new Snake(100, 100, 10);

        root.getChildren().add(snake.getHead());
        snake.setDeltas(0, 1);
        snake.setSpeed(4);

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                snake.setDeltas(0, 1);
            }

            if (event.getCode() == KeyCode.DOWN) {
                snake.setDeltas(0, -1);
            }

            if (event.getCode() == KeyCode.LEFT) {
                snake.setDeltas(1, 0);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                snake.setDeltas(-1, 0);
            }
        });

        root.setFocusTraversable(true);
        root.requestFocus();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                snake.move();
            }
        }.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
