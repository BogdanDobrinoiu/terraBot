package main.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Air extends Entities {
    private double humidity;
    private double temperature;
    private double oxygenLevel;
    private double airQuality;

    public abstract void calculateQuality();
}
