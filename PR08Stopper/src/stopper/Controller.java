package stopper;

import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Label textLabel;

    Timeline timeline = new Timeline();

    private IntegerProperty integerProperty;

    public void startButtonClick(){

        timeline.cycleDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                textLabel.textProperty().bind(Bindings.convert(integerProperty));
            }
        });
    }

    public void stopButtonClick() {
        integerProperty.setValue(0);
    }
}
