package ee.ttu.iti0202.publictransport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

public class Departure {
    private String name;
    private String time;
    private String destination;

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.parse(time.replace("Z", ""));
    }

    public String getDestination() {
        return destination;
    }

    public int getMinutesFromNow() {
        return (int) Duration.between(LocalDateTime.now(), getTime()).toMinutes();
    }

    @Override
    public String toString() {
        return "Departure{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'';
    }
}
