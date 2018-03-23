package stopper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label textLabel;

    private Timeline timeline;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty(0);
    private DecimalFormat df = new DecimalFormat("#.##");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textLabel.textProperty().bind(timeSeconds.asString());
    }


    public void startButtonClick() {
        if (timeSeconds.get() > 0) timeSeconds.setValue(0);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> timeSeconds.setValue(Double.valueOf(df.format(timeSeconds.get() + .1)))));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    public void stopButtonClick() {
        timeline.stop();
    }
}
