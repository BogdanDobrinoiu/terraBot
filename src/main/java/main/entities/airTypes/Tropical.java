package main.entities.airTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public abstract class Tropical extends Air {
    private double co2Level;

    @Override
    public void calculateQuality() {
        this.setAirQuality(this.getOxygenLevel() * 2 + this.getHumidity() * 0.5 - this.co2Level * 0.01);
        this.setAirQuality(normalizeScore(this.getAirQuality()));
    }
}
