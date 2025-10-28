package main.entities.airTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public abstract class Temperat extends Air {
    private double pollenLevel;

    @Override
    public void calculateQuality() {
        this.setAirQuality(this.getOxygenLevel() * 2 + this.getHumidity() * 0.7 - pollenLevel * 0.1);
        this.setAirQuality(normalizeScore(this.getAirQuality()));
    }
}
